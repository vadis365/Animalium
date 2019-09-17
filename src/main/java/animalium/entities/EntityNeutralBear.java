package animalium.entities;

import javax.annotation.Nullable;

import animalium.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityNeutralBear extends EntityBear {

	public EntityNeutralBear(World world) {
		super(world);
		setSize(2F, 2F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityBear.AIBearAttack(this));
		tasks.addTask(3, new EntityAIWander(this, 0.6D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityPlayer.class != entity;
	}

	@Override
	public void setAttackTarget(EntityLivingBase entity) {
		if (entity instanceof EntityPlayer)
			super.setAttackTarget(null);
		else
			super.setAttackTarget(entity);
	}

	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
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
    public void travel(float strafe, float up, float forward) {
        if (isBeingRidden() && canBeSteered()) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)getControllingPassenger();
            rotationYaw = entitylivingbase.rotationYaw;
            prevRotationYaw = rotationYaw;
            rotationPitch = entitylivingbase.rotationPitch * 0.5F;
            setRotation(rotationYaw, rotationPitch);
            renderYawOffset = rotationYaw;
            rotationYawHead = renderYawOffset;
            strafe = entitylivingbase.moveStrafing * 0.4F;
            forward = entitylivingbase.moveForward * 0.4F;

            if (forward <= 0.0F) 
                forward *= 0.25F;

            jumpMovementFactor = getAIMoveSpeed() * 0.1F;

            if (canPassengerSteer()) {
                setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, up, forward);
            }
            else if (entitylivingbase instanceof EntityPlayer) {
            	setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, up, forward);
            }

            prevLimbSwingAmount = limbSwingAmount;
            double d1 = posX - prevPosX;
            double d0 = posZ - prevPosZ;
            float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F)
                f2 = 1.0F;

            limbSwingAmount += (f2 - limbSwingAmount) * 0.4F;
            limbSwing += limbSwingAmount;
        }
        else {
            jumpMovementFactor = 0.02F;
            super.travel(strafe, up, forward);
        }
    }

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
    public boolean shouldRiderSit() {
        return true;
    }

	@Override
    public boolean canBeSteered() {
        return this.getControllingPassenger() instanceof EntityLivingBase;
    }

	@Override
    public boolean canPassengerSteer() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof EntityPlayer ? ((EntityPlayer)entity).isUser() : !this.world.isRemote;
    }

    @Nullable
    public Entity getControllingPassenger()  {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }

	@Override
	public void updatePassenger(Entity entity) {
		super.updatePassenger(entity);
		if (entity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.35D;
			double offSetZ = Math.cos(a) * 0.35D;
			entity.setPosition(posX - offSetX, posY + 1.9D + entity.getYOffset(), posZ - offSetZ);
		}
	}
}
