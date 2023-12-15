package animalium.common;

import animalium.init.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonEvents {

	@SubscribeEvent
	public void onEntityJump(LivingEvent.LivingJumpEvent e) {
		if (e.getEntity() instanceof Player) {
			ItemStack is = ((Player) e.getEntity()).getItemBySlot(EquipmentSlot.FEET);
			if (!is.isEmpty() && is.getItem() == ModItems.WILD_DOG_PELT_BOOTS.get() && !e.getEntity().isCrouching()) {
				float f = 0.82F;
				Vec3 movement = e.getEntity().getDeltaMovement();
				e.getEntity().setDeltaMovement(movement.x, (double) f, movement.z);
				if (e.getEntity().isSprinting()) {
					float f1 = e.getEntity().getYRot() * ((float) Math.PI / 180F);
					e.getEntity().setDeltaMovement(e.getEntity().getDeltaMovement().add((double) (-Math.sin(f1) * 0.2F), 0.0D, (double) (Math.cos(f1) * 0.2F)));
				}
				e.getEntity().setOnGround(false);
			}
		}
	}
}
