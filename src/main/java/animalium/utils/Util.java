package animalium.utils;

import java.util.List;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Util {
	public static final String MOD_ID = "animalium";
	public static final String MOD_NAME = "Animalium";
	public static final String VERSION = "1.0.0";

	public static final Logger LOGGER = LogUtils.getLogger();

	public static void Log(String message) {
		Log(LogLevel.Info, message);
	}

	public static void Log(LogLevel level, String message) {
		LOGGER.info(MOD_ID + "[{}]: {}", level.toString(), message);
	}

	public static class BlockTags {
		public static final TagKey<Block> NEEDS_BEAR_PAXEL = tag("needs_bear_paxel");

		private static TagKey<Block> tag(String name) {
			return net.minecraft.tags.BlockTags.create(new ResourceLocation(MOD_ID, name));
		}
	}

	public static boolean isDimBlacklisted(String dimensionIn, List<? extends String> blacklistedDims) {
		return blacklistedDims.contains(dimensionIn);
	}

}