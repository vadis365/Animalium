package animalium.entities;

import java.util.ArrayList;
import java.util.List;

import animalium.ModItems;
import animalium.configs.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityRat extends EntityMob {

	private EntityAIAttackMelee aiAttack;
	private EntityAIAvoidEntity<EntityLivingBase> aiRunAway;
	private EntityAINearestAttackableTarget<EntityPlayer> aiTarget;
	private static final DataParameter<Boolean> CAN_ATTACK = EntityDataManager.createKey(EntityRat.class, DataSerializers.BOOLEAN);

	public EntityRat(World world) {
		super(world);
		setSize(0.9F, 0.9F);
		if (world != null && !world.isRemote) {
			if (ConfigHandler.RAT_ATTACK_MOBS)
				targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, true, null));
			if (ConfigHandler.RAT_ATTACK_CREATURES)
				targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, true, null));
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(CAN_ATTACK, false);
	}

	public boolean getCanAttack() {
		return dataManager.get(CAN_ATTACK);
	}

	private void setCanAttack(boolean canAttack) {
		dataManager.set(CAN_ATTACK, canAttack);
	}

	@Override
	protected void initEntityAI() {
		aiAttack =  new EntityRat.AIRatAttack(this);
		aiRunAway = new EntityAIAvoidEntity<EntityLivingBase>(this, EntityLivingBase.class, 10.0F, 0.6D, 0.6D);
		aiTarget =  new EntityRat.AIRatTarget(this, EntityPlayer.class);

		tasks.addTask(0, aiAttack);
		tasks.addTask(1, aiRunAway);
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(3, new EntityRat.AIRatAttack(this));
		tasks.addTask(4, new EntityAIWander(this, 0.65D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, aiTarget);
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!getEntityWorld().isRemote) {
			if (getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty() && !getCanAttack()) {
				tasks.removeTask(aiRunAway);
				tasks.addTask(0, aiAttack);
				targetTasks.addTask(0, aiTarget);
				setCanAttack(true);
			}

			if (!getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty() && getCanAttack()) {
				tasks.removeTask(aiAttack);
				targetTasks.removeTask(aiTarget);
				tasks.addTask(1, aiRunAway);
				setCanAttack(false);
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			boolean attacked;
			if (attacked = super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityPlayer && getRNG().nextInt(ConfigHandler.RAT_STEALS_PROBABILITY) == 0 && ConfigHandler.RAT_STEALS_ITEMS) {
					EntityPlayer player = (EntityPlayer) entity;
					if (!getEntityWorld().isRemote && getCanAttack()) {
						ItemStack stack = player.getHeldItemMainhand();
						if (!stack.isEmpty()) {
							ItemStack stack2 = stack.copy();
							if (stack.hasTagCompound())
								stack2.setTagCompound(stack.getTagCompound());
							stack2.splitStack(stack.getCount() - 1);
							setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack2);
							setDropChance(EntityEquipmentSlot.MAINHAND, 0F);
							stack.shrink(1);
							if (stack.isEmpty())
								player.inventory.deleteStack(stack);
						}
					}
				}
			}
			return attacked;
		}
		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityRat.class != entity;
	}

	@Override
    protected boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if (this.getEntityWorld().getLightFor(EnumSkyBlock.BLOCK, blockpos) >= 8)
            return false;
        return true;
    }

	@Override
    public boolean getCanSpawnHere() {
		if(isDimBlacklisted(dimension))
			return false;
        return getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && isNotColliding() && posY <= ConfigHandler.RAT_SPAWN_Y_HEIGHT;
    }

	private Boolean isDimBlacklisted(int dimensionIn) {
		List<Integer> dimBlackList = new ArrayList<Integer>();
		for (int dims = 0; dims < ConfigHandler.RAT_BLACKLISTED_DIMS.length; dims++) {
			String dimEntry = ConfigHandler.RAT_BLACKLISTED_DIMS[dims].trim();
			dimBlackList.add(Integer.valueOf(dimEntry));
		}
		if(dimBlackList.contains(dimensionIn))
			return true;
		return false;
	}

	@Override
    public boolean isNotColliding() {
        return !getEntityWorld().containsAnyLiquid(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && getEntityWorld().checkNoEntityCollision(getEntityBoundingBox(), this);
    }

	@Override
	protected boolean canDespawn() {
		if(!getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty())
			return false;
		else
			return true;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if(!getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty()) {
			ItemStack stack = getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
			entityDropItem(stack, 1F);
		}
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.RAT_MEAT);
			if (isBurning())
				stack = new ItemStack(ModItems.RAT_MEAT_COOKED);
			entityDropItem(stack, 1.0F);
		}
	}

	@Override
	protected float getSoundPitch() {
		return 0.5F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_RABBIT_DEATH;
	}

	static class AIRatAttack extends EntityAIAttackMelee {
		private final EntityRat rat;

		public AIRatAttack(EntityRat rat) {
			super(rat, 0.65D, false);
			this.rat = rat;
		}

		@Override
		public boolean shouldExecute() {
			return super.shouldExecute() && rat.getCanAttack();
		}

		@Override
		public boolean shouldContinueExecuting() {
			float f = attacker.getBrightness();

			if (f >= 0.5F && attacker.getRNG().nextInt(100) == 0 || !rat.getCanAttack()) {
				attacker.setAttackTarget((EntityLivingBase) null);
				return false;
			} else {
				return super.shouldContinueExecuting();
			}
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (4.0F + attackTarget.width);
		}
	}

	static class AIRatTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AIRatTarget(EntityRat rat, Class<T> classTarget) {
			super(rat, classTarget, true);
		}

		@Override
		public boolean shouldExecute() {
			float f = taskOwner.getBrightness();
			return f >= 0.5F ? false : super.shouldExecute();
		}
	}

}