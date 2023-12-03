package animalium.client.render.entity;

import animalium.client.ClientEvents;

import animalium.client.model.ModelPiranha;
import animalium.common.entities.EntityPiranha;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderPiranha extends MobRenderer<EntityPiranha, ModelPiranha<EntityPiranha>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/piranha.png");

	public RenderPiranha(EntityRendererProvider.Context context) {
        super(context, new ModelPiranha(context.bakeLayer(ClientEvents.PIRANHA)), 0.5F);
    }

	@Override
	protected void scale(EntityPiranha entity, PoseStack matrix, float partialTickTime) {
		if (entity.isGrounded() && !entity.isLeaping()) {
			matrix.translate(0F, 0.5F, 0F); // translation
			matrix.mulPose(Axis.ZP.rotationDegrees(90.0F)); // rotation
			matrix.translate(-0.7F, 0.7F, 0F); // translation
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityPiranha entity) {
		return TEXTURE;
	}
}
