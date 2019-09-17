package animalium.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IEntityMultiPartDog
{
    World getWorld();

    boolean attackEntityFromPart(EntityDogPart dogPart, DamageSource source, float damage);

}