package animalium.client.model;

import animalium.common.entities.EntityWildDog;
import com.google.common.collect.ImmutableList;
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
public class ModelWildDog<T extends EntityWildDog> extends EntityModel<T> {

    ModelPart body_rear;
    ModelPart body_mid;
    ModelPart tail1;
    ModelPart tail2;
    ModelPart tail3;
    ModelPart r_hindleg1;
    ModelPart l_hindleg1;
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


    public ModelWildDog(ModelPart root) {
        
        this.body_rear = root.getChild("body_rear");
        this.tail1 = body_rear.getChild("tail1");
        this.tail2 = tail1.getChild("tail2");
        this.tail3 = tail2.getChild("tail3");
        this.l_hindleg1 = body_rear.getChild("l_hindleg1");
        this.l_hindleg2 = l_hindleg1.getChild("l_hindleg2");
        this.l_hindleg3 = l_hindleg2.getChild("l_hindleg3");
        this.l_hind_paw = l_hindleg3.getChild("l_hind_paw");
        this.r_hindleg1 = body_rear.getChild("r_hindleg1");
        this.r_hindleg2 = r_hindleg1.getChild("r_hindleg2");
        this.r_hindleg3 = r_hindleg2.getChild("r_hindleg3");
        this.r_hind_paw = r_hindleg3.getChild("r_hind_paw");
        this.body_mid = body_rear.getChild("body_mid");
        this.body_front = body_mid.getChild("body_front");
        this.l_foreleg1 = body_front.getChild("l_foreleg1");
        this.l_foreleg2 = l_foreleg1.getChild("l_foreleg2");
        this.l_fore_paw = l_foreleg2.getChild("l_fore_paw");
        this.neck = body_front.getChild("neck");
        this.head = neck.getChild("head");
        this.lower_jaw = head.getChild("lower_jaw");
        this.upper_jaw = head.getChild("upper_jaw");
        this.r_ear = head.getChild("r_ear");
        this.l_ear = head.getChild("l_ear");
        this.r_foreleg1 = body_front.getChild("r_foreleg1");
        this.r_foreleg2 = r_foreleg1.getChild("r_foreleg2");
        this.r_fore_paw = r_foreleg2.getChild("r_fore_paw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body_rear = partdefinition.addOrReplaceChild("body_rear", CubeListBuilder.create().texOffs(26, 45).addBox(-4.5F, 0.0F, -6.0F, 9.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition tail1 = body_rear.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(80, 29).addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -6.0F, 0.0698F, 0.0F, 0.0F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(81, 39).addBox(-1.5F, -1.5F, -5.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.7F, 0.1047F, 0.0F, 0.0F));

        PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(83, 49).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.6F, 0.0349F, 0.0F, 0.0F));

        PartDefinition l_hindleg1 = body_rear.addOrReplaceChild("l_hindleg1", CubeListBuilder.create().texOffs(7, 36).addBox(-3.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.5F, -2.5F, 0.0F, 0.0F, -0.0127F));

        PartDefinition l_hindleg2 = l_hindleg1.addOrReplaceChild("l_hindleg2", CubeListBuilder.create().texOffs(11, 49).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.5F, -1.5F, -1.2217F, 0.0F, 0.0F));

        PartDefinition l_hindleg3 = l_hindleg2.addOrReplaceChild("l_hindleg3", CubeListBuilder.create().texOffs(0, 55).addBox(-0.9474F, -0.6901F, -0.6992F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, 1.0F, 1.6581F, 0.0F, 0.0F));

        PartDefinition l_hind_paw = l_hindleg3.addOrReplaceChild("l_hind_paw", CubeListBuilder.create().texOffs(9, 58).addBox(-2.0F, -0.2929F, -1.2929F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1077F, 5.8662F, 0.5209F, -0.7854F, 0.0F, 0.0F));

        PartDefinition r_hindleg1 = body_rear.addOrReplaceChild("r_hindleg1", CubeListBuilder.create().texOffs(57, 36).addBox(-1.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 3.5F, -2.5F));

        PartDefinition r_hindleg2 = r_hindleg1.addOrReplaceChild("r_hindleg2", CubeListBuilder.create().texOffs(61, 49).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 4.5342F, -1.5805F, -1.2217F, 0.0F, 0.0F));

        PartDefinition r_hindleg3 = r_hindleg2.addOrReplaceChild("r_hindleg3", CubeListBuilder.create().texOffs(76, 55).addBox(-1.0F, -0.1349F, -1.2469F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.5F, 1.6581F, 0.0F, 0.0F));

        PartDefinition r_hind_paw = r_hindleg3.addOrReplaceChild("r_hind_paw", CubeListBuilder.create().texOffs(59, 58).addBox(-2.0F, -0.2929F, -1.2071F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.2555F, -0.0128F, -0.7854F, 0.0F, 0.0F));

        PartDefinition body_mid = body_rear.addOrReplaceChild("body_mid", CubeListBuilder.create().texOffs(28, 30).addBox(-3.5F, -0.075F, 0.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition body_front = body_mid.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(26, 12).addBox(-4.0F, -0.425F, -5.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.555F, 9.9138F, 0.1745F, 0.0F, 0.0F));

        PartDefinition l_foreleg1 = body_front.addOrReplaceChild("l_foreleg1", CubeListBuilder.create().texOffs(11, 0).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 4.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition l_foreleg2 = l_foreleg1.addOrReplaceChild("l_foreleg2", CubeListBuilder.create().texOffs(13, 12).addBox(-1.0F, 0.0008F, -0.4523F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.3772F, -0.0465F, 1.4835F, 0.0F, 0.0F));

        PartDefinition l_fore_paw = l_foreleg2.addOrReplaceChild("l_fore_paw", CubeListBuilder.create().texOffs(10, 25).addBox(-1.5F, -0.2929F, -1.4964F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.38F, 0.5002F, -0.7854F, 0.0F, 0.0F));

        PartDefinition neck = body_front.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(31, 0).addBox(-2.5F, -0.9581F, -8.9088F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.25F, 7.75F, -0.0873F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(79, 16).addBox(-3.0F, -2.5F, 0.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.9848F, -3.8264F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(81, 0).addBox(-1.5F, -1.2344F, -1.6705F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, 3.475F, -0.1745F, 0.0F, 0.0F));

        PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(82, 9).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8179F, 6.119F, -0.0873F, 0.0F, 0.0F));

        PartDefinition r_ear = head.addOrReplaceChild("r_ear", CubeListBuilder.create().texOffs(94, 0).addBox(-1.15F, -2.5F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -2.5F, 0.0F));

        PartDefinition l_ear = head.addOrReplaceChild("l_ear", CubeListBuilder.create().texOffs(78, 0).addBox(-0.85F, -2.5F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.5F, 0.0F));

        PartDefinition r_foreleg1 = body_front.addOrReplaceChild("r_foreleg1", CubeListBuilder.create().texOffs(60, 0).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 4.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition r_foreleg2 = r_foreleg1.addOrReplaceChild("r_foreleg2", CubeListBuilder.create().texOffs(62, 12).addBox(-1.0F, 0.4067F, -0.9075F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.9019F, -0.4102F, 1.4835F, 0.0F, 0.0F));

        PartDefinition r_fore_paw = r_foreleg2.addOrReplaceChild("r_fore_paw", CubeListBuilder.create().texOffs(59, 25).addBox(-1.5F, -0.2929F, -1.2929F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.6399F, -0.0909F, -0.7854F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body_rear).forEach((p_228279_8_) -> {
            p_228279_8_.render(poseStack, consumer, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float heady = Mth.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
        //neck.yRot = heady;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
        float animation = Mth.sin((limbSwing * 0.6F + 2) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
        float animation2 = Mth.sin((limbSwing * 0.6F) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
        float animation3 = Mth.sin((limbSwing * 0.6F + 4) * 0.5F) * 0.3F * limbSwingAngle * 0.3F;
        float flap = Mth.sin((entity.tickCount) * 0.2F) * 0.6F;

        tail1.yRot = flap * 0.2F;
        tail2.yRot = tail1.yRot * 1.2F;
        tail3.yRot = tail2.yRot * 1.4F;

        tail1.xRot = 0.06981317007977318F - animation * 1F;
        tail2.xRot = 0.10471975511965977F - animation * 3F;
        tail3.xRot = 0.03490658503988659F - animation * 4F;

        if (entity.getX() == entity.xOld) {
            r_foreleg1.xRot = -0.6981317007977318F + (animation2 * 8F) + flap * 0.05F;
            r_foreleg2.xRot = 1.2217304763960306F + (animation2 * 6F) - flap * 0.025F;
            r_fore_paw.xRot = -0.5235987755982988F - animation2 * 18F + flap * 0.075F;

            l_foreleg1.xRot = -0.6981317007977318F + (animation * 8F) + flap * 0.05F;
            l_foreleg2.xRot = 1.2217304763960306F + (animation * 6F) - flap * 0.025F;
            l_fore_paw.xRot = -0.5235987755982988F - (animation * 18F) + flap * 0.075F;

            r_hindleg1.xRot = -0.1F - (animation2 * 6F) - flap * 0.05F;
            l_hindleg1.xRot = -0.1F - (animation3 * 6F) - flap * 0.05F;

            r_hindleg2.xRot = -1.2217304763960306F + (r_hindleg1.xRot + animation2) + flap * 0.05F;
            l_hindleg2.xRot = -1.2217304763960306F + (l_hindleg1.xRot + animation3) + flap * 0.05F;

            r_hindleg3.xRot = 1.5707963267948966F + flap * 0.025F;
            l_hindleg3.xRot = 1.5707963267948966F + flap * 0.025F;

            r_hind_paw.xRot = -0.9981317007977318F - (r_hindleg1.xRot * 1.25F) - flap * 0.05F;
            l_hind_paw.xRot = -0.9981317007977318F - (l_hindleg1.xRot * 1.25F) - flap * 0.05F;

            body_front.xRot = 0.17453292519943295F - (animation2 * 3F) - flap * 0.025F;

            body_mid.xRot = -0.5235987755982988F - (animation2 * 2F) - flap * 0.025F;

            body_rear.xRot = 0.3490658503988659F + (animation2 * 2F) + flap * 0.025F;

            neck.xRot = -0.17453292519943295F + (animation2 * 2.9F) + flap * 0.025F;
        } else {
            r_foreleg1.xRot = -0.6981317007977318F + animation2 * 8F;
            l_foreleg1.xRot = -0.6981317007977318F + animation * 8F;

            r_foreleg2.xRot = 1.2217304763960306F + animation2 * 7F;
            l_foreleg2.xRot = 1.2217304763960306F + animation * 7F;

            r_fore_paw.xRot = -0.5235987755982988F - animation2 * 14F;
            l_fore_paw.xRot = -0.5235987755982988F - animation * 14F;

            r_hindleg1.xRot = -0.1F - animation2 * 6F;
            l_hindleg1.xRot = -0.1F - animation3 * 6F;

            r_hindleg2.xRot = -1.2217304763960306F + (r_hindleg1.xRot + animation2);
            l_hindleg2.xRot = -1.2217304763960306F + (l_hindleg1.xRot + animation3);

            r_hindleg3.xRot = 1.5707963267948966F;
            l_hindleg3.xRot = 1.5707963267948966F;

            r_hind_paw.xRot = -0.9981317007977318F - r_hindleg1.xRot * 1.25F;
            l_hind_paw.xRot = -0.9981317007977318F - l_hindleg1.xRot * 1.25F;

            body_front.xRot = 0.17453292519943295F - animation2 * 3F;

            body_mid.xRot = -0.5235987755982988F - animation2 * 2F;

            body_rear.xRot = 0.3490658503988659F + animation2 * 2F;

            neck.xRot = -0.17453292519943295F + animation2 * 2.9F;

        }

        if (!entity.onGround())//onGround
            lower_jaw.xRot = -0.9F;
        else
            lower_jaw.xRot = -0.2490658503988659F + flap * 0.2F;

    }

    public void setRotateAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.xRot = x;
        ModelPart.yRot = y;
        ModelPart.zRot = z;
    }
}
