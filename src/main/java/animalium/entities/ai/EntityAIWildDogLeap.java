package animalium.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIWildDogLeap extends EntityAIBase {
	EntityLiving leaper;
	EntityLivingBase leapTarget;
	float leapMotionY;

	public EntityAIWildDogLeap(EntityLiving leapingEntity, float leapMotionYIn) {
		leaper = leapingEntity;
		leapMotionY = leapMotionYIn;
		setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		leapTarget = leaper.getAttackTarget();

		if (leapTarget == null) {
			return false;
		} else {
			double d0 = leaper.getDistanceSq(leapTarget);
			return d0 >= 9.0D && d0 <= 16.0D ? (!leaper.onGround ? false : leaper.getRNG().nextInt(2) == 0) : false;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !leaper.onGround;
	}

	@Override
	public void startExecuting() {
		double d0 = leapTarget.posX - leaper.posX;
		double d1 = leapTarget.posZ - leaper.posZ;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
		leaper.motionX += d0 / (double) f * 0.5D * 0.800000011920929D + leaper.motionX * 0.20000000298023224D;
		leaper.motionZ += d1 / (double) f * 0.5D * 0.800000011920929D + leaper.motionZ * 0.20000000298023224D;
		leaper.motionY = (double) leapMotionY;
	}
}
