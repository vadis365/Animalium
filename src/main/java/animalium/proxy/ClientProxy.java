package animalium.proxy;

import animalium.client.render.entity.RenderBear;
import animalium.client.render.entity.RenderPiranha;
import animalium.client.render.entity.RenderRat;
import animalium.client.render.entity.RenderWildDog;
import animalium.entities.EntityBear;
import animalium.entities.EntityPiranha;
import animalium.entities.EntityRat;
import animalium.entities.EntityWildDog;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPiranha.class, RenderPiranha::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWildDog.class, RenderWildDog::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, RenderBear::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, RenderRat::new);
	}
}