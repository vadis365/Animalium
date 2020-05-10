package animalium.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import animalium.entities.EntityPiranha;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelPiranha<T extends EntityPiranha> extends EntityModel<T> {

	ModelRenderer jawBottom;
	ModelRenderer toothBL;
	ModelRenderer toothBML;
	ModelRenderer toothMBR;
	ModelRenderer toothBR;
	ModelRenderer head;
	ModelRenderer toothTL;
	ModelRenderer toothTM;
	ModelRenderer toothTR;
	ModelRenderer bodyMain;
	ModelRenderer finDorsal;
	ModelRenderer finR;
	ModelRenderer finL;
	ModelRenderer bodyBack;
	ModelRenderer tail;
	ModelRenderer finTailTop;
	ModelRenderer finTailBottom;

	public ModelPiranha() {
		textureWidth = 64;
		textureHeight = 128;

		jawBottom = new ModelRenderer(this, 6, 0);
		jawBottom.addBox(-4F, 0F, -5F, 8, 3, 6);
		jawBottom.setRotationPoint(0F, 19F, -5F);
		setRotation(jawBottom, 0.3717861F, 0F, 0F);
		toothBL = new ModelRenderer(this, 6, 0);
		toothBL.addBox(2.8F, -1F, -4.8F, 1, 1, 1);
		toothBL.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBL, 0.3717861F, 0F, 0F);
		toothBML = new ModelRenderer(this, 6, 0);
		toothBML.addBox(0.5F, -1F, -4.8F, 1, 1, 1);
		toothBML.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBML, 0.3717861F, 0F, 0F);
		toothMBR = new ModelRenderer(this, 6, 0);
		toothMBR.addBox(-1.5F, -1F, -4.8F, 1, 1, 1);
		toothMBR.setRotationPoint(0F, 19F, -5F);
		setRotation(toothMBR, 0.3717861F, 0F, 0F);
		toothBR = new ModelRenderer(this, 6, 0);
		toothBR.addBox(-3.8F, -1F, -4.8F, 1, 1, 1);
		toothBR.setRotationPoint(0F, 19F, -5F);
		setRotation(toothBR, 0.3717861F, 0F, 0F);
		head = new ModelRenderer(this, 9, 10);
		head.addBox(-3F, -5F, -4F, 6, 7, 5);
		head.setRotationPoint(0F, 18F, -5F);
		setRotation(head, -0.7853982F, 0F, 0F);
		toothTL = new ModelRenderer(this, 6, 0);
		toothTL.addBox(1.7F, 2F, -3.8F, 1, 1, 1);
		toothTL.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTL, -0.7853982F, 0F, 0F);
		toothTM = new ModelRenderer(this, 6, 0);
		toothTM.addBox(-0.5F, 2F, -3.8F, 1, 1, 1);
		toothTM.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTM, -0.7853982F, 0F, 0F);
		toothTR = new ModelRenderer(this, 6, 0);
		toothTR.addBox(-2.7F, 2F, -3.75F, 1, 1, 1);
		toothTR.setRotationPoint(0F, 18F, -5F);
		setRotation(toothTR, -0.7853982F, 0F, 0F);
		bodyMain = new ModelRenderer(this, 4, 23);
		bodyMain.addBox(-3.5F, -4.5F, 0F, 7, 11, 9);
		bodyMain.setRotationPoint(0F, 16F, -5F);
		setRotation(bodyMain, 0F, 0F, 0F);
		finDorsal = new ModelRenderer(this, 0, 44);
		finDorsal.addBox(-0.5F, -0.5F, 5.4F, 1, 3, 4);
		finDorsal.setRotationPoint(0F, 16F, -5F);
		setRotation(finDorsal, 0.8726646F, 0F, 0F);
		finR = new ModelRenderer(this, 28, 23);
		finR.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 5);
		finR.setRotationPoint(-3.5F, 21F, -3F);
		setRotation(finR, -0.1745329F, -0.4363323F, 0F);
		finL = new ModelRenderer(this, 0, 23);
		finL.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 5);
		finL.setRotationPoint(3.5F, 21F, -3F);
		setRotation(finL, -0.1745329F, 0.4363323F, 0F);
		bodyBack = new ModelRenderer(this, 11, 44);
		bodyBack.addBox(-2.5F, -4F, -1F, 5, 8, 4);
		bodyBack.setRotationPoint(0F, 0F, 9F);
		setRotation(bodyBack, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 13, 57);
		tail.addBox(-1.5F, -2F, -1F, 3, 4, 4);
		tail.setRotationPoint(0F, 0F, 3F);
		setRotation(tail, 0F, 0F, 0F);
		finTailTop = new ModelRenderer(this, 14, 66);
		finTailTop.addBox(-0.5F, -1.5F, 0.5F, 1, 3, 5);
		finTailTop.setRotationPoint(0F, 0F, 2F);
		setRotation(finTailTop, 1.047198F, 0F, 0F);
		finTailBottom = new ModelRenderer(this, 14, 75);
		finTailBottom.addBox(-0.5F, -1.5F, 0.5F, 1, 3, 5);
		finTailBottom.setRotationPoint(0F, 0F, 2F);
		setRotation(finTailBottom, -1.047198F, 0F, 0F);

		bodyMain.addChild(bodyBack);
		bodyBack.addChild(tail);
		tail.addChild(finTailTop);
		tail.addChild(finTailBottom);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(jawBottom, toothBL, toothBML, toothMBR, toothBR, head, toothTL, toothTM, toothTR, bodyMain, finDorsal, finR, finL).forEach((p_228279_8_) -> {
            p_228279_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
	}

	@Override
	 public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
