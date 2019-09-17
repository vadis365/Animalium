package animalium.configs;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();
	public Configuration CONFIG;
	public static int PIRANHA_MIN_SPAWN_SIZE;
	public static int PIRANHA_MAX_SPAWN_SIZE;
	public static int PIRANHA_SPAWN_PROBABILITY;
	
	public static int WILD_DOG_MIN_SPAWN_SIZE;
	public static int WILD_DOG_MAX_SPAWN_SIZE;
	public static int WILD_DOG_SPAWN_PROBABILITY;
	public static int WILD_DOG_SPAWN_Y_HEIGHT;

	public static int BEAR_MIN_SPAWN_SIZE;
	public static int BEAR_MAX_SPAWN_SIZE;
	public static int BEAR_SPAWN_PROBABILITY;
	public static int BEAR_SPAWN_Y_HEIGHT;
	public static boolean BEAR_SPAWN_ONLY_AT_DAY;

	public static int RAT_MIN_SPAWN_SIZE;
	public static int RAT_MAX_SPAWN_SIZE;
	public static int RAT_SPAWN_PROBABILITY;
	public static int RAT_SPAWN_Y_HEIGHT;

	public static boolean PIRANHA_ATTACK_MOBS;
	public static boolean PIRANHA_ATTACK_CREATURES;

	public static boolean WILD_DOG_ATTACK_MOBS;
	public static boolean WILD_DOG_ATTACK_CREATURES;
	public static boolean WILD_DOG_SHOW_HITBOX;

	public static boolean BEAR_ATTACK_MOBS;
	public static boolean BEAR_ATTACK_CREATURES;

	public static boolean RAT_ATTACK_MOBS;
	public static boolean RAT_ATTACK_CREATURES;

	public final String[] usedCategories = { "Animal Spawn Settings", "Animal Attack Settings", "Wild Dog Hit Box Complaints!" };

	public void loadConfig(FMLPreInitializationEvent event) {
		CONFIG = new Configuration(event.getSuggestedConfigurationFile());
		CONFIG.load();
		syncConfigs();
	}

	private void syncConfigs() {
		PIRANHA_MIN_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Piranha Spawn Group Minimum Size", 1).getInt(1);
		PIRANHA_MAX_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Piranha Spawn Group Maximum Size", 3).getInt(3);
		PIRANHA_SPAWN_PROBABILITY = CONFIG.get("Animal Spawn Settings", "Piranha Spawn Chance Probability", 10).getInt(10);

		WILD_DOG_MIN_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Wild Dog Spawn Group Minimum Size", 1).getInt(1);
		WILD_DOG_MAX_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Wild Dog Spawn Group Maximum Size", 3).getInt(3);
		WILD_DOG_SPAWN_PROBABILITY = CONFIG.get("Animal Spawn Settings", "Wild Dog Spawn Chance Probability", 10).getInt(10);
		WILD_DOG_SPAWN_Y_HEIGHT = CONFIG.get("Animal Spawn Settings", "Wild Dog Max Y Spawn Height", 256).getInt(256);

		BEAR_MIN_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Bear Spawn Group Minimum Size", 1).getInt(1);
		BEAR_MAX_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Bear Spawn Group Maximum Size", 1).getInt(1);
		BEAR_SPAWN_PROBABILITY = CONFIG.get("Animal Spawn Settings", "Bear Spawn Chance Probability", 1).getInt(1);
		BEAR_SPAWN_Y_HEIGHT = CONFIG.get("Animal Spawn Settings", "Bear Max Y Spawn Height", 256).getInt(256);
		BEAR_SPAWN_ONLY_AT_DAY = CONFIG.get("Animal Spawn Settings", "Bears Spawn Only During Day", true).getBoolean(true);

		RAT_MIN_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Rat Spawn Group Minimum Size", 1).getInt(1);
		RAT_MAX_SPAWN_SIZE = CONFIG.get("Animal Spawn Settings", "Rat Spawn Group Maximum Size", 3).getInt(3);
		RAT_SPAWN_PROBABILITY = CONFIG.get("Animal Spawn Settings", "Rat Spawn Chance Probability", 10).getInt(10);
		RAT_SPAWN_Y_HEIGHT = CONFIG.get("Animal Spawn Settings", "Rat Max Y Spawn Height", 256).getInt(256);

		PIRANHA_ATTACK_MOBS = CONFIG.get("Animal Attack Settings", "Piranhas Attack Monsters", false).getBoolean(false);
		PIRANHA_ATTACK_CREATURES = CONFIG.get("Animal Attack Settings", "Piranhas Attack Everything (Except Piranhas)", false).getBoolean(false);

		WILD_DOG_ATTACK_MOBS = CONFIG.get("Animal Attack Settings", "Wild Dogs Attack Monsters", false).getBoolean(false);
		WILD_DOG_ATTACK_CREATURES = CONFIG.get("Animal Attack Settings", "Wild Dogs Attack Everything (Except Wild Dogs)", false).getBoolean(false);
		WILD_DOG_SHOW_HITBOX = CONFIG.get("Wild Dog Hit Box Complaints!", "Render Dog Hitbox for Scrub Combat Practice", false).getBoolean(false);

		BEAR_ATTACK_MOBS = CONFIG.get("Animal Attack Settings", "Bears Attack Monsters", false).getBoolean(false);
		BEAR_ATTACK_CREATURES = CONFIG.get("Animal Attack Settings", "Bears Attack Everything (Except Bears)", false).getBoolean(false);

		RAT_ATTACK_MOBS = CONFIG.get("Animal Attack Settings", "Rats Attack Monsters", false).getBoolean(false);
		RAT_ATTACK_CREATURES = CONFIG.get("Animal Attack Settings", "Rats Attack Everything (Except Rats)", false).getBoolean(false);

		if (CONFIG.hasChanged())
			CONFIG.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals("animalium"))
			syncConfigs();
	}
}