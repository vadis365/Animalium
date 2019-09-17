package animalium.client.render.entity;

import animalium.client.model.ModelPiranha;
import animalium.entities.EntityPiranha;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPiranha extends RenderLiving<EntityPiranha> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/piranha.png");

	public RenderPiranha(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelPiranha(), 0.5F);
    }

	@Override
	protected void preRenderCallback(EntityPiranha entity, float f) {
		if (entity.isGrounded() && !entity.isLeaping()) {
			GlStateManager.translate(0F, 0.5F, 0F);
			GlStateManager.rotate(90F, 0F, 0F, 1F);
			GlStateManager.translate(-0.7F, 0.7F, 0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPiranha entity) {
		return TEXTURE;
	}
}
