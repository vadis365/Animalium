package animalium.init;


import java.util.EnumMap;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmourMaterials implements ArmorMaterial {

    ARMOUR_DOG_PELT("wild_dog_pelt", 19, Util.make(new EnumMap<>(ArmorItem.Type.class), types -> {
        types.put(ArmorItem.Type.BOOTS, 2);
        types.put(ArmorItem.Type.LEGGINGS, 3);
        types.put(ArmorItem.Type.CHESTPLATE, 2);
        types.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 3.0F, () -> Ingredient.of(ModItems.WILD_DOG_PELT.get()));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), p_266653_ -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    private final String armorName;
    private final int durabilityFactor;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final Supplier<Ingredient> repairMaterial;

    ModArmourMaterials(String name, int durability, EnumMap<ArmorItem.Type, Integer> pTypes_, int enchant, SoundEvent sound, float toughness, Supplier<Ingredient> ingredient) {
        armorName = name;
        durabilityFactor = durability;
        protectionFunctionForType = pTypes_;
        enchantability = enchant;
        equipSound = sound;
        armorToughness = toughness;
        repairMaterial = ingredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type typeIn) {
        return HEALTH_FUNCTION_FOR_TYPE.get(typeIn) * durabilityFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type typeIn) {
        return protectionFunctionForType.get(typeIn);
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return armorName;
    }

    public float getToughness() {
        return armorToughness;
    }

	@Override
	public float getKnockbackResistance() {
		return 0F;
	}
}

