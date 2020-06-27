package animalium.entities;

import java.util.Random;

import animalium.ModEntities;
import animalium.ModItems;
import animalium.configs.Config;
import animalium.entities.ai.WildDogLeapAtTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.item.Items;
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
	public EntityDogPart dogPartHead;

	public EntityWildDog(EntityType<? extends EntityWildDog> type, World world) {
		super(type, world);
		dogPartHead = new EntityDogPart(this, "head", 0.5F, 0.5F);
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
		targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(PlayerEntity.class));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

		if (Config.WILD_DOG_ATTACK_MOBS.get())
			targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, true));
		if (Config.WILD_DOG_ATTACK_CREATURES.get())
			targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	}
/*
	@Override
	   protected void registerAttributes() {
	    super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}
*/
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.func_234295_eP_()
				.func_233815_a_(Attributes.field_233818_a_, 20D) //health
				.func_233815_a_(Attributes.field_233819_b_, 32D) //follow range
				.func_233815_a_(Attributes.field_233821_d_, (double) 0.6F) //move speed
				.func_233815_a_(Attributes.field_233823_f_, 4D); //attack damage	
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn != ModEntities.WILD_DOG;
	}

	public static boolean isValidLightLevel(IWorld world, BlockPos pos) {
        if (world.getLightFor(LightType.BLOCK, pos) >= 8)
            return false;
        return true;
    }

	public static boolean canSpawnHere(EntityType<EntityWildDog> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		if(isDimBlacklisted(world.func_230315_m_().func_241513_m_())) //getDimension().getType().getId()????
			return false;
        return world.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(world, pos) && pos.getY() <= Config.WILD_DOG_SPAWN_Y_HEIGHT.get();
	}

	@Override
	public boolean isNotColliding(IWorldReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.checkNoEntityCollision(this);
	}

	public static boolean isDimBlacklisted(int dimensionIn) {
		if(Config.WILD_DOG_BLACKLISTED_DIMS.get().contains(dimensionIn))
			return true;
		return false;
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(ModItems.WILD_DOG_PELT);
			if (isBurning())
				stack = new ItemStack(Items.BONE);
			entityDropItem(stack, 1.0F);
		}
	}

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
		dogPartHead.setLocationAndAngles(getPosX() - offSetX, getPosY() + 0.7D, getPosZ() - offSetZ, 0.0F, 0.0F);
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

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityWildDog dog) {
			super(dog, 0.6D, false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}
}