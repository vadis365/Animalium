package animalium.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import animalium.entities.EntityWildDog;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelWildDog<T extends EntityWildDog> extends EntityModel<T> {

	ModelRenderer body_rear;
	ModelRenderer body_mid;
	ModelRenderer tail1;
	ModelRenderer r_hindleg1;
	ModelRenderer l_hindleg1;
	ModelRenderer body_front;
	ModelRenderer neck;
	ModelRenderer l_foreleg1;
	ModelRenderer r_foreleg1;
	ModelRenderer head;
	ModelRenderer lower_jaw;
	ModelRenderer upper_jaw;
	ModelRenderer l_ear;
	ModelRenderer r_ear;
	ModelRenderer l_foreleg2;
	ModelRenderer l_fore_paw;
	ModelRenderer r_foreleg2;
	ModelRenderer r_fore_paw;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer r_hindleg2;
	ModelRenderer r_hindleg3;
	ModelRenderer r_hind_paw;
	ModelRenderer l_hindleg2;
	ModelRenderer l_hindleg3;
	ModelRenderer l_hind_paw;

	public ModelWildDog() {
		textureWidth = 128;
		textureHeight = 64;
		r_fore_paw = new ModelRenderer(this, 59, 25);
		r_fore_paw.setRotationPoint(0.0F, 9.6F, -0.5F);
		r_fore_paw.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 4, 0.0F);
		setRotateAngle(r_fore_paw, -0.5235987755982988F, 0.0F, 0.0F);
		lower_jaw = new ModelRenderer(this, 81, 0);
		lower_jaw.setRotationPoint(0.0F, 2.5F, 3.0F);
		lower_jaw.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 6, 0.0F);
		tail1 = new ModelRenderer(this, 80, 29);
		tail1.setRotationPoint(0.0F, 2.5F, -6.0F);
		tail1.addBox(-2.5F, -2.0F, -4.0F, 5, 4, 5, 0.0F);
		setRotateAngle(tail1, 0.06981317007977318F, 0.0F, 0.0F);
		body_front = new ModelRenderer(this, 26, 12);
		body_front.setRotationPoint(0.0F, 0.0F, 6.0F);
		body_front.addBox(-4.0F, 0.0F, 0.0F, 8, 9, 8, 0.0F);
		setRotateAngle(body_front, 0.17453292519943295F, 0.0F, 0.0F);
		l_fore_paw = new ModelRenderer(this, 10, 25);
		l_fore_paw.setRotationPoint(0.0F, 9.6F, -0.5F);
		l_fore_paw.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 4, 0.0F);
		setRotateAngle(l_fore_paw, -0.5235987755982988F, 0.0F, 0.0F);
		r_foreleg2 = new ModelRenderer(this, 62, 12);
		r_foreleg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		r_foreleg2.addBox(-1.0F, 0.0F, -1.5F, 2, 10, 2, 0.0F);
		setRotateAngle(r_foreleg2, 1.2217304763960306F, 0.0F, 0.0F);
		r_hindleg2 = new ModelRenderer(this, 61, 49);
		r_hindleg2.setRotationPoint(1.0F, 4.5F, -1.5F);
		r_hindleg2.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		setRotateAngle(r_hindleg2, -1.2217304763960306F, -0.0F, 0.0F);
		upper_jaw = new ModelRenderer(this, 82, 9);
		upper_jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		upper_jaw.addBox(-2.0F, -1.0F, 4.5F, 4, 2, 4, 0.0F);
		setRotateAngle(upper_jaw, -0.17453292519943295F, -0.0F, 0.0F);
		head = new ModelRenderer(this, 79, 16);
		head.setRotationPoint(0.0F, 0.0F, 2.0F);
		head.addBox(-3.0F, -2.5F, 0.0F, 6, 6, 5, 0.0F);
		setRotateAngle(head, 0.17453292519943295F, -0.0F, 0.0F);
		tail2 = new ModelRenderer(this, 81, 39);
		tail2.setRotationPoint(0.0F, 0.0F, -3.7F);
		tail2.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 6, 0.0F);
		setRotateAngle(tail2, 0.10471975511965977F, -0.0F, 0.0F);
		l_hindleg1 = new ModelRenderer(this, 7, 36);
		l_hindleg1.setRotationPoint(-4.0F, 3.5F, -2.5F);
		l_hindleg1.addBox(-3.5F, -1.0F, -2.5F, 5, 7, 5, 0.0F);
		setRotateAngle(l_hindleg1, 0.0F, -0.0F, -0.012740903539558604F);
		l_hind_paw = new ModelRenderer(this, 9, 58);
		l_hind_paw.setRotationPoint(0.0F, 6.0F, -1.0F);
		l_hind_paw.addBox(-2.0F, -1.0F, -0.5F, 4, 2, 4, 0.0F);
		setRotateAngle(l_hind_paw, -0.6981317007977318F, 0.0F, 0.0F);
		r_hind_paw = new ModelRenderer(this, 59, 58);
		r_hind_paw.setRotationPoint(0.0F, 6.0F, -1.0F);
		r_hind_paw.addBox(-2.0F, -1.0F, -0.5F, 4, 2, 4, 0.0F);
		setRotateAngle(r_hind_paw, -0.6981317007977318F, -0.0F, 0.0F);
		l_hindleg3 = new ModelRenderer(this, 0, 55);
		l_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
		l_hindleg3.addBox(-1.0F, -1.0F, -1.5F, 2, 7, 2, 0.0F);
		setRotateAngle(l_hindleg3, 1.5707963267948966F, -0.0F, 0.0F);
		l_foreleg1 = new ModelRenderer(this, 11, 0);
		l_foreleg1.setRotationPoint(-5.0F, 3.0F, 6.0F);
		l_foreleg1.addBox(-1.5F, -1.0F, -1.5F, 3, 8, 3, 0.0F);
		setRotateAngle(l_foreleg1, -0.6981317007977318F, -0.0F, 0.0F);
		r_hindleg1 = new ModelRenderer(this, 57, 36);
		r_hindleg1.setRotationPoint(4.0F, 3.5F, -2.5F);
		r_hindleg1.addBox(-1.5F, -1.0F, -2.5F, 5, 7, 5, 0.0F);
		neck = new ModelRenderer(this, 31, 0);
		neck.setRotationPoint(0.0F, 3.0F, 8.0F);
		neck.addBox(-2.5F, -2.0F, -3.0F, 5, 5, 6, 0.0F);
		setRotateAngle(neck, -0.17453292519943295F, 0.0F, 0.0F);
		l_foreleg2 = new ModelRenderer(this, 13, 12);
		l_foreleg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		l_foreleg2.addBox(-1.0F, 0.0F, -1.5F, 2, 10, 2, 0.0F);
		setRotateAngle(l_foreleg2, 1.2217304763960306F, -0.0F, 0.0F);
		l_hindleg2 = new ModelRenderer(this, 11, 49);
		l_hindleg2.setRotationPoint(-1.0F, 4.5F, -1.5F);
		l_hindleg2.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		setRotateAngle(l_hindleg2, -1.2217304763960306F, -0.0F, 0.0F);
		r_foreleg1 = new ModelRenderer(this, 60, 0);
		r_foreleg1.setRotationPoint(5.0F, 3.0F, 6.0F);
		r_foreleg1.addBox(-1.5F, -1.0F, -1.5F, 3, 8, 3, 0.0F);
		setRotateAngle(r_foreleg1, -0.6981317007977318F, -0.0F, 0.0F);
		r_hindleg3 = new ModelRenderer(this, 76, 55);
		r_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
		r_hindleg3.addBox(-1.0F, -1.0F, -1.5F, 2, 7, 2, 0.0F);
		setRotateAngle(r_hindleg3, 1.5707963267948966F, 0.0F, 0.0F);
		tail3 = new ModelRenderer(this, 83, 49);
		tail3.setRotationPoint(0.0F, 0.0F, -4.6F);
		tail3.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
		setRotateAngle(tail3, 0.03490658503988659F, 0.0F, 0.0F);
		body_rear = new ModelRenderer(this, 26, 45);
		body_rear.setRotationPoint(0.0F, 6.0F, -6.0F);
		body_rear.addBox(-4.5F, 0.0F, -6.0F, 9, 7, 7, 0.0F);
		setRotateAngle(body_rear, 0.3490658503988659F, 0.0F, 0.0F);
		body_mid = new ModelRenderer(this, 28, 30);
		body_mid.setRotationPoint(0.0F, 0.0F, 0.0F);
		body_mid.addBox(-3.5F, 0.0F, 0.5F, 7, 7, 7, 0.0F);
		setRotateAngle(body_mid, -0.5235987755982988F, 0.0F, 0.0F);
		r_ear = new ModelRenderer(this, 94, 0);
		r_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
		r_ear.addBox(1.0F, -5.5F, 0.0F, 2, 3, 2, 0.0F);
		l_ear = new ModelRenderer(this, 78, 0);
		l_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
		l_ear.addBox(-3.0F, -5.5F, 0.0F, 2, 3, 2, 0.0F);

		r_foreleg2.addChild(r_fore_paw);
		head.addChild(lower_jaw);
		body_rear.addChild(tail1);
		body_mid.addChild(body_front);
		l_foreleg2.addChild(l_fore_paw);
		r_foreleg1.addChild(r_foreleg2);
		r_hindleg1.addChild(r_hindleg2);
		head.addChild(upper_jaw);
		neck.addChild(head);
		tail1.addChild(tail2);
		body_rear.addChild(l_hindleg1);
		l_hindleg3.addChild(l_hind_paw);
		r_hindleg3.addChild(r_hind_paw);
		l_hindleg2.addChild(l_hindleg3);
		body_front.addChild(l_foreleg1);
		body_rear.addChild(r_hindleg1);
		body_front.addChild(neck);
		l_foreleg1.addChild(l_foreleg2);
		l_hindleg1.addChild(l_hindleg2);
		body_front.addChild(r_foreleg1);
		r_hindleg2.addChild(r_hindleg3);
		tail2.addChild(tail3);
		body_rear.addChild(body_mid);
		head.addChild(r_ear);
		head.addChild(l_ear);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body_rear).forEach((p_228279_8_) -> {
            p_228279_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);});
	}

	@Override
	 public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float heady = MathHelper.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		neck.rotateAngleY = heady;
	}

	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		float animation = MathHelper.sin((limbSwing * 0.6F + 2) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation2 = MathHelper.sin((limbSwing * 0.6F) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation3 = MathHelper.sin((limbSwing * 0.6F + 4) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float flap = MathHelper.sin((entity.ticksExisted) * 0.2F) * 0.6F;

		tail1.rotateAngleY = flap * 0.2F;
		tail2.rotateAngleY = tail1.rotateAngleY * 1.2F;
		tail3.rotateAngleY = tail2.rotateAngleY * 1.4F;

		tail1.rotateAngleX = 0.06981317007977318F - animation * 1F;
		tail2.rotateAngleX = 0.10471975511965977F - animation * 3F;
		tail3.rotateAngleX = 0.03490658503988659F - animation * 4F;

		if(entity.getPosX() == entity.lastTickPosX) {
			r_foreleg1.rotateAngleX = -0.6981317007977318F + (animation2 * 8F) + flap * 0.05F;
			r_foreleg2.rotateAngleX = 1.2217304763960306F  + (animation2 * 6F) - flap * 0.025F;
			r_fore_paw.rotateAngleX = -0.5235987755982988F - animation2 * 18F + flap * 0.075F;
			
			l_foreleg1.rotateAngleX = -0.6981317007977318F + (animation * 8F) + flap * 0.05F;
			l_foreleg2.rotateAngleX = 1.2217304763960306F  + (animation * 6F) - flap * 0.025F;
			l_fore_paw.rotateAngleX = -0.5235987755982988F - (animation * 18F) + flap * 0.075F;

			r_hindleg1.rotateAngleX = -0.1F - (animation2 * 6F) - flap * 0.05F;
			l_hindleg1.rotateAngleX = -0.1F - (animation3 * 6F) - flap * 0.05F;

			r_hindleg2.rotateAngleX = -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2) + flap * 0.05F;
			l_hindleg2.rotateAngleX = -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3) + flap * 0.05F;

			r_hindleg3.rotateAngleX = 1.5707963267948966F + flap * 0.025F;
			l_hindleg3.rotateAngleX = 1.5707963267948966F + flap * 0.025F;

			r_hind_paw.rotateAngleX = -0.9981317007977318F - (r_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;
			l_hind_paw.rotateAngleX = -0.9981317007977318F - (l_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;

			body_front.rotateAngleX = 0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;

			body_mid.rotateAngleX = -0.5235987755982988F - (animation2 * 2F) - flap * 0.025F;

			body_rear.rotateAngleX = 0.3490658503988659F + (animation2 * 2F) + flap * 0.025F;

			neck.rotateAngleX = -0.17453292519943295F + (animation2 * 2.9F) + flap * 0.025F;
		}
		else {
			r_foreleg1.rotateAngleX = -0.6981317007977318F + animation2 * 8F;
			l_foreleg1.rotateAngleX = -0.6981317007977318F + animation * 8F;

			r_foreleg2.rotateAngleX = 1.2217304763960306F + animation2 * 7F;
			l_foreleg2.rotateAngleX = 1.2217304763960306F + animation * 7F;

			r_fore_paw.rotateAngleX = -0.5235987755982988F - animation2 * 14F;
			l_fore_paw.rotateAngleX = -0.5235987755982988F - animation * 14F;

			r_hindleg1.rotateAngleX = -0.1F - animation2 * 6F;
			l_hindleg1.rotateAngleX = -0.1F - animation3 * 6F;

			r_hindleg2.rotateAngleX = -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2);
			l_hindleg2.rotateAngleX = -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3);

			r_hindleg3.rotateAngleX = 1.5707963267948966F;
			l_hindleg3.rotateAngleX = 1.5707963267948966F;

			r_hind_paw.rotateAngleX = -0.9981317007977318F - r_hindleg1.rotateAngleX * 1.25F;
			l_hind_paw.rotateAngleX = -0.9981317007977318F - l_hindleg1.rotateAngleX * 1.25F;

			body_front.rotateAngleX = 0.17453292519943295F - animation2 * 3F;

			body_mid.rotateAngleX = -0.5235987755982988F - animation2 * 2F;

			body_rear.rotateAngleX = 0.3490658503988659F + animation2 * 2F;

			neck.rotateAngleX = -0.17453292519943295F + animation2 * 2.9F;

		}

		if (!entity.onGround)
			lower_jaw.rotateAngleX = -0.9F;
		else
			lower_jaw.rotateAngleX = -0.2490658503988659F + flap * 0.2F;

	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
