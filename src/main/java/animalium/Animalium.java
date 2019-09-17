package animalium;

import animalium.configs.ConfigHandler;
import animalium.entities.EntityBear;
import animalium.entities.EntityPiranha;
import animalium.entities.EntityRat;
import animalium.entities.EntityNeutralBear;
import animalium.entities.EntityWildDog;
import animalium.items.ItemBearClawPaxel;
import animalium.items.ItemDogPeltBoots;
import animalium.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "animalium", name = "animalium", version = "0.2.1", guiFactory = "animalium.configs.ConfigGuiFactory", dependencies = "after:BiomesOPlenty")

public class Animalium {

	@Instance("animalium")
	public static Animalium INSTANCE;

	@SidedProxy(clientSide = "animalium.proxy.ClientProxy", serverSide = "animalium.proxy.CommonProxy")
	public static CommonProxy PROXY;
	public static Item WILD_DOG_PELT, BEAR_MEAT, BEAR_MEAT_COOKED, BEAR_CLAW, BEAR_CLAW_PAXEL, RAT_MEAT, RAT_MEAT_COOKED, DOG_PELT_BOOTS;
	public static ToolMaterial TOOL_BEAR_CLAW_PAXEL = EnumHelper.addToolMaterial("BEAR_CLAW_PAXEL", 2, 1079, 8.0F, 4.0F, 14);
	public static ArmorMaterial ARMOR_DOG_PELT = EnumHelper.addArmorMaterial("ARMOR_DOG_PELT", "wild_dog_pelt", 11, new int[] { 2, 3, 2, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F);

	public static CreativeTabs TAB = new CreativeTabs("animalium") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack (BEAR_CLAW_PAXEL);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
		EntityRegistry.registerModEntity(getEntityResource("piranha"), EntityPiranha.class, "piranha", 1, this, 120, 1, true, -126, -48326583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityPiranha.class, SpawnPlacementType.IN_WATER);

		EntityRegistry.registerModEntity(getEntityResource("wild_dog"), EntityWildDog.class, "wild_dog", 2, this, 120, 1, true, -310, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityWildDog.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("bear"), EntityBear.class, "bear", 3, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityBear.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("bear_tamed"), EntityNeutralBear.class, "bear_tamed", 3, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityBear.class, SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(getEntityResource("rat"), EntityRat.class, "rat", 4, this, 120, 1, true, -3546547, -65179583);
		EntitySpawnPlacementRegistry.setPlacementType(EntityRat.class, SpawnPlacementType.ON_GROUND);

		WILD_DOG_PELT = new Item().setCreativeTab(TAB);
		GameRegistry.register(WILD_DOG_PELT.setRegistryName("animalium", "wild_dog_pelt").setUnlocalizedName("animalium.wild_dog_pelt"));

		BEAR_MEAT = new ItemFood(3, 0.3F, true).setCreativeTab(TAB);
		GameRegistry.register(BEAR_MEAT.setRegistryName("animalium", "bear_meat").setUnlocalizedName("animalium.bear_meat"));

		BEAR_MEAT_COOKED = new ItemFood(8, 0.8F, true).setCreativeTab(TAB);
		GameRegistry.register(BEAR_MEAT_COOKED.setRegistryName("animalium", "bear_meat_cooked").setUnlocalizedName("animalium.bear_meat_cooked"));

		RAT_MEAT = new ItemFood(2, 0.3F, true) {
			@Override
			public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
				if (target instanceof EntityBear) {
					if (!player.getEntityWorld().isRemote) {
						EntityBear bear = (EntityBear) target;
						EntityNeutralBear tamedBear = new EntityNeutralBear(player.getEntityWorld());
						tamedBear.copyLocationAndAnglesFrom(bear);
						player.getEntityWorld().removeEntity(bear);
						player.getEntityWorld().spawnEntity(tamedBear);
						System.out.println("Bear Tamed");
					}
					stack.shrink(1);
					return true;
				} else {
					return false;
				}
			}
			
		};
		((ItemFood) RAT_MEAT).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.3F).setCreativeTab(TAB);
		GameRegistry.register(RAT_MEAT.setRegistryName("animalium", "rat_meat").setUnlocalizedName("animalium.rat_meat"));

		RAT_MEAT_COOKED = new ItemFood(4, 0.6F, true).setCreativeTab(TAB);
		GameRegistry.register(RAT_MEAT_COOKED.setRegistryName("animalium", "rat_meat_cooked").setUnlocalizedName("animalium.rat_meat_cooked"));

		BEAR_CLAW = new Item().setCreativeTab(TAB);
		GameRegistry.register(BEAR_CLAW.setRegistryName("animalium", "bear_claw").setUnlocalizedName("animalium.bear_claw"));

		BEAR_CLAW_PAXEL = new ItemBearClawPaxel(TOOL_BEAR_CLAW_PAXEL);
		GameRegistry.register(BEAR_CLAW_PAXEL.setRegistryName("animalium", "bear_claw_paxel").setUnlocalizedName("animalium.bear_claw_paxel"));

		DOG_PELT_BOOTS = new ItemDogPeltBoots();
		GameRegistry.register(DOG_PELT_BOOTS.setRegistryName("animalium", "dog_boots").setUnlocalizedName("animalium.dog_boots"));

		ModRecipes.addRecipes();
		ModRecipes.registerSmelting();

		PROXY.registerRenderers();

		for (Biome allBiomes : ForgeRegistries.BIOMES.getValues()) {
			if (!BiomeDictionary.hasType(allBiomes, Type.NETHER) && !BiomeDictionary.hasType(allBiomes, Type.END)) {
				if (BiomeDictionary.hasType(allBiomes, Type.WATER) || BiomeDictionary.hasType(allBiomes, Type.SWAMP))
					EntityRegistry.addSpawn(EntityPiranha.class, ConfigHandler.PIRANHA_SPAWN_PROBABILITY, ConfigHandler.PIRANHA_MIN_SPAWN_SIZE, ConfigHandler.PIRANHA_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if (!BiomeDictionary.hasType(allBiomes, Type.WATER)) {
					EntityRegistry.addSpawn(EntityWildDog.class, ConfigHandler.WILD_DOG_SPAWN_PROBABILITY, ConfigHandler.WILD_DOG_MIN_SPAWN_SIZE, ConfigHandler.WILD_DOG_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
					EntityRegistry.addSpawn(EntityBear.class, ConfigHandler.BEAR_SPAWN_PROBABILITY, ConfigHandler.BEAR_MIN_SPAWN_SIZE, ConfigHandler.BEAR_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
					EntityRegistry.addSpawn(EntityRat.class, ConfigHandler.RAT_SPAWN_PROBABILITY, ConfigHandler.RAT_MIN_SPAWN_SIZE, ConfigHandler.RAT_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				}
			}
		}
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation("animalium", entityName);
	}

	@EventHandler
	public void posInit(FMLPostInitializationEvent event) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(DOG_PELT_BOOTS);
	}
}