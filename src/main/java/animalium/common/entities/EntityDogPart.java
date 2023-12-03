package animalium.common.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;

public class EntityDogPart extends Entity {

	public final EntityWildDog dog;
	public final String partName;
	private final EntityDimensions entitySize;

	public EntityDogPart(EntityWildDog dog, String partName, float sizeWidth, float sizeHeight) {
		super(dog.getType(), dog.level());
		this.entitySize = EntityDimensions.fixed(sizeWidth, sizeHeight);
		this.refreshDimensions();
		this.dog = dog;
		this.partName = partName;
	}


	@Override
	protected void defineSynchedData() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {

	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return isInvulnerableTo(source) ? false : dog.hurtFromPart(this, source, amount);
	}

	@Override
	public boolean is(Entity entityIn) {
		return this == entityIn || dog == entityIn;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		throw new UnsupportedOperationException();
	}

	public EntityDimensions getSize(Pose poseIn) {
		return entitySize;
	}
}
