package animalium.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import animalium.client.model.ModelPiranha;
import animalium.entities.EntityPiranha;
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
	protected void preRenderCallback(EntityPiranha entity, float f) {
		if (entity.isGrounded() && !entity.isLeaping()) {
			GlStateManager.translatef(0F, 0.5F, 0F);
			GlStateManager.rotatef(90F, 0F, 0F, 1F);
			GlStateManager.translatef(-0.7F, 0.7F, 0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPiranha entity) {
		return TEXTURE;
	}
}
