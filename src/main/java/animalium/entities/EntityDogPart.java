package animalium.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDogPart extends Entity {
	/** The dog entity this dog part belongs to */
	public IEntityMultiPartDog entityDogObj;
	public String partName;

	public EntityDogPart(IEntityMultiPartDog parent, String partName, float base, float sizeHeight) {
		super(parent.getWorld());
		setSize(base, sizeHeight);
		entityDogObj = parent;
		this.partName = partName;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return getEntityBoundingBox();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return isEntityInvulnerable(source) ? false : entityDogObj.attackEntityFromPart(this, source, amount);
	}

	@Override
	public boolean isEntityEqual(Entity entityIn) {
		return this == entityIn || entityDogObj == entityIn;
	}
}
