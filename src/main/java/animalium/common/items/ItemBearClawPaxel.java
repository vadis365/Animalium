package animalium.common.items;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import animalium.utils.Util;
import animalium.init.ModToolTiers;

import animalium.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class ItemBearClawPaxel extends DiggerItem {

	public ItemBearClawPaxel(Tier tier, Function<Properties, Properties> properties) {
		super(1.0F, 1.0F, tier, Util.BlockTags.NEEDS_BEAR_PAXEL, properties.apply(new Properties()
				.defaultDurability(tier.getUses())
		));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	   public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.bear_claw_paxel").withStyle(ChatFormatting.YELLOW));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (isCorrectToolForDrops(state))
			return ModToolTiers.PAXEL.getSpeed();
		return 2.0F;
	}



	@Override
	public boolean isCorrectToolForDrops(BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL);
	}

	@Override
	public boolean isRepairable(ItemStack stack) {
		return stack.getItem() == ModItems.BEAR_CLAW.get();
	}


	@Override
	public @NotNull InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.PASS;
		} else {
			Player player = context.getPlayer();
			BlockState blockstate1 = blockstate.getToolModifiedState(context, net.minecraftforge.common.ToolActions.SHOVEL_FLATTEN, false);
			BlockState blockstate2 = null;
			if (blockstate1 != null && level.isEmptyBlock(blockpos.above())) {
				level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
				blockstate2 = blockstate1;
			} else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
				if (!level.isClientSide()) {
					level.levelEvent(null, 1009, blockpos, 0);
				}

				CampfireBlock.dowse(context.getPlayer(), level, blockpos, blockstate);
				blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
			}

			if (blockstate2 != null) {
				if (!level.isClientSide) {
					level.setBlock(blockpos, blockstate2, 11);
					level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
					if (player != null) {
						context.getItemInHand().hurtAndBreak(1, player, p_43122_ -> p_43122_.broadcastBreakEvent(context.getHand()));
					}
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		}
	}

}

