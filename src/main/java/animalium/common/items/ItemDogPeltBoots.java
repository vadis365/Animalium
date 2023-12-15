package animalium.common.items;

import java.util.List;

import javax.annotation.Nullable;

import animalium.init.ModArmourMaterials;
import animalium.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemDogPeltBoots extends ArmorItem {

	public ItemDogPeltBoots(ModArmourMaterials material, ArmorItem.Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.wild_dog_pelt_boots").withStyle(ChatFormatting.YELLOW));//applyTextStyle
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.WILD_DOG_PELT_BOOTS.get())
			return "animalium:textures/item/armour_wild_dog_pelt_boots.png";
		else
			return null;
	}

	@Override
	public boolean isValidRepairItem(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.WILD_DOG_PELT.get();
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof Player) {
			ItemStack is = ((Player) entity).getItemBySlot(EquipmentSlot.FEET);
			if (!is.isEmpty() && is.getItem() == this && !entity.isCrouching()) {
				entity.fallDistance = 0.0F;
				if (entity.isSprinting() && entity.onGround()) {//onGround
					float f1 = entity.getYRot() * ((float) Math.PI / 180F);
					entity.setDeltaMovement(entity.getDeltaMovement().add((double) (-Mth.sin(f1) * 0.1F), 0.0D, (double) (Mth.cos(f1) * 0.1F)));
				}
			}
		}
	}
}