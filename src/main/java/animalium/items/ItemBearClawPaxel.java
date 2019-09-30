package animalium.items;

import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sun.istack.internal.Nullable;

import animalium.ModItems;
import animalium.ModToolMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ItemBearClawPaxel extends ToolItem {

    private static final Map<Block, BlockState> PATH_STUFF = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));

	public ItemBearClawPaxel(IItemTier tier, Function<Properties, Properties> properties) {
		super(1.0F, 1.0F, tier, new HashSet<>(), properties.apply(new Properties()
				.defaultMaxDamage((int) (tier.getMaxUses()))
				.addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
				.addToolType(ToolType.SHOVEL, tier.getHarvestLevel())
				.addToolType(ToolType.AXE, tier.getHarvestLevel())
		));
	}

	@Override
	   public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
		if ("pickaxe".equals(tool.getName()) || "axe".equals(tool.getName()) || "shovel".equals(tool.getName()))
			return  ModToolMaterials.TOOL_BEAR_CLAW_PAXEL.getHarvestLevel();
		return -1;
	}

	@Override
	public boolean canHarvestBlock(BlockState state) {
		Block block = state.getBlock();
		int i = this.getTier().getHarvestLevel();
		if (state.getHarvestTool() == ToolType.PICKAXE)
			return i >= state.getHarvestLevel();

		Material material = state.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL || block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
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

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		if (context.getFace() != Direction.DOWN && world.getBlockState(pos.up()).isAir(world, pos.up())) {
			BlockState state = PATH_STUFF.get(world.getBlockState(pos).getBlock());
			if (state != null) {
				PlayerEntity player = context.getPlayer();
				world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(pos, state, 11);
					if (player != null) {
						context.getItem().damageItem(1, player, entity -> {
							entity.sendBreakAnimation(context.getHand());
						});
					}
				}

				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.PASS;
	}

}

