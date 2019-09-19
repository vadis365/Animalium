package animalium.client.model;

import animalium.entities.EntityBear;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelBear<T extends Entity> extends EntityModel<T> {
	RendererModel body_rear;
	RendererModel body_mid;
	RendererModel r_hindleg1;
	RendererModel l_hindleg1;
	RendererModel body_front;
	RendererModel neck;
	RendererModel l_foreleg1;
	RendererModel r_foreleg1;
	RendererModel head;
	RendererModel lower_jaw;
	RendererModel upper_jaw;
	RendererModel l_ear;
	RendererModel r_ear;
	RendererModel l_foreleg2;
	RendererModel l_fore_paw;
	RendererModel r_foreleg2;
	RendererModel r_fore_paw;
	RendererModel r_hindleg2;
	RendererModel r_hindleg3;
	RendererModel r_hind_paw;
	RendererModel l_hindleg2;
	RendererModel l_hindleg3;
	RendererModel l_hind_paw;

	public ModelBear() {
		 textureWidth = 128;
	        textureHeight = 128;
	        l_hind_paw = new RendererModel(this, 1, 85);
	        l_hind_paw.setRotationPoint(0.0F, 4.5F, 0.0F);
	        l_hind_paw.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 6, 0.0F);
	        setRotateAngle(l_hind_paw, -0.6981317007977318F, 0.0F, 0.0F);
	        upper_jaw = new RendererModel(this, 105, 9);
	        upper_jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
	        upper_jaw.addBox(-2.5F, -1.8F, 5.7F, 5, 3, 4, 0.0F);
	        setRotateAngle(upper_jaw, -0.17453292519943295F, 0.0F, 0.0F);
	        l_hindleg2 = new RendererModel(this, 3, 60);
	        l_hindleg2.setRotationPoint(-2.0F, 5.0F, -1.5F);
	        l_hindleg2.addBox(-2.0F, -3.0F, -1.5F, 4, 8, 5, 0.0F);
	        setRotateAngle(l_hindleg2, -1.2217304763960306F, -0.0F, 0.0F);
	        neck = new RendererModel(this, 35, 0);
	        neck.setRotationPoint(0.0F, 4.2F, 11.5F);
	        neck.addBox(-3.5F, -3.5F, -3.0F, 7, 7, 7, 0.0F);
	        lower_jaw = new RendererModel(this, 104, 0);
	        lower_jaw.setRotationPoint(0.0F, 3.0F, 4.0F);
	        lower_jaw.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 6, 0.0F);
	        setRotateAngle(lower_jaw, -0.7740535232594852F, 0.0F, 0.0F);
	        l_foreleg1 = new RendererModel(this, 2, 0);
	        l_foreleg1.setRotationPoint(-5.5F, 3.5F, 6.0F);
	        l_foreleg1.addBox(-2.5F, -4.5F, -2.5F, 4, 11, 6, 0.0F);
	        setRotateAngle(l_foreleg1, 0.17453292519943295F, 0.0F, 0.17453292519943295F);
	        l_hindleg1 = new RendererModel(this, 0, 42);
	        l_hindleg1.setRotationPoint(-5.5F, 4.5F, -5.0F);
	        l_hindleg1.addBox(-4.5F, -2.5F, -3.5F, 5, 10, 7, 0.0F);
	        setRotateAngle(l_hindleg1, -0.3490658503988659F, 0.0F, 0.0F);
	        l_hindleg3 = new RendererModel(this, 5, 74);
	        l_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
	        l_hindleg3.addBox(-1.5F, -1.0F, -1.5F, 3, 6, 4, 0.0F);
	        setRotateAngle(l_hindleg3, 1.5707963267948966F, -0.0F, 0.0F);
	        l_fore_paw = new RendererModel(this, 1, 32);
	        l_fore_paw.setRotationPoint(0.5F, 7.0F, 0.0F);
	        l_fore_paw.addBox(-2.5F, -1.5F, -2.0F, 5, 3, 6, 0.0F);
	        setRotateAngle(l_fore_paw, -0.17453292519943295F, 0.0F, 0.0F);
	        l_foreleg2 = new RendererModel(this, 4, 18);
	        l_foreleg2.setRotationPoint(-0.5F, 6.0F, 0.0F);
	        l_foreleg2.addBox(-1.5F, -2.0F, -1.5F, 4, 9, 4, 0.0F);
	        setRotateAngle(l_foreleg2, 0.17453292519943295F, 0.0F, -0.17453292519943295F);
	        r_fore_paw = new RendererModel(this, 75, 32);
	        r_fore_paw.setRotationPoint(-0.5F, 7.0F, 0.0F);
	        r_fore_paw.addBox(-2.5F, -1.5F, -2.0F, 5, 3, 6, 0.0F);
	        setRotateAngle(r_fore_paw, -0.17453292519943295F, 0.0F, 0.0F);
	        r_foreleg2 = new RendererModel(this, 78, 18);
	        r_foreleg2.setRotationPoint(0.5F, 6.0F, 0.0F);
	        r_foreleg2.addBox(-2.5F, -2.0F, -2.0F, 4, 9, 4, 0.0F);
	        setRotateAngle(r_foreleg2, 0.17453292519943295F, 0.0F, 0.17453292519943295F);
	        r_hindleg3 = new RendererModel(this, 79, 74);
	        r_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
	        r_hindleg3.addBox(-1.5F, -1.0F, -1.5F, 3, 6, 4, 0.0F);
	        setRotateAngle(r_hindleg3, 1.5707963267948966F, 0.0F, 0.0F);
	        r_foreleg1 = new RendererModel(this, 76, 0);
	        r_foreleg1.setRotationPoint(5.5F, 3.5F, 6.0F);
	        r_foreleg1.addBox(-1.5F, -4.5F, -2.5F, 4, 11, 6, 0.0F);
	        setRotateAngle(r_foreleg1, 0.17453292519943295F, 0.0F, -0.17453292519943295F);
	        body_mid = new RendererModel(this, 32, 40);
	        body_mid.setRotationPoint(0.0F, 0.0F, 0.0F);
	        body_mid.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 7, 0.0F);
	        setRotateAngle(body_mid, -0.6981317007977318F, 0.0F, 0.0F);
	        r_hind_paw = new RendererModel(this, 75, 85);
	        r_hind_paw.setRotationPoint(0.0F, 4.5F, 0.0F);
	        r_hind_paw.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 6, 0.0F);
	        setRotateAngle(r_hind_paw, -0.6981317007977318F, -0.0F, 0.0F);
	        l_ear = new RendererModel(this, 100, 0);
	        l_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
	        l_ear.addBox(-3.0F, -6.0F, 0.0F, 2, 2, 2, 0.0F);
	        setRotateAngle(l_ear, 0.0F, 0.0F, -0.3490658503988659F);
	        body_rear = new RendererModel(this, 27, 58);
	        body_rear.setRotationPoint(0.0F, 4.0F, -6.0F);
	        body_rear.addBox(-5.5F, 0.0F, -11.0F, 11, 11, 11, 0.0F);
	        setRotateAngle(body_rear, 0.6981317007977318F, 0.0F, 0.0F);
	        body_front = new RendererModel(this, 26, 15);
	        body_front.setRotationPoint(0.0F, 1.0F, 7.0F);
	        body_front.addBox(-5.5F, -1.1F, -0.5F, 11, 12, 12, 0.0F);
	        setRotateAngle(body_front, -0.17453292519943295F, 0.0F, 0.0F);
	        head = new RendererModel(this, 100, 17);
	        head.setRotationPoint(0.0F, 0.0F, 2.0F);
	        head.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 6, 0.0F);
	        setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
	        r_ear = new RendererModel(this, 120, 0);
	        r_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
	        r_ear.addBox(1.0F, -6.0F, 0.0F, 2, 2, 2, 0.0F);
	        setRotateAngle(r_ear, 0.0F, 0.0F, 0.3490658503988659F);
	        r_hindleg1 = new RendererModel(this, 74, 42);
	        r_hindleg1.setRotationPoint(5.5F, 4.5F, -5.5F);
	        r_hindleg1.addBox(-0.5F, -2.5F, -3.5F, 5, 10, 7, 0.0F);
	        setRotateAngle(r_hindleg1, -0.3490658503988659F, 0.0F, 0.0F);
	        r_hindleg2 = new RendererModel(this, 77, 60);
	        r_hindleg2.setRotationPoint(2.0F, 5.0F, -1.5F);
	        r_hindleg2.addBox(-2.0F, -3.0F, -1.5F, 4, 8, 5, 0.0F);
	        setRotateAngle(r_hindleg2, -1.2217304763960306F, 0.0F, 0.0F);
	        l_hindleg3.addChild(l_hind_paw);
	        head.addChild(upper_jaw);
	        l_hindleg1.addChild(l_hindleg2);
	        body_front.addChild(neck);
	        head.addChild(lower_jaw);
	        body_front.addChild(l_foreleg1);
	        body_rear.addChild(l_hindleg1);
	        l_hindleg2.addChild(l_hindleg3);
	        l_foreleg2.addChild(l_fore_paw);
	        l_foreleg1.addChild(l_foreleg2);
	        r_foreleg2.addChild(r_fore_paw);
	        r_foreleg1.addChild(r_foreleg2);
	        r_hindleg2.addChild(r_hindleg3);
	        body_front.addChild(r_foreleg1);
	        body_rear.addChild(body_mid);
	        r_hindleg3.addChild(r_hind_paw);
	        head.addChild(l_ear);
	        body_mid.addChild(body_front);
	        neck.addChild(head);
	        head.addChild(r_ear);
	        body_rear.addChild(r_hindleg1);
	        r_hindleg1.addChild(r_hindleg2);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float scale) {
		body_rear.render(scale);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.setRotationAngles(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		float heady = MathHelper.sin((rotationYaw / (180F / (float) Math.PI)) * 0.5F);
		neck.rotateAngleY = heady;
	}

	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		
		EntityBear bear = (EntityBear) entity;
		float animation = MathHelper.sin((limbSwing * 0.6F + 2) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation2 = MathHelper.sin((limbSwing * 0.6F) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation3 = MathHelper.sin((limbSwing * 0.6F + 4) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
		float flap = MathHelper.sin((bear.ticksExisted) * 0.3F) * 0.8F;
		float standingAngle = bear.smoothedAngle(partialRenderTicks);

		if (bear.posX == bear.lastTickPosX) {
			r_foreleg1.rotateAngleX = 0.17453292519943295F + (animation2 * 8F) + flap * 0.05F;
			r_foreleg2.rotateAngleX = 0.17453292519943295F + (animation2 * 6F) - flap * 0.025F;
			r_fore_paw.rotateAngleX = -(standingAngle*1.25F) -0.17453292519943295F - animation2 * 18F + flap * 0.05F;

			l_foreleg1.rotateAngleX = 0.17453292519943295F + (animation * 8F) + flap * 0.05F;
			l_foreleg2.rotateAngleX = 0.17453292519943295F + (animation * 6F) - flap * 0.025F;
			l_fore_paw.rotateAngleX = -(standingAngle * 1.25F) -0.17453292519943295F - (animation * 18F) + flap * 0.05F;

			r_hindleg1.rotateAngleX = -(standingAngle * 0.75F) -0.1F - (animation2 * 6F) - flap * 0.05F;
			l_hindleg1.rotateAngleX = -(standingAngle * 0.75F) -0.1F - (animation3 * 6F) - flap * 0.05F;

			r_hindleg2.rotateAngleX = standingAngle -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2) + flap * 0.05F;
			l_hindleg2.rotateAngleX = standingAngle -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3) + flap * 0.05F;

			r_hindleg3.rotateAngleX = -standingAngle +1.5707963267948966F + flap * 0.025F;
			l_hindleg3.rotateAngleX = -standingAngle +1.5707963267948966F + flap * 0.025F;

			r_hind_paw.rotateAngleX = -(standingAngle * 0.5F) -0.9981317007977318F - (r_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;
			l_hind_paw.rotateAngleX = -(standingAngle * 0.5F) -0.9981317007977318F - (l_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;

			body_front.rotateAngleX = -0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;
			body_front.rotateAngleZ = 0F - animation2 * 1.5F;

			body_mid.rotateAngleX = -0.6981317007977318F - (animation2 * 2F) - flap * 0.025F;

			body_rear.rotateAngleX = standingAngle + 0.6981317007977318F + (animation2 * 2F) + flap * 0.025F;

			neck.rotateAngleX = -(standingAngle * 0.5F) -0.17453292519943295F + (animation2 * 2.9F) + flap * 0.025F;
			head.rotateAngleX = -(standingAngle * 0.5F) + 0.17453292519943295F;
			head.rotateAngleZ = -(standingAngle * 0.1F * flap * 6F);
			
		} else {
			r_foreleg1.rotateAngleX = 0.17453292519943295F + (standingAngle * 0.5F * flap) + animation2 * 6F;
			l_foreleg1.rotateAngleX = 0.17453292519943295F - (standingAngle * 0.5F * flap) + animation * 6F;
			
			r_foreleg2.rotateAngleX = 0.17453292519943295F + animation2 * 5F;
			l_foreleg2.rotateAngleX = 0.17453292519943295F + animation * 5F;

			r_fore_paw.rotateAngleX = -(standingAngle * 1.25F) -0.17453292519943295F - animation2 * 12F;
			l_fore_paw.rotateAngleX = -(standingAngle * 1.25F) -0.17453292519943295F - animation * 12F;

			r_hindleg1.rotateAngleX = -(standingAngle * 0.75F) -0.1F - animation2 * 6F;
			l_hindleg1.rotateAngleX = -(standingAngle * 0.75F) -0.1F - animation3 * 6F;

			r_hindleg2.rotateAngleX = standingAngle -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2);
			l_hindleg2.rotateAngleX = standingAngle -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3);

			r_hindleg3.rotateAngleX = -standingAngle + 1.5707963267948966F;
			l_hindleg3.rotateAngleX = -standingAngle + 1.5707963267948966F;

			r_hind_paw.rotateAngleX = -(standingAngle * 0.5F) -0.9981317007977318F - r_hindleg1.rotateAngleX * 1.25F;
			l_hind_paw.rotateAngleX = -(standingAngle * 0.5F) -0.9981317007977318F - l_hindleg1.rotateAngleX * 1.25F;

			body_front.rotateAngleX = -0.17453292519943295F - animation2 * 3F;

			body_front.rotateAngleZ = (standingAngle * 0.5F * flap);

			body_mid.rotateAngleX = -0.6981317007977318F - animation2 * 2F;

			body_rear.rotateAngleX = standingAngle + 0.6981317007977318F + animation2 * 2F;

			neck.rotateAngleX = -(standingAngle * 0.5F) -0.17453292519943295F + animation2 * 2.9F;
			head.rotateAngleX = -(standingAngle * 0.5F) + 0.17453292519943295F;
			head.rotateAngleZ = -(standingAngle * 0.1F * flap * 6F);
		}

		if (!bear.onGround)
			lower_jaw.rotateAngleX = -0.9F;
		else {
			if(standingAngle > 0)
				lower_jaw.rotateAngleX = -0.5F + flap * 0.75F;
			else
				lower_jaw.rotateAngleX = -0.2490658503988659F + flap * 0.2F;
			}

	}

	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
