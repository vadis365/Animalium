package animalium.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import animalium.client.model.ModelBear;
import animalium.entities.EntityBear;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBear extends MobRenderer<EntityBear, ModelBear<EntityBear>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/bear.png");

	public RenderBear(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelBear<>(), 1.5F);
    }

	@Override
	protected void preRenderCallback(EntityBear entity, float partialTickTime) {
		GlStateManager.scalef(1.5F, 1.5F, 1.5F);
		GlStateManager.rotatef(180F, 0F, 1F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBear entity) {
		return TEXTURE;
	}
}
