package animalium;

import animalium.common.CommonEvents;
import animalium.configs.Config;
import animalium.init.ModCreativeTab;
import animalium.init.ModEntities;
import animalium.init.ModItems;
import animalium.utils.Util;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

//TODO: FIX BLACKLISTED DIMS (Couldent figure out how to get dimension from LevelAccessor)
//TODO: FIX TAMED BEAR MOUNT NOT SYNCHING POSITION

@Mod(Util.MOD_ID)
@Mod.EventBusSubscriber(modid = Util.MOD_ID)
public class Animalium {

    static {

    }

    public Animalium() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.register(this);

        ModEntities.ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Path path = FMLPaths.CONFIGDIR.get().resolve("animalium-common.toml");
        Config.loadConfig(Config.COMMON_CONFIG, path);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        EVENT_BUS.register(new CommonEvents());
    }

}
