package animalium.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import animalium.client.model.ModelRat;
import animalium.common.entities.EntityRat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class RenderRatLayer extends RenderLayer<EntityRat, ModelRat<EntityRat>> {

    public RenderRatLayer(RenderLayerParent<EntityRat, ModelRat<EntityRat>> entity) {
        super(entity);
    }

    @Override
    public void render(PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityRat entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entity.getMainHandItem();
        if (!stack.isEmpty())
            this.renderHeldItem(poseStackIn, bufferIn, packedLightIn, entity, stack);
    }

    private void renderHeldItem(PoseStack poseStack, MultiBufferSource renderBuffer, int packedLightIn, EntityRat entity, ItemStack stack) {
        if (!stack.isEmpty()) {
            poseStack.pushPose(); //push
            float animation = Mth.sin((entity.walkAnimation.position() * 0.4F) * 1.5F) * 0.3F * entity.walkAnimation.speed() * 0.3F;
            poseStack.translate(0.0F, 1F + animation, 1.5F); // translation
            poseStack.mulPose(Axis.XP.rotationDegrees(110.0F)); //rotation
            poseStack.scale(1F, 1F, 1F); // scale
            Minecraft.getInstance().getTextureManager().bindForSetup(TextureAtlas.LOCATION_BLOCKS);
            Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.GROUND, false, poseStack, renderBuffer, packedLightIn, packedLightIn, Minecraft.getInstance().getItemRenderer().getModel(stack, null, null, 0));
            poseStack.popPose(); //pop
        }
    }
}