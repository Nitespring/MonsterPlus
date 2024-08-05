package github.nitespring.monsterplus.client.render.entities.mobs.ancienthero;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.monsterplus.common.entity.AncientHero;
import github.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

public class AncientHeroModel<T extends AncientHero> extends SkeletonModel<T> {

	private final ModelPart head;
	private final ModelPart mouth;
	private final ModelPart hat;
	private final ModelPart crest;
	private final ModelPart body;
	private final ModelPart body_armour;
	private final ModelPart cape;
	private final ModelPart left_arm;
	private final ModelPart left_arm_armour;
	private final ModelPart right_arm;
	private final ModelPart right_arm_armour;
	private final ModelPart left_leg;
	private final ModelPart right_leg;


	public AncientHeroModel(ModelPart part) {
		super(part);
		head = part.getChild("head");
		mouth = head.getChild("mouth");
		hat = part.getChild("hat");
		body = part.getChild("body");
		right_arm = part.getChild("right_arm");
		left_arm = part.getChild("left_arm");
		right_leg = part.getChild("right_leg");
		left_leg = part.getChild("left_leg");
		crest = hat.getChild("crest");
		body_armour = body.getChild("body_armour");
		cape = body.getChild("cape");
		left_arm_armour = left_arm.getChild("left_arm_armour");
		right_arm_armour = right_arm.getChild("right_arm_armour");

	}
	
	
	public static LayerDefinition createBodyLayer() {
	    MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
	    PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition crest = hat.addOrReplaceChild("crest", CubeListBuilder.create().texOffs(32, 60).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 40).addBox(0.0F, -20.0F, -6.0F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_armour = body.addOrReplaceChild("body_armour", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(46, 35).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_arm_armour = left_arm.addOrReplaceChild("left_arm_armour", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, -2.75F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(24, 49).addBox(-1.0F, -7.0F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_arm_armour = right_arm.addOrReplaceChild("right_arm_armour", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-3.0F, -2.75F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(24, 49).mirror().addBox(-6.0F, -7.0F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.1F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	   }

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		cape.copyFrom(body);
		if(entity.getTarget()!=null){
			head.y = -2.0f;
			hat.y = -2.0f;
			mouth.y = 3.0f + 1.5f*limbSwingAmount+ (float) (1.5f * Math.cos(ageInTicks*0.25));
		}else{
			mouth.y = 1.0f + 1.25f*limbSwingAmount+ (float) (0.5f * Math.cos(ageInTicks*0.1));
		}
		cape.xRot = 0.25f*limbSwingAmount + (15f + (float) ((10.0 + Math.abs(limbSwingAmount)) * Math.cos(ageInTicks*0.1*(1+0.05f*limbSwingAmount))))* (float) (Math.PI / 180.0);
		switch(entity.getEntityState()){
			case 1:
				hat.visible=false;
				head.visible=true;
				body_armour.visible=true;
				right_arm_armour.visible=true;
				left_arm_armour.visible=true;
				cape.visible=true;
				break;
			case 2:
				hat.visible=false;
				head.visible=false;
				body_armour.visible=true;
				right_arm_armour.visible=true;
				left_arm_armour.visible=true;
				cape.visible=true;
				break;
			case 3:
				hat.visible=false;
				head.visible=false;
				body_armour.visible=false;
				right_arm_armour.visible=false;
				left_arm_armour.visible=false;
				cape.visible=false;
				break;
			default:
				hat.visible=true;
				head.visible=true;
				body_armour.visible=true;
				right_arm_armour.visible=true;
				left_arm_armour.visible=true;
				cape.visible=true;
				break;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
	}

	

}
