package animalium.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import animalium.client.model.ModelBear;
import animalium.entities.EntityBear;
import net.minecraft.client.renderer.Vector3f;
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
	protected void func_225620_a_(EntityBear entity, MatrixStack matrix, float partialTickTime) {
		matrix.func_227862_a_(1.5F, 1.5F, 1.5F); // scale
		matrix.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F)); //rotation
	}

	@Override
	public ResourceLocation getEntityTexture(EntityBear entity) {
		return TEXTURE;
	}
}
