package animalium.entities.ai;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

public class WildDogLeapAtTargetGoal extends Goal {
	   private final MobEntity leaper;
	   private LivingEntity leapTarget;
	   private final float leapMotionY;

	public WildDogLeapAtTargetGoal(MobEntity leapingEntity, float leapMotionYIn) {
		leaper = leapingEntity;
		leapMotionY = leapMotionYIn;
		setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		leapTarget = leaper.getAttackTarget();

		if (leapTarget == null) {
			return false;
		} else {
			double d0 = leaper.getDistanceSq(leapTarget);
			return d0 >= 9.0D && d0 <= 16.0D ? (!leaper.func_233570_aj_() ? false : leaper.getRNG().nextInt(5) == 0) : false;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !leaper.func_233570_aj_(); //onGround
	}

	@Override
	   public void startExecuting() {
	      Vector3d vec3d = leaper.getMotion();
	      Vector3d vec3d1 = new Vector3d(leapTarget.getPosX() - leaper.getPosX(), 0.0D, leapTarget.getPosZ() - leaper.getPosZ());
	      if (vec3d1.lengthSquared() > 1.0E-7D) {
	         vec3d1 = vec3d1.normalize().scale(0.4D).add(vec3d.scale(0.2D));
	      }

	      leaper.setMotion(vec3d1.x, (double)leapMotionY, vec3d1.z);
	   }
}
