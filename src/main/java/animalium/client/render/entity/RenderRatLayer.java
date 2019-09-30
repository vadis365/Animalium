package animalium.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import animalium.client.model.ModelRat;
import animalium.entities.EntityRat;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderRatLayer extends LayerRenderer<EntityRat, ModelRat<EntityRat>> {

	private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	public RenderRatLayer(IEntityRenderer <EntityRat, ModelRat<EntityRat>> entity) {
		super(entity);
	}

	@Override
	public void render(EntityRat entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack stack = entity.getHeldItemMainhand();

		if (!stack.isEmpty())
			this.renderHeldItem(entity, stack);
	}

	@SuppressWarnings("deprecation")
	private void renderHeldItem(EntityRat entity, ItemStack stack) {
		if (!stack.isEmpty()) {
			Item item = stack.getItem();
			Block block = Block.getBlockFromItem(item);
			GlStateManager.pushMatrix();
	        boolean transparent3D = itemRenderer.shouldRenderItemIn3D(stack) && block.getRenderLayer() == BlockRenderLayer.TRANSLUCENT;
	        if (transparent3D)
	        	GlStateManager.depthMask(false);

			float animation = MathHelper.sin((entity.limbSwing * 0.4F) * 1.5F) * 0.3F * entity.limbSwingAmount * 0.3F;
			GlStateManager.translatef(0.0F, 1F + animation, 1.75F);
			GlStateManager.rotatef(-110.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scalef(0.5F, 0.5F, 0.5F);

			itemRenderer.renderItem(stack, entity, ItemCameraTransforms.TransformType.GROUND, false);
			//itemRenderer.renderItem(stack, itemRenderer.getItemModelWithOverrides(stack, (World) null, (LivingEntity) null));
			if (transparent3D)
				GlStateManager.depthMask(true);

			GlStateManager.popMatrix();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
