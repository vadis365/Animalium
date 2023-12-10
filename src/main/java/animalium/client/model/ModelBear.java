package animalium.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import animalium.common.entities.EntityBear;
import net.minecraft.client.model.HierarchicalModel;
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
public class ModelBear<T extends EntityBear> extends HierarchicalModel<T> {
	public ModelPart root;
	public ModelPart body_rear;
	public ModelPart body_mid;
	public ModelPart r_hindleg1;
	public ModelPart l_hindleg1;
	public ModelPart body_front;
	public ModelPart neck;
	public ModelPart l_foreleg1;
	public ModelPart r_foreleg1;
	public ModelPart head;
	public ModelPart lower_jaw;
	public ModelPart upper_jaw;
	public ModelPart l_ear;
	public ModelPart r_ear;
	public ModelPart l_foreleg2;
	public ModelPart l_fore_paw;
	public ModelPart r_foreleg2;
	public ModelPart r_fore_paw;
	public ModelPart r_hindleg2;
	public ModelPart r_hindleg3;
	public ModelPart r_hind_paw;
	public ModelPart l_hindleg2;
	public ModelPart l_hindleg3;
	public ModelPart l_hind_paw;

	public ModelBear(ModelPart root) {
		this.root = root;
		body_rear = root.getChild("root").getChild("body_rear");
		l_hindleg1 = body_rear.getChild("l_hindleg1");
		l_hindleg2 = l_hindleg1.getChild("l_hindleg2");
		l_hindleg3 = l_hindleg2.getChild("l_hindleg3");
		l_hind_paw = l_hindleg3.getChild("l_hind_paw");
		body_mid = body_rear.getChild("body_mid");
		body_front = body_mid.getChild("body_front");
		neck = body_front.getChild("neck");
		head = neck.getChild("head");
		upper_jaw = head.getChild("upper_jaw");
		lower_jaw = head.getChild("lower_jaw");
		l_ear = head.getChild("l_ear");
		r_ear = head.getChild("r_ear");
		l_foreleg1 = body_front.getChild("l_foreleg1");
		l_foreleg2 = l_foreleg1.getChild("l_foreleg2");
		l_fore_paw = l_foreleg2.getChild("l_fore_paw");
		r_foreleg1 = body_front.getChild("r_foreleg1");
		r_foreleg2 = r_foreleg1.getChild("r_foreleg2");
		r_fore_paw = r_foreleg2.getChild("r_fore_paw");
		r_hindleg1 = body_rear.getChild("r_hindleg1");
		r_hindleg2 = r_hindleg1.getChild("r_hindleg2");
		r_hindleg3 = r_hindleg2.getChild("r_hindleg3");
		r_hind_paw = r_hindleg3.getChild("r_hind_paw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body_rear = root.addOrReplaceChild("body_rear", CubeListBuilder.create().texOffs(27, 58).addBox(-5.5F, 0.0F, -11.0F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -6.0F, 0.6981F, 0.0F, 0.0F));
		PartDefinition l_hindleg1 = body_rear.addOrReplaceChild("l_hindleg1", CubeListBuilder.create().texOffs(0, 42).addBox(-4.5F, -2.5F, -3.5F, 5.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 4.5F, -5.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition l_hindleg2 = l_hindleg1.addOrReplaceChild("l_hindleg2", CubeListBuilder.create().texOffs(3, 60).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 5.1567F, -1.7677F, -1.3963F, 0.0F, 0.0F));
		PartDefinition l_hindleg3 = l_hindleg2.addOrReplaceChild("l_hindleg3", CubeListBuilder.create().texOffs(5, 74).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.2929F, 1.2071F, 1.5708F, 0.0F, 0.0F));
		PartDefinition l_hind_paw = l_hindleg3.addOrReplaceChild("l_hind_paw", CubeListBuilder.create().texOffs(1, 85).addBox(-2.5F, -1.5F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.4622F, 0.7448F, -0.7854F, 0.0F, 0.0F));
		PartDefinition body_mid = body_rear.addOrReplaceChild("body_mid", CubeListBuilder.create().texOffs(32, 40).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));
		PartDefinition body_front = body_mid.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(26, 15).addBox(-5.5F, -1.1F, -0.5F, 11.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 7.0F, -0.1745F, 0.0F, 0.0F));
		PartDefinition neck = body_front.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(35, 0).addBox(-3.5F, 0.3227F, -7.4031F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.2F, 17.5F));
		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(100, 17).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.8227F, -2.4031F, 0.1745F, 0.0F, 0.0F));
		PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(105, 9).addBox(-2.5F, -1.5693F, -1.3673F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, 5.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(104, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.8F, 2.5F, -0.1196F, 0.0F, 0.0F));
		PartDefinition l_ear = head.addOrReplaceChild("l_ear", CubeListBuilder.create().texOffs(100, 0).addBox(-4.7956F, -1.0666F, 16.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.2F, -18.5F, 0.0F, 0.0F, -0.3491F));
		PartDefinition r_ear = head.addOrReplaceChild("r_ear", CubeListBuilder.create().texOffs(120, 0).addBox(1.0F, -6.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));
		PartDefinition l_foreleg1 = body_front.addOrReplaceChild("l_foreleg1", CubeListBuilder.create().texOffs(2, 0).addBox(-2.5F, -4.5F, -2.5F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.149F, 8.5817F, 0.1745F, 0.0F, 0.1745F));
		PartDefinition l_foreleg2 = l_foreleg1.addOrReplaceChild("l_foreleg2", CubeListBuilder.create().texOffs(4, 18).addBox(-1.5F, -2.0F, -1.5F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, 0.0F, 0.1745F, 0.0F, -0.1745F));
		PartDefinition l_fore_paw = l_foreleg2.addOrReplaceChild("l_fore_paw", CubeListBuilder.create().texOffs(1, 32).addBox(-2.5F, -1.5F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.596F, 7.2352F, 0.681F, -0.1745F, 0.0F, 0.0F));
		PartDefinition r_foreleg1 = body_front.addOrReplaceChild("r_foreleg1", CubeListBuilder.create().texOffs(76, 0).addBox(-2.1336F, -1.5546F, -6.4904F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -0.5F, 12.0F, 0.1745F, 0.0F, -0.1745F));
		PartDefinition r_foreleg2 = r_foreleg1.addOrReplaceChild("r_foreleg2", CubeListBuilder.create().texOffs(78, 18).addBox(-2.3962F, 3.4391F, 0.099F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 5.0F, -7.0F, 0.1745F, 0.0F, 0.1745F));
		PartDefinition r_fore_paw = r_foreleg2.addOrReplaceChild("r_fore_paw", CubeListBuilder.create().texOffs(75, 32).addBox(-2.2515F, -2.0006F, -2.0088F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6972F, 11.9816F, 2.012F, -0.1745F, 0.0F, 0.0F));
		PartDefinition r_hindleg1 = body_rear.addOrReplaceChild("r_hindleg1", CubeListBuilder.create().texOffs(74, 42).addBox(-0.5F, -2.5F, -3.5F, 5.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 4.5F, -5.5F, -0.0873F, 0.0F, 0.0F));
		PartDefinition r_hindleg2 = r_hindleg1.addOrReplaceChild("r_hindleg2", CubeListBuilder.create().texOffs(77, 60).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 5.0F, -1.5F, -1.3963F, 0.0F, 0.0F));
		PartDefinition r_hindleg3 = r_hindleg2.addOrReplaceChild("r_hindleg3", CubeListBuilder.create().texOffs(79, 74).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.2302F, 1.517F, 1.4835F, 0.0F, 0.0F));
		PartDefinition r_hind_paw = r_hindleg3.addOrReplaceChild("r_hind_paw", CubeListBuilder.create().texOffs(75, 85).addBox(-2.5F, -1.75F, -1.5F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, 0.0F, -0.6981F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		root().render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = (float) Math.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		neck.yRot =(float) Math.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		float animation = (float) (Math.sin((limbSwing * 0.6F + 2) * 0.5F) * 0.3F * limbSwingAngle * 0.3F);
		float animation2 = (float) (Math.sin((limbSwing * 0.6F) * 0.5F) * 0.3F * limbSwingAngle * 0.3F);
		float animation3 = (float) (Math.sin((limbSwing * 0.6F + 4) * 0.5F) * 0.3F * limbSwingAngle * 0.3F);
		float flap = (float) (Math.sin((entity.tickCount) * 0.3F) * 0.8F);
		float standingAngle = entity.smoothedAngle(partialRenderTicks);

		if (entity.getX() == entity.xOld) {
			r_foreleg1.xRot = 0.17453292519943295F + (animation2 * 8F) + flap * 0.05F;
			r_foreleg2.xRot = 0.17453292519943295F + (animation2 * 6F) - flap * 0.025F;
			r_fore_paw.xRot = -(standingAngle*1.25F) -0.17453292519943295F - animation2 * 18F + flap * 0.05F;

			l_foreleg1.xRot = 0.17453292519943295F + (animation * 8F) + flap * 0.05F;
			l_foreleg2.xRot = 0.17453292519943295F + (animation * 6F) - flap * 0.025F;
			l_fore_paw.xRot = -(standingAngle * 1.25F) -0.17453292519943295F - (animation * 18F) + flap * 0.05F;

			r_hindleg1.xRot = -(standingAngle * 0.75F) -0.1F - (animation2 * 6F) - flap * 0.05F;
			l_hindleg1.xRot = -(standingAngle * 0.75F) -0.1F - (animation3 * 6F) - flap * 0.05F;

			r_hindleg2.xRot = standingAngle -1.2217304763960306F + (r_hindleg1.xRot + animation2) + flap * 0.05F;
			l_hindleg2.xRot = standingAngle -1.2217304763960306F + (l_hindleg1.xRot + animation3) + flap * 0.05F;

			r_hindleg3.xRot = -standingAngle +1.5707963267948966F + flap * 0.025F;
			l_hindleg3.xRot = -standingAngle +1.5707963267948966F + flap * 0.025F;

			r_hind_paw.xRot = -(standingAngle * 0.5F) -0.9981317007977318F - (r_hindleg1.xRot * 1.25F) - flap * 0.05F;
			l_hind_paw.xRot = -(standingAngle * 0.5F) -0.9981317007977318F - (l_hindleg1.xRot * 1.25F) - flap * 0.05F;

			body_front.xRot = -0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;
			body_front.zRot = 0F - animation2 * 1.5F;

			body_mid.xRot = -0.6981317007977318F - (animation2 * 2F) - flap * 0.025F;

			body_rear.xRot = standingAngle + 0.6981317007977318F + (animation2 * 2F) + flap * 0.025F;

			neck.xRot = -(standingAngle * 0.5F) -0.17453292519943295F + (animation2 * 2.9F) + flap * 0.025F;
			head.xRot = -(standingAngle * 0.5F) + 0.17453292519943295F;
			head.zRot = -(standingAngle * 0.1F * flap * 6F);

		} else {
			r_foreleg1.xRot = 0.17453292519943295F + (standingAngle * 0.5F * flap) + animation2 * 6F;
			l_foreleg1.xRot = 0.17453292519943295F - (standingAngle * 0.5F * flap) + animation * 6F;

			r_foreleg2.xRot = 0.17453292519943295F + animation2 * 5F;
			l_foreleg2.xRot = 0.17453292519943295F + animation * 5F;

			r_fore_paw.xRot = -(standingAngle * 1.25F) -0.17453292519943295F - animation2 * 12F;
			l_fore_paw.xRot = -(standingAngle * 1.25F) -0.17453292519943295F - animation * 12F;

			r_hindleg1.xRot = -(standingAngle * 0.75F) -0.1F - animation2 * 6F;
			l_hindleg1.xRot = -(standingAngle * 0.75F) -0.1F - animation3 * 6F;

			r_hindleg2.xRot = standingAngle -1.2217304763960306F + (r_hindleg1.xRot + animation2);
			l_hindleg2.xRot = standingAngle -1.2217304763960306F + (l_hindleg1.xRot + animation3);

			r_hindleg3.xRot = -standingAngle + 1.5707963267948966F;
			l_hindleg3.xRot = -standingAngle + 1.5707963267948966F;

			r_hind_paw.xRot = -(standingAngle * 0.5F) -0.9981317007977318F - r_hindleg1.xRot * 1.25F;
			l_hind_paw.xRot = -(standingAngle * 0.5F) -0.9981317007977318F - l_hindleg1.xRot * 1.25F;

			body_front.xRot = -0.17453292519943295F - animation2 * 3F;

			body_front.zRot = (standingAngle * 0.5F * flap);

			body_mid.xRot = -0.6981317007977318F - animation2 * 2F;

			body_rear.xRot = standingAngle + 0.6981317007977318F + animation2 * 2F;

			neck.xRot = -(standingAngle * 0.5F) -0.17453292519943295F + animation2 * 2.9F;
			head.xRot = -(standingAngle * 0.5F) + 0.17453292519943295F;
			head.zRot = -(standingAngle * 0.1F * flap * 6F);
		}

		if (!entity.onGround())
			lower_jaw.xRot = -0.9F;
		else {
			if(standingAngle > 0)
				lower_jaw.xRot = -0.5F + flap * 0.75F;
			else
				lower_jaw.xRot = -0.2490658503988659F + flap * 0.2F;
			}
	}

	public void setRotateAngle(ModelPart ModelPart, float x, float y, float z) {
		ModelPart.xRot = x;
		ModelPart.yRot = y;
		ModelPart.zRot = z;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
