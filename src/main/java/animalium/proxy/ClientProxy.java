package animalium.proxy;

import animalium.Animalium;
import animalium.client.render.entity.RenderBear;
import animalium.client.render.entity.RenderPiranha;
import animalium.client.render.entity.RenderRat;
import animalium.client.render.entity.RenderWildDog;
import animalium.entities.EntityBear;
import animalium.entities.EntityPiranha;
import animalium.entities.EntityRat;
import animalium.entities.EntityWildDog;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPiranha.class, RenderPiranha::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWildDog.class, RenderWildDog::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, RenderBear::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, RenderRat::new);

		ModelLoader.setCustomModelResourceLocation(Animalium.WILD_DOG_PELT, 0, new ModelResourceLocation("animalium:wild_dog_pelt", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.DOG_PELT_BOOTS, 0, new ModelResourceLocation("animalium:dog_boots", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.BEAR_MEAT, 0, new ModelResourceLocation("animalium:bear_meat", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.BEAR_MEAT_COOKED, 0, new ModelResourceLocation("animalium:bear_meat_cooked", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.BEAR_CLAW, 0, new ModelResourceLocation("animalium:bear_claw", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.BEAR_CLAW_PAXEL, 0, new ModelResourceLocation("animalium:bear_claw_paxel", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.RAT_MEAT, 0, new ModelResourceLocation("animalium:rat_meat", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Animalium.RAT_MEAT_COOKED, 0, new ModelResourceLocation("animalium:rat_meat_cooked", "inventory"));
	}
}