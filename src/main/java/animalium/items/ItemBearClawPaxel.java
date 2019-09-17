package animalium.items;

import animalium.Animalium;
import animalium.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBearClawPaxel extends ItemTool {

	public ItemBearClawPaxel(ToolMaterial material) {
		super(1.0F, 1.0F, material, null);
		setCreativeTab(Animalium.TAB);
	}

	@Override
	  public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
		if ("pickaxe".equals(toolClass) || "axe".equals(toolClass) || "shovel".equals(toolClass))
			return Animalium.TOOL_BEAR_CLAW_PAXEL.getHarvestLevel();
		return -1;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (isToolEffective(state))
			return Animalium.TOOL_BEAR_CLAW_PAXEL.getEfficiency();
		return 2.0F;
	}

	public boolean isToolEffective(IBlockState state) {
		return state.getBlock().isToolEffective("pickaxe", state) || state.getBlock().isToolEffective("axe", state) || state.getBlock().isToolEffective("shovel", state);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.BEAR_CLAW;
	}
}