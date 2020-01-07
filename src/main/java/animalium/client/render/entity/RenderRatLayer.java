package animalium.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import animalium.client.model.ModelRat;
import animalium.entities.EntityRat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderRatLayer extends LayerRenderer<EntityRat, ModelRat<EntityRat>> {

	public RenderRatLayer(IEntityRenderer <EntityRat, ModelRat<EntityRat>> entity) {
		super(entity);
	}

	@Override
	public void func_225628_a_(MatrixStack matrix, IRenderTypeBuffer renderBuffer, int somethingInt, EntityRat entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
		ItemStack stack = entity.getHeldItemMainhand();
		if (!stack.isEmpty())
			this.renderHeldItem(matrix, renderBuffer, somethingInt, entity, stack);
	}

	@SuppressWarnings("deprecation")
	private void renderHeldItem(MatrixStack matrix, IRenderTypeBuffer renderBuffer, int somethingInt, EntityRat entity, ItemStack stack) {
		if (!stack.isEmpty()) {
			matrix.func_227860_a_(); //push
			float animation = MathHelper.sin((entity.limbSwing * 0.4F) * 1.5F) * 0.3F * entity.limbSwingAmount * 0.3F;
			matrix.func_227861_a_(0.0F, 1F + animation, 1.5F); // translation
			matrix.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(110.0F)); //rotation
			matrix.func_227862_a_(1F, 1F, 1F); // scale
			Minecraft.getInstance().getFirstPersonRenderer().func_228397_a_(entity, stack, ItemCameraTransforms.TransformType.GROUND, false, matrix, renderBuffer, somethingInt);
			matrix.func_227865_b_(); //pop
		}
	}
}
