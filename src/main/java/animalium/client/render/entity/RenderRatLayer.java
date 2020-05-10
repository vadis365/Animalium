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
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityRat entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = entity.getHeldItemMainhand();
		if (!stack.isEmpty())
			this.renderHeldItem(matrixStackIn, bufferIn, packedLightIn, entity, stack);
	}

	@SuppressWarnings("deprecation")
	private void renderHeldItem(MatrixStack matrix, IRenderTypeBuffer renderBuffer, int packedLightIn, EntityRat entity, ItemStack stack) {
		if (!stack.isEmpty()) {
			matrix.push(); //push
			float animation = MathHelper.sin((entity.limbSwing * 0.4F) * 1.5F) * 0.3F * entity.limbSwingAmount * 0.3F;
			matrix.translate(0.0F, 1F + animation, 1.5F); // translation
			matrix.rotate(Vector3f.XP.rotationDegrees(110.0F)); //rotation
			matrix.scale(1F, 1F, 1F); // scale
			Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, stack, ItemCameraTransforms.TransformType.GROUND, false, matrix, renderBuffer, packedLightIn);
			matrix.pop(); //pop
		}
	}

}
