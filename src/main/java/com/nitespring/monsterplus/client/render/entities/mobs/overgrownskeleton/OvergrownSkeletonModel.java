package com.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton;

import com.nitespring.monsterplus.common.entity.OvergrownSkeleton;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class OvergrownSkeletonModel<T extends OvergrownSkeleton> extends SkeletonModel<T> {

	public OvergrownSkeletonModel(ModelPart p_170941_) {
		super(p_170941_);
		
	}
	
	
	public static LayerDefinition createBodyLayer() {
	    MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
	    PartDefinition partdefinition = meshdefinition.getRoot();
	    PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
	    			.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
	    			.texOffs(0, 52).addBox(5.0F, -1.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 52).addBox(6.0F, -2.0F, 0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

	    			body.addOrReplaceChild("foliage_r1", CubeListBuilder.create().texOffs(43, 48).addBox(5.0F, -5.0F, -3.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3008F, -0.1349F, -0.628F));

	    			body.addOrReplaceChild("foliage_r2", CubeListBuilder.create().texOffs(26, 48).addBox(3.75F, -5.0F, -2.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3726F, -0.1719F, -0.4241F));

	    			body.addOrReplaceChild("foliage_r3", CubeListBuilder.create().texOffs(26, 48).addBox(4.0F, -5.0F, -3.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.2214F, -0.2598F, -0.6681F));

	    			body.addOrReplaceChild("foliage_r4", CubeListBuilder.create().texOffs(9, 48).addBox(3.0F, -5.0F, -3.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3442F, -0.0363F, -0.396F));

	    			body.addOrReplaceChild("foliage_r5", CubeListBuilder.create().texOffs(9, 48).addBox(3.0F, -5.0F, -3.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3894F, -0.1778F, -0.6375F));

	    PartDefinition waist_foliage = body.addOrReplaceChild("waist_foliage", CubeListBuilder.create().texOffs(40, 32).addBox(-4.0F, 10.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

	    PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 52).addBox(3.0F, -9.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 52).addBox(-5.0F, -9.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

	    			head.addOrReplaceChild("foliage_r6", CubeListBuilder.create().texOffs(43, 48).addBox(5.5F, -5.0F, -2.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 3.0F, 0.8943F, -0.3344F, -1.9849F));

	    			head.addOrReplaceChild("foliage_r7", CubeListBuilder.create().texOffs(26, 48).addBox(4.75F, -5.0F, -3.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 3.0F, 0.8478F, -0.4143F, -2.0963F));

	    			head.addOrReplaceChild("foliage_r8", CubeListBuilder.create().texOffs(9, 48).addBox(4.0F, -5.0F, -3.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 3.0F, 0.993F, -0.3646F, -2.0189F));

	    			head.addOrReplaceChild("foliage_r9", CubeListBuilder.create().texOffs(43, 48).addBox(8.5F, -12.0F, 0.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.265F, 0.9183F, -0.6681F));

	    			head.addOrReplaceChild("foliage_r10", CubeListBuilder.create().texOffs(26, 48).addBox(6.25F, -12.5F, -1.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -0.0404F, 1.2302F, -0.7945F));

	    			head.addOrReplaceChild("foliage_r11", CubeListBuilder.create().texOffs(26, 48).addBox(8.0F, -12.0F, -3.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.265F, 0.5256F, -0.6681F));

	    			head.addOrReplaceChild("foliage_r12", CubeListBuilder.create().texOffs(9, 48).addBox(9.5F, -10.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.2691F, 0.1863F, -1.1561F));

	    			head.addOrReplaceChild("foliage_r13", CubeListBuilder.create().texOffs(9, 48).addBox(6.0F, -13.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3054F, 0.1745F, -0.6545F));

	    			head.addOrReplaceChild("foliage_r14", CubeListBuilder.create().texOffs(9, 48).addBox(7.0F, -12.0F, 0.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3054F, 0.9599F, -0.6545F));

	    partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

	    partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

	    partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

	    partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

	    partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

	      
	  
	      return LayerDefinition.create(meshdefinition, 64, 64);
	   }
	
	
	
	

}
