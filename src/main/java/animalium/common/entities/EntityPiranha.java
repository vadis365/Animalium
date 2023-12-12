package animalium.common.entities;

import java.util.List;

import animalium.configs.Config;
import animalium.init.ModEntities;
import animalium.init.ModItems;
import animalium.utils.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class EntityPiranha extends Monster {

	private static final EntityDataAccessor<Boolean> IS_LEAPING = SynchedEntityData.defineId(EntityPiranha.class, EntityDataSerializers.BOOLEAN);


	public EntityPiranha(EntityType<? extends EntityPiranha> type, Level level) {
		super(type, level);
		moveControl = new EntityPiranha.PiranhaMoveHelperController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new EntityPiranha.AttackGoal(this));
		goalSelector.addGoal(1, new MoveTowardsRestrictionGoal(this, 0.75D));
		goalSelector.addGoal(2, new RandomStrollGoal(this, 0.70D, 80));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new EntityPiranha.TargetGoal<>(this, Player.class));
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
		
		if (Config.PIRANHA_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityPiranha.TargetGoal<>(this, Monster.class));
		if (Config.PIRANHA_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityPiranha.TargetGoal<>(this, LivingEntity.class));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(IS_LEAPING, false);
	}


	public boolean isLeaping() {
		return entityData.get(IS_LEAPING);
	}

	private void setIsLeaping(boolean leaping) {
		entityData.set(IS_LEAPING, leaping);
	}

	public boolean isGrounded() {
		return !isInWater() && level().isEmptyBlock(new BlockPos(Mth.floor(getX()), Mth.floor(getY() + 1), Mth.floor(getZ()))) && level().getBlockState(new BlockPos(Mth.floor(getX()), Mth.floor(getY() - 1), Mth.floor(getZ()))).isSolid();
	}

	@Override
	public boolean saveAsPassenger(CompoundTag tag) {
		return super.saveAsPassenger(tag);
	}

	@Override
	 public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.8f)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}
	
	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
    protected PathNavigation createNavigation(Level world){
        return new WaterBoundPathNavigation(this, world);
    }

	@Override
	public boolean checkSpawnObstruction(LevelReader level) {
		return level.noCollision(this);
	}

	public static boolean canSpawnHere(EntityType<EntityPiranha> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		ResourceKey<Level> dimensionKey = ((Level) level).dimension();
		if (Util.isDimBlacklisted(dimensionKey.location().toString(), Config.PIRANHA_BLACKLISTED_DIMS.get()))
			return false;
		if(pos.getY() < Config.PIRANHA_SPAWN_MIN_Y_HEIGHT.get() || pos.getY() > Config.PIRANHA_SPAWN_MAX_Y_HEIGHT.get())
			return false;
		return (random.nextInt(10) == 0 || !level.canSeeSky(pos)) && level.getDifficulty() != Difficulty.PEACEFUL && (spawn == MobSpawnType.SPAWNER || level.getFluidState(pos).is(FluidTags.WATER));
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	public static String getDimensionRegName(ResourceKey<Level> reg) {
		return reg.location().toString();
	}

	@Override
	   protected SoundEvent getAmbientSound() {
        return isInWater() ? SoundEvents.GENERIC_SWIM : SoundEvents.GUARDIAN_FLOP;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.GUARDIAN_HURT_LAND;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.GHAST_DEATH;
	}
	
	@Override
	public void remove(Entity.RemovalReason reason) {
		super.remove(reason);
		playSound(SoundEvents.GHAST_DEATH, 1F, 2F);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(isInWater() ? SoundEvents.GENERIC_SWIM : SoundEvents.GUARDIAN_FLOP, 0.5F, 2F);
    }

//	@SuppressWarnings("deprecation")
//	public float getBlockPathWeight(BlockPos pos, LevelReader world) {
//		return world.getFluidState(pos).is(FluidTags.WATER) ? 10.0F + world.getBrightness(pos) - 0.5F : super.getWalkTargetValue(pos, world);
//	}

	@Override
	public void aiStep() {
		if (level().isClientSide) {
			if (isInWater()) {
				Vec3 vec3d = getViewVector(0.0F);
				for (int i = 0; i < 2; ++i)
					level().addParticle(ParticleTypes.BUBBLE, getX() + (this.random.nextDouble() - 0.5D) * (double)this.getBbWidth() - vec3d.x * 1.5D, getY() + this.random.nextDouble() * (double)this.getBbHeight() - vec3d.y * 1.5D, getZ() + (this.random.nextDouble() - 0.5D) * (double)this.getBbWidth() - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (isInWater()) {
			setAirSupply(300);
		} else if (onGround()) {
			setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F)));
			setYRot(random.nextFloat() * 360.0F);
			if(isLeaping())
				setIsLeaping(false);
			setOnGround(false);
			hasImpulse = true;
			if(level().getGameTime()%5==0) {
				level().playSound(null, getX(), getY(), getZ(), SoundEvents.GUARDIAN_FLOP, SoundSource.HOSTILE, 1F, 1F);
				this.hurt(this.damageSources().drown(), 0.5F);
			}
		}

		super.aiStep();
	}
	
	@Override
    public void travel(Vec3 travel_vector) {
		if (isEffectiveAi()) {
			if (isInWater()) {
				moveRelative(0.1F, travel_vector);
				move(MoverType.SELF, getDeltaMovement());
				setDeltaMovement(getDeltaMovement().scale(0.8999999761581421D));
				if (getTarget() == null) {
					setDeltaMovement(getDeltaMovement().add(0.0D, -0.005D, 0.0D));
				}
			} else {
				super.travel(travel_vector);
			}
		} else {
			super.travel(travel_vector);
		}
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		if (level().random.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.PIRANHA_STEAK.get());
			if (this.isOnFire())
				stack = new ItemStack(ModItems.PIRANHA_STEAK_COOKED.get());
			this.spawnAtLocation(stack, 1.0F);
		}
	}

	@Override
	 public void tick() {
		if(!level().isClientSide) {
		if(getTarget() != null && !level().containsAnyLiquid(getTarget().getBoundingBox())) {
			double distance = distanceTo(getTarget());
			if (distance > 1.0F && distance < 6.0F) // && getTarget().getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && getTarget().getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && rand.nextInt(3) == 0)
				if (isInWater() && level().isEmptyBlock(new BlockPos((int) getX(), (int) getY() + 1, (int) getZ()))) {
					if(!isLeaping()) {
						setIsLeaping(true);
						level().playSound(null, getX(), getY(), getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1F, 2F);
					}
					double distanceX = getTarget().getX() - getX();
					double distanceY = getTarget().getY() + (double)(getTarget().getBbHeight() * 0.5F) - (this.getY() + (double)this.getEyeHeight());
					double distanceZ = getTarget().getZ() - getZ();
					float distanceSqrRoot = Mth.sqrt((float) (distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ));
					double motionX = distanceX / distanceSqrRoot * 0.5D * 0.900000011920929D + getDeltaMovement().x * 0.70000000298023224D;
					double motionY = 0.125D;
					double motionZ = distanceZ / distanceSqrRoot * 0.5D * 0.900000011920929D + getDeltaMovement().z * 0.70000000298023224D;
					setDeltaMovement(getDeltaMovement().add(motionX * 0.25D, motionY, motionZ * 0.25D));
					}
			}
		}
		super.tick();
	}

	@Override
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != ModEntities.PIRANHA.get();
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			if (super.doHurtTarget(entity))
				return true;
		}
		return false;
	}

	@Override
	protected void pushEntities() {
		super.pushEntities();
		if (Config.PIRANHA_DAMAGE_BOATS.get()) {
			List<Entity> list = this.level().getEntities(this, this.getBoundingBox());

			if (!list.isEmpty() && this.random.nextInt(Config.PIRANHA_DAMAGE_BOATS_CHANCE.get()) == 0) {
				for (int k = 0; k < list.size(); ++k) {
					if (((Entity) list.get(k) instanceof Boat)) {
						doHurtTarget(list.get(k));
					}
				}
			}
		}
	}

	//AIs
	static class PiranhaMoveHelperController extends MoveControl {
		private final EntityPiranha piranha;

		public PiranhaMoveHelperController(EntityPiranha piranha) {
			super(piranha);
			this.piranha = piranha;
		}

		public void tick() {
	         if (this.operation == MoveControl.Operation.MOVE_TO && !this.piranha.getNavigation().isDone()) {
	             Vec3 vec3d = new Vec3(this.wantedX - this.piranha.getX(), this.wantedY - this.piranha.getY(), this.wantedZ - this.piranha.getZ());
	             double d0 = vec3d.length();
	             double d1 = vec3d.x / d0;
	             double d2 = vec3d.y / d0;
	             double d3 = vec3d.z / d0;
	             float f = (float)(Mth.atan2(vec3d.z, vec3d.x) * (double)(180F / (float)Math.PI)) - 90.0F;
	             this.piranha.setYRot(this.rotlerp(this.piranha.getYRot(), f, 90.0F));
	             this.piranha.yBodyRot = this.piranha.getYRot();
	             float f1 = (float)(this.speedModifier * this.piranha.getAttributeValue(Attributes.MOVEMENT_SPEED));
	             float f2 = Mth.lerp(0.125F, this.piranha.getSpeed(), f1);
	             this.piranha.setSpeed(f2);
	             double d4 = Math.sin((double)(this.piranha.tickCount + this.piranha.getId()) * 0.5D) * 0.05D;
	             double d5 = Math.cos((double)(this.piranha.getYRot() * ((float)Math.PI / 180F)));
	             double d6 = Math.sin((double)(this.piranha.getYRot() * ((float)Math.PI / 180F)));
	             double d7 = Math.sin((double)(this.piranha.tickCount + this.piranha.getId()) * 0.75D) * 0.05D;
	             this.piranha.setDeltaMovement(this.piranha.getDeltaMovement().add(d4 * d5, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, d4 * d6));
	             LookControl lookcontroller = this.piranha.getLookControl();
	             double d8 = this.piranha.getX() + d1 * 2.0D;
	             double d9 = this.piranha.getEyeY() + d2 / d0;
	             double d10 = this.piranha.getZ() + d3 * 2.0D;
	             double d11 = lookcontroller.getWantedX();
	             double d12 = lookcontroller.getWantedY();
	             double d13 = lookcontroller.getWantedZ();
	             if (!lookcontroller.isLookingAtTarget()) {
	                d11 = d8;
	                d12 = d9;
	                d13 = d10;
	             }
	             this.piranha.getLookControl().setLookAt(Mth.lerp(0.125D, d11, d8), Mth.lerp(0.125D, d12, d9), Mth.lerp(0.125D, d13, d10), 10.0F, 40.0F);
	          } else {
	             this.piranha.setSpeed(0.0F);
	          }
	       }
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityPiranha piranha, Class<T> classTarget) {
			super(piranha, classTarget, true);
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityPiranha piranha) {
			super(piranha, 0.75D, true);
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double p_25558_) {
			double attackRange = (double) ((enemy.getBbWidth() + 0.5F) * (enemy.getBbWidth() + 0.5F));
			if (this.mob.distanceToSqr(enemy.getX(), enemy.getY(), enemy.getZ()) <= attackRange) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
			}
			else {
				this.resetAttackCooldown();
			}
		}
	}
}