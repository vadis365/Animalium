package animalium.client;

import animalium.client.model.ModelBear;
import animalium.client.model.ModelPiranha;
import animalium.client.model.ModelRat;
import animalium.client.model.ModelWildDog;
import animalium.client.render.entity.RenderBear;
import animalium.client.render.entity.RenderPiranha;
import animalium.client.render.entity.RenderRat;
import animalium.client.render.entity.RenderWildDog;
import animalium.init.ModEntities;
import animalium.utils.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    public static ModelLayerLocation BEAR = new ModelLayerLocation(
            new ResourceLocation(Util.MOD_ID + "bear"), "bear");
    public static ModelLayerLocation WILD_DOG = new ModelLayerLocation(
            new ResourceLocation(Util.MOD_ID + "wild_dog"), "wild_dog");
    public static ModelLayerLocation RAT = new ModelLayerLocation(
            new ResourceLocation(Util.MOD_ID + "rat"), "rat");
    public static ModelLayerLocation PIRANHA = new ModelLayerLocation(
            new ResourceLocation(Util.MOD_ID + "piranha"), "piranha");


    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClientEvents.BEAR, ModelBear::createBodyLayer);
        event.registerLayerDefinition(ClientEvents.WILD_DOG, ModelWildDog::createBodyLayer);
        event.registerLayerDefinition(ClientEvents.RAT, ModelRat::createBodyLayer);
        event.registerLayerDefinition(ClientEvents.PIRANHA, ModelPiranha::createBodyLayer);

    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BEAR.get(), RenderBear::new);
        event.registerEntityRenderer(ModEntities.BEAR_TAMED.get(), RenderBear::new);
        event.registerEntityRenderer(ModEntities.WILD_DOG.get(), RenderWildDog::new);
        event.registerEntityRenderer(ModEntities.RAT.get(), RenderRat::new);
        event.registerEntityRenderer(ModEntities.PIRANHA.get(), RenderPiranha::new);
    }
}
