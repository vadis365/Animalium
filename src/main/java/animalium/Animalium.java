package animalium;

import animalium.configs.ConfigHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Reference.MOD_ID)
public class Animalium {
	
	public Animalium () {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
	}

//	public static ToolMaterial TOOL_BEAR_CLAW_PAXEL = EnumHelper.addToolMaterial("BEAR_CLAW_PAXEL", 2, 1079, 8.0F, 4.0F, 14);
//	public static ArmorMaterial ARMOR_DOG_PELT = EnumHelper.addArmorMaterial("ARMOR_DOG_PELT", "wild_dog_pelt", 19, new int[] { 2, 3, 2, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F);

	public static ItemGroup TAB = new ItemGroup(Reference.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack (ModItems.BEAR_CLAW_PAXEL);
		}
	};


	private void setup(final FMLCommonSetupEvent event) {
	/*	ConfigHandler.INSTANCE.loadConfig(event);

		ModItems.init();
		ModRecipes.init();
		ModRecipes.registerSmelting();

		EntityRegistry.registerModEntity(getEntityResource("piranha"), EntityPiranha.class, "piranha", 1, this, 120, 1, true, -126, -48326583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityPiranha.class, SpawnPlacementType.IN_WATER);

		EntityRegistry.registerModEntity(getEntityResource("wild_dog"), EntityWildDog.class, "wild_dog", 2, this, 120, 1, true, -310, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityWildDog.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("bear"), EntityBear.class, "bear", 3, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityBear.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("bear_tamed"), EntityNeutralBear.class, "bear_tamed", 5, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityBear.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("rat"), EntityRat.class, "rat", 4, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityRat.class, SpawnPlacementType.ON_GROUND);

		PROXY.registerRenderers();

		for (Biome allBiomes : ForgeRegistries.BIOMES.getValues()) {
			if (BiomeDictionary.hasType(allBiomes, Type.WATER) || BiomeDictionary.hasType(allBiomes, Type.SWAMP))
				if(ConfigHandler.PIRANHA_SPAWN_PROBABILITY > 0 && ConfigHandler.PIRANHA_MIN_SPAWN_SIZE > 0 && ConfigHandler.PIRANHA_MAX_SPAWN_SIZE > 0)
					EntityRegistry.addSpawn(EntityPiranha.class, ConfigHandler.PIRANHA_SPAWN_PROBABILITY, ConfigHandler.PIRANHA_MIN_SPAWN_SIZE, ConfigHandler.PIRANHA_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
			if (!BiomeDictionary.hasType(allBiomes, Type.WATER)) {
				if(ConfigHandler.WILD_DOG_SPAWN_PROBABILITY > 0 && ConfigHandler.WILD_DOG_MIN_SPAWN_SIZE > 0 && ConfigHandler.WILD_DOG_MAX_SPAWN_SIZE > 0)
					EntityRegistry.addSpawn(EntityWildDog.class, ConfigHandler.WILD_DOG_SPAWN_PROBABILITY, ConfigHandler.WILD_DOG_MIN_SPAWN_SIZE, ConfigHandler.WILD_DOG_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.BEAR_SPAWN_PROBABILITY > 0 && ConfigHandler.BEAR_MIN_SPAWN_SIZE > 0 && ConfigHandler.BEAR_MAX_SPAWN_SIZE > 0)
					EntityRegistry.addSpawn(EntityBear.class, ConfigHandler.BEAR_SPAWN_PROBABILITY, ConfigHandler.BEAR_MIN_SPAWN_SIZE, ConfigHandler.BEAR_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.RAT_SPAWN_PROBABILITY > 0 && ConfigHandler.RAT_MIN_SPAWN_SIZE > 0 && ConfigHandler.RAT_MAX_SPAWN_SIZE > 0)
					EntityRegistry.addSpawn(EntityRat.class, ConfigHandler.RAT_SPAWN_PROBABILITY, ConfigHandler.RAT_MIN_SPAWN_SIZE, ConfigHandler.RAT_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				}
		}
	*/	
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.DOG_PELT_BOOTS);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {}

	private void enqueueIMC(final InterModEnqueueEvent event) {}

	private void processIMC(final InterModProcessEvent event) {}
}
