package animalium.utils;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;

import java.util.Set;

public class Util {
	public static final String MOD_ID = "animalium";
	public static final String MOD_NAME = "Animalium";
	public static final String VERSION = "0.6.4";

	public static final Logger LOGGER = LogUtils.getLogger();

	public static void Log(String message) {
		Log(LogLevel.Info, message);
	}

	public static void Log(LogLevel level, String message) {
		LOGGER.info(MOD_ID + "[{}]: {}", level.toString(), message);
	}

	public static boolean isDimBlacklisted(String dimensionIn, Set<String> blacklistedDims) {
		return blacklistedDims.contains(dimensionIn);
	}

	public static class BlockTags {
		public static final TagKey<Block> NEEDS_BEAR_PAXEL = tag("needs_bear_paxel");

		private static TagKey<Block> tag(String name){
			return net.minecraft.tags.BlockTags.create(new ResourceLocation(MOD_ID, name));
		}

	}

}