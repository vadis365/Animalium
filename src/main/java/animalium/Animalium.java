package animalium;

import animalium.client.render.entity.RenderBear;
import animalium.client.render.entity.RenderPiranha;
import animalium.client.render.entity.RenderRat;
import animalium.client.render.entity.RenderWildDog;
import animalium.entities.EntityBear;
import animalium.entities.EntityPiranha;
import animalium.entities.EntityRat;
import animalium.entities.EntityWildDog;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

	public static ItemGroup TAB = new ItemGroup(Reference.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack (ModItems.BEAR_CLAW);
		}
	};

	private void setup(final FMLCommonSetupEvent event) {
	/*	

		ModRecipes.registerSmelting();

		PROXY.registerRenderers();
*/	

		MinecraftForge.EVENT_BUS.register(ModItems.DOG_PELT_BOOTS);
	}
	


	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, RenderBear :: new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWildDog.class, RenderWildDog :: new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPiranha.class, RenderPiranha::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, RenderRat::new);
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {}

	private void processIMC(final InterModProcessEvent event) {}
}
