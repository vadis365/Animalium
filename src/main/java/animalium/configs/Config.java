package animalium.configs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import animalium.utils.Util;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Util.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final String CATEGORY_SPAWNS = "Animal Spawn Settings";
    public static final String CATEGORY_ATTACKS = "Animal Attack Settings";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

	public static ForgeConfigSpec.IntValue WILD_DOG_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue WILD_DOG_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> WILD_DOG_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.IntValue BEAR_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue BEAR_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue BEAR_SPAWN_ONLY_AT_DAY;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> BEAR_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.IntValue RAT_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue RAT_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> RAT_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.IntValue PIRANHA_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue PIRANHA_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> PIRANHA_BLACKLISTED_DIMS;

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

	static List<String> blacklistedDimsPiranha = new ArrayList<>();
	static List<String> blacklistedDimsBear = new ArrayList<>();
	static List<String> blacklistedDimsWildDog = new ArrayList<>();
	static List<String> blacklistedDimsRat = new ArrayList<>();

    static {
        COMMON_BUILDER.comment("Animal Spawn Settings").push(CATEGORY_SPAWNS);
        PIRANHA_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Piranha Min Y Spawn Height").defineInRange("piranha_min_y", -64, -2032, 2032);
        PIRANHA_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Piranha Max Y Spawn Height").defineInRange("piranha_max_y", 319, -2032, 2032);
		PIRANHA_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Piranha Spawn Dimension Blacklist").defineList("piranha_dimensions", blacklistedDimsPiranha, p-> isPointless());

		WILD_DOG_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Wild Dog Min Y Spawn Height").defineInRange("wild_dog_min_y", -64, -2032, 2032);
		WILD_DOG_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Wild Dog Max Y Spawn Height").defineInRange("wild_dog_max_y", 319, -2032, 2032);
		WILD_DOG_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Wild Dog Spawn Dimension Blacklist").defineList("wild_dog_dimensions", blacklistedDimsWildDog, p-> isPointless());

		BEAR_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Bear Min Y Spawn Height").defineInRange("bear_min_y", -64, -2032, 2032);
		BEAR_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Bear Max Y Spawn Height").defineInRange("bear_max_y", 319, -2032, 2032);
		BEAR_SPAWN_ONLY_AT_DAY = COMMON_BUILDER.comment("Bears Spawn Only During Day").define("bear_only_day_spawns", true);
		BEAR_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Bear Spawn Dimension Blacklist").defineList("bear_dimensions", blacklistedDimsBear, p-> isPointless());

		RAT_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Rat Min Y Spawn Height").defineInRange("rat_min_y", -64, -2032, 2032);
		RAT_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Rat Max Y Spawn Height").defineInRange("rat_max_y", 319, -2032, 2032);
		RAT_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Rat Spawn Dimension Blacklist").defineList("rat_dimensions", blacklistedDimsRat, p-> isPointless());
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Animal Attack Settings").push(CATEGORY_ATTACKS);
        PIRANHA_ATTACK_MOBS = COMMON_BUILDER.comment("Piranhas Attack Monsters").define("piranha_should_attack_monsters", false);
		PIRANHA_ATTACK_CREATURES = COMMON_BUILDER.comment("Piranhas Attack Everything (Except Piranhas)").define("piranha_should_attack_all", false);
		PIRANHA_DAMAGE_BOATS = COMMON_BUILDER.comment("Piranhas Damage Boats on Colliding").define("piranha_should_damage_boats", true);
		PIRANHA_DAMAGE_BOATS_CHANCE = COMMON_BUILDER.comment("Piranhas Damage Boats Probablility").defineInRange("piranha_chance_to_damage_boats", 30, 0, Integer.MAX_VALUE);

		WILD_DOG_ATTACK_MOBS = COMMON_BUILDER.comment("Wild Dogs Attack Monsters").define("wild_dog_should_attack_monsters", false);
		WILD_DOG_ATTACK_CREATURES = COMMON_BUILDER.comment("Wild Dogs Attack Everything (Except Wild Dogs)").define("wild_dog_should_attack_all", false);

		BEAR_ATTACK_MOBS = COMMON_BUILDER.comment("Bears Attack Monsters").define("bear_should_attack_monsters", false);
		BEAR_ATTACK_CREATURES = COMMON_BUILDER.comment("Bears Attack Everything (Except Bears)").define("bear_should_attack_all", false);

		RAT_ATTACK_MOBS = COMMON_BUILDER.comment("Rats Attack Monsters").define("rat_should_attack_monsters", false);
		RAT_ATTACK_CREATURES = COMMON_BUILDER.comment("Rats Attack Everything (Except Rats)").define("rat_should_attack_all", false);
		RAT_STEALS_ITEMS = COMMON_BUILDER.comment("Rats Steal Items").define("rat_should_steal", true);
		RAT_STEALS_PROBABILITY = COMMON_BUILDER.comment("Rat Item Stealing Probability Bigger Numbers = Less Chance").defineInRange("rat_stealing_chance", 10, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

	public static boolean isSafeInteger(int checkNum) {
		return checkNum >= Integer.MIN_VALUE && checkNum <= Integer.MAX_VALUE;
	}

	public static boolean isPointless() {
		return true;
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
    public static void onLoad(final ModConfigEvent.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {
    }
}

