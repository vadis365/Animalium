package animalium.entities;

import java.util.ArrayList;
import java.util.List;

import animalium.configs.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPiranha extends EntityMob {
	private static final DataParameter<Boolean> IS_LEAPING = EntityDataManager.createKey(EntityPiranha.class, DataSerializers.BOOLEAN);

	public EntityPiranha(World world) {
		super(world);
		setSize(0.9F, 0.9F);
		moveHelper = new EntityPiranha.PiranhaMoveHelper(this);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityPiranha.AIPiranhaAttack(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.75D));
		tasks.addTask(2, new EntityAIWander(this, 0.75D, 80));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, true, null));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
		
		if (ConfigHandler.PIRANHA_ATTACK_MOBS)
			targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, true, null));
		if (ConfigHandler.PIRANHA_ATTACK_CREATURES)
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, true, null));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_LEAPING, false);
	}

	public boolean isLeaping() {
		return dataManager.get(IS_LEAPING);
	}

	private void setIsLeaping(boolean leaping) {
		dataManager.set(IS_LEAPING, leaping);
	}

	public boolean isGrounded() {
		return !isInWater() && getEntityWorld().isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY + 1), MathHelper.floor(posZ))) && getEntityWorld().getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 1), MathHelper.floor(posZ))).getBlock().isCollidable();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
    protected PathNavigate createNavigator(World world){
        return new PathNavigateSwimmer(this, world);
    }

	@Override
    protected boolean isValidLightLevel() {
        return true;
    }

	@Override
    public boolean isNotColliding() {
		 return this.getEntityWorld().checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.getEntityWorld().getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

	@Override
    public boolean getCanSpawnHere() {
		if(isDimBlacklisted(dimension))
			return false;
		return (posY > 45.0D && posY <= 80) && super.getCanSpawnHere();
    }

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	private Boolean isDimBlacklisted(int dimensionIn) {
		List<Integer> dimBlackList = new ArrayList<Integer>();
		for (int dims = 0; dims < ConfigHandler.PIRANHA_BLACKLISTED_DIMS.length; dims++) {
			String dimEntry = ConfigHandler.PIRANHA_BLACKLISTED_DIMS[dims].trim();
			dimBlackList.add(Integer.valueOf(dimEntry));
		}
		if(dimBlackList.contains(dimensionIn))
			return true;
		return false;
	}

	@Override
	   protected SoundEvent getAmbientSound() {
        return isInWater() ? SoundEvents.ENTITY_GENERIC_SWIM : SoundEvents.ENTITY_GUARDIAN_FLOP;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_GUARDIAN_HURT_LAND;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GHAST_DEATH;
	}
	
	@Override
	public void setDead() {
		super.setDead();
		playSound(SoundEvents.ENTITY_GHAST_DEATH, 1F, 2F);
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
		playSound(isInWater() ? SoundEvents.ENTITY_GENERIC_SWIM : SoundEvents.ENTITY_GUARDIAN_FLOP, 0.5F, 2F);
    }

	@Override
    public float getBlockPathWeight(BlockPos pos) {
        return getEntityWorld().getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + getEntityWorld().getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

	@Override
	public void onLivingUpdate() {
		if (getEntityWorld().isRemote) {
			if (isInWater()) {
				Vec3d vec3d = getLook(0.0F);
				for (int i = 0; i < 2; ++i)
					getEntityWorld().spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX + (rand.nextDouble() - 0.5D) * (double) width - vec3d.x * 1.5D, posY + rand.nextDouble() * (double) height - vec3d.y * 1.5D, posZ + (rand.nextDouble() - 0.5D) * (double) width - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		if (inWater) {
			setAir(300);
		} else if (onGround) {
			motionY += 0.5D;
			motionX += (double) ((rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
			motionZ += (double) ((rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
			rotationYaw = rand.nextFloat() * 360.0F;
			if(isLeaping())
				setIsLeaping(false);
			onGround = false;
			isAirBorne = true;
			if(getEntityWorld().getWorldTime()%5==0)
				getEntityWorld().playSound((EntityPlayer) null, posX, posY, posZ, SoundEvents.ENTITY_GUARDIAN_FLOP, SoundCategory.HOSTILE, 1F, 1F);
				this.damageEntity(DamageSource.DROWN, 0.5F);
		}

		super.onLivingUpdate();
	}
	
	@Override
	public void travel(float strafe, float up, float forward) {
		if (isServerWorld()) {
			if (isInWater()) {
				moveRelative(strafe, up,  forward, 0.1F);
				move(MoverType.SELF, motionX, motionY, motionZ);
				motionX *= 0.8999999761581421D;
				motionY *= 0.8999999761581421D;
				motionZ *= 0.8999999761581421D;

				if (getAttackTarget() == null) {
					motionY -= 0.005D;
				}
			} else {
				super.travel(strafe, up, forward);
			}
		} else {
			super.travel(strafe, up, forward);
		}
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getEntityWorld().rand.nextInt(5) == 0) {
			ItemStack stack = new ItemStack(Items.FISH);
			if (this.isBurning())
				stack = new ItemStack(Items.COOKED_FISH);
			entityDropItem(stack, 1.0F);
		}
	}

	@Override
	public void onUpdate() {
		if(!getEntityWorld().isRemote) {
		if(getAttackTarget() != null && !getEntityWorld().containsAnyLiquid(getAttackTarget().getEntityBoundingBox())) {
			Double distance = this.getPosition().getDistance((int) getAttackTarget().posX, (int) getAttackTarget().posY, (int) getAttackTarget().posZ);
			if (distance > 1.0F && distance < 6.0F) // && getAttackTarget().getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && getAttackTarget().getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && rand.nextInt(3) == 0)
				if (isInWater() && getEntityWorld().isAirBlock(new BlockPos((int) posX, (int) posY + 1, (int) posZ))) {
					if(!isLeaping()) {
						setIsLeaping(true);
						getEntityWorld().playSound((EntityPlayer) null, posX, posY, posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 1F, 2F);
					}
					double distanceX = getAttackTarget().posX - posX;
					double distanceZ = getAttackTarget().posZ - posZ;
					float distanceSqrRoot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);
					motionX = distanceX / distanceSqrRoot * 0.5D * 0.900000011920929D + motionX * 0.70000000298023224D;
					motionZ = distanceZ / distanceSqrRoot * 0.5D * 0.900000011920929D + motionZ * 0.70000000298023224D;
					motionY = 0.4D;
					}
			}
		}
		super.onUpdate();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityPiranha.class != entity;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity))
				return true;
		}
		return false;
	}

	@Override
	protected void collideWithNearbyEntities() {
		super.collideWithNearbyEntities();
		if (ConfigHandler.PIRANHA_DAMAGE_BOATS) {
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

			if (!list.isEmpty() && this.rand.nextInt(ConfigHandler.PIRANHA_DAMAGE_BOATS_CHANCE) == 0) {
				for (int k = 0; k < list.size(); ++k) {
					if (((Entity) list.get(k) instanceof EntityBoat)) {
						attackEntityAsMob(list.get(k));
					}
				}
			}
		}
	}

	//AIs
	
	static class PiranhaMoveHelper extends EntityMoveHelper {
		private final EntityPiranha piranha;

		public PiranhaMoveHelper(EntityPiranha piranha) {
			super(piranha);
			this.piranha = piranha;
		}

		public void onUpdateMoveHelper() {
			if (action == EntityMoveHelper.Action.MOVE_TO && !piranha.getNavigator().noPath()) {
				double d0 = posX - piranha.posX;
				double d1 = posY - piranha.posY;
				double d2 = posZ - piranha.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				d3 = (double) MathHelper.sqrt(d3);
				d1 = d1 / d3;
				float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
				piranha.rotationYaw = limitAngle(piranha.rotationYaw, f, 90.0F);
				piranha.renderYawOffset = piranha.rotationYaw;
				float f1 = (float) (speed * piranha.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				piranha.setAIMoveSpeed(piranha.getAIMoveSpeed() + (f1 - piranha.getAIMoveSpeed()) * 0.125F);
				double d4 = Math.sin((double) (piranha.ticksExisted + piranha.getEntityId()) * 0.5D) * 0.05D;
				double d5 = Math.cos((double) (piranha.rotationYaw * 0.017453292F));
				double d6 = Math.sin((double) (piranha.rotationYaw * 0.017453292F));
				piranha.motionX += d4 * d5;
				piranha.motionZ += d4 * d6;
				d4 = Math.sin((double) (piranha.ticksExisted + piranha.getEntityId()) * 0.75D) * 0.05D;
				piranha.motionY += d4 * (d6 + d5) * 0.25D;
				piranha.motionY += (double) piranha.getAIMoveSpeed() * d1 * 0.1D;
				EntityLookHelper entitylookhelper = piranha.getLookHelper();
				double d7 = piranha.posX + d0 / d3 * 2.0D;
				double d8 = (double) piranha.getEyeHeight() + piranha.posY + d1 / d3;
				double d9 = piranha.posZ + d2 / d3 * 2.0D;
				double d10 = entitylookhelper.getLookPosX();
				double d11 = entitylookhelper.getLookPosY();
				double d12 = entitylookhelper.getLookPosZ();

				if (!entitylookhelper.getIsLooking()) {
					d10 = d7;
					d11 = d8;
					d12 = d9;
				}

				piranha.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
			} else {
				piranha.setAIMoveSpeed(0.0F);
			}
		}
	}
	
	static class AIPiranhaAttack extends EntityAIAttackMelee {
		
		public AIPiranhaAttack(EntityPiranha piranha) {
			super(piranha, 0.75D, true);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (0.5F + attackTarget.width);
		}
	}
}