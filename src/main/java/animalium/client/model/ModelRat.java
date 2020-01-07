package animalium.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import animalium.entities.EntityRat;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelRat<T extends EntityRat> extends EntityModel<T> {
    ModelRenderer body_rear;
    ModelRenderer body_mid;
    ModelRenderer r_hindleg1;
    ModelRenderer l_hindleg1;
    ModelRenderer tail1;
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
    ModelRenderer r_hindleg2;
    ModelRenderer r_hindleg3;
    ModelRenderer r_hind_paw;
    ModelRenderer l_hindleg2;
    ModelRenderer l_hindleg3;
    ModelRenderer l_hind_paw;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;

    public ModelRat() {
        textureWidth = 128;
        textureHeight = 128;
        r_foreleg1 = new ModelRenderer(this, 76, 0);
        r_foreleg1.setRotationPoint(5.5F, 3.7F, 6.0F);
        r_foreleg1.func_228301_a_(-1.5F, -2.5F, -2.0F, 3, 8, 4, 0.0F);
        setRotateAngle(r_foreleg1, 0.17453292519943295F, 0.0F, -0.17453292519943295F);
        r_hind_paw = new ModelRenderer(this, 75, 85);
        r_hind_paw.setRotationPoint(0.0F, 4.5F, 0.0F);
        r_hind_paw.func_228301_a_(-2.5F, -0.5F, -1.5F, 5, 2, 6, 0.0F);
        setRotateAngle(r_hind_paw, -0.6981317007977318F, -0.0F, 0.0F);
        neck = new ModelRenderer(this, 35, 0);
        neck.setRotationPoint(0.0F, 2.6F, 11.5F);
        neck.func_228301_a_(-3.5F, -3.5F, -3.0F, 7, 7, 7, 0.0F);
        body_rear = new ModelRenderer(this, 27, 58);
        body_rear.setRotationPoint(0.0F, 7.5F, -6.0F);
        body_rear.func_228301_a_(-5.5F, 0.0F, -11.0F, 11, 11, 11, 0.0F);
        setRotateAngle(body_rear, 0.6981317007977318F, 0.0F, 0.0F);
        body_front = new ModelRenderer(this, 26, 15);
        body_front.setRotationPoint(0.0F, 1.0F, 7.0F);
        body_front.func_228301_a_(-5.5F, -1.1F, -0.5F, 11, 10, 12, 0.0F);
        setRotateAngle(body_front, -0.17453292519943295F, 0.0F, 0.0F);
        l_hindleg1 = new ModelRenderer(this, 0, 42);
        l_hindleg1.setRotationPoint(-5.5F, 4.5F, -5.0F);
        l_hindleg1.func_228301_a_(-4.5F, -2.5F, -3.5F, 5, 8, 7, 0.0F);
        setRotateAngle(l_hindleg1, -0.3490658503988659F, 0.0F, 0.0F);
        r_fore_paw = new ModelRenderer(this, 75, 32);
        r_fore_paw.setRotationPoint(-0.5F, 3.5F, 0.0F);
        r_fore_paw.func_228301_a_(-2.5F, -0.5F, -1.5F, 4, 2, 6, 0.0F);
        setRotateAngle(r_fore_paw, -0.17453292519943295F, 0.0F, 0.0F);
        r_hindleg2 = new ModelRenderer(this, 77, 60);
        r_hindleg2.setRotationPoint(2.0F, 1.5F, -1.5F);
        r_hindleg2.func_228301_a_(-2.0F, -3.0F, -1.5F, 4, 8, 3, 0.0F);
        setRotateAngle(r_hindleg2, -1.2217304763960306F, 0.0F, 0.0F);
        l_hind_paw = new ModelRenderer(this, 1, 85);
        l_hind_paw.setRotationPoint(0.0F, 4.5F, 0.0F);
        l_hind_paw.func_228301_a_(-2.5F, -0.5F, -1.5F, 5, 2, 6, 0.0F);
        setRotateAngle(l_hind_paw, -0.6981317007977318F, 0.0F, 0.0F);
        tail1 = new ModelRenderer(this, 36, 81);
        tail1.setRotationPoint(0.0F, 2.5F, -10.0F);
        tail1.func_228301_a_(-2.5F, -2.5F, -7.0F, 5, 5, 8, 0.0F);
        setRotateAngle(tail1, -0.3490658503988659F, 0.0F, 0.0F);
        upper_jaw = new ModelRenderer(this, 101, 10);
        upper_jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        upper_jaw.func_228301_a_(-2.5F, -2.9F, 7.5F, 5, 3, 5, 0.0F);
        setRotateAngle(upper_jaw, -0.17453292519943295F, 0.0F, 0.0F);
        l_ear = new ModelRenderer(this, 94, 0);
        l_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
        l_ear.func_228301_a_(-4.0F, -7.0F, 0.0F, 4, 4, 2, 0.0F);
        setRotateAngle(l_ear, 0.0F, 0.0F, -0.3490658503988659F);
        l_foreleg1 = new ModelRenderer(this, 2, 0);
        l_foreleg1.setRotationPoint(-5.5F, 3.7F, 6.0F);
        l_foreleg1.func_228301_a_(-1.5F, -2.5F, -2.0F, 3, 8, 4, 0.0F);
        setRotateAngle(l_foreleg1, 0.17453292519943295F, 0.0F, 0.17453292519943295F);
        l_fore_paw = new ModelRenderer(this, 1, 32);
        l_fore_paw.setRotationPoint(0.5F, 3.5F, 0.0F);
        l_fore_paw.func_228301_a_(-1.5F, -0.5F, -1.5F, 4, 2, 6, 0.0F);
        setRotateAngle(l_fore_paw, -0.17453292519943295F, 0.0F, 0.0F);
        tail4 = new ModelRenderer(this, 40, 118);
        tail4.setRotationPoint(0.0F, 0.0F, -7.0F);
        tail4.func_228301_a_(-0.5F, -0.5F, -8.0F, 1, 1, 8, 0.0F);
        setRotateAngle(tail4, -0.08726646259971647F, 0.0F, 0.0F);
        lower_jaw = new ModelRenderer(this, 100, 0);
        lower_jaw.setRotationPoint(0.0F, 3.0F, 4.0F);
        lower_jaw.func_228301_a_(-2.0F, -1.5F, 1.0F, 4, 2, 7, 0.0F);
        tail2 = new ModelRenderer(this, 38, 95);
        tail2.setRotationPoint(0.0F, 0.0F, -6.2F);
        tail2.func_228301_a_(-1.5F, -1.5F, -7.0F, 3, 3, 8, 0.0F);
        setRotateAngle(tail2, -0.17453292519943295F, 0.0F, 0.0F);
        l_hindleg2 = new ModelRenderer(this, 3, 60);
        l_hindleg2.setRotationPoint(-2.0F, 1.5F, -1.5F);
        l_hindleg2.func_228301_a_(-2.0F, -3.0F, -1.5F, 4, 8, 3, 0.0F);
        setRotateAngle(l_hindleg2, -1.2217304763960306F, 0.0F, 0.0F);
        r_hindleg1 = new ModelRenderer(this, 74, 42);
        r_hindleg1.setRotationPoint(5.5F, 4.5F, -5.0F);
        r_hindleg1.func_228301_a_(-0.5F, -2.5F, -3.5F, 5, 8, 7, 0.0F);
        setRotateAngle(r_hindleg1, -0.3490658503988659F, 0.0F, 0.0F);
        r_ear = new ModelRenderer(this, 116, 0);
        r_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
        r_ear.func_228301_a_(0.0F, -7.0F, 0.0F, 4, 4, 2, 0.0F);
        setRotateAngle(r_ear, 0.0F, 0.0F, 0.3490658503988659F);
        l_hindleg3 = new ModelRenderer(this, 5, 74);
        l_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
        l_hindleg3.func_228301_a_(-1.5F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(l_hindleg3, 1.5707963267948966F, -0.0F, 0.0F);
        l_foreleg2 = new ModelRenderer(this, 4, 18);
        l_foreleg2.setRotationPoint(-0.5F, 6.0F, 0.0F);
        l_foreleg2.func_228301_a_(-0.5F, -2.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(l_foreleg2, 0.17453292519943295F, 0.0F, -0.17453292519943295F);
        r_foreleg2 = new ModelRenderer(this, 78, 18);
        r_foreleg2.setRotationPoint(0.5F, 6.0F, 0.0F);
        r_foreleg2.func_228301_a_(-2.5F, -2.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(r_foreleg2, 0.17453292519943295F, 0.0F, 0.17453292519943295F);
        tail3 = new ModelRenderer(this, 39, 107);
        tail3.setRotationPoint(0.0F, 0.0F, -6.0F);
        tail3.func_228301_a_(-1.0F, -1.0F, -8.0F, 2, 2, 8, 0.0F);
        setRotateAngle(tail3, -0.08726646259971647F, 0.0F, 0.0F);
        head = new ModelRenderer(this, 95, 19);
        head.setRotationPoint(0.0F, 0.0F, 2.0F);
        head.func_228301_a_(-4.0F, -3.2F, 0.0F, 8, 7, 8, 0.0F);
        setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
        r_hindleg3 = new ModelRenderer(this, 79, 74);
        r_hindleg3.setRotationPoint(0.0F, 4.0F, 0.5F);
        r_hindleg3.func_228301_a_(-1.5F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(r_hindleg3, 1.5707963267948966F, 0.0F, 0.0F);
        body_mid = new ModelRenderer(this, 32, 40);
        body_mid.setRotationPoint(0.0F, 0.0F, 0.0F);
        body_mid.func_228301_a_(-5.0F, 0.0F, 0.0F, 10, 10, 7, 0.0F);
        setRotateAngle(body_mid, -0.6981317007977318F, 0.0F, 0.0F);
        body_front.addChild(r_foreleg1);
        r_hindleg3.addChild(r_hind_paw);
        body_front.addChild(neck);
        body_mid.addChild(body_front);
        body_rear.addChild(l_hindleg1);
        r_foreleg2.addChild(r_fore_paw);
        r_hindleg1.addChild(r_hindleg2);
        l_hindleg3.addChild(l_hind_paw);
        body_rear.addChild(tail1);
        head.addChild(upper_jaw);
        head.addChild(l_ear);
        body_front.addChild(l_foreleg1);
        l_foreleg2.addChild(l_fore_paw);
        tail3.addChild(tail4);
        head.addChild(lower_jaw);
        tail1.addChild(tail2);
        l_hindleg1.addChild(l_hindleg2);
        body_rear.addChild(r_hindleg1);
        head.addChild(r_ear);
        l_hindleg2.addChild(l_hindleg3);
        l_foreleg1.addChild(l_foreleg2);
        r_foreleg1.addChild(r_foreleg2);
        tail2.addChild(tail3);
        neck.addChild(head);
        r_hindleg2.addChild(r_hindleg3);
        body_rear.addChild(body_mid);
    }
    
	protected Iterable<ModelRenderer> func_225600_b_() {
		return ImmutableList.of(this.body_rear);
	}

	@Override
	public void func_225597_a_(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch) {
		float heady = MathHelper.sin((rotationYaw / (180F / (float) Math.PI)) * 0.5F);
		neck.rotateAngleY = heady;
	}

/*
	@Override
	public void render(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float scale) {
		body_rear.render(scale);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.setRotationAngles(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		//float heady = MathHelper.sin((rotationYaw / (180F / (float) Math.PI)) * 0.5F);
		//neck.rotateAngleY = heady;
	}
*/
	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		
		float animation = MathHelper.sin((limbSwing * 0.4F + 2) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation2 = MathHelper.sin((limbSwing * 0.4F) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation3 = MathHelper.sin((limbSwing * 0.4F + 4) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float flap = MathHelper.sin((entity.ticksExisted) * 0.2F) * 0.6F;

		tail1.rotateAngleY = flap *0.2F;
		tail2.rotateAngleY = tail1.rotateAngleY * 1.5F;
		tail3.rotateAngleY = tail2.rotateAngleY * 1.75F;
		tail4.rotateAngleY = tail3.rotateAngleY * 2F;

		tail1.rotateAngleX = -0.3490658503988659F - animation * 0.5F;
		tail2.rotateAngleX = -0.17453292519943295F - animation * 0.75F;
		tail3.rotateAngleX = -0.08726646259971647F - animation * 1F;
		tail4.rotateAngleX = -0.08726646259971647F - animation * 1.25F;

		if(entity.func_226277_ct_() == entity.lastTickPosX) {
			r_foreleg1.rotateAngleX = 0.17453292519943295F + (animation2 * 8F) + flap * 0.05F;
			r_foreleg2.rotateAngleX = 0.17453292519943295F  + (animation2 * 6F) - flap * 0.025F;
			r_fore_paw.rotateAngleX = -0.17453292519943295F - animation2 * 18F + flap * 0.075F;
			
			l_foreleg1.rotateAngleX = 0.17453292519943295F + (animation * 8F) + flap * 0.05F;
			l_foreleg2.rotateAngleX = 0.17453292519943295F  + (animation * 6F) - flap * 0.025F;
			l_fore_paw.rotateAngleX = -0.17453292519943295F - (animation * 18F) + flap * 0.075F;

			r_hindleg1.rotateAngleX = -0.3490658503988659F - (animation2 * 6F) - flap * 0.05F;
			l_hindleg1.rotateAngleX = -0.3490658503988659F - (animation3 * 6F) - flap * 0.05F;

			r_hindleg2.rotateAngleX = -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2) + flap * 0.05F;
			l_hindleg2.rotateAngleX = -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3) + flap * 0.05F;

			r_hindleg3.rotateAngleX = 1.5707963267948966F + flap * 0.025F;
			l_hindleg3.rotateAngleX = 1.5707963267948966F + flap * 0.025F;

			r_hind_paw.rotateAngleX = -0.6981317007977318F - (r_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;
			l_hind_paw.rotateAngleX = -0.6981317007977318F - (l_hindleg1.rotateAngleX * 1.25F) - flap * 0.05F;

			body_front.rotateAngleX = -0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;

			body_mid.rotateAngleX = -0.6981317007977318F - (animation2 * 2F) - flap * 0.025F;

			body_rear.rotateAngleX = 0.6981317007977318F + (animation2 * 2F) + flap * 0.025F;

			neck.rotateAngleX = 0 + (animation2 * 2.9F) + flap * 0.025F;
		}
		else {
			r_foreleg1.rotateAngleX = 0.17453292519943295F  + animation2 * 8F;
			l_foreleg1.rotateAngleX = 0.17453292519943295F  + animation * 8F;

			r_foreleg2.rotateAngleX = 0.17453292519943295F  + animation2 * 7F;
			l_foreleg2.rotateAngleX = 0.17453292519943295F  + animation * 7F;

			r_fore_paw.rotateAngleX = -0.17453292519943295F - animation2 * 14F;
			l_fore_paw.rotateAngleX = -0.17453292519943295F - animation * 14F;

			r_hindleg1.rotateAngleX = -0.3490658503988659F - animation2 * 6F;
			l_hindleg1.rotateAngleX = -0.3490658503988659F - animation3 * 6F;

			r_hindleg2.rotateAngleX = -1.2217304763960306F + (r_hindleg1.rotateAngleX + animation2);
			l_hindleg2.rotateAngleX = -1.2217304763960306F + (l_hindleg1.rotateAngleX + animation3);

			r_hindleg3.rotateAngleX = 1.5707963267948966F;
			l_hindleg3.rotateAngleX = 1.5707963267948966F;

			r_hind_paw.rotateAngleX = -0.6981317007977318F - r_hindleg1.rotateAngleX * 1.25F;
			l_hind_paw.rotateAngleX = -0.6981317007977318F - l_hindleg1.rotateAngleX * 1.25F;

			body_front.rotateAngleX = -0.17453292519943295F - animation2 * 3F;

			body_mid.rotateAngleX = -0.6981317007977318F - animation2 * 2F;

			body_rear.rotateAngleX = 0.6981317007977318F + animation2 * 2F;

			neck.rotateAngleX = 0 + animation2 * 2.9F;

		}

		//if (!rat.onGround)
		//	lower_jaw.rotateAngleX = -0.9F;
		//else
		//	lower_jaw.rotateAngleX = -0.2490658503988659F + flap * 0.2F;

	}

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	public void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		ImmutableList.of(this.body_rear).forEach((p_228279_8_) -> {
            p_228279_8_.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);});
	}
}
