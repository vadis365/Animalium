package animalium.common.entities;


import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import animalium.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;


public class EntityNeutralBear extends EntityBear {

	public EntityNeutralBear(EntityType<? extends EntityNeutralBear> type, Level level) {
		super(type, level);
	}

	@Override
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != EntityType.PLAYER;
	}

	@Override
	public void setTarget(LivingEntity entity) {
		if (entity instanceof Player)
			super.setTarget(null);
		else
			super.setTarget(entity);
	}

	@Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack is = player.getItemInHand(hand);
		float healingBuff = 0.0F;
		if (!level().isClientSide && !is.isEmpty() && is.getItem() == ModItems.RAT_MEAT.get()) {
				healingBuff = 2.0F;

				if (getHealth() < getMaxHealth()) {
					heal(healingBuff);
					player.swing(hand);
					is.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}

		if (!level().isClientSide && is.isEmpty()) {
			player.startRiding(this, true);
			return InteractionResult.SUCCESS;
		}
		 else
			return super.mobInteract(player, hand);
	}

	@Override
	public void travel(Vec3 travel_vector) {
		if (isVehicle()) {
			LivingEntity RiderEntity = getControllingPassenger();
			if (RiderEntity != null) {
				this.setYRot(RiderEntity.getYRot());
				this.yRotO = this.getYRot();
				this.setXRot(RiderEntity.getXRot() * 0.5F);
				this.setRot(this.getYRot(), this.getXRot());
				this.yBodyRot = this.getYRot();
				this.yHeadRot = this.yBodyRot;
				float strafe = RiderEntity.xxa * 0.4F;
				float forward = RiderEntity.zza * 0.4F;

				if (forward <= 0.0F)
					forward *= 0.25F;

				setSpeed(getSpeed() * 0.1F);

				if (isControlledByLocalInstance()) {
					setSpeed((float) getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					super.travel(new Vec3(strafe, travel_vector.y, forward));
				} else if (RiderEntity instanceof Player) {
					setSpeed((float) getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					super.travel(new Vec3(strafe, travel_vector.y, forward));
				}
			}

//	            this.walkAnimation. = limbSwingAmount;
//	            double d1 = getPosX() - prevPosX;
//	            double d0 = getPosZ() - prevPosZ;
//	            float f2 = Mth.sqrt(d1 * d1 + d0 * d0) * 4.0F;
			//
//	            if (f2 > 1.0F)
//	                f2 = 1.0F;
//	            limbSwingAmount += (f2 - limbSwingAmount) * 0.4F;
//	            limbSwing += limbSwingAmount;
		} else {
			// jumpMovementFactor = 0.02F;
			super.travel(travel_vector);
		}
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Nullable
	private Vec3 getDismountLocationInDirection(Vec3 vector, LivingEntity entity) {
		double d0 = this.getX() + vector.x;
		double d1 = this.getBoundingBox().minY;
		double d2 = this.getZ() + vector.z;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (Pose pose : entity.getDismountPoses()) {
			blockpos$mutableblockpos.set(d0, d1, d2);
			double d3 = this.getBoundingBox().maxY + 0.75D;

			while (true) {
				double d4 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
				if ((double) blockpos$mutableblockpos.getY() + d4 > d3) {
					break;
				}

				if (DismountHelper.isBlockFloorValid(d4)) {
					AABB aabb = entity.getLocalBoundsForPose(pose);
					Vec3 vec3 = new Vec3(d0, (double) blockpos$mutableblockpos.getY() + d4, d2);
					if (DismountHelper.canDismountTo(this.level(), entity, aabb.move(vec3))) {
						entity.setPose(pose);
						return vec3;
					}
				}

				blockpos$mutableblockpos.move(Direction.UP);
				if (!((double) blockpos$mutableblockpos.getY() < d3)) {
					break;
				}
			}
		}

		return null;
	}

	@Override
	public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
		Vec3 vec3 = getCollisionHorizontalEscapeVector((double) this.getBbWidth(), (double) entity.getBbWidth(),
				this.getYRot() + (entity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
		Vec3 vec31 = this.getDismountLocationInDirection(vec3, entity);
		if (vec31 != null) {
			return vec31;
		} else {
			Vec3 vec32 = getCollisionHorizontalEscapeVector((double) this.getBbWidth(), (double) entity.getBbWidth(),
					this.getYRot() + (entity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
			Vec3 vec33 = this.getDismountLocationInDirection(vec32, entity);
			return vec33 != null ? vec33 : this.position();
		}
	}

    @Override
    public boolean isControlledByLocalInstance() {
        Entity entity = this.getRider();
        return entity instanceof Player ? ((Player)entity).isLocalPlayer() : !this.level().isClientSide;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
       return getRider() instanceof LivingEntity ? (LivingEntity) getRider() : null;
    }

    @Nullable
    public Entity getRider()  {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		super.positionRider(entity, moveFunction);
		if (entity instanceof LivingEntity) {
			double a = Math.toRadians(yBodyRot);
			double offSetX = -Math.sin(a) * 0.35D;
			double offSetZ = Math.cos(a) * 0.35D;
			entity.setPos(getX() - offSetX, getY() + 1.65D + entity.getMyRidingOffset(), getZ() - offSetZ);
		}
	}
}
