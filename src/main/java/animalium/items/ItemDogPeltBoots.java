package animalium.items;

import animalium.Animalium;
import animalium.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDogPeltBoots extends ItemArmor {

	public ItemDogPeltBoots() {
		super(Animalium.ARMOR_DOG_PELT, 2, EntityEquipmentSlot.FEET);
		setCreativeTab(Animalium.TAB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.DOG_PELT_BOOTS)
			return "animalium:textures/items/dog_boots.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.WILD_DOG_PELT;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.fallDistance = 0.0F;
		if (player.isSprinting() && player.onGround) {
			player.motionX *= 1D + 0.5D;
			player.motionZ *= 1D + 0.5D;
		}
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.getEntityLiving() instanceof EntityPlayer) {
			ItemStack is = ((EntityPlayer) e.getEntityLiving()).getItemStackFromSlot(EntityEquipmentSlot.FEET);
			if (!is.isEmpty() && is.getItem() == this && !e.getEntityLiving().isSneaking())
				e.getEntityLiving().motionY += 0.4D;
		}
	}
}