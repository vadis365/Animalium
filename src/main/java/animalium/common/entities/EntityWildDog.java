package animalium.common.entities;

import animalium.init.ModEntities;
import animalium.init.ModItems;
import animalium.configs.Config;
import animalium.common.entities.ai.WildDogLeapAtTargetGoal;
import animalium.utils.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;

public class EntityWildDog extends Monster {

    public EntityDogPart[] dogPartArray;
	public EntityDogPart dogPartHead;

	public EntityWildDog(EntityType<? extends EntityWildDog> type, Level level) {
		super(type, level);
		dogPartHead = new EntityDogPart(this, "head", 0.5F, 0.5F);
		dogPartArray = new EntityDogPart[] {dogPartHead};
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new WildDogLeapAtTargetGoal(this, 0.5F));
		goalSelector.addGoal(3, new EntityWildDog.AttackGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.4D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(Player.class));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

		if (Config.WILD_DOG_ATTACK_MOBS.get())
			targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true));
		if (Config.WILD_DOG_ATTACK_CREATURES.get())
			targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20D)
				.add(Attributes.FOLLOW_RANGE, 24D)
				.add(Attributes.MOVEMENT_SPEED, 0.6f)
				.add(Attributes.ATTACK_DAMAGE, 4D);
	}

	@Override
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != ModEntities.WILD_DOG.get();
	}

	public static boolean isValidLightLevel(LevelAccessor level, BlockPos pos) {
        return level.getBrightness(LightLayer.BLOCK, pos) < 8;
    }

	public static boolean canSpawnHere(EntityType<EntityWildDog> entity, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
		ResourceKey<Level> dimensionKey = ((Level) level).dimension();

		if (Util.isDimBlacklisted(dimensionKey.location().toString(), Config.WILD_DOG_BLACKLISTED_DIMS.get()))
			return false;

		return level.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(level, pos) && pos.getY() <= Config.WILD_DOG_SPAWN_Y_HEIGHT.get();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader level) {
		return level.noCollision(this);
	}

	public static String getDimensionRegName(ResourceKey<Level> reg) {
		return reg.location().toString();
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		if (this.random.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.WILD_DOG_PELT.get());
			if (isOnFire())
				stack = new ItemStack(Items.BONE);
			this.spawnAtLocation(stack, 1.0F);
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.WOLF_GROWL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.WOLF_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.WOLF_DEATH;
	}

	@Override
    public void tick() {
    	super.tick();
		yBodyRot = getYRot();
		double a = Math.toRadians(getYRot());
		double offSetX = -Mth.sin((float) a) * -0.75D;
		double offSetZ = Mth.cos((float) a) * -0.75D;
		dogPartHead.setPos(getX() - offSetX, getY() + 0.7D, getZ() - offSetZ);
		dogPartHead.setYRot(0);
		dogPartHead.setXRot(0);
    }

	public boolean hurtFromPart(EntityDogPart dogPart, DamageSource source, float damage) {
		if (source.getDirectEntity() instanceof Player || source.is(DamageTypes.EXPLOSION))
			hurtDogFrom(source, damage);
		return true;
	}

	@Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.THORNS))
			hurtFromPart(dogPartHead, source, amount);
        return hurtDogFrom(source, amount);
    }

    protected boolean hurtDogFrom(DamageSource source, float amount){
        return super.hurt(source, amount);
    }



	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityWildDog dog) {
			super(dog, 0.6D, false);
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