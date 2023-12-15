package animalium.init;

import java.util.function.Supplier;

import animalium.common.entities.EntityBear;
import animalium.common.entities.EntityNeutralBear;
import animalium.common.entities.EntityPiranha;
import animalium.common.entities.EntityRat;
import animalium.common.entities.EntityWildDog;
import animalium.utils.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Util.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Util.MOD_ID);

	public static RegistryObject< EntityType<EntityPiranha>> PIRANHA = ENTITIES.register("piranha", () -> EntityType.Builder.of(EntityPiranha::new, MobCategory.MONSTER).sized(0.9F, 0.9F).build(Util.MOD_ID + "piranha"));
	public static RegistryObject<EntityType<EntityWildDog>> WILD_DOG = ENTITIES.register("wild_dog", () -> EntityType.Builder.of(EntityWildDog::new, MobCategory.MONSTER).sized(0.9F, 1.2F).build(Util.MOD_ID + "wild_dog"));
	public static RegistryObject< EntityType<EntityBear>> BEAR = ENTITIES.register("bear", () -> EntityType.Builder.of(EntityBear::new, MobCategory.MONSTER).sized(2F, 2F).build(Util.MOD_ID + "bear"));
	public static RegistryObject< EntityType<EntityNeutralBear>> BEAR_TAMED = ENTITIES.register("bear_tamed", () -> EntityType.Builder.of(EntityNeutralBear::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(3).setUpdateInterval(1).sized(2F, 2F).build(Util.MOD_ID + "bear_tamed"));
	public static RegistryObject< EntityType<EntityRat>> RAT = ENTITIES.register("rat", () -> EntityType.Builder.of(EntityRat::new, MobCategory.MONSTER).sized(0.9F, 0.9F).build(Util.MOD_ID + "rat"));

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		registerPlacement(event, BEAR, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBear::canSpawnHere);
		registerPlacement(event, PIRANHA, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityPiranha::canSpawnHere);
		registerPlacement(event, WILD_DOG, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityWildDog::canSpawnHere);
		registerPlacement(event, RAT, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRat::canSpawnHere);
	}

	private static <E extends Mob, T extends EntityType<E>> void registerPlacement(SpawnPlacementRegisterEvent event, Supplier<T> entity, SpawnPlacements.Type type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<E> predicate) {
		event.register(entity.get(), type, heightmap, predicate, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}

	@SubscribeEvent
	public static void initializeAttributes(EntityAttributeCreationEvent event) {
		event.put(BEAR.get(), EntityBear.createAttributes().build());
		event.put(BEAR_TAMED.get(), EntityBear.createAttributes().build());
		event.put(PIRANHA.get(), EntityPiranha.createAttributes().build());
		event.put(WILD_DOG.get(), EntityWildDog.createAttributes().build());
		event.put(RAT.get(), EntityRat.createAttributes().build());
	}

}
