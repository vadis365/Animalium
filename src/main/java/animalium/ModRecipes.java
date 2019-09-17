package animalium;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

	public static void addRecipes() {
		addShapelessRecipe(new ItemStack(Items.LEATHER), Animalium.WILD_DOG_PELT, Animalium.WILD_DOG_PELT, Animalium.WILD_DOG_PELT, Animalium.WILD_DOG_PELT);
		addShapedRecipe(new ItemStack(Animalium.BEAR_CLAW_PAXEL, 1, 0), "XXX", "XS ", "XS ", 'X', Animalium.BEAR_CLAW, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(Animalium.DOG_PELT_BOOTS, 1, 0), "RPR", "PLP", "SPS", 'R', "dustRedstone", 'P', Animalium.WILD_DOG_PELT, 'L', Items.LEATHER_BOOTS, 'S', "slimeball");
	}

	private static void addShapelessRecipe(ItemStack output, Object... parameters) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, parameters));
	}

	private static void addShapedRecipe(ItemStack output, Object... parameters) {
		GameRegistry.addRecipe(new ShapedOreRecipe(output, parameters));
	}
	
	public static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(Animalium.BEAR_MEAT), new ItemStack(Animalium.BEAR_MEAT_COOKED), 1F);
		GameRegistry.addSmelting(new ItemStack(Animalium.RAT_MEAT), new ItemStack(Animalium.RAT_MEAT_COOKED), 1F);
	}
}