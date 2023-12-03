package animalium.common.items;

import animalium.common.entities.EntityBear;
import animalium.common.entities.EntityNeutralBear;
import animalium.init.ModEntities;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemRatMeat extends Item {
    public ItemRatMeat(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof EntityBear) {
            if (!player.level().isClientSide()) {
                EntityBear bear = (EntityBear) target;
                EntityNeutralBear tamedBear = ModEntities.BEAR_TAMED.get().create(player.level());
                tamedBear.copyPosition(bear);
                bear.remove(Entity.RemovalReason.DISCARDED);
                player.level().addFreshEntity(tamedBear);
            }
            stack.shrink(1);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
