package animalium.client.render.entity;

import animalium.client.model.ModelRat;
import animalium.entities.EntityRat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderRat extends RenderLiving<EntityRat> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("animalium:textures/entity/rat.png");

	public RenderRat(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelRat(), 0.5F);
        this.addLayer(new RenderRatLayer(this));
    }

	@Override
	protected void preRenderCallback(EntityRat entity, float partialTickTime) {
		float size = 0.5F;
		GlStateManager.scale(size, size, size);
		GlStateManager.translate(0F, -0.0625F, 0F);
		GlStateManager.rotate(180F, 0F, 1F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRat entity) {
		return TEXTURE;
	}
}
