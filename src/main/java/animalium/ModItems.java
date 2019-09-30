package animalium;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import animalium.entities.EntityBear;
import animalium.entities.EntityNeutralBear;
import animalium.items.ItemBearClawPaxel;
import animalium.items.ItemDogPeltBoots;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	public static Item WILD_DOG_PELT;
	public static Item BEAR_MEAT;
	public static Item BEAR_MEAT_COOKED;
	public static Item BEAR_CLAW;
	public static Item BEAR_CLAW_PAXEL;
	public static Item RAT_MEAT;
	public static Item RAT_MEAT_COOKED;
	public static Item DOG_PELT_BOOTS;
	
	public static void init() {
	WILD_DOG_PELT = new Item(new Item.Properties().group(Animalium.TAB));
	WILD_DOG_PELT.setRegistryName(Reference.MOD_ID, "wild_dog_pelt");

	BEAR_MEAT = new Item((new Item.Properties().group(Animalium.TAB).food((new Food.Builder()).hunger(3).saturation(0.3F).meat().build())));
	BEAR_MEAT.setRegistryName(Reference.MOD_ID, "bear_meat");

	BEAR_MEAT_COOKED = new Item((new Item.Properties().group(Animalium.TAB).food((new Food.Builder()).hunger(8).saturation(0.8F).meat().build())));
	BEAR_MEAT_COOKED.setRegistryName(Reference.MOD_ID, "bear_meat_cooked");

	RAT_MEAT = new Item((new Item.Properties().group(Animalium.TAB).food((new Food.Builder()).hunger(2).saturation(0.3F).effect(new EffectInstance(Effects.HUNGER, 600, 0), 0.3F).meat().build()))) {
		@Override
		public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
			if (target instanceof EntityBear) {
				if (!player.getEntityWorld().isRemote) {
					EntityBear bear = (EntityBear) target;
					EntityNeutralBear tamedBear = ModEntities.BEAR_TAMED.create(player.getEntityWorld());
					tamedBear.copyLocationAndAnglesFrom(bear);
					bear.remove();
					player.getEntityWorld().addEntity(tamedBear);
				}
				stack.shrink(1);
				return true;
			} else {
				return false;
			}
		}
	};

	RAT_MEAT.setRegistryName(Reference.MOD_ID, "rat_meat");

	RAT_MEAT_COOKED = new Item((new Item.Properties().group(Animalium.TAB).food((new Food.Builder()).hunger(4).saturation(0.6F).meat().build())));
	RAT_MEAT_COOKED.setRegistryName(Reference.MOD_ID, "rat_meat_cooked");

	BEAR_CLAW = new Item(new Item.Properties().group(Animalium.TAB));
	BEAR_CLAW.setRegistryName(Reference.MOD_ID, "bear_claw");

	BEAR_CLAW_PAXEL = new ItemBearClawPaxel(ModToolMaterials.TOOL_BEAR_CLAW_PAXEL, p -> p.group(Animalium.TAB));//hmmmmm
	BEAR_CLAW_PAXEL.setRegistryName(Reference.MOD_ID, "bear_claw_paxel");

	DOG_PELT_BOOTS = new ItemDogPeltBoots(ModArmourMaterials.ARMOUR_DOG_PELT, EquipmentSlotType.FEET, new Item.Properties().group(Animalium.TAB).maxStackSize(1).maxDamage(1).defaultMaxDamage(ModArmourMaterials.ARMOUR_DOG_PELT.getDurability(EquipmentSlotType.FEET)));
	DOG_PELT_BOOTS.setRegistryName(Reference.MOD_ID, "dog_boots");
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistrationHandlerItems {
		public static final List<Item> ITEMS = new ArrayList<Item>();

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			init();
			final Item[] items = {
					WILD_DOG_PELT,
					BEAR_MEAT,
					BEAR_MEAT_COOKED,
					BEAR_CLAW,
					BEAR_CLAW_PAXEL,
					RAT_MEAT,
					RAT_MEAT_COOKED,
					DOG_PELT_BOOTS
					};
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);
				ITEMS.add(item);
			}
		}
	}
}
