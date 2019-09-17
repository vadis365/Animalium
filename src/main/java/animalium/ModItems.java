package animalium;

import java.util.ArrayList;
import java.util.List;

import animalium.entities.EntityBear;
import animalium.entities.EntityNeutralBear;
import animalium.items.ItemBearClawPaxel;
import animalium.items.ItemDogPeltBoots;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	public static Item WILD_DOG_PELT, BEAR_MEAT, BEAR_MEAT_COOKED, BEAR_CLAW, BEAR_CLAW_PAXEL, RAT_MEAT, RAT_MEAT_COOKED, DOG_PELT_BOOTS;
	
	public static void init() {
	WILD_DOG_PELT = new Item().setCreativeTab(Animalium.TAB);
	WILD_DOG_PELT.setRegistryName("animalium", "wild_dog_pelt").setUnlocalizedName("animalium.wild_dog_pelt");

	BEAR_MEAT = new ItemFood(3, 0.3F, true).setCreativeTab(Animalium.TAB);
	BEAR_MEAT.setRegistryName("animalium", "bear_meat").setUnlocalizedName("animalium.bear_meat");

	BEAR_MEAT_COOKED = new ItemFood(8, 0.8F, true).setCreativeTab(Animalium.TAB);
	BEAR_MEAT_COOKED.setRegistryName("animalium", "bear_meat_cooked").setUnlocalizedName("animalium.bear_meat_cooked");

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
				}
				stack.shrink(1);
				return true;
			} else {
				return false;
			}
		}
		
	};
	((ItemFood) RAT_MEAT).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.3F).setCreativeTab(Animalium.TAB);
	RAT_MEAT.setRegistryName("animalium", "rat_meat").setUnlocalizedName("animalium.rat_meat");

	RAT_MEAT_COOKED = new ItemFood(4, 0.6F, true).setCreativeTab(Animalium.TAB);
	RAT_MEAT_COOKED.setRegistryName("animalium", "rat_meat_cooked").setUnlocalizedName("animalium.rat_meat_cooked");

	BEAR_CLAW = new Item().setCreativeTab(Animalium.TAB);
	BEAR_CLAW.setRegistryName("animalium", "bear_claw").setUnlocalizedName("animalium.bear_claw");

	BEAR_CLAW_PAXEL = new ItemBearClawPaxel(Animalium.TOOL_BEAR_CLAW_PAXEL);
	BEAR_CLAW_PAXEL.setRegistryName("animalium", "bear_claw_paxel").setUnlocalizedName("animalium.bear_claw_paxel");

	DOG_PELT_BOOTS = new ItemDogPeltBoots();
	DOG_PELT_BOOTS.setRegistryName("animalium", "dog_boots").setUnlocalizedName("animalium.dog_boots");
	}
	
	@Mod.EventBusSubscriber(modid = "animalium")
	public static class RegistrationHandlerItems {
		public static final List<Item> ITEMS = new ArrayList<Item>();

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
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
