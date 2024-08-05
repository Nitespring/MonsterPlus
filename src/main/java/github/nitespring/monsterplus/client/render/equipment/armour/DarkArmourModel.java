package github.nitespring.monsterplus.client.render.equipment.armour;

import github.nitespring.monsterplus.common.entity.EnderEye;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class DarkArmourModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    private final ModelPart cloak;
    public DarkArmourModel(ModelPart root) {
        super(root);
        cloak = body.getChild("cloak");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.75F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");
        PartDefinition hat = partdefinition.getChild("hat");
        PartDefinition body = partdefinition.getChild("body");
        PartDefinition right_arm = partdefinition.getChild("right_arm");
        PartDefinition left_arm = partdefinition.getChild("left_arm");
        PartDefinition right_leg = partdefinition.getChild("right_leg");
        PartDefinition left_leg = partdefinition.getChild("left_leg");

        PartDefinition crest = head.addOrReplaceChild("crest", CubeListBuilder.create().texOffs(32, 60).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 40).addBox(0.0F, -20.0F, -6.0F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, -0.5F, -0.25F, 8.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));
        PartDefinition right_arm_spikes = right_arm.addOrReplaceChild("right_arm_spikes", CubeListBuilder.create().texOffs(24, 49).mirror().addBox(-6.75F, -6.5F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition left_arm_spikes = left_arm.addOrReplaceChild("left_arm_spikes", CubeListBuilder.create().texOffs(24, 49).addBox(-0.25F, -6.5F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        //cloak.copyFrom(body);
        cloak.xRot = 1.5f*limbSwingAmount + (15f - (float) ((10.0 + Math.abs(limbSwingAmount)) * Math.cos(ageInTicks*0.1*(1-0.1f*limbSwingAmount))))* (float) (Math.PI / 180.0);
    }



}
