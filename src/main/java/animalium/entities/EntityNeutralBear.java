package animalium.entities;


import javax.annotation.Nullable;

import animalium.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityNeutralBear extends EntityBear {

	public EntityNeutralBear(EntityType<? extends EntityNeutralBear> type, World world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new EntityBear.AttackGoal(this));
		goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return typeIn!= EntityType.PLAYER;
	}

	@Override
	public void setAttackTarget(LivingEntity entity) {
		if (entity instanceof PlayerEntity)
			super.setAttackTarget(null);
		else
			super.setAttackTarget(entity);
	}

	@Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack is = player.getHeldItem(hand);
		float healingBuff = 0.0F;
		if (!getEntityWorld().isRemote && !is.isEmpty() && is.getItem() == ModItems.RAT_MEAT) {
				healingBuff = 2.0F;

				if (getHealth() < getMaxHealth()) {
					heal(healingBuff);
					player.swingArm(hand);
					is.shrink(1);
				}
				return true;
			}

		if (!getEntityWorld().isRemote && is.isEmpty()) {
			player.startRiding(this, true);
			return true;
		}
		 else
			return super.processInteract(player, hand);
	}

	@Override
    public void travel(Vec3d travel_vector) {
        if (isBeingRidden() && canBeSteered()) {
            LivingEntity entitylivingbase = (LivingEntity)getControllingPassenger();
            rotationYaw = entitylivingbase.rotationYaw;
            prevRotationYaw = rotationYaw;
            rotationPitch = entitylivingbase.rotationPitch * 0.5F;
            setRotation(rotationYaw, rotationPitch);
            renderYawOffset = rotationYaw;
            rotationYawHead = renderYawOffset;
            float strafe = entitylivingbase.moveStrafing * 0.4F;
            float forward = entitylivingbase.moveForward * 0.4F;

            if (forward <= 0.0F) 
                forward *= 0.25F;

            jumpMovementFactor = getAIMoveSpeed() * 0.1F;

            if (canPassengerSteer()) {
                setAIMoveSpeed((float)getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                super.travel(new Vec3d((double)strafe, travel_vector.y, (double)forward));
            }
            else if (entitylivingbase instanceof PlayerEntity) {
            	setAIMoveSpeed((float)getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
            	super.travel(new Vec3d((double)strafe, travel_vector.y, (double)forward));
            }

            prevLimbSwingAmount = limbSwingAmount;
            double d1 = func_226277_ct_() - prevPosX;
            double d0 = func_226281_cx_() - prevPosZ;
            float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F)
                f2 = 1.0F;

            limbSwingAmount += (f2 - limbSwingAmount) * 0.4F;
            limbSwing += limbSwingAmount;
        }
        else {
            jumpMovementFactor = 0.02F;
            super.travel(travel_vector);
        }
    }

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
    public boolean shouldRiderSit() {
        return true;
    }

	@Override
    public boolean canBeSteered() {
        return this.getControllingPassenger() instanceof LivingEntity;
    }

	@Override
    public boolean canPassengerSteer() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof PlayerEntity ? ((PlayerEntity)entity).isUser() : !this.world.isRemote;
    }

    @Nullable
    public Entity getControllingPassenger()  {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof LivingEntity) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.35D;
			double offSetZ = Math.cos(a) * 0.35D;
			entity.setPosition(func_226277_ct_() - offSetX, func_226278_cu_() + 1.65D + entity.getYOffset(), func_226281_cx_() - offSetZ);
		}
	}
}
