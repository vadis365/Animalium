package animalium.client.render.entity;

import animalium.entities.EntityRat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRatLayer implements LayerRenderer<EntityRat> {
	private final RenderRat ratRenderer;

	public RenderRatLayer(RenderRat livingEntityRendererIn) {
		this.ratRenderer = livingEntityRendererIn;
	}

	@Override
	public void doRenderLayer(EntityRat entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack stack = entity.getHeldItemMainhand();

		if (stack != null)
			this.renderHeldItem(entity, stack);
	}

	private void renderHeldItem(EntityRat entity, ItemStack stack) {
		if (stack != null) {
			GlStateManager.pushMatrix();
			float animation = MathHelper.sin((entity.limbSwing * 0.4F) * 1.5F) * 0.3F * entity.limbSwingAmount * 0.3F;
			GlStateManager.translate(0.0F, 1F + animation, 1.75F);
			GlStateManager.rotate(-110.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, (World) null, (EntityLivingBase) null));
			GlStateManager.popMatrix();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
