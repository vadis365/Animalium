package animalium.init;

import java.util.List;

import animalium.utils.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

public class ModToolTiers {

    public static final Tier PAXEL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 1079, 8.0f, 4.0F, 14,
                    Util.BlockTags.NEEDS_BEAR_PAXEL, () -> Ingredient.of(ModItems.BEAR_CLAW.get())),
            new ResourceLocation(Util.MOD_ID, "bear_claw"), List.of(Tiers.STONE), List.of());

}
