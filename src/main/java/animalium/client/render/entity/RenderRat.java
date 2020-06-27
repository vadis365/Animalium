package animalium.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import animalium.client.model.ModelRat;
import animalium.entities.EntityRat;
import net.minecraft.client.renderer.Vector3f;
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
	protected void preRenderCallback(EntityRat entity, MatrixStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F); // scale
		matrix.translate(0F, -0.0625F, 0F); // translation
		matrix.rotate(Vector3f.YP.rotationDegrees(180.0F)); //rotation
	}

	@Override
	public ResourceLocation getEntityTexture(EntityRat entity) {
		return TEXTURE;
	}
}
