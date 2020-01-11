package animalium.items;

import java.util.List;

import javax.annotation.Nullable;

import animalium.ModArmourMaterials;
import animalium.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemDogPeltBoots extends ArmorItem {

	public ItemDogPeltBoots(ModArmourMaterials material, EquipmentSlotType slot, Properties properties) {
		super(material, slot, properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.wild_dog_pelt_boots").applyTextStyle(TextFormatting.YELLOW));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EquipmentSlotType slot, String type) {
		if (is.getItem() == ModItems.WILD_DOG_PELT_BOOTS)
			return "animalium:textures/items/armour_wild_dog_pelt_boots.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.WILD_DOG_PELT;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == this && !entity.isCrouching()) {
				entity.fallDistance = 0.0F;
				if (entity.isSprinting() && entity.onGround) {
					float f1 = entity.rotationYaw * ((float) Math.PI / 180F);
					entity.setMotion(entity.getMotion().add((double) (-MathHelper.sin(f1) * 0.3F), 0.0D, (double) (MathHelper.cos(f1) * 0.3F)));
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) e.getEntityLiving()).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == this && !e.getEntityLiving().isCrouching()) {
			      float f = 0.82F;
			      Vec3d vec3d = e.getEntityLiving().getMotion();
			      e.getEntityLiving().setMotion(vec3d.x, (double)f, vec3d.z);
			      if (e.getEntityLiving().isSprinting()) {
			         float f1 = e.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
			         e.getEntityLiving().setMotion(e.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
			      }
			      e.getEntityLiving().isAirBorne = true;
				}
		}
	}
}