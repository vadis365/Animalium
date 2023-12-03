package animalium.client.model;

import com.google.common.collect.ImmutableList;

import animalium.common.entities.EntityPiranha;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelPiranha<T extends EntityPiranha> extends EntityModel<T> {

	private final ModelPart jawBottom;
	private final ModelPart toothBL;
	private final ModelPart toothBML;
	private final ModelPart toothMBR;
	private final ModelPart toothBR;
	private final ModelPart head;
	private final ModelPart toothTL;
	private final ModelPart toothTM;
	private final ModelPart toothTR;
	private final ModelPart bodyMain;
	private final ModelPart finDorsal;
	private final ModelPart finR;
	private final ModelPart finL;
	private final ModelPart bodyBack;
	private final ModelPart tail;
	private final ModelPart finTailTop;
	private final ModelPart finTailBottom;



	public ModelPiranha(ModelPart root) {
		this.jawBottom = root.getChild("jawBottom");
		this.toothBL = root.getChild("toothBL");
		this.toothBML = root.getChild("toothBML");
		this.toothMBR = root.getChild("toothMBR");
		this.toothBR = root.getChild("toothBR");
		this.head = root.getChild("head");
		this.toothTL = root.getChild("toothTL");
		this.toothTM = root.getChild("toothTM");
		this.toothTR = root.getChild("toothTR");
		this.bodyMain = root.getChild("bodyMain");
		this.finDorsal = root.getChild("finDorsal");
		this.finR = root.getChild("finR");
		this.finL = root.getChild("finL");
		this.bodyBack = bodyMain.getChild("bodyBack");
		this.tail = bodyBack.getChild("tail");
		this.finTailTop = tail.getChild("finTailTop");
		this.finTailBottom = tail.getChild("finTailBottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition jawBottom = partdefinition.addOrReplaceChild("jawBottom", CubeListBuilder.create().texOffs(6, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));

		PartDefinition toothBL = partdefinition.addOrReplaceChild("toothBL", CubeListBuilder.create().texOffs(6, 0).addBox(2.8F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));

		PartDefinition toothBML = partdefinition.addOrReplaceChild("toothBML", CubeListBuilder.create().texOffs(6, 0).addBox(0.5F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));

		PartDefinition toothMBR = partdefinition.addOrReplaceChild("toothMBR", CubeListBuilder.create().texOffs(6, 0).addBox(-1.5F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));

		PartDefinition toothBR = partdefinition.addOrReplaceChild("toothBR", CubeListBuilder.create().texOffs(6, 0).addBox(-3.8F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(9, 10).addBox(-3.0F, -5.0F, -4.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition toothTL = partdefinition.addOrReplaceChild("toothTL", CubeListBuilder.create().texOffs(6, 0).addBox(1.7F, 2.0F, -3.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition toothTM = partdefinition.addOrReplaceChild("toothTM", CubeListBuilder.create().texOffs(6, 0).addBox(-0.5F, 2.0F, -3.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition toothTR = partdefinition.addOrReplaceChild("toothTR", CubeListBuilder.create().texOffs(6, 0).addBox(-2.7F, 2.0F, -3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition bodyMain = partdefinition.addOrReplaceChild("bodyMain", CubeListBuilder.create().texOffs(4, 23).addBox(-3.5F, -4.5F, 0.0F, 7.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -5.0F));

		PartDefinition bodyBack = bodyMain.addOrReplaceChild("bodyBack", CubeListBuilder.create().texOffs(11, 44).addBox(-2.5F, -4.0F, -1.0F, 5.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail = bodyBack.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(13, 57).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition finTailTop = tail.addOrReplaceChild("finTailTop", CubeListBuilder.create().texOffs(14, 66).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition finTailBottom = tail.addOrReplaceChild("finTailBottom", CubeListBuilder.create().texOffs(14, 75).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition finDorsal = partdefinition.addOrReplaceChild("finDorsal", CubeListBuilder.create().texOffs(0, 44).addBox(-0.5F, -0.5F, 5.4F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -5.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition finR = partdefinition.addOrReplaceChild("finR", CubeListBuilder.create().texOffs(28, 23).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 21.0F, -3.0F, -0.1745F, -0.4363F, 0.0F));

		PartDefinition finL = partdefinition.addOrReplaceChild("finL", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 21.0F, -3.0F, -0.1745F, 0.4363F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer consumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(jawBottom, toothBL, toothBML, toothMBR, toothBR, head, toothTL, toothTM, toothTR, bodyMain, finDorsal, finR, finL).forEach((p_228279_8_) -> {
            p_228279_8_.render(matrixStackIn, consumer, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
	}

	@Override
	 public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}


	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
            float flap = Mth.sin((entity.tickCount + partialRenderTicks) * 0.5F) * 0.6F;
            if (entity.isGrounded())
            	flap = Mth.sin((entity.tickCount + partialRenderTicks) * 1.5F) * 0.6F;
    		jawBottom.xRot = 0.5F + flap;
    		toothBL.xRot = 0.5F + flap;
    		toothBML.xRot = 0.5F + flap;
    		toothMBR.xRot = 0.5F + flap;
    		toothBR.xRot = 0.5F + flap;

    		finDorsal.yRot = bodyMain.yRot = -0.05F + flap * 0.2F;
    		finR.yRot = -0.5F - flap;
    		finL.yRot = 0.5F + flap;
    		bodyBack.yRot = bodyMain.yRot * 1.2F;
    		tail.yRot = bodyMain.yRot * 1.4F;
    		finTailTop.yRot = bodyMain.yRot * 1.6F;
    		finTailBottom.yRot = bodyMain.yRot * 1.6F;
    }

}
