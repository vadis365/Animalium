package animalium.entities;

import java.util.Random;

import animalium.ModEntities;
import animalium.configs.Config;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class EntityRat extends MonsterEntity {

	private AttackGoal aiAttack;
	private AvoidEntityGoal<LivingEntity> aiRunAway;
	private NearestAttackableTargetGoal<PlayerEntity> aiTarget;
	private static final DataParameter<Boolean> CAN_ATTACK = EntityDataManager.createKey(EntityRat.class, DataSerializers.BOOLEAN);

	public EntityRat(EntityType<? extends EntityRat> type, World world) {
		super(type, world);
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(CAN_ATTACK, false);
	}

	public boolean getCanAttack() {
		return dataManager.get(CAN_ATTACK);
	}

	private void setCanAttack(boolean canAttack) {
		dataManager.set(CAN_ATTACK, canAttack);
	}

	@Override
	protected void registerGoals() {
		aiAttack =  new AttackGoal(this);
		aiRunAway = new AvoidEntityGoal<LivingEntity>(this, LivingEntity.class, 10.0F, 0.6D, 0.6D);
		aiTarget =  new EntityRat.TargetGoal<>(this, PlayerEntity.class);

		goalSelector.addGoal(0, aiAttack);
		goalSelector.addGoal(1, aiRunAway);
		goalSelector.addGoal(2, new SwimGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this,  0.65D));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));
		targetSelector.addGoal(0, aiTarget);
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
		
		if (Config.RAT_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityRat.TargetGoal<>(this, MonsterEntity.class));
		if (Config.RAT_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityRat.TargetGoal<>(this, LivingEntity.class));
	}

	@Override
	   protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	   public void tick() {
		super.tick();

		if (!getEntityWorld().isRemote) {
			if (getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty() && !getCanAttack()) {
				goalSelector.removeGoal(aiRunAway);
				goalSelector.addGoal(0, aiAttack);
				targetSelector.addGoal(0, aiTarget);
				setCanAttack(true);
			}

			if (!getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty() && getCanAttack()) {
				goalSelector.removeGoal(aiAttack);
				targetSelector.removeGoal(aiTarget);
				goalSelector.addGoal(1, aiRunAway);
				setCanAttack(false);
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			boolean attacked;
			if (attacked = super.attackEntityAsMob(entity)) {
				if (entity instanceof PlayerEntity && getRNG().nextInt(Config.RAT_STEALS_PROBABILITY.get()) == 0 && Config.RAT_STEALS_ITEMS.get()) {
					PlayerEntity player = (PlayerEntity) entity;
					if (!getEntityWorld().isRemote && getCanAttack()) {
						ItemStack stack = player.getHeldItemMainhand();
						if (!stack.isEmpty()) {
							ItemStack stack2 = stack.copy();
							if (stack.hasTag())
								stack2.setTag(stack.getTag());
							stack2.split(stack.getCount() - 1);
							setItemStackToSlot(EquipmentSlotType.MAINHAND, stack2);
							setDropChance(EquipmentSlotType.MAINHAND, 0F);
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
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn!= ModEntities.RAT;
	}

    protected boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getBoundingBox().minY, this.posZ);
        if (this.getEntityWorld().getLightFor(LightType.BLOCK, blockpos) >= 8)
            return false;
        return true;
    }

	@Override
	  public boolean canSpawn(IWorld world, SpawnReason spawnReasonIn) {
//		if(isDimBlacklisted(dimension.getId()))
//			return false;
        return getEntityWorld().getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel() && isNotColliding(getEntityWorld()) && posY <= Config.RAT_SPAWN_Y_HEIGHT.get();
    }

	public static boolean canSpawnHere(EntityType<EntityRat> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}
/*
	private Boolean isDimBlacklisted(int dimensionIn) {
		List<Integer> dimBlackList = new ArrayList<Integer>();
		for (int dims = 0; dims < Config.RAT_BLACKLISTED_DIMS.get().length; dims++) {
			String dimEntry = Config.RAT_BLACKLISTED_DIMS.get()[dims].trim();
			dimBlackList.add(Integer.valueOf(dimEntry));
		}
		if(dimBlackList.contains(dimensionIn))
			return true;
		return false;
	}
*/
	@Override
    public boolean isNotColliding(IWorldReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.checkNoEntityCollision(this);
    }

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		if(!getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty())
			return false;
		else
			return true;
	}
/*
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if(!getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
			ItemStack stack = getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			entityDropItem(stack, 1F);
		}
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.RAT_MEAT);
			if (isBurning())
				stack = new ItemStack(ModItems.RAT_MEAT_COOKED);
			entityDropItem(stack, 1.0F);
		}
	}
*/
	@Override
	protected float getSoundPitch() {
		return 0.5F;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
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

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityRat rat, Class<T> classTarget) {
			super(rat, classTarget, true);
		}

		@Override
		public boolean shouldExecute() {
			float f = goalOwner.getBrightness();
			return f >= 0.5F ? false : super.shouldExecute();
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		private final EntityRat rat;
		
		public AttackGoal(EntityRat rat) {
			super(rat, 0.6D, false);
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
				attacker.setAttackTarget((LivingEntity) null);
				return false;
			} else {
				return super.shouldContinueExecuting();
			}
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}

}