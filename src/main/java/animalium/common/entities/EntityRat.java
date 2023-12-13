package animalium.common.entities;

import animalium.init.ModEntities;
import animalium.init.ModItems;
import animalium.configs.Config;
import animalium.utils.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
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
import net.minecraft.world.level.dimension.DimensionType;

public class EntityRat extends Monster {

	private AttackGoal aiAttack;
	private AvoidEntityGoal<LivingEntity> aiRunAway;
	private NearestAttackableTargetGoal<Player> aiTarget;

	private static final EntityDataAccessor<Boolean> CAN_ATTACK = SynchedEntityData.defineId(EntityRat.class, EntityDataSerializers.BOOLEAN);

	public EntityRat(EntityType<? extends EntityRat> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CAN_ATTACK, false);

	}

	public boolean getCanAttack() {
		return this.entityData.get(CAN_ATTACK);
	}

	private void setCanAttack(boolean canAttack) {
		this.entityData.set(CAN_ATTACK, canAttack);
	}

	@Override
	protected void registerGoals() {
		aiAttack =  new AttackGoal(this);
		aiRunAway = new AvoidEntityGoal<LivingEntity>(this, LivingEntity.class, 10.0F, 0.6D, 0.6D);
		aiTarget =  new EntityRat.TargetGoal<>(this, Player.class);

		goalSelector.addGoal(0, aiAttack);
		goalSelector.addGoal(1, aiRunAway);
		goalSelector.addGoal(2, new FloatGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this,  0.30D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, aiTarget);
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
		
		if (Config.RAT_ATTACK_MOBS.get())
			targetSelector.addGoal(1, new EntityRat.TargetGoal<>(this, Monster.class));
		if (Config.RAT_ATTACK_CREATURES.get())
			targetSelector.addGoal(2, new EntityRat.TargetGoal<>(this, LivingEntity.class));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 5D)
				.add(Attributes.FOLLOW_RANGE, 24D)
				.add(Attributes.MOVEMENT_SPEED, 0.65f)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	@Override
	   public void tick() {
		super.tick();

		if (!level().isClientSide) {
			if (getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() && !getCanAttack()) {
				goalSelector.removeGoal(aiRunAway);
				goalSelector.addGoal(0, aiAttack);
				targetSelector.addGoal(0, aiTarget);
				setCanAttack(true);
			}

			if (!getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() && getCanAttack()) {
				goalSelector.removeGoal(aiAttack);
				targetSelector.removeGoal(aiTarget);
				goalSelector.addGoal(1, aiRunAway);
				setCanAttack(false);
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			boolean attacked;
			if (attacked = super.doHurtTarget(entity)) {
				if (entity instanceof Player && random.nextInt(Config.RAT_STEALS_PROBABILITY.get()) == 0 && Config.RAT_STEALS_ITEMS.get()) {
					Player player = (Player) entity;
					if (!level().isClientSide && getCanAttack()) {
						ItemStack stack = player.getMainHandItem();
						if (!stack.isEmpty()) {
							ItemStack stack2 = stack.copy();
							if (stack.hasTag())
								stack2.setTag(stack.getTag());
							stack2.split(stack.getCount() - 1);
							setItemSlot(EquipmentSlot.MAINHAND, stack2);
							setDropChance(EquipmentSlot.MAINHAND, 0F);
							stack.shrink(1);
							if (stack.isEmpty())
								player.getInventory().removeItem(stack);
						}
					}
				}
			}
			return attacked;
		}
		return false;
	}

	@Override
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != ModEntities.RAT.get();
	}

	public static boolean canSpawnInDark(LevelAccessor level, BlockPos pos, RandomSource random) {
		DimensionType dimensiontype = level.dimensionType();
		int i = dimensiontype.monsterSpawnBlockLightLimit();
		if (i < 15 && level.getBrightness(LightLayer.BLOCK, pos) > i)
			return false;
		else {
			int j = ((Level) level).isThundering() ? level.getMaxLocalRawBrightness(pos, 10) : level.getMaxLocalRawBrightness(pos);
			return j <= dimensiontype.monsterSpawnLightTest().sample(random);
		}
	}

	public static boolean canSpawnHere(EntityType<EntityRat> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		ResourceKey<Level> dimensionKey = ((Level) level).dimension();
		if (Util.isDimBlacklisted(dimensionKey.location().toString(), Config.RAT_BLACKLISTED_DIMS.get()))
			return false;
		if(pos.getY() < Config.RAT_SPAWN_MIN_Y_HEIGHT.get() || pos.getY() > Config.RAT_SPAWN_MAX_Y_HEIGHT.get())
			return false;
        return level.getDifficulty() != Difficulty.PEACEFUL && canSpawnInDark(level, pos, random);
	}

	public static String getDimensionRegName(ResourceKey<Level> reg) {
		return reg.location().toString();
	}

	@Override
    public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
    }

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		if(!getItemBySlot(EquipmentSlot.MAINHAND).isEmpty())
			return false;
		else
			return true;
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		if(!getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
			ItemStack stack = getItemBySlot(EquipmentSlot.MAINHAND);
			this.spawnAtLocation(stack, 1F);
		}
		if (random.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.RAT_MEAT.get());
			if (isOnFire())
				stack = new ItemStack(ModItems.RAT_MEAT_COOKED.get());
			this.spawnAtLocation(stack, 1.0F);
		}
	}

	@Override
	public float getVoicePitch() {
		return 0.5f;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.RABBIT_HURT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.RABBIT_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.RABBIT_DEATH;
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityRat rat, Class<T> classTarget) {
			super(rat, classTarget, true);
		}

		@Override
		public boolean canUse() {
			float f = mob.getLightLevelDependentMagicValue();
			return f >= 0.5F ? false : super.canUse();
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		private final EntityRat rat;
		
		public AttackGoal(EntityRat rat) {
			super(rat, 0.6D, false);
			this.rat = rat;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && rat.getCanAttack();
		}

		@Override
		public boolean canContinueToUse() {
			float f = mob.getLightLevelDependentMagicValue();

			if (f >= 0.5F && mob.level().random.nextInt(100) == 0 || !rat.getCanAttack()) {
				mob.setTarget((LivingEntity) null);
				return false;
			} else {
				return super.canContinueToUse();
			}
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double p_25558_) {
			double attackReachSqr = (1.8D + enemy.getBbWidth()) * (1.8D + enemy.getBbWidth());
			if (this.mob.distanceToSqr(enemy.getX(), enemy.getY(), enemy.getZ()) <= attackReachSqr) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
			} else {
				this.resetAttackCooldown();
			}
		}
	}

}