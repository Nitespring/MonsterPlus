package github.nitespring.monsterplus.client.render.equipment.crystalarmour;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.model.ForgeItemModelShaper;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.generators.loaders.ItemLayerModelBuilder;

import java.awt.*;

public class CrystalArmourModel extends HumanoidModel<LivingEntity>{

	//HumanoidArmorLayer
	private final ResourceLocation resource;
	public CrystalArmourModel(ModelPart p_170677_) {
		super(p_170677_, RenderType::entityTranslucent);
		this.resource = null;
	}
	public CrystalArmourModel(ResourceLocation resource,ModelPart p_170677_) {
		super(p_170677_, RenderType::entityTranslucent);
		this.resource = resource;
	}
	
	

	
	
	public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
	      MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
	      PartDefinition partdefinition = meshdefinition.getRoot();
	      PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
	    		    .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation)
	    			.texOffs(0, 34).addBox(-2.0F, -17.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    			PartPose.offset(0.0F, 0.0F, 0.0F));

	    			head.addOrReplaceChild("crystal_r1", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -15.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.7854F, 0.0F));
	    			head.addOrReplaceChild("crystal_r2", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -15.5F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.7854F, 0.0F));
	    			head.addOrReplaceChild("crystal_r3", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -15.5F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -0.7854F, 0.0F));
	    			head.addOrReplaceChild("crystal_r4", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -15.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.7854F, 0.0F));
	    			head.addOrReplaceChild("crystal_r5", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -14.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F))
	    					.texOffs(0, 34).addBox(-2.0F, -16.0F, -3.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
	    			head.addOrReplaceChild("crystal_r6", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -14.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F))
	    					.texOffs(0, 34).addBox(-2.0F, -13.0F, -0.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
	    			head.addOrReplaceChild("crystal_r7", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-4.0F, -14.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));
	    			head.addOrReplaceChild("crystal_r8", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(0.0F, -14.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));
	    			head.addOrReplaceChild("crystal_r9", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(0.0F, -14.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
	    			head.addOrReplaceChild("crystal_r10", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-4.0F, -14.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
	    			head.addOrReplaceChild("crystal_r11", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(1.0F, -14.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));
	    			head.addOrReplaceChild("crystal_r12", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -10.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, -0.6545F, 0.0F, 0.7854F));
	    			head.addOrReplaceChild("crystal_r13", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -12.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, -0.5236F, 0.0F, 0.7854F));
	    			head.addOrReplaceChild("crystal_r14", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -12.0F, 1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, 0.5236F, 0.0F, 0.7854F));
	    			head.addOrReplaceChild("crystal_r15", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -10.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, -0.7854F));
	    			head.addOrReplaceChild("crystal_r16", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -12.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, -0.7854F));
	    			head.addOrReplaceChild("crystal_r17", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-0.5F, -12.0F, 1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.7854F));
	    			head.addOrReplaceChild("crystal_r18", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-5.0F, -14.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));
	    			head.addOrReplaceChild("crystal_r19", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-1.0F, -14.0F, -4.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, -0.2618F));
	    			head.addOrReplaceChild("crystal_r20", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-3.0F, -14.0F, -4.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.7F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.2618F));
	    			head.addOrReplaceChild("crystal_r21", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-3.0F, -15.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.5236F));
	    			head.addOrReplaceChild("crystal_r22", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-4.0F, -14.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.5236F));
	    			head.addOrReplaceChild("crystal_r23", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(0.0F, -14.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, -0.5236F));
	    			head.addOrReplaceChild("crystal_r24", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-1.0F, -15.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, -0.5236F));
	    			head.addOrReplaceChild("crystal_r25", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -12.0F, -5.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
	    			head.addOrReplaceChild("crystal_r26", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-2.0F, -13.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

	    
	    PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
	    			.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), 
	    			PartPose.offset(0.0F, 0.0F, 0.0F));

	    			body.addOrReplaceChild("crystal_r27", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-5.75F, -2.0F, 5.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F))
	    					.texOffs(0, 34).addBox(-4.75F, -4.0F, 3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.2618F));
	    			body.addOrReplaceChild("crystal_r28", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(1.75F, -2.0F, 5.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F))
	    					.texOffs(0, 34).addBox(0.75F, -4.0F, 3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.2618F));
	    			body.addOrReplaceChild("crystal_r29", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(2.0F, -7.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.5236F));
	    			body.addOrReplaceChild("crystal_r30", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-6.0F, -7.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, -0.5236F));
	    			body.addOrReplaceChild("crystal_r31", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-6.0F, -7.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.5236F));
	    			body.addOrReplaceChild("crystal_r32", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(2.0F, -7.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.5236F));

	    PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
	    			.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), 
	    			PartPose.offset(-5.0F, 2.0F, 0.0F));

	    			right_arm.addOrReplaceChild("crystal_r33", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-10.0F, -7.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));

	    			right_arm.addOrReplaceChild("crystal_r34", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-10.0F, -7.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));

	    			right_arm.addOrReplaceChild("crystal_r35", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-10.0F, -10.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

	    PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
	    			.texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), 
	    			PartPose.offset(5.0F, 2.0F, 0.0F));
	    
	    			left_arm.addOrReplaceChild("crystal_r36", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(6.0F, -7.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, -0.2618F, 0.0F, 0.2618F));
	    			left_arm.addOrReplaceChild("crystal_r37", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(6.0F, -7.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), 
	    					PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));
	    			left_arm.addOrReplaceChild("crystal_r38", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(6.0F, -10.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

	    PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
	    			.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), 
	    			PartPose.offset(-1.9F, 12.0F, 0.0F));

	    			right_leg.addOrReplaceChild("crystal_r39", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(3.0F, 15.5F, 6.25F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, -0.2618F, 0.0F, 0.2618F));
	    			right_leg.addOrReplaceChild("crystal_r40", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-4.0F, 14.0F, 8.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
	    			right_leg.addOrReplaceChild("crystal_r41", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-11.0F, 14.5F, 6.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F))
	    					.texOffs(0, 34).addBox(-12.5F, 14.5F, 5.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));
	    			right_leg.addOrReplaceChild("crystal_r42", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-4.0F, 14.0F, -12.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
	    			right_leg.addOrReplaceChild("crystal_r43", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-11.0F, 14.75F, -10.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F))
	    					.texOffs(0, 34).addBox(-12.5F, 14.5F, -9.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));
	    			right_leg.addOrReplaceChild("crystal_r44", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(3.0F, 15.75F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));
	    			right_leg.addOrReplaceChild("crystal_r45", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-14.5F, 13.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

	    PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
	    			.texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), 
	    			PartPose.offset(1.9F, 12.0F, 0.0F));

	    			left_leg.addOrReplaceChild("crystal_r46", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-7.0F, 15.5F, 6.25F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));
	    			left_leg.addOrReplaceChild("crystal_r47", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(0.0F, 14.0F, 8.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
	    			left_leg.addOrReplaceChild("crystal_r48", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(7.0F, 14.5F, 6.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F))
	    					.texOffs(0, 34).addBox(8.5F, 14.5F, 5.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, -0.2618F, 0.0F, 0.2618F));
	    			left_leg.addOrReplaceChild("crystal_r49", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(-7.0F, 15.5F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));
	    			left_leg.addOrReplaceChild("crystal_r50", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(0.0F, 14.0F, -12.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
	    			left_leg.addOrReplaceChild("crystal_r51", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(7.0F, 14.5F, -10.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F))
	    					.texOffs(0, 34).addBox(8.5F, 14.5F, -9.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));
	    			left_leg.addOrReplaceChild("crystal_r52", CubeListBuilder.create()
	    					.texOffs(0, 34).addBox(10.5F, 13.5F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-1.25F)), 
	    					PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

	      
	      
	      
	      return meshdefinition; //LayerDefinition.create(meshdefinition, 64, 64);
	}




	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer vertexconsumer, int p_102036_, int p_102037_,
							   int i) {
		if(resource != null) {
			vertexconsumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.entityTranslucent(resource,false));
			super.renderToBuffer(stack, vertexconsumer, 255, p_102037_, -1);
		}else {
			super.renderToBuffer(stack, vertexconsumer, 200, p_102037_, FastColor.ARGB32.color(64, 255, 255, 255));
		}
	}
	

	

}
