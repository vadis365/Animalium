package animalium;

import animalium.configs.Config;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModSpawns {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoading(BiomeLoadingEvent event) {
		if (event.getCategory() == Biome.Category.OCEAN || event.getCategory() == Biome.Category.SWAMP || event.getCategory() == Biome.Category.RIVER)
			if (Config.PIRANHA_SPAWN_PROBABILITY.get() > 0 && Config.PIRANHA_MIN_SPAWN_SIZE.get() > 0 && Config.PIRANHA_MAX_SPAWN_SIZE.get() > 0)
				event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new Spawners(ModEntities.PIRANHA, Config.PIRANHA_SPAWN_PROBABILITY.get(), Config.PIRANHA_MIN_SPAWN_SIZE.get(), Config.PIRANHA_MAX_SPAWN_SIZE.get()));
		if (event.getCategory() != Biome.Category.OCEAN && event.getCategory() != Biome.Category.RIVER) {
			if (Config.WILD_DOG_SPAWN_PROBABILITY.get() > 0 && Config.WILD_DOG_MIN_SPAWN_SIZE.get() > 0 && Config.WILD_DOG_MAX_SPAWN_SIZE.get() > 0)
				event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new Spawners(ModEntities.WILD_DOG, Config.WILD_DOG_SPAWN_PROBABILITY.get(), Config.WILD_DOG_MIN_SPAWN_SIZE.get(), Config.WILD_DOG_MAX_SPAWN_SIZE.get()));
			if (Config.BEAR_SPAWN_PROBABILITY.get() > 0 && Config.BEAR_MIN_SPAWN_SIZE.get() > 0 && Config.BEAR_MAX_SPAWN_SIZE.get() > 0)
				event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new Spawners(ModEntities.BEAR, Config.BEAR_SPAWN_PROBABILITY.get(), Config.BEAR_MIN_SPAWN_SIZE.get(), Config.BEAR_MAX_SPAWN_SIZE.get()));
			if (Config.RAT_SPAWN_PROBABILITY.get() > 0 && Config.RAT_MIN_SPAWN_SIZE.get() > 0 && Config.RAT_MAX_SPAWN_SIZE.get() > 0)
				event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new Spawners(ModEntities.RAT, Config.RAT_SPAWN_PROBABILITY.get(), Config.RAT_MIN_SPAWN_SIZE.get(), Config.RAT_MAX_SPAWN_SIZE.get()));
		}
	}
}
