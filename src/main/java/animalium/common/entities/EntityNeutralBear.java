package animalium.common.entities;


import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import animalium.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
			LivingEntity RiderEntity = (LivingEntity) getRider();
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

	// Copy of boat dismounting location code
	public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
		Vec3 vec3 = getCollisionHorizontalEscapeVector((double) (this.getBbWidth() * Mth.SQRT_OF_TWO), (double) entity.getBbWidth(), entity.getYRot());
		double d0 = this.getX() + vec3.x;
		double d1 = this.getZ() + vec3.z;
		BlockPos blockpos = BlockPos.containing(d0, this.getBoundingBox().maxY, d1);
		BlockPos blockpos1 = blockpos.below();
		if (!this.level().isWaterAt(blockpos1)) {
			List<Vec3> list = Lists.newArrayList();
			double d2 = this.level().getBlockFloorHeight(blockpos);
			if (DismountHelper.isBlockFloorValid(d2)) {
				list.add(new Vec3(d0, (double) blockpos.getY() + d2, d1));
			}

			double d3 = this.level().getBlockFloorHeight(blockpos1);
			if (DismountHelper.isBlockFloorValid(d3)) {
				list.add(new Vec3(d0, (double) blockpos1.getY() + d3, d1));
			}

			for (Pose pose : entity.getDismountPoses()) {
				for (Vec3 vec31 : list) {
					if (DismountHelper.canDismountTo(this.level(), vec31, entity, pose)) {
						entity.setPose(pose);
						return vec31;
					}
				}
			}
		}
		return super.getDismountLocationForPassenger(entity);
	}

    @Override
    public boolean isControlledByLocalInstance() {
        Entity entity = this.getRider();
        return entity instanceof Player ? ((Player)entity).isLocalPlayer() : !this.level().isClientSide;
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
