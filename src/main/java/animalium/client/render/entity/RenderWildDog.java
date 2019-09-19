package animalium.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import animalium.client.model.ModelWildDog;
import animalium.entities.EntityWildDog;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWildDog extends MobRenderer<EntityWildDog, ModelWildDog<EntityWildDog>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/wild_dog.png");

	public RenderWildDog(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelWildDog<>(), 0.5F);
    }

	@Override
	protected void preRenderCallback(EntityWildDog entity, float partialTickTime) {
		GlStateManager.translatef(0F, -0.06F, 0F);
		GlStateManager.rotatef(180F, 0F, 1F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWildDog entity) {
		return TEXTURE;
	}
}
