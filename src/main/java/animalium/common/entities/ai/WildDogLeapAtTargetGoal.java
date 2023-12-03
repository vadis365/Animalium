package animalium.common.entities.ai;

import java.util.EnumSet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

public class WildDogLeapAtTargetGoal extends Goal {
	   private final Monster leaper;
	   private LivingEntity leapTarget;
	   private final float leapMotionY;

	public WildDogLeapAtTargetGoal(Monster leapingEntity, float leapMotionYIn) {
		leaper = leapingEntity;
		leapMotionY = leapMotionYIn;
		setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		leapTarget = leaper.getTarget();

		if (leapTarget == null) {
			return false;
		} else {
			double d0 = leaper.distanceToSqr(leapTarget);
			return d0 >= 9.0D && d0 <= 16.0D ? (!leaper.onGround() ? false : leaper.level().random.nextInt(5) == 0) : false;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !leaper.onGround(); //onGround
	}

	@Override
	   public void start() {
	      Vec3 vec3 = leaper.getDeltaMovement();
		Vec3 vec31 = new Vec3(leapTarget.getX() - leaper.getX(), 0.0D, leapTarget.getZ() - leaper.getZ());
	      if (vec31.lengthSqr() > 1.0E-7D) {
	         vec31 = vec31.normalize().scale(0.4D).add(vec3.scale(0.2D));
	      }

	      leaper.setDeltaMovement(vec31.x, (double)leapMotionY, vec31.z);
	   }
}
