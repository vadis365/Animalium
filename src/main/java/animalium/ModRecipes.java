package animalium;

public class ModRecipes {
	/*
	public static IRecipe LEATHER, BEAR_CLAW_PAXEL, DOG_PELT_BOOTS;

	public static void init() {
		LEATHER = new ShapelessOreRecipe(getResource("recipe_pelt _to_leather"), new ItemStack(Items.LEATHER), new ItemStack(ModItems.WILD_DOG_PELT), new ItemStack(ModItems.WILD_DOG_PELT), new ItemStack(ModItems.WILD_DOG_PELT), new ItemStack(ModItems.WILD_DOG_PELT));
		LEATHER.setRegistryName(getResource("animalium:recipe_pelt _to_leather"));
		
		BEAR_CLAW_PAXEL = new ShapedOreRecipe(getResource("recipe_bear_claw_paxel"), new ItemStack(ModItems.BEAR_CLAW_PAXEL, 1, 0), "XXX", "XS ", "XS ", 'X', new ItemStack(ModItems.BEAR_CLAW), 'S', "stickWood");
		BEAR_CLAW_PAXEL.setRegistryName(getResource("animalium:recipe_bear_claw_paxel"));
		
		DOG_PELT_BOOTS = new ShapedOreRecipe(getResource("recipe_dog_pelt_boots"), new ItemStack(ModItems.DOG_PELT_BOOTS), "RPR", "PLP", "SPS", 'R', "dustRedstone", 'P', new ItemStack(ModItems.WILD_DOG_PELT), 'L', new ItemStack(Items.LEATHER_BOOTS), 'S', "slimeball");
		DOG_PELT_BOOTS.setRegistryName(getResource("animalium:recipe_dog_pelt_boots"));
	}

	@Mod.EventBusSubscriber(modid = "animalium")
	public static class RegistrationHandlerRecipes {
		@SubscribeEvent
		public static void registerRecipes(final RegistryEvent.Register<IRecipe> event) {
		final IForgeRegistry<IRecipe> registry = event.getRegistry();
		event.getRegistry().registerAll(
				LEATHER,
				BEAR_CLAW_PAXEL,
				DOG_PELT_BOOTS
				);
		}
	}

	private static ResourceLocation getResource(String inName) {
		return new ResourceLocation(inName);
	}

	public static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModItems.BEAR_MEAT), new ItemStack(ModItems.BEAR_MEAT_COOKED), 1F);
		GameRegistry.addSmelting(new ItemStack(ModItems.RAT_MEAT), new ItemStack(ModItems.RAT_MEAT_COOKED), 1F);
	}
	*/
}