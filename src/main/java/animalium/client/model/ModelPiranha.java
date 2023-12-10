package animalium.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import animalium.common.entities.EntityPiranha;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelPiranha<T extends EntityPiranha> extends EntityModel<T> {
	public ModelPart root;
	public ModelPart jawBottom;
	public ModelPart toothBL;
	public ModelPart toothBML;
	public ModelPart toothMBR;
	public ModelPart toothBR;
	public ModelPart head;
	public ModelPart toothTL;
	public ModelPart toothTM;
	public ModelPart toothTR;
	public ModelPart bodyMain;
	public ModelPart finDorsal;
	public ModelPart finR;
	public ModelPart finL;
	public ModelPart bodyBack;
	public ModelPart tail;
	public ModelPart finTailTop;
	public ModelPart finTailBottom;

	public ModelPiranha(ModelPart root) {
		this.root = root;
		jawBottom = root.getChild("root").getChild("jawBottom");
		toothBL = root.getChild("toothBL");
		toothBML = root.getChild("toothBML");
		toothMBR = root.getChild("toothMBR");
		toothBR = root.getChild("toothBR");
		head = root.getChild("head");
		toothTL = root.getChild("toothTL");
		toothTM = root.getChild("toothTM");
		toothTR = root.getChild("toothTR");
		bodyMain = root.getChild("bodyMain");
		finDorsal = root.getChild("finDorsal");
		finR = root.getChild("finR");
		finL = root.getChild("finL");
		bodyBack = bodyMain.getChild("bodyBack");
		tail = bodyBack.getChild("tail");
		finTailTop = tail.getChild("finTailTop");
		finTailBottom = tail.getChild("finTailBottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition toothBML = root.addOrReplaceChild("toothBML", CubeListBuilder.create().texOffs(6, 0).addBox(-1.5F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));
		PartDefinition bodyMain = root.addOrReplaceChild("bodyMain", CubeListBuilder.create().texOffs(4, 23).addBox(-3.5F, -4.5F, 0.0F, 7.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -5.0F));
		PartDefinition bodyBack = bodyMain.addOrReplaceChild("bodyBack", CubeListBuilder.create().texOffs(11, 44).addBox(-2.5F, -4.0F, -1.0F, 5.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));
		PartDefinition tail = bodyBack.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(13, 57).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));
		PartDefinition finTailTop = tail.addOrReplaceChild("finTailTop", CubeListBuilder.create().texOffs(14, 66).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 1.0472F, 0.0F, 0.0F));
		PartDefinition finTailBottom = tail.addOrReplaceChild("finTailBottom", CubeListBuilder.create().texOffs(14, 75).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -1.0472F, 0.0F, 0.0F));
		PartDefinition finR = root.addOrReplaceChild("finR", CubeListBuilder.create().texOffs(28, 23).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 21.0F, -3.0F, -0.1745F, 0.4363F, 0.0F));
		PartDefinition toothTR = root.addOrReplaceChild("toothTR", CubeListBuilder.create().texOffs(6, 0).addBox(1.7F, 2.0F, -3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition finDorsal = root.addOrReplaceChild("finDorsal", CubeListBuilder.create().texOffs(1, 45).addBox(-0.5F, -0.5F, 5.4F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -5.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition jawBottom = root.addOrReplaceChild("jawBottom", CubeListBuilder.create().texOffs(6, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));
		PartDefinition toothMBR = root.addOrReplaceChild("toothMBR", CubeListBuilder.create().texOffs(6, 0).addBox(0.5F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));
		PartDefinition toothTM = root.addOrReplaceChild("toothTM", CubeListBuilder.create().texOffs(6, 0).addBox(-0.5F, 2.0F, -3.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition finL = root.addOrReplaceChild("finL", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 21.0F, -3.0F, -0.1745F, -0.4363F, 0.0F));
		PartDefinition toothTL = root.addOrReplaceChild("toothTL", CubeListBuilder.create().texOffs(6, 0).addBox(-2.7F, 2.0F, -3.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(9, 10).addBox(-3.0F, -5.0F, -4.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition toothBR = root.addOrReplaceChild("toothBR", CubeListBuilder.create().texOffs(6, 0).addBox(2.8F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));
		PartDefinition toothBL = root.addOrReplaceChild("toothBL", CubeListBuilder.create().texOffs(6, 0).addBox(-3.8F, -1.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.3718F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		root.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
