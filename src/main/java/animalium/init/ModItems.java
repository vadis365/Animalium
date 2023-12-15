package animalium.init;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

import com.google.common.collect.Sets;

import animalium.common.items.ItemBearClawPaxel;
import animalium.common.items.ItemDogPeltBoots;
import animalium.common.items.ItemRatMeat;
import animalium.utils.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {


	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Util.MOD_ID);
	public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

	static{
		initSpawnEggs();
	}

	public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
		RegistryObject<Item> item = ITEMS.register(name, supplier);
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

	public static Item.Properties basicItem() {
		return new Item.Properties();
	}

	public static FoodProperties.Builder foodItem() {
		return new FoodProperties.Builder();
	}

	public static RegistryObject<Item> WILD_DOG_PELT = registerWithTab("wild_dog_pelt", () -> new Item(basicItem()));
	public static RegistryObject<Item> BEAR_MEAT = registerWithTab("bear_meat", () -> new Item(basicItem().food(foodItem().nutrition(3).saturationMod(0.3F).meat().build())));
	public static RegistryObject<Item> BEAR_MEAT_COOKED = registerWithTab("bear_meat_cooked", () -> new Item(basicItem().food(foodItem().nutrition(8).saturationMod(0.1f).build())));
	public static RegistryObject<Item> BEAR_CLAW = registerWithTab("bear_claw", () -> new Item(basicItem()));;
	public static RegistryObject<Item> BEAR_CLAW_PAXEL = registerWithTab("bear_claw_paxel", () -> new ItemBearClawPaxel(ModToolTiers.PAXEL, properties -> properties.rarity(Rarity.RARE)));
	public static RegistryObject<Item> RAT_MEAT = registerWithTab("rat_meat", () -> new ItemRatMeat(basicItem().food(foodItem()
			.nutrition(2).saturationMod(0.3f)
			.effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3f).build())));

	public static RegistryObject<Item> RAT_MEAT_COOKED = registerWithTab("rat_meat_cooked", () -> new Item(basicItem().food(foodItem().nutrition(4).saturationMod(0.6f).build())));
	public static RegistryObject<Item> WILD_DOG_PELT_BOOTS = registerWithTab("wild_dog_pelt_boots", () -> new ItemDogPeltBoots(ModArmourMaterials.ARMOUR_DOG_PELT, ArmorItem.Type.BOOTS, basicItem().stacksTo(1).durability(256).defaultDurability(ModArmourMaterials.ARMOUR_DOG_PELT.getDefenseForType(ArmorItem.Type.BOOTS))));
	public static RegistryObject<Item> PIRANHA_STEAK = registerWithTab("piranha_steak", () -> new Item(basicItem().food(foodItem().nutrition(2).saturationMod(0.8f).build())));;
	public static RegistryObject<Item> PIRANHA_STEAK_COOKED = registerWithTab("piranha_steak_cooked", () -> new Item(basicItem().food(foodItem().nutrition(4).saturationMod(0.6F).build())));

	public static void initSpawnEggs() {
		registerWithTab("bear_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BEAR, -3546547, -65179583, basicItem()));
		registerWithTab("wild_dog_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WILD_DOG, -310, -65179583, basicItem()));
		registerWithTab("rat_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.RAT, -3546547, -65179583, basicItem()));
		registerWithTab("piranha_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.PIRANHA, -126, -48326583, basicItem()));
	}

}
