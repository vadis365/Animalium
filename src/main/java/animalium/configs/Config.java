package animalium.configs;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import animalium.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final String CATEGORY_SPAWNS = "Animal Spawn Settings";
    public static final String CATEGORY_ATTACKS = "Animal Attack Settings";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;
    
    public static ForgeConfigSpec.IntValue PIRANHA_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue PIRANHA_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue PIRANHA_SPAWN_PROBABILITY;
//	public static ForgeConfigSpec.ConfigValue<String[]> PIRANHA_BLACKLISTED_DIMS;
	
	public static ForgeConfigSpec.IntValue WILD_DOG_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue WILD_DOG_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue WILD_DOG_SPAWN_PROBABILITY;
	public static ForgeConfigSpec.IntValue WILD_DOG_SPAWN_Y_HEIGHT;
//	public static ForgeConfigSpec.ConfigValue<String[]> WILD_DOG_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.IntValue BEAR_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue BEAR_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue BEAR_SPAWN_PROBABILITY;
	public static ForgeConfigSpec.IntValue BEAR_SPAWN_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue BEAR_SPAWN_ONLY_AT_DAY;
//	public static ForgeConfigSpec.ConfigValue<String[]> BEAR_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.IntValue RAT_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue RAT_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue RAT_SPAWN_PROBABILITY;
	public static ForgeConfigSpec.IntValue RAT_SPAWN_Y_HEIGHT;
//	public static ForgeConfigSpec.ConfigValue<String[]> RAT_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.BooleanValue PIRANHA_ATTACK_MOBS;
	public static ForgeConfigSpec.BooleanValue PIRANHA_ATTACK_CREATURES;
	public static ForgeConfigSpec.BooleanValue PIRANHA_DAMAGE_BOATS;
	public static ForgeConfigSpec.IntValue PIRANHA_DAMAGE_BOATS_CHANCE;

	public static ForgeConfigSpec.BooleanValue WILD_DOG_ATTACK_MOBS;
	public static ForgeConfigSpec.BooleanValue WILD_DOG_ATTACK_CREATURES;

	public static ForgeConfigSpec.BooleanValue BEAR_ATTACK_MOBS;
	public static ForgeConfigSpec.BooleanValue BEAR_ATTACK_CREATURES;

	public static ForgeConfigSpec.BooleanValue RAT_ATTACK_MOBS;
	public static ForgeConfigSpec.BooleanValue RAT_ATTACK_CREATURES;
	public static ForgeConfigSpec.BooleanValue RAT_STEALS_ITEMS;
	public static ForgeConfigSpec.IntValue RAT_STEALS_PROBABILITY;


    static {

        COMMON_BUILDER.comment("Animal Spawn Settings").push(CATEGORY_SPAWNS);
        PIRANHA_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Piranha Spawn Group Minimum Size").defineInRange("min_spawn_size", 1, 1, Integer.MAX_VALUE);
		PIRANHA_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Piranha Spawn Group Maximum Size").defineInRange("max_spawn_size", 3, 1, Integer.MAX_VALUE);
		PIRANHA_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Piranha Spawn Chance Probability").defineInRange("chance", 10, 1, Integer.MAX_VALUE);
	//	PIRANHA_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Piranha Spawn Dimension Blacklist").define("dimensions", new String[] { "1","-1"});

		WILD_DOG_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Wild Dog Spawn Group Minimum Size").defineInRange("min_spawn_size", 1, 1, Integer.MAX_VALUE);
		WILD_DOG_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Wild Dog Spawn Group Maximum Size").defineInRange("max_spawn_size", 3, 1, Integer.MAX_VALUE);
		WILD_DOG_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Wild Dog Spawn Chance Probability").defineInRange("chance", 10, 1, Integer.MAX_VALUE);
		WILD_DOG_SPAWN_Y_HEIGHT = COMMON_BUILDER.comment("Wild Dog Max Y Spawn Height").defineInRange("max_y", 256, 1, 256);
	//	WILD_DOG_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Wild Dog Spawn Dimension Blacklist").define("dimensions", new String[] { "1","-1"});

		BEAR_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Bear Spawn Group Minimum Size").defineInRange("min_spawn_size", 1, 1, Integer.MAX_VALUE);
		BEAR_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Bear Spawn Group Maximum Size").defineInRange("max_spawn_size", 1, 1, Integer.MAX_VALUE);
		BEAR_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Bear Spawn Chance Probability").defineInRange("chance", 1, 1, Integer.MAX_VALUE);
		BEAR_SPAWN_Y_HEIGHT = COMMON_BUILDER.comment("Bear Max Y Spawn Height").defineInRange("max_y", 256, 1, 256);
		BEAR_SPAWN_ONLY_AT_DAY = COMMON_BUILDER.comment("Bears Spawn Only During Day").define("only_day_spawns", true);
	//	BEAR_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Bear Spawn Dimension Blacklist").define("dimensions", new String[] { "1","-1"});

		RAT_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Rat Spawn Group Minimum Size").defineInRange("min_spawn_size", 1, 1, Integer.MAX_VALUE);
		RAT_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Rat Spawn Group Maximum Size").defineInRange("max_spawn_size", 3, 1, Integer.MAX_VALUE);
		RAT_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Rat Spawn Chance Probability").defineInRange("chance", 10, 1, Integer.MAX_VALUE);
		RAT_SPAWN_Y_HEIGHT = COMMON_BUILDER.comment("Rat Max Y Spawn Height").defineInRange("max_y", 256, 1, 256);
	//	RAT_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Rat Spawn Dimension Blacklist").define("dimensions", new String[] { "1","-1"});
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Animal Attack Settings").push(CATEGORY_ATTACKS);
        PIRANHA_ATTACK_MOBS = COMMON_BUILDER.comment("Piranhas Attack Monsters").define("should_attack_monsters", false);
		PIRANHA_ATTACK_CREATURES = COMMON_BUILDER.comment("Piranhas Attack Everything (Except Piranhas)").define("should_attack_all", false);
		PIRANHA_DAMAGE_BOATS = COMMON_BUILDER.comment("Piranhas Damage Boats on Colliding").define("should_damage_boats", true);
		PIRANHA_DAMAGE_BOATS_CHANCE = COMMON_BUILDER.comment("Piranhas Damage Boats Probablility").defineInRange("chance_to_damage_boats", 30, 0, Integer.MAX_VALUE);
		
		WILD_DOG_ATTACK_MOBS = COMMON_BUILDER.comment("Wild Dogs Attack Monsters").define("should_attack_monsters", false);
		WILD_DOG_ATTACK_CREATURES = COMMON_BUILDER.comment("Wild Dogs Attack Everything (Except Wild Dogs)").define("should_attack_all", false);

		BEAR_ATTACK_MOBS = COMMON_BUILDER.comment("Bears Attack Monsters").define("should_attack_monsters", false);
		BEAR_ATTACK_CREATURES = COMMON_BUILDER.comment("Bears Attack Everything (Except Bears)").define("should_attack_all", false);

		RAT_ATTACK_MOBS = COMMON_BUILDER.comment("Rats Attack Monsters").define("should_attack_monsters", false);
		RAT_ATTACK_CREATURES = COMMON_BUILDER.comment("Rats Attack Everything (Except Rats)").define("should_attack_all", false);
		RAT_STEALS_ITEMS = COMMON_BUILDER.comment("Rats Steal Items").define("should_steal", true);
		RAT_STEALS_PROBABILITY = COMMON_BUILDER.comment("Rat Item Stealing Probability Bigger Numbers = Less Chance").defineInRange("stealing_chance", 10, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
  
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {
    }
}

