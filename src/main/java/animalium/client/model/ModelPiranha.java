package animalium.client.model;

import animalium.entities.EntityPiranha;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelPiranha<T extends EntityPiranha> extends EntityModel<T> {

	RendererModel jawBottom;
	RendererModel toothBL;
	RendererModel toothBML;
	RendererModel toothMBR;
	RendererModel toothBR;
	RendererModel head;
	RendererModel toothTL;
	RendererModel toothTM;
	RendererModel toothTR;
	RendererModel bodyMain;
	RendererModel finDorsal;
	RendererModel finR;
	RendererModel finL;
	RendererModel bodyBack;
	RendererModel tail;
	RendererModel finTailTop;
	RendererModel finTailBottom;

	public ModelPiranha() {
		textureWidth = 64;
		textureHeight = 128;

		jawBottom = new RendererModel(this, 6, 0);
		jawBottom.addBox(-4F, 0F, -5F, 8, 3, 6);
		jawBottom.setRotationPoint(0F, 19F, -5F);
		setRotation(jawBottom, 0.3717861F, 0F, 0F);
		toothBL = new RendererModel(this, 6, 0);
		toothBL.addBox(2.8F, -1F, -4.8F, 1, 1, 1);
		toothBL.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBL, 0.3717861F, 0F, 0F);
		toothBML = new RendererModel(this, 6, 0);
		toothBML.addBox(0.5F, -1F, -4.8F, 1, 1, 1);
		toothBML.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBML, 0.3717861F, 0F, 0F);
		toothMBR = new RendererModel(this, 6, 0);
		toothMBR.addBox(-1.5F, -1F, -4.8F, 1, 1, 1);
		toothMBR.setRotationPoint(0F, 19F, -5F);
		setRotation(toothMBR, 0.3717861F, 0F, 0F);
		toothBR = new RendererModel(this, 6, 0);
		toothBR.addBox(-3.8F, -1F, -4.8F, 1, 1, 1);
		toothBR.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBR, 0.3717861F, 0F, 0F);
		head = new RendererModel(this, 9, 10);
		head.addBox(-3F, -5F, -4F, 6, 7, 5);
		head.setRotationPoint(0F, 18F, -5F);
		setRotation(head, -0.7853982F, 0F, 0F);
		toothTL = new RendererModel(this, 6, 0);
		toothTL.addBox(1.7F, 2F, -3.8F, 1, 1, 1);
		toothTL.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTL, -0.7853982F, 0F, 0F);
		toothTM = new RendererModel(this, 6, 0);
		toothTM.addBox(-0.5F, 2F, -3.8F, 1, 1, 1);
		toothTM.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTM, -0.7853982F, 0F, 0F);
		toothTR = new RendererModel(this, 6, 0);
		toothTR.addBox(-2.7F, 2F, -3.75F, 1, 1, 1);
		toothTR.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTR, -0.7853982F, 0F, 0F);
		bodyMain = new RendererModel(this, 4, 23);
		bodyMain.addBox(-3.5F, -4.5F, 0F, 7, 11, 9);
		bodyMain.setRotationPoint(0F, 16F, -5F);
		setRotation(bodyMain, 0F, 0F, 0F);
		finDorsal = new RendererModel(this, 0, 44);
		finDorsal.addBox(-0.5F, -0.5F, 5.4F, 1, 3, 4);
		finDorsal.setRotationPoint(0F, 16F, -5F);
		setRotation(finDorsal, 0.8726646F, 0F, 0F);
		finR = new RendererModel(this, 28, 23);
		finR.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 5);
		finR.setRotationPoint(-3.5F, 21F, -3F);
		setRotation(finR, -0.1745329F, -0.4363323F, 0F);
		finL = new RendererModel(this, 0, 23);
		finL.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 5);
		finL.setRotationPoint(3.5F, 21F, -3F);
		setRotation(finL, -0.1745329F, 0.4363323F, 0F);
		bodyBack = new RendererModel(this, 11, 44);
		bodyBack.addBox(-2.5F, -4F, -1F, 5, 8, 4);
		bodyBack.setRotationPoint(0F, 0F, 9F);
		setRotation(bodyBack, 0F, 0F, 0F);
		tail = new RendererModel(this, 13, 57);
		tail.addBox(-1.5F, -2F, -1F, 3, 4, 4);
		tail.setRotationPoint(0F, 0F, 3F);
		setRotation(tail, 0F, 0F, 0F);
		finTailTop = new RendererModel(this, 14, 66);
		finTailTop.addBox(-0.5F, -1.5F, 0.5F, 1, 3, 5);
		finTailTop.setRotationPoint(0F, 0F, 2F);
		setRotation(finTailTop, 1.047198F, 0F, 0F);
		finTailBottom = new RendererModel(this, 14, 75);
		finTailBottom.addBox(-0.5F, -1.5F, 0.5F, 1, 3, 5);
		finTailBottom.setRotationPoint(0F, 0F, 2F);
		setRotation(finTailBottom, -1.047198F, 0F, 0F);
		
		bodyMain.addChild(bodyBack);
		bodyBack.addChild(tail);
		tail.addChild(finTailTop);
		tail.addChild(finTailBottom);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float scale) {
		jawBottom.render(scale);
		toothBL.render(scale);
		toothBML.render(scale);
		toothMBR.render(scale);
		toothBR.render(scale);
		head.render(scale);
		toothTL.render(scale);
		toothTM.render(scale);
		toothTR.render(scale);
		bodyMain.render(scale);
		finDorsal.render(scale);
		finR.render(scale);
		finL.render(scale);
	}

	private void setRotation(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {

            float flap = MathHelper.sin((entity.ticksExisted + partialRenderTicks) * 0.5F) * 0.6F;
            if (entity.isGrounded())
            	flap = MathHelper.sin((entity.ticksExisted + partialRenderTicks) * 1.5F) * 0.6F;
            jawBottom.rotateAngleX = 0.5F + flap;
    		jawBottom.rotateAngleX = 0.5F + flap;
    		toothBL.rotateAngleX = 0.5F + flap;
    		toothBML.rotateAngleX = 0.5F + flap;
    		toothMBR.rotateAngleX = 0.5F + flap;
    		toothBR.rotateAngleX = 0.5F + flap;

    		finDorsal.rotateAngleY = bodyMain.rotateAngleY = -0.05F + flap * 0.2F;
    		finR.rotateAngleY = -0.5F - flap;
    		finL.rotateAngleY = 0.5F + flap;
    		bodyBack.rotateAngleY = bodyMain.rotateAngleY * 1.2F;
    		tail.rotateAngleY = bodyMain.rotateAngleY * 1.4F;
    		finTailTop.rotateAngleY = bodyMain.rotateAngleY * 1.6F;
    		finTailBottom.rotateAngleY = bodyMain.rotateAngleY * 1.6F;
    }

}
