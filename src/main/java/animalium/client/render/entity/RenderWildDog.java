package animalium.client.render.entity;

import animalium.client.model.ModelWildDog;
import animalium.configs.ConfigHandler;
import animalium.entities.EntityDogPart;
import animalium.entities.EntityWildDog;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWildDog extends RenderLiving<EntityWildDog> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/wild_dog.png");

	public RenderWildDog(RenderManager renderManager) {
        super(renderManager, new ModelWildDog(), 0.5F);
    }

	@Override
	public void doRender(EntityWildDog entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender(entity, x, y, z, yaw, partialTicks);
		if (ConfigHandler.WILD_DOG_SHOW_HITBOX) {
			// Main Entity Hitbox
			renderDebugBoundingBox(entity, x, y, z, yaw, partialTicks, 0, 0, 0);
			
			// Child Entity Hitbox
			EntityDogPart headPart = (EntityDogPart) entity.dogPartHead;
			renderDebugBoundingBox(headPart, x, y, z, yaw, partialTicks, headPart.posX - entity.posX, headPart.posY - entity.posY, headPart.posZ - entity.posZ);
		}
	}

	private void renderDebugBoundingBox(Entity entity, double x, double y, double z, float yaw, float partialTicks, double xOff, double yOff, double zOff) {
		GlStateManager.depthMask(false);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - entity.posX + x + xOff, axisalignedbb.minY - entity.posY + y + yOff, axisalignedbb.minZ - entity.posZ + z + zOff, axisalignedbb.maxX - entity.posX + x + xOff, axisalignedbb.maxY - entity.posY + y + yOff, axisalignedbb.maxZ - entity.posZ + z + zOff);
		RenderGlobal.drawSelectionBoundingBox(axisalignedbb1, 1F, 1F, 1F, 1F);
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}

	@Override
	protected void preRenderCallback(EntityWildDog entity, float partialTickTime) {
		GlStateManager.translate(0F, -0.06F, 0F);
		GlStateManager.rotate(180F, 0F, 1F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWildDog entity) {
		return TEXTURE;
	}
}
