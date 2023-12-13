package github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie;

import github.nitespring.monsterplus.common.entity.CrystalZombie;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CrystalZombieModel<T extends CrystalZombie> extends ZombieModel<T>{

	public CrystalZombieModel(ModelPart p_171090_) {
		super(p_171090_);
		
	}
	
	
	public static LayerDefinition createBodyLayer() {
	      MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
	      PartDefinition partdefinition = meshdefinition.getRoot();
	      PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-5.0F, -9.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-4.0F, -2.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
	    			.texOffs(0, 34).addBox(-4.0F, -2.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
	    			.texOffs(0, 34).addBox(2.0F, -3.0F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
	    			.texOffs(0, 34).addBox(0.0F, -1.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(0.5F, -3.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
	    			.texOffs(0, 34).addBox(2.0F, -9.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 40).addBox(3.0F, -9.0F, 2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-2.0F, -9.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-1.0F, -9.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-3.0F, -10.0F, 2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-6.0F, -9.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-6.0F, -6.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-6.0F, -6.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-6.0F, -8.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-3.0F, -8.0F, 2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-2.0F, -8.0F, 3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-5.0F, -6.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-5.0F, -7.0F, 2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-1.0F, -4.0F, 2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(0.0F, -7.0F, 2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-2.0F, -6.0F, 3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-5.0F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
	    			.texOffs(0, 34).addBox(-1.0F, -10.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(0.0F, -7.0F, -6.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 40).addBox(3.0F, -6.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-5.0F, -10.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 40).addBox(1.0F, -10.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(-6.0F, -8.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 40).addBox(-5.0F, -4.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
	    			.texOffs(0, 34).addBox(3.0F, -5.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(2.0F, -6.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
	    			.texOffs(0, 34).addBox(2.0F, -7.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-5.0F, -7.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-4.0F, -9.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-2.0F, -7.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(-2.0F, -9.0F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
	    			.texOffs(0, 34).addBox(2.0F, -4.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), 
	    			PartPose.offset(0.0F, 0.0F, 0.0F));
	      
	        head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 46).addBox(-3.5F, -13.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, -0.3491F));
			head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -13.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
			head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 46).addBox(-2.5F, -15.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
			head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 46).addBox(-1.5F, -14.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.1745F));
			head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 46).addBox(-2.5F, -14.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, -0.2618F));
			head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -12.0F, -0.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F));
			head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -13.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));
			head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -13.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));
			head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 46).addBox(-4.0F, -12.0F, -3.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
			head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -13.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));
			head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -14.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));
			head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -15.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));
			head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 46).addBox(-5.5F, -12.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.1745F));
			head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 46).addBox(-5.0F, -12.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
			head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 46).addBox(1.0F, -12.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.7854F));
	      
			partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-8.0F, 1.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F))
					.texOffs(0, 34).addBox(-7.0F, 1.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
					.texOffs(0, 34).addBox(-6.0F, -1.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F))
					.texOffs(0, 34).addBox(5.0F, 2.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F))
					.texOffs(0, 34).addBox(5.5F, 2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-8.5F, 3.0F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-9.0F, 2.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-8.0F, -1.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(-7.0F, 1.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(3.0F, -1.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(3.0F, 1.0F, 0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(4.0F, 2.0F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(5.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(5.0F, -1.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F))
					.texOffs(0, 34).addBox(-3.0F, 7.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.0F, 11.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(0.0F, 8.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-1.0F, 4.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(4.0F, 3.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
					.texOffs(0, 34).addBox(2.0F, 2.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-5.0F, 3.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(2.0F, 7.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(-5.0F, 9.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
					.texOffs(0, 34).addBox(-5.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(-5.0F, 6.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(2.0F, 3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F))
					.texOffs(0, 34).addBox(2.0F, 1.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(2.0F, 10.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(0.0F, 10.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-3.0F, 7.0F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-5.0F, 10.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.0F, 4.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(0.0F, 1.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
					PartPose.offset(0.0F, 0.0F, 0.0F));

			body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 46).addBox(-7.5F, -5.5F, -1.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));
			body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 46).addBox(3.5F, -4.5F, -2.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));
			body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 46).addBox(4.5F, -6.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));
			body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 46).addBox(3.5F, -6.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.2618F));
			body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 46).addBox(-7.5F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.2618F));
			body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 46).addBox(-8.5F, -4.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
			
			partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
			partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
	      
			partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(0, 34).addBox(-1.4F, 9.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.4F, 9.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.4F, 6.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.4F, 2.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.4F, 3.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-1.9F, 3.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.4F, 6.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.4F, 0.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
					.texOffs(0, 34).addBox(-1.4F, 0.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), 
					PartPose.offset(1.9F, 12.0F, 0.0F));

			partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.6F, 9.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.6F, 9.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.6F, 6.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-2.6F, 2.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.6F, 3.5F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-1.6F, 3.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-1.6F, -0.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
					.texOffs(0, 34).addBox(-0.6F, 6.5F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
					PartPose.offset(-1.9F, 12.0F, 0.0F));

	      
	      return LayerDefinition.create(meshdefinition, 64, 64);
	}
	      

}
