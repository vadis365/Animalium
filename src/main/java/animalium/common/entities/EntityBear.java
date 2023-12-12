package animalium.common.entities;

import animalium.configs.Config;
import animalium.init.ModEntities;
import animalium.init.ModItems;
import animalium.utils.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityBear extends Monster {
	private static final EntityDataAccessor<Boolean> IS_STANDING = SynchedEntityData.defineId(EntityBear.class, EntityDataSerializers.BOOLEAN);

	public float standingAngle, prevStandingAngle;
	public EntityBear(EntityType<? extends EntityBear> type, Level level) {
		super(type, level);
		setMaxUpStep(2F);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_STANDING, false);
	}

	public boolean isStanding() {
		return this.entityData.get(IS_STANDING);
	}

	private void setIsStanding(boolean standing) {
		this.entityData.set(IS_STANDING, standing);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new EntityBear.AttackGoal(this));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
		goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new EntityBear.TargetGoal<>(this, Player.class));

		if (Config.BEAR_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityBear.TargetGoal<>(this, Monster.class));
		if (Config.BEAR_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityBear.TargetGoal<>(this, LivingEntity.class));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.75f)
				.add(Attributes.ATTACK_DAMAGE, 6D);
	}

	@Override
	   public void tick() {
		if (!level().isClientSide) {

			if (getTarget() != null) {
				lookAt(getTarget(), 10.0F, 20.0F);
				double distance = distanceTo(getTarget());

				if (distance > 5.0D)
					setIsStanding(false);

				if (distance <= 5.0D)
					setIsStanding(true);
			}
	
			if (getTarget() == null)
				setIsStanding(false);
		}

		if (level().isClientSide) {
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
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != ModEntities.BEAR.get();
	}

	protected static boolean isValidLightLevel(LevelAccessor level, BlockPos pos) {
		if (Config.BEAR_SPAWN_ONLY_AT_DAY.get()) {
			if (level.getSkyDarken() < 4)
				if (level.getBrightness(LightLayer.BLOCK, pos) >= 6)
					return true;
		} else if (level.getBrightness(LightLayer.BLOCK, pos) >= 8)
			return false;
		return true;
	}

	public static boolean canSpawnHere(EntityType<EntityBear> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		ResourceKey<Level> dimensionKey = ((Level) level).dimension();

		if (Util.isDimBlacklisted(dimensionKey.location().toString(), Config.BEAR_BLACKLISTED_DIMS.get()))
			return false;
		if (Config.BEAR_SPAWN_ONLY_AT_DAY.get()) {
			if (level.getSkyDarken() < 4)
				return level.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(level, pos) && pos.getY() <= Config.BEAR_SPAWN_Y_HEIGHT.get();
			else
				return false;
		}
		return level.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(level, pos) && pos.getY() <= Config.BEAR_SPAWN_Y_HEIGHT.get();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		int randomAmount = 1 + random.nextInt(2 + looting);
		for (int count = 0; count < randomAmount; ++count)
			this.spawnAtLocation(new ItemStack(ModItems.BEAR_CLAW.get()), 0F);

		this.spawnAtLocation(new ItemStack(isOnFire() ? ModItems.BEAR_MEAT_COOKED.get() : ModItems.BEAR_MEAT.get()), 0.0F);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			boolean hasHitTarget = entity.hurt(this.damageSources().mobAttack(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue()));

			if (hasHitTarget) {
				entity.push(-Math.sin(getYRot() * 3.141593F / 180.0F) * 0.5F, 0.2D, Math.cos(getYRot() * 3.141593F / 180.0F) * 0.5F);
				if (!level().isClientSide)
					level().playSound(null, getX(), getY(), getZ(), SoundEvents.POLAR_BEAR_WARNING, SoundSource.HOSTILE, 1F, 1F);
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.POLAR_BEAR_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.POLAR_BEAR_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.POLAR_BEAR_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
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
		protected void checkAndPerformAttack(LivingEntity enemy, double p_25558_) {
			double attackReachSqr = (2D + enemy.getBbWidth()) * (2D + enemy.getBbWidth());
			if (this.mob.distanceToSqr(enemy.getX(), enemy.getY(), enemy.getZ()) <= attackReachSqr) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
			} else {
				this.resetAttackCooldown();
			}
		}
	}

}