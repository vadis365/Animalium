package animalium.entities;

import animalium.Animalium;
import animalium.configs.ConfigHandler;
import animalium.entities.ai.EntityAIWildDogLeap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityWildDog extends EntityMob implements IEntityMultiPartDog {

    public EntityDogPart[] dogPartArray;
	public EntityDogPart dogPartHead = new EntityDogPart(this, "head", 0.5F, 0.5F);

	public EntityWildDog(World world) {
		super(world);
		dogPartArray = new EntityDogPart[] {dogPartHead};
		setSize(0.9F, 1.2F);
		if (world != null && !world.isRemote) {
			if (ConfigHandler.WILD_DOG_ATTACK_MOBS)
				targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, true, null));
			if (ConfigHandler.WILD_DOG_ATTACK_CREATURES)
				targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, true, null));
		}
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIWildDogLeap(this, 0.5F));
		tasks.addTask(3, new EntityWildDog.AIWildDogAttack(this));
		tasks.addTask(4, new EntityAIWander(this, 0.6D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, true, null));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityWildDog.class != entity;
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
        return getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && isNotColliding() && posY <= ConfigHandler.WILD_DOG_SPAWN_Y_HEIGHT;
    }

	@Override
    public boolean isNotColliding() {
        return !getEntityWorld().containsAnyLiquid(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && getEntityWorld().checkNoEntityCollision(getEntityBoundingBox(), this);
    }

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(Animalium.WILD_DOG_PELT);
			if (isBurning())
				stack = new ItemStack(Items.BONE);
			entityDropItem(stack, 1.0F);
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WOLF_GROWL;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return SoundEvents.ENTITY_WOLF_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WOLF_DEATH;
	}
	
    public void onUpdate() {
    	super.onUpdate();
    	renderYawOffset = rotationYaw;
		double a = Math.toRadians(rotationYaw);
		double offSetX = -Math.sin(a) * -0.75D;
		double offSetZ = Math.cos(a) * -0.75D;
		dogPartHead.setLocationAndAngles(posX - offSetX, posY + 0.7D, posZ - offSetZ, 0.0F, 0.0F);
    }

	static class AIWildDogAttack extends EntityAIAttackMelee {
		
		public AIWildDogAttack(EntityWildDog dog) {
			super(dog, 0.6D, false);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (4.0F + attackTarget.width);
		}
	}

	@Override
	public boolean attackEntityFromPart(EntityDogPart dogPart, DamageSource source, float damage) {
		if (source.getEntity() instanceof EntityPlayer || source.isExplosion())
			attackDogFrom(source, damage);
		return true;
	}

	@Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
            attackEntityFromPart(dogPartHead, source, amount);
        return attackDogFrom(source, amount);
    }

    protected boolean attackDogFrom(DamageSource source, float amount){
        return super.attackEntityFrom(source, amount);
    }

	@Override
	public World getWorld() {
		return this.getEntityWorld();
	}

	@Override
    public Entity[] getParts(){
        return dogPartArray;
    }

}