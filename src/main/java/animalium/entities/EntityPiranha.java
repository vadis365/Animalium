package animalium.entities;

import java.util.List;
import java.util.Random;

import animalium.ModEntities;
import animalium.ModItems;
import animalium.configs.Config;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class EntityPiranha extends MonsterEntity {
	private static final DataParameter<Boolean> IS_LEAPING = EntityDataManager.createKey(EntityPiranha.class, DataSerializers.BOOLEAN);

	public EntityPiranha(EntityType<? extends EntityPiranha> type, World world) {
		super(type, world);
		moveController = new EntityPiranha.PiranhaMoveHelperController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new EntityPiranha.AttackGoal(this));
		goalSelector.addGoal(1, new MoveTowardsRestrictionGoal(this, 0.75D));
		goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.75D, 80));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(4, new LookRandomlyGoal(this));
		targetSelector.addGoal(0, new EntityPiranha.TargetGoal<>(this, PlayerEntity.class));
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
		
		if (Config.PIRANHA_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityPiranha.TargetGoal<>(this, MonsterEntity.class));
		if (Config.PIRANHA_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityPiranha.TargetGoal<>(this, LivingEntity.class));
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(IS_LEAPING, false);
	}

	public boolean isLeaping() {
		return dataManager.get(IS_LEAPING);
	}

	private void setIsLeaping(boolean leaping) {
		dataManager.set(IS_LEAPING, leaping);
	}

	public boolean isGrounded() {
		return !isInWater() && getEntityWorld().isAirBlock(new BlockPos(MathHelper.floor(getPosX()), MathHelper.floor(getPosY() + 1), MathHelper.floor(getPosZ()))) && getEntityWorld().getBlockState(new BlockPos(MathHelper.floor(getPosX()), MathHelper.floor(getPosY() - 1), MathHelper.floor(getPosZ()))).isSolid();
	}

	@Override
	public boolean writeUnlessRemoved(CompoundNBT nbt) {
		return super.writeUnlessRemoved(nbt);
	}

	@Override
	 public void read(CompoundNBT nbt) {
		super.read(nbt);
	}
/*
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}
*/
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.func_234295_eP_()
				.func_233815_a_(Attributes.field_233818_a_, 10D) //health
				.func_233815_a_(Attributes.field_233819_b_, 32D) //follow range
				.func_233815_a_(Attributes.field_233821_d_, (double) 0.8F) //move speed
				.func_233815_a_(Attributes.field_233823_f_, 2D); //attack damage	
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
    protected PathNavigator createNavigator(World world){
        return new SwimmerPathNavigator(this, world);
    }

	@Override
	public boolean isNotColliding(IWorldReader world) {
		return world.checkNoEntityCollision(this);
	}

	public static boolean canSpawnHere(EntityType<EntityPiranha> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		if(isDimBlacklisted(getDimensionRegName(world.getWorld().func_234923_W_())))
			return false;
		if(pos.getY() < 45.0D || pos.getY() >= 80)
			return false;
		return (random.nextInt(10) == 0 || !world.canBlockSeeSky(pos)) && world.getDifficulty() != Difficulty.PEACEFUL && (spawn_reason == SpawnReason.SPAWNER || world.getFluidState(pos).isTagged(FluidTags.WATER));
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	public static boolean isDimBlacklisted(String dimensionIn) {
		if(Config.PIRANHA_BLACKLISTED_DIMS.get().contains(dimensionIn))
			return true;
		return false;
	}

	public static String getDimensionRegName(RegistryKey<World> reg) {
		return reg.func_240901_a_().toString();
	}

	@Override
	   protected SoundEvent getAmbientSound() {
        return isInWater() ? SoundEvents.ENTITY_GENERIC_SWIM : SoundEvents.ENTITY_GUARDIAN_FLOP;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_GUARDIAN_HURT_LAND;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GHAST_DEATH;
	}
	
	@Override
	public void remove() {
		super.remove();
		playSound(SoundEvents.ENTITY_GHAST_DEATH, 1F, 2F);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(isInWater() ? SoundEvents.ENTITY_GENERIC_SWIM : SoundEvents.ENTITY_GUARDIAN_FLOP, 0.5F, 2F);
    }

	@SuppressWarnings("deprecation")
	public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
		return world.getFluidState(pos).isTagged(FluidTags.WATER) ? 10.0F + world.getBrightness(pos) - 0.5F : super.getBlockPathWeight(pos, world);
	}

	@Override
	public void livingTick() {
		if (getEntityWorld().isRemote) {
			if (isInWater()) {
				Vector3d vec3d = getLook(0.0F);
				for (int i = 0; i < 2; ++i)
					getEntityWorld().addParticle(ParticleTypes.BUBBLE, getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth() - vec3d.x * 1.5D, getPosY() + this.rand.nextDouble() * (double)this.getHeight() - vec3d.y * 1.5D, getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth() - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (inWater) {
			setAir(300);
		} else if (onGround) {
			setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5D, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F)));
			rotationYaw = rand.nextFloat() * 360.0F;
			if(isLeaping())
				setIsLeaping(false);
			onGround = false;
			isAirBorne = true;
			if(getEntityWorld().getGameTime()%5==0)
				getEntityWorld().playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_GUARDIAN_FLOP, SoundCategory.HOSTILE, 1F, 1F);
				this.damageEntity(DamageSource.DROWN, 0.5F);
		}

		super.livingTick();
	}
	
	@Override
    public void travel(Vector3d travel_vector) {
		if (isServerWorld()) {
			if (isInWater()) {
				moveRelative(0.1F, travel_vector);
				move(MoverType.SELF, getMotion());
				setMotion(getMotion().scale(0.8999999761581421D));
				if (getAttackTarget() == null) {
					setMotion(getMotion().add(0.0D, -0.005D, 0.0D));
				}
			} else {
				super.travel(travel_vector);
			}
		} else {
			super.travel(travel_vector);
		}
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.PIRANHA_STEAK);
			if (this.isBurning())
				stack = new ItemStack(ModItems.PIRANHA_STEAK_COOKED);
			entityDropItem(stack, 1.0F);
		}
	}

	@Override
	 public void tick() {
		if(!getEntityWorld().isRemote) {
		if(getAttackTarget() != null && !getEntityWorld().containsAnyLiquid(getAttackTarget().getBoundingBox())) {
			Double distance = (double) getDistance(getAttackTarget());
			if (distance > 1.0F && distance < 6.0F) // && getAttackTarget().getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && getAttackTarget().getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && rand.nextInt(3) == 0)
				if (isInWater() && getEntityWorld().isAirBlock(new BlockPos((int) getPosX(), (int) getPosY() + 1, (int) getPosZ()))) {
					if(!isLeaping()) {
						setIsLeaping(true);
						getEntityWorld().playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 1F, 2F);
					}
					double distanceX = getAttackTarget().getPosX() - getPosX();
					double distanceY = getAttackTarget().getPosY() + (double)(getAttackTarget().getHeight() * 0.5F) - (this.getPosY() + (double)this.getEyeHeight());
					double distanceZ = getAttackTarget().getPosZ() - getPosZ();
					float distanceSqrRoot = MathHelper.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
					double motionX = distanceX / distanceSqrRoot * 0.5D * 0.900000011920929D + getMotion().getX() * 0.70000000298023224D;
					double motionY = 0.125D;
					double motionZ = distanceZ / distanceSqrRoot * 0.5D * 0.900000011920929D + getMotion().getZ() * 0.70000000298023224D;
					setMotion(getMotion().add(motionX * 0.25D, motionY, motionZ * 0.25D));
					}
			}
		}
		super.tick();
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn!= ModEntities.PIRANHA;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity))
				return true;
		}
		return false;
	}

	@Override
	protected void collideWithNearbyEntities() {
		super.collideWithNearbyEntities();
		if (Config.PIRANHA_DAMAGE_BOATS.get()) {
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox());

			if (!list.isEmpty() && this.rand.nextInt(Config.PIRANHA_DAMAGE_BOATS_CHANCE.get()) == 0) {
				for (int k = 0; k < list.size(); ++k) {
					if (((Entity) list.get(k) instanceof BoatEntity)) {
						attackEntityAsMob(list.get(k));
					}
				}
			}
		}
	}

	//AIs
	
	static class PiranhaMoveHelperController extends MovementController {
		private final EntityPiranha piranha;

		public PiranhaMoveHelperController(EntityPiranha piranha) {
			super(piranha);
			this.piranha = piranha;
		}

		public void tick() {
	         if (this.action == MovementController.Action.MOVE_TO && !this.piranha.getNavigator().noPath()) {
	             Vector3d vec3d = new Vector3d(this.posX - this.piranha.getPosX(), this.posY - this.piranha.getPosY(), this.posZ - this.piranha.getPosZ());
	             double d0 = vec3d.length();
	             double d1 = vec3d.x / d0;
	             double d2 = vec3d.y / d0;
	             double d3 = vec3d.z / d0;
	             float f = (float)(MathHelper.atan2(vec3d.z, vec3d.x) * (double)(180F / (float)Math.PI)) - 90.0F;
	             this.piranha.rotationYaw = this.limitAngle(this.piranha.rotationYaw, f, 90.0F);
	             this.piranha.renderYawOffset = this.piranha.rotationYaw;
	             float f1 = (float)(this.speed * this.piranha.getAttribute(Attributes.field_233821_d_).getValue());
	             float f2 = MathHelper.lerp(0.125F, this.piranha.getAIMoveSpeed(), f1);
	             this.piranha.setAIMoveSpeed(f2);
	             double d4 = Math.sin((double)(this.piranha.ticksExisted + this.piranha.getEntityId()) * 0.5D) * 0.05D;
	             double d5 = Math.cos((double)(this.piranha.rotationYaw * ((float)Math.PI / 180F)));
	             double d6 = Math.sin((double)(this.piranha.rotationYaw * ((float)Math.PI / 180F)));
	             double d7 = Math.sin((double)(this.piranha.ticksExisted + this.piranha.getEntityId()) * 0.75D) * 0.05D;
	             this.piranha.setMotion(this.piranha.getMotion().add(d4 * d5, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, d4 * d6));
	             LookController lookcontroller = this.piranha.getLookController();
	             double d8 = this.piranha.getPosX() + d1 * 2.0D;
	             double d9 = (double)this.piranha.getEyeHeight() + this.piranha.getPosY() + d2 / d0;
	             double d10 = this.piranha.getPosZ() + d3 * 2.0D;
	             double d11 = lookcontroller.getLookPosX();
	             double d12 = lookcontroller.getLookPosY();
	             double d13 = lookcontroller.getLookPosZ();
	             if (!lookcontroller.getIsLooking()) {
	                d11 = d8;
	                d12 = d9;
	                d13 = d10;
	             }
	             this.piranha.getLookController().setLookPosition(MathHelper.lerp(0.125D, d11, d8), MathHelper.lerp(0.125D, d12, d9), MathHelper.lerp(0.125D, d13, d10), 10.0F, 40.0F);
	          } else {
	             this.piranha.setAIMoveSpeed(0.0F);
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
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (0.5F + attackTarget.getWidth());
		}
	}
}