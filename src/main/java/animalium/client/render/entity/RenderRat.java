package animalium.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import animalium.client.model.ModelRat;
import animalium.entities.EntityRat;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class RenderRat extends MobRenderer<EntityRat, ModelRat<EntityRat>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/rat.png");

	public RenderRat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelRat<>(), 0.5F);
        this.addLayer(new RenderRatLayer(this));
    }

	@Override
	protected void preRenderCallback(EntityRat entity, float partialTickTime) {
		float size = 0.5F;
		GlStateManager.scalef(size, size, size);
		GlStateManager.translatef(0F, -0.0625F, 0F);
		GlStateManager.rotatef(180F, 0F, 1F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRat entity) {
		return TEXTURE;
	}
}
