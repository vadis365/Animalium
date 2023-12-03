package animalium.client.model;

import com.google.common.collect.ImmutableList;

import animalium.common.entities.EntityRat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelRat<T extends EntityRat> extends EntityModel<T> {
    ModelPart body_rear;
    ModelPart body_mid;
    ModelPart r_hindleg1;
    ModelPart l_hindleg1;
    ModelPart tail1;
    ModelPart body_front;
    ModelPart neck;
    ModelPart l_foreleg1;
    ModelPart r_foreleg1;
    ModelPart head;
    ModelPart lower_jaw;
    ModelPart upper_jaw;
    ModelPart l_ear;
    ModelPart r_ear;
    ModelPart l_foreleg2;
    ModelPart l_fore_paw;
    ModelPart r_foreleg2;
    ModelPart r_fore_paw;
    ModelPart r_hindleg2;
    ModelPart r_hindleg3;
    ModelPart r_hind_paw;
    ModelPart l_hindleg2;
    ModelPart l_hindleg3;
    ModelPart l_hind_paw;
    ModelPart tail2;
    ModelPart tail3;
    ModelPart tail4;

    public ModelRat(ModelPart root) {

        this.body_rear = root.getChild("body_rear");
        this.l_hindleg1 = body_rear.getChild("l_hindleg1");
        this.l_hindleg2 = l_hindleg1.getChild("l_hindleg2");
        this.l_hindleg3 = l_hindleg2.getChild("l_hindleg3");
        this.l_hind_paw = l_hindleg3.getChild("l_hind_paw");
        this.tail1 = body_rear.getChild("tail1");
        this.tail2 = tail1.getChild("tail2");
        this.tail3 = tail2.getChild("tail3");
        this.tail4 = tail3.getChild("tail4");
        this.r_hindleg1 = body_rear.getChild("r_hindleg1");
        this.r_hindleg2 = r_hindleg1.getChild("r_hindleg2");
        this.r_hindleg3 = r_hindleg2.getChild("r_hindleg3");
        this.r_hind_paw = r_hindleg3.getChild("r_hind_paw");
        this.body_mid = body_rear.getChild("body_mid");
        this.body_front = body_mid.getChild("body_front");
        this.r_foreleg1 = body_front.getChild("r_foreleg1");
        this.r_foreleg2 = r_foreleg1.getChild("r_foreleg2");
        this.r_fore_paw = r_foreleg2.getChild("r_fore_paw");
        this.neck = body_front.getChild("neck");
        this.head = neck.getChild("head");
        this.upper_jaw = head.getChild("upper_jaw");
        this.l_ear = head.getChild("l_ear");
        this.lower_jaw = head.getChild("lower_jaw");
        this.r_ear = head.getChild("r_ear");
        this.l_foreleg1 = body_front.getChild("l_foreleg1");
        this.l_foreleg2 = l_foreleg1.getChild("l_foreleg2");
        this.l_fore_paw = l_foreleg2.getChild("l_fore_paw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body_rear = partdefinition.addOrReplaceChild("body_rear", CubeListBuilder.create().texOffs(27, 58).addBox(-5.5F, 0.0F, -11.0F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.5F, -6.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition l_hindleg1 = body_rear.addOrReplaceChild("l_hindleg1", CubeListBuilder.create().texOffs(0, 42).addBox(-4.5F, -2.5F, -3.5F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 4.5F, -5.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition l_hindleg2 = l_hindleg1.addOrReplaceChild("l_hindleg2", CubeListBuilder.create().texOffs(3, 60).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.5F, -1.5F, -1.2217F, 0.0F, 0.0F));

        PartDefinition l_hindleg3 = l_hindleg2.addOrReplaceChild("l_hindleg3", CubeListBuilder.create().texOffs(5, 74).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition l_hind_paw = l_hindleg3.addOrReplaceChild("l_hind_paw", CubeListBuilder.create().texOffs(1, 85).addBox(-2.5F, -0.5F, -1.5F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.525F, 4.0F, -0.5F, -0.6981F, 0.0F, 0.0F));

        PartDefinition tail1 = body_rear.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(36, 81).addBox(-2.5F, -2.5F, -7.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -10.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(38, 95).addBox(-1.5F, -1.5F, -7.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.2F, -0.1745F, 0.0F, 0.0F));

        PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(39, 107).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, -0.5F, -8.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.8F, -0.0873F, 0.0011F, 0.013F));

        PartDefinition r_hindleg1 = body_rear.addOrReplaceChild("r_hindleg1", CubeListBuilder.create().texOffs(74, 42).addBox(-0.5F, -2.5F, -3.5F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 4.5F, -5.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition r_hindleg2 = r_hindleg1.addOrReplaceChild("r_hindleg2", CubeListBuilder.create().texOffs(77, 60).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.5F, -1.475F, -1.2217F, 0.0F, 0.0F));

        PartDefinition r_hindleg3 = r_hindleg2.addOrReplaceChild("r_hindleg3", CubeListBuilder.create().texOffs(79, 74).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition r_hind_paw = r_hindleg3.addOrReplaceChild("r_hind_paw", CubeListBuilder.create().texOffs(75, 85).addBox(-2.5F, -0.5F, -1.5F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.0F, -0.5F, -0.6981F, 0.0F, 0.0F));

        PartDefinition body_mid = body_rear.addOrReplaceChild("body_mid", CubeListBuilder.create().texOffs(32, 40).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition body_front = body_mid.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(26, 15).addBox(-5.5F, -1.1F, -0.5F, 11.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.05F, 6.0F, -0.0829F, 0.0F, 0.0F));

        PartDefinition r_foreleg1 = body_front.addOrReplaceChild("r_foreleg1", CubeListBuilder.create().texOffs(76, 0).addBox(-1.5F, -2.5F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 3.7F, 6.0F, 0.1745F, 0.0F, -0.1745F));

        PartDefinition r_foreleg2 = r_foreleg1.addOrReplaceChild("r_foreleg2", CubeListBuilder.create().texOffs(78, 18).addBox(-2.5F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, 0.1745F, 0.0F, 0.1745F));

        PartDefinition r_fore_paw = r_foreleg2.addOrReplaceChild("r_fore_paw", CubeListBuilder.create().texOffs(75, 32).addBox(-2.4116F, -1.2891F, -4.393F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.45F, 4.8F, -0.225F, -2.8753F, -0.0295F, 3.1336F));

        PartDefinition neck = body_front.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(35, 0).addBox(-3.5F, -3.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.275F, 10.875F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(95, 19).addBox(-4.0F, -4.1998F, -0.0175F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.525F, 2.0F, -0.0567F, 0.0F, 0.0F));

        PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(101, 10).addBox(-2.5F, -2.1483F, 25.6245F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5998F, -18.5175F, -0.1745F, 0.0F, 0.0F));

        PartDefinition l_ear = head.addOrReplaceChild("l_ear", CubeListBuilder.create().texOffs(94, 0).addBox(-2.0834F, -3.4944F, -1.2585F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.5998F, 0.4825F, 0.0F, 0.0F, -0.3491F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(100, 0).addBox(-2.0F, -1.6124F, -2.2814F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.4002F, 6.4825F, -0.1745F, 0.0F, 0.0F));

        PartDefinition r_ear = head.addOrReplaceChild("r_ear", CubeListBuilder.create().texOffs(116, 0).addBox(-1.9166F, -3.4944F, -1.2585F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.5998F, 0.4825F, 0.0F, 0.0F, 0.3491F));

        PartDefinition l_foreleg1 = body_front.addOrReplaceChild("l_foreleg1", CubeListBuilder.create().texOffs(2, 0).addBox(-1.5F, -2.5F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.7F, 6.0F, 0.1745F, 0.0F, 0.1745F));

        PartDefinition l_foreleg2 = l_foreleg1.addOrReplaceChild("l_foreleg2", CubeListBuilder.create().texOffs(4, 18).addBox(-0.5F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, 0.0F, 0.1745F, 0.0F, -0.1745F));

        PartDefinition l_fore_paw = l_foreleg2.addOrReplaceChild("l_fore_paw", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -0.5F, -1.5F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.975F, -0.2F, -0.2662F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
 
	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer consumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body_rear).forEach((p_228279_8_) -> {
            p_228279_8_.render(matrixStackIn, consumer, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
	}

	@Override
	 public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float heady = Mth.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		ItemStack stack = entity.getMainHandItem();
//		if (!stack.isEmpty())
//			neck.yRot = 0F;
//		else
//			neck.yRot = heady;
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {

		float animation = Mth.sin((limbSwing * 0.4F + 2) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation2 = Mth.sin((limbSwing * 0.4F) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float animation3 = Mth.sin((limbSwing * 0.4F + 4) * 1.5F) * 0.3F * limbSwingAngle * 0.3F;
		float flap = Mth.sin((entity.tickCount) * 0.2F) * 0.6F;

		tail1.yRot = flap *0.2F;
		tail2.yRot = tail1.yRot * 1.5F;
		tail3.yRot = tail2.yRot * 1.75F;
		tail4.yRot = tail3.yRot * 2F;

		tail1.xRot = -0.3490658503988659F - animation * 0.5F;
		tail2.xRot = -0.17453292519943295F - animation * 0.75F;
		tail3.xRot = -0.08726646259971647F - animation * 1F;
		tail4.xRot = -0.08726646259971647F - animation * 1.25F;

		if(entity.getX() == entity.xOld) {
			r_foreleg1.xRot = 0.17453292519943295F + (animation2 * 8F) + flap * 0.05F;
			r_foreleg2.xRot = 0.17453292519943295F  + (animation2 * 6F) - flap * 0.025F;
			r_fore_paw.xRot = -0.17453292519943295F - animation2 * 18F + flap * 0.075F;

			l_foreleg1.xRot = 0.17453292519943295F + (animation * 8F) + flap * 0.05F;
			l_foreleg2.xRot = 0.17453292519943295F  + (animation * 6F) - flap * 0.025F;
			l_fore_paw.xRot = -0.17453292519943295F - (animation * 18F) + flap * 0.075F;

			r_hindleg1.xRot = -0.3490658503988659F - (animation2 * 6F) - flap * 0.05F;
			l_hindleg1.xRot = -0.3490658503988659F - (animation3 * 6F) - flap * 0.05F;

			r_hindleg2.xRot = -1.2217304763960306F + (r_hindleg1.xRot + animation2) + flap * 0.05F;
			l_hindleg2.xRot = -1.2217304763960306F + (l_hindleg1.xRot + animation3) + flap * 0.05F;

			r_hindleg3.xRot = 1.5707963267948966F + flap * 0.025F;
			l_hindleg3.xRot = 1.5707963267948966F + flap * 0.025F;

			r_hind_paw.xRot = -0.6981317007977318F - (r_hindleg1.xRot * 1.25F) - flap * 0.05F;
			l_hind_paw.xRot = -0.6981317007977318F - (l_hindleg1.xRot * 1.25F) - flap * 0.05F;

			body_front.xRot = -0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;

			body_mid.xRot = -0.6981317007977318F - (animation2 * 2F) - flap * 0.025F;

			body_rear.xRot = 0.6981317007977318F + (animation2 * 2F) + flap * 0.025F;

			neck.xRot = 0 + (animation2 * 2.9F) + flap * 0.025F;
		}
		else {
			r_foreleg1.xRot = 0.17453292519943295F  + animation2 * 8F;
			l_foreleg1.xRot = 0.17453292519943295F  + animation * 8F;

			r_foreleg2.xRot = 0.17453292519943295F  + animation2 * 7F;
			l_foreleg2.xRot = 0.17453292519943295F  + animation * 7F;

			r_fore_paw.xRot = -0.17453292519943295F - animation2 * 14F;
			l_fore_paw.xRot = -0.17453292519943295F - animation * 14F;

			r_hindleg1.xRot = -0.3490658503988659F - animation2 * 6F;
			l_hindleg1.xRot = -0.3490658503988659F - animation3 * 6F;

			r_hindleg2.xRot = -1.2217304763960306F + (r_hindleg1.xRot + animation2);
			l_hindleg2.xRot = -1.2217304763960306F + (l_hindleg1.xRot + animation3);

			r_hindleg3.xRot = 1.5707963267948966F;
			l_hindleg3.xRot = 1.5707963267948966F;

			r_hind_paw.xRot = -0.6981317007977318F - r_hindleg1.xRot * 1.25F;
			l_hind_paw.xRot = -0.6981317007977318F - l_hindleg1.xRot * 1.25F;

			body_front.xRot = -0.17453292519943295F - animation2 * 3F;

			body_mid.xRot = -0.6981317007977318F - animation2 * 2F;

			body_rear.xRot = 0.6981317007977318F + animation2 * 2F;

			neck.xRot = 0 + animation2 * 2.9F;
		}

		if (!entity.onGround())
			lower_jaw.xRot = -0.9F;
		else
			lower_jaw.xRot = -0.2490658503988659F + flap * 0.2F;

	}

}
