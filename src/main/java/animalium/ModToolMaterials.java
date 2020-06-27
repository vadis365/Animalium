package animalium;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum ModToolMaterials implements IItemTier {
    TOOL_BEAR_CLAW_PAXEL(2, 1079, 8.0F, 4.0F, 14, () -> Ingredient.fromItems(ModItems.BEAR_CLAW));

    private final int harvestLevel;
    private final int maximumUse;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    ModToolMaterials(int level, int maxUse, float efficiency, float attack, int enchant, Supplier<Ingredient> ingredient) {
        harvestLevel = level;
        maximumUse = maxUse;
        toolEfficiency = efficiency;
        attackDamage = attack;
        enchantability = enchant;
        repairMaterial = ingredient;
    }

    @Override
    public int getMaxUses() {
        return maximumUse;
    }

    @Override
    public float getEfficiency() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
