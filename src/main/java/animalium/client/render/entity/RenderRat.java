package animalium.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import animalium.client.ClientEvents;
import animalium.client.model.ModelRat;
import animalium.common.entities.EntityRat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class RenderRat extends MobRenderer<EntityRat, ModelRat<EntityRat>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/rat.png");

	public RenderRat(EntityRendererProvider.Context context) {
        super(context, new ModelRat<>(context.bakeLayer(ClientEvents.RAT)), 0.5F);
        this.addLayer(new RenderRatLayer(this));
    }

	@Override
	protected void scale(EntityRat entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F); // scale
		matrix.translate(0F, -0.0625F, 0F); // translation
		matrix.mulPose(Axis.YP.rotationDegrees(180.0F)); //rotation
	}

	@Override
	public ResourceLocation getTextureLocation(EntityRat entity) {
		return TEXTURE;
	}
}
