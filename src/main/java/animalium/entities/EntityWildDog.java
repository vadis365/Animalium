package animalium.entities;

import java.util.Random;

import animalium.ModEntities;
import animalium.configs.Config;
import animalium.entities.ai.WildDogLeapAtTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class EntityWildDog extends MonsterEntity {

    public EntityDogPart[] dogPartArray;
	public EntityDogPart dogPartHead = new EntityDogPart(this, "head", 0.5F, 0.5F);

	public EntityWildDog(EntityType<? extends EntityWildDog> type, World world) {
		super(type, world);
		dogPartArray = new EntityDogPart[] {dogPartHead};
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new WildDogLeapAtTargetGoal(this, 0.5F));
		goalSelector.addGoal(3, new EntityWildDog.AttackGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new EntityWildDog.HurtByAggressorGoal(this));
		targetSelector.addGoal(2, new EntityWildDog.TargetGoal<>(this, PlayerEntity.class));

		if (Config.WILD_DOG_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityWildDog.TargetGoal<>(this, MobEntity.class));
		if (Config.WILD_DOG_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityWildDog.TargetGoal<>(this, LivingEntity.class));
	}

	@Override
	   protected void registerAttributes() {
	    super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn!= ModEntities.WILD_DOG;
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
        return getEntityWorld().getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel() && isNotColliding(getEntityWorld()) && posY <= Config.WILD_DOG_SPAWN_Y_HEIGHT.get();
    }

	public static boolean canSpawnHere(EntityType<EntityWildDog> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	public boolean isNotColliding(IWorldReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.checkNoEntityCollision(this);
	}
/*
	private Boolean isDimBlacklisted(int dimensionIn) {
		List<Integer> dimBlackList = new ArrayList<Integer>();
		for (int dims = 0; dims < Config.WILD_DOG_BLACKLISTED_DIMS.get().length; dims++) {
			String dimEntry = Config.WILD_DOG_BLACKLISTED_DIMS.get()[dims].trim();
			dimBlackList.add(Integer.valueOf(dimEntry));
		}
		if(dimBlackList.contains(dimensionIn))
			return true;
		return false;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.WILD_DOG_PELT);
			if (isBurning())
				stack = new ItemStack(Items.BONE);
			entityDropItem(stack, 1.0F);
		}
	}
*/
	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WOLF_GROWL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_WOLF_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WOLF_DEATH;
	}

	@Override
    public void tick() {
    	super.tick();
    	renderYawOffset = rotationYaw;
		double a = Math.toRadians(rotationYaw);
		double offSetX = -Math.sin(a) * -0.75D;
		double offSetZ = Math.cos(a) * -0.75D;
		dogPartHead.setLocationAndAngles(posX - offSetX, posY + 0.7D, posZ - offSetZ, 0.0F, 0.0F);
    }

	public boolean attackEntityFromPart(EntityDogPart dogPart, DamageSource source, float damage) {
		if (source.getTrueSource() instanceof PlayerEntity || source.isExplosion())
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

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityWildDog dog, Class<T> classTarget) {
			super(dog, classTarget, true);
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityWildDog dog) {
			super(dog, 0.6D, false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}

	static class HurtByAggressorGoal extends HurtByTargetGoal {
		public HurtByAggressorGoal(EntityWildDog wild_dog) {
			super(wild_dog);
			this.setCallsForHelp(new Class[] { EntityWildDog.class });
		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if (mobIn instanceof EntityWildDog && this.goalOwner.canEntityBeSeen(targetIn)) {
				mobIn.setAttackTarget(targetIn);
			}

		}
	}

}