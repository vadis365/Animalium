package animalium;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents {
	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) e.getEntityLiving()).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == ModItems.WILD_DOG_PELT_BOOTS && !e.getEntityLiving().isCrouching()) {
				float f = 0.82F;
				Vector3d vec3d = e.getEntityLiving().getMotion();
				e.getEntityLiving().setMotion(vec3d.x, (double) f, vec3d.z);
				if (e.getEntityLiving().isSprinting()) {
					float f1 = e.getEntityLiving().rotationYaw * ((float) Math.PI / 180F);
					e.getEntityLiving().setMotion(e.getEntityLiving().getMotion().add((double) (-MathHelper.sin(f1) * 0.2F), 0.0D, (double) (MathHelper.cos(f1) * 0.2F)));
				}
				e.getEntityLiving().isAirBorne = true;
			}
		}
	}
}
