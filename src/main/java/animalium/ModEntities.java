package animalium;

import animalium.entities.EntityBear;
import animalium.entities.EntityNeutralBear;
import animalium.entities.EntityWildDog;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

//	public static EntityType<EntityPirahna> PIRANHA;
	public static EntityType<EntityWildDog> WILD_DOG;
	public static EntityType<EntityBear> BEAR;
	public static EntityType<EntityNeutralBear> BEAR_TAMED;
//	public static EntityType<EntityRat> RAT;

	public static void init() {
		
		BEAR = EntityType.Builder.create(EntityBear::new, EntityClassification.MONSTER).size(2F, 2F).build(getEntityResource("bear").toString());
		EntitySpawnPlacementRegistry.register(BEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityBear::canSpawnHere);
		//PIRANHA = EntityType.Builder.create(EntityPiranha::new, EntityClassification.MONSTER).size(0.9F, 0.9F).build(getEntityResource("piranha").toString());
		WILD_DOG = EntityType.Builder.create(EntityWildDog::new, EntityClassification.MONSTER).size(0.9F, 1.2F).build(getEntityResource("wild_dog").toString());
		EntitySpawnPlacementRegistry.register(WILD_DOG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityWildDog::canSpawnHere);
		BEAR_TAMED = EntityType.Builder.create(EntityNeutralBear::new, EntityClassification.MONSTER).size(2F, 2F).build(getEntityResource("bear_tamed").toString());
		//RAT = EntityType.Builder.create(EntityRat::new, EntityClassification.MONSTER).size(0.9F, 0.9F).build(getEntityResource("rat").toString());
		

	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
		init();
		final IForgeRegistry<EntityType<?>> registry = e.getRegistry();
	        registry.register(BEAR.setRegistryName(Reference.MOD_ID, "bear"));
	       registry.register(BEAR_TAMED.setRegistryName(Reference.MOD_ID, "bear_tamed"));
	       registry.register(WILD_DOG.setRegistryName(Reference.MOD_ID, "wild_dog"));
	    //    registry.register(RAT.setRegistryName(Reference.MOD_ID, "rat"));
	    //    registry.register(PIRANHA.setRegistryName(Reference.MOD_ID, "piranha"));
	    }

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MOD_ID, entityName);
	}
}
