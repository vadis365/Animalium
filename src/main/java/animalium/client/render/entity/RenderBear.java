package animalium.client.render.entity;

import animalium.client.ClientEvents;
import animalium.client.model.ModelBear;
import animalium.common.entities.EntityBear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBear extends MobRenderer<EntityBear, ModelBear<EntityBear>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/bear.png");

	public RenderBear(EntityRendererProvider.Context context) {
        super(context, new ModelBear(context.bakeLayer(ClientEvents.BEAR)), 1.5F);
    }

	@Override
	protected void scale(EntityBear entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.5F, 1.5F, 1.5F); // scale
		matrix.mulPose(Axis.YP.rotationDegrees(180.0F)); //rotation
	}

	@Override
	public ResourceLocation getTextureLocation(EntityBear entity) {
		return TEXTURE;
	}
}
