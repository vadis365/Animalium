package animalium.entities;

import java.util.Random;

import animalium.ModEntities;
import animalium.ModItems;
import animalium.configs.Config;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityBear extends MonsterEntity {
	private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.createKey(EntityBear.class, DataSerializers.BOOLEAN);
	public float standingAngle, prevStandingAngle;
	
	public EntityBear(EntityType<? extends EntityBear> type, World world) {
		super(type, world);
		stepHeight = 2F;
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(IS_STANDING, false);
	}

	public boolean isStanding() {
		return dataManager.get(IS_STANDING);
	}

	private void setIsStanding(boolean standing) {
		dataManager.set(IS_STANDING, standing);
	}
	
	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new EntityBear.AttackGoal(this));
		goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new EntityBear.TargetGoal<>(this, PlayerEntity.class));

		if (Config.BEAR_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityBear.TargetGoal<>(this, MonsterEntity.class));
		if (Config.BEAR_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityBear.TargetGoal<>(this, LivingEntity.class));
	}

	@Override
	   protected void registerAttributes() {
	      super.registerAttributes();
	      getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
	      getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
	      getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	      getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	      getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
	}

	@Override
	   public void tick() {
		if (!getEntityWorld().isRemote) {

			if (getAttackTarget() != null) {
				faceEntity(getAttackTarget(), 10.0F, 20.0F);
				double distance = getDistance(getAttackTarget());

				if (distance > 5.0D)
					setIsStanding(false);

				if (distance <= 5.0D)
					setIsStanding(true);
			}
	
			if (getAttackTarget() == null)
				setIsStanding(false);
		}

		if (getEntityWorld().isRemote) {
			prevStandingAngle = standingAngle;

			if (standingAngle > 0 && !isStanding())
				standingAngle -= 0.1F;

			if (isStanding() && standingAngle <= 1F)
				standingAngle += 0.1F;
			
			if (standingAngle < 0 && !isStanding())
				standingAngle = 0F;

			if (isStanding() && standingAngle > 1F)
				standingAngle = 1F;
		}

	      super.tick();
	}
	
	@OnlyIn(Dist.CLIENT)
    public float smoothedAngle(float partialTicks) {
        return standingAngle + (standingAngle - prevStandingAngle) * partialTicks;
    }

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn!= ModEntities.BEAR;
	}

	protected static boolean isValidLightLevel(IWorld world, BlockPos pos) {
		if (Config.BEAR_SPAWN_ONLY_AT_DAY.get()) {
			if (world.getSkylightSubtracted() < 4)
				if (world.getLightFor(LightType.BLOCK, pos) >= 6)
					return true;
		} else if (world.getLightFor(LightType.BLOCK, pos) >= 8)
			return false;
		return true;
	}

	public static boolean canSpawnHere(EntityType<EntityBear> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		if(isDimBlacklisted(world.getDimension().getType().getId()))
			return false;
		if (Config.BEAR_SPAWN_ONLY_AT_DAY.get()) {
			if (world.getSkylightSubtracted() < 4)
				return world.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(world, pos) && pos.getY() <= Config.BEAR_SPAWN_Y_HEIGHT.get();
			else
				return false;
		}
		return world.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(world, pos) && pos.getY() <= Config.BEAR_SPAWN_Y_HEIGHT.get();
	}

	@Override
	public boolean isNotColliding(IWorldReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.func_226668_i_(this);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public static boolean isDimBlacklisted(int dimensionIn) {
		if(Config.BEAR_BLACKLISTED_DIMS.get().contains(dimensionIn))
			return true;
		return false;
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int randomAmount = 1 + rand.nextInt(2 + looting);
		for (int count = 0; count < randomAmount; ++count)
			entityDropItem(new ItemStack(ModItems.BEAR_CLAW), 0F);

		entityDropItem(new ItemStack(isBurning() ? ModItems.BEAR_MEAT_COOKED : ModItems.BEAR_MEAT), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			boolean hasHitTarget = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue()));

			if (hasHitTarget) {
				entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.2D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 0.5F);
				if (!getEntityWorld().isRemote)
					getEntityWorld().playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_POLAR_BEAR_WARNING, SoundCategory.HOSTILE, 1F, 1F);
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_POLAR_BEAR_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityBear bear, Class<T> classTarget) {
			super(bear, classTarget, true);
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityBear bear) {
			super(bear, 0.6D, false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}

}