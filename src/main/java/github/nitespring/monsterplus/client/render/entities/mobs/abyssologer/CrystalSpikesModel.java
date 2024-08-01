package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.nitespring.monsterplus.common.entity.projectiles.CrystalSpikes;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;


public class CrystalSpikesModel <T extends CrystalSpikes> extends HierarchicalModel<T> {
	
	private final ModelPart root;
	private final ModelPart base;
	private final ModelPart segment_1;
	private final ModelPart segment_2;
	private final ModelPart segment_3;
	private final ModelPart segment_4;
	private final ModelPart tip;

	
	public CrystalSpikesModel(ModelPart root) {
		this.root = root;
		this.base = root.getChild("base");
		this.segment_1 = root.getChild("segment_1");
		this.segment_2 = root.getChild("segment_2");
		this.segment_3 = root.getChild("segment_3");
		this.segment_4 = root.getChild("segment_4");
		this.tip = root.getChild("base");
		
	}
	
	

	
	
		
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition cube_r1 = base.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 7).addBox(1.0F, -7.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.4797F, 1.1955F, 2.8564F));

		PartDefinition cube_r2 = base.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 7).addBox(0.0F, -9.0F, -4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0155F, 0.2849F, 1.2867F));

		PartDefinition cube_r3 = base.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 7).addBox(-3.0F, -10.0F, 1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9294F, 0.5576F, 0.0074F));

		PartDefinition cube_r4 = base.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 7).addBox(-3.0F, -10.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8596F, 0.5576F, 0.0074F));

		PartDefinition cube_r5 = base.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 10).addBox(-4.5F, -5.0F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r6 = base.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition segment_1 = partdefinition.addOrReplaceChild("segment_1", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition cube_r7 = segment_1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 20).addBox(-1.5F, -3.0F, -3.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -6.0F, -0.5F, 0.3054F, -0.3491F, -0.7854F));

		PartDefinition cube_r8 = segment_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(52, 20).addBox(-3.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5571F, -3.8856F, -1.4364F, 0.259F, 0.2768F, 0.9816F));

		PartDefinition cube_r9 = segment_1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(52, 20).addBox(-0.5F, -5.0F, -2.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4429F, -3.8856F, -4.4364F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r10 = segment_1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(52, 20).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0849F, -4.3064F, 2.934F, -0.8812F, 0.7441F, 0.0493F));

		PartDefinition cube_r11 = segment_1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 20).addBox(-0.5F, -6.0F, -3.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3303F, -7.1009F, 3.1205F, -1.0809F, -0.7454F, 0.1249F));

		PartDefinition cube_r12 = segment_1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, 5.0F, 11.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(53, 9).addBox(-3.0F, 0.0F, 4.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2927F, -21.5464F, -3.2778F, 2.2935F, 0.8279F, -3.1355F));

		PartDefinition cube_r13 = segment_1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(53, 9).addBox(-2.0F, 10.0F, -24.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.8199F, 0.4962F, -0.0091F));

		PartDefinition cube_r14 = segment_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, 12.0F, 21.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r15 = segment_1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(53, 9).addBox(-26.0F, 8.0F, 2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.3613F, 0.1422F, -0.9988F));

		PartDefinition cube_r16 = segment_1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(53, 9).addBox(20.0F, 13.0F, 5.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.0846F, 0.1719F, 0.7287F));

		PartDefinition cube_r17 = segment_1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(53, 9).addBox(3.0F, 10.0F, 17.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.6411F, 0.4483F, 0.1053F));

		PartDefinition cube_r18 = segment_1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(53, 9).addBox(0.0F, -14.0F, 18.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r19 = segment_1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(53, 9).addBox(-2.0F, 10.0F, -16.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.6454F, 0.4962F, -0.0091F));

		PartDefinition cube_r20 = segment_1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(53, 9).addBox(-14.0F, 6.0F, 10.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.3927F, -0.3054F, -0.6545F));

		PartDefinition cube_r21 = segment_1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(53, 9).addBox(-3.0F, 13.0F, 8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.3579F, 0.8451F, -0.0475F));

		PartDefinition cube_r22 = segment_1.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, -14.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition segment_2 = partdefinition.addOrReplaceChild("segment_2", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition cube_r23 = segment_2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(53, 9).addBox(-2.0F, 2.0F, 11.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r24 = segment_2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(53, 9).addBox(-15.0F, -6.0F, 6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.2754F, -0.2872F, -2.1761F));

		PartDefinition cube_r25 = segment_2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(53, 9).addBox(-15.0F, 4.0F, -4.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.2174F, 0.0189F, -0.7397F));

		PartDefinition cube_r26 = segment_2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(36, 39).addBox(-3.5F, -21.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.9599F, 0.0F));

		PartDefinition segment_3 = partdefinition.addOrReplaceChild("segment_3", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition cube_r27 = segment_3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(53, 9).addBox(8.0F, 1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.1772F, 0.1719F, 0.7287F));

		PartDefinition cube_r28 = segment_3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2927F, -21.5464F, -3.2778F, 2.2935F, 0.8279F, -3.1355F));

		PartDefinition cube_r29 = segment_3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, 0.0F, -8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r30 = segment_3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(53, 9).addBox(-11.0F, 3.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.1309F, 0.0F, -0.6545F));

		PartDefinition cube_r31 = segment_3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, 4.0F, 4.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r32 = segment_3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(32, 26).addBox(-3.0F, -27.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition segment_4 = partdefinition.addOrReplaceChild("segment_4", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition cube_r33 = segment_4.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(53, 9).addBox(4.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, -0.0871F, 0.043F, 0.7057F));

		PartDefinition cube_r34 = segment_4.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(53, 9).addBox(-7.0F, 2.0F, -3.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.1309F, 0.0F, -0.6545F));

		PartDefinition cube_r35 = segment_4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(53, 9).addBox(-4.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r36 = segment_4.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(32, 7).addBox(-2.0F, -33.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition tip = partdefinition.addOrReplaceChild("tip", CubeListBuilder.create().texOffs(53, 9).addBox(-1.0F, -38.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


		
	
	

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		
		
		
		
		float f1 = (limbSwing + Mth.sin(limbSwing * 2.7F)) * 0.6F * 12.0F;
		
		this.root.y = 2.4f - f1;
		this.root.yRot = (entity.rotationModifier)*f1/6;
	      
	      
	}

	//@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int i) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
	}

	@Override
	public ModelPart root() {
		
		return this.root;
	}
	
	
	
	
	
	
	
}
