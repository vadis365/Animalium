package animalium.items;

import com.sun.istack.internal.Nullable;

import animalium.ModItems;
import animalium.ModToolMaterials;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

public class ItemBearClawPaxel extends ToolItem {

	public ItemBearClawPaxel(ModToolMaterials material) {
		super(1.0F, 1.0F, material, null, null);
	}

	@Override
	   public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
		if ("pickaxe".equals(tool.getName()) || "axe".equals(tool.getName()) || "shovel".equals(tool.getName()))
			return  ModToolMaterials.TOOL_BEAR_CLAW_PAXEL.getHarvestLevel();
		return -1;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (isToolEffective(state))
			return  ModToolMaterials.TOOL_BEAR_CLAW_PAXEL.getEfficiency();
		return 2.0F;
	}

	public boolean isToolEffective(BlockState state) {
		return state.isToolEffective(ToolType.get("pickaxe")) || state.isToolEffective(ToolType.get("axe")) || state.isToolEffective(ToolType.get("shovel"));
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.BEAR_CLAW;
	}
}