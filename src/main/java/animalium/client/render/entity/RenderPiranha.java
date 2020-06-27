package animalium.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import animalium.client.model.ModelPiranha;
import animalium.entities.EntityPiranha;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderPiranha extends MobRenderer<EntityPiranha, ModelPiranha<EntityPiranha>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/piranha.png");

	public RenderPiranha(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPiranha<>(), 0.5F);
    }

	@Override
	protected void preRenderCallback(EntityPiranha entity, MatrixStack matrix, float partialTickTime) {
		if (entity.isGrounded() && !entity.isLeaping()) {
			matrix.translate(0F, 0.5F, 0F); // translation
			matrix.rotate(Vector3f.ZP.rotationDegrees(90.0F)); // rotation
			matrix.translate(-0.7F, 0.7F, 0F); // translation
		}
	}

	@Override
	public ResourceLocation getEntityTexture(EntityPiranha entity) {
		return TEXTURE;
	}
}
