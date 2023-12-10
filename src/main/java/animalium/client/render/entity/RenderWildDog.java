package animalium.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import animalium.client.ClientEvents;
import animalium.client.model.ModelWildDog;
import animalium.common.entities.EntityWildDog;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWildDog extends MobRenderer<EntityWildDog, ModelWildDog<EntityWildDog>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/wild_dog.png");

	public RenderWildDog(EntityRendererProvider.Context context) {
        super(context, new ModelWildDog<>(context.bakeLayer(ClientEvents.WILD_DOG)), 0.5F);
    }

    @Override
    protected void scale(EntityWildDog entity, PoseStack matrix, float partialTickTime) {
        matrix.translate(0F, -0.06F, 0F); // translation
        matrix.mulPose(Axis.YP.rotationDegrees(180.0F)); //rotation
    }

    /*
        @Override
        public void doRender(EntityWildDog entity, double x, double y, double z, float yaw, float partialTicks) {
            super.doRender(entity, x, y, z, yaw, partialTicks);
            // if (ConfigHandler.WILD_DOG_SHOW_HITBOX) {
            // Main Entity Hitbox
            renderDebugBoundingBox(entity, x, y, z, yaw, partialTicks, 0, 0, 0);

            // Child Entity Hitbox
            EntityDogPart headPart = (EntityDogPart) entity.dogPartHead;
            renderDebugBoundingBox(headPart, x, y, z, yaw, partialTicks, headPart.posX - entity.posX, headPart.posY - entity.posY, headPart.posZ - entity.posZ);
            // }
        }
    */
    @Override
    public ResourceLocation getTextureLocation(EntityWildDog entity) {
        return TEXTURE;
    }
/*
	private void renderDebugBoundingBox(Entity entity, double x, double y, double z, float yaw, float partialTicks, double xOff, double yOff, double zOff) {
		GlStateManager.depthMask(false);
		GlStateManager.disableTexture();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		AxisAlignedBB axisalignedbb = entity.getBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - entity.posX + x + xOff, axisalignedbb.minY - entity.posY + y + yOff, axisalignedbb.minZ - entity.posZ + z + zOff, axisalignedbb.maxX - entity.posX + x + xOff, axisalignedbb.maxY - entity.posY + y + yOff, axisalignedbb.maxZ - entity.posZ + z + zOff);
		WorldRenderer.drawSelectionBoundingBox(axisalignedbb1, 1F, 1F, 1F, 1F);
		GlStateManager.enableTexture();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}
*/
}
