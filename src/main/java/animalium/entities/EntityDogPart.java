package animalium.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;

public class EntityDogPart extends Entity {

	public final EntityWildDog dog;
	public final String partName;
	private final EntitySize entitySize;

	public EntityDogPart(EntityWildDog dog, String partName, float sizeWidth, float sizeHeight) {
		super(dog.getType(), dog.getEntityWorld());
		this.entitySize = EntitySize.flexible(sizeWidth, sizeHeight);
		this.recalculateSize();
		this.dog = dog;
		this.partName = partName;
	}

	@Override
	protected void registerData() {
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return isInvulnerableTo(source) ? false : dog.attackEntityFromPart(this, source, amount);
	}

	@Override
	public boolean isEntityEqual(Entity entityIn) {
		return this == entityIn || dog == entityIn;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		throw new UnsupportedOperationException();
	}

	public EntitySize getSize(Pose poseIn) {
		return entitySize;
	}
}
