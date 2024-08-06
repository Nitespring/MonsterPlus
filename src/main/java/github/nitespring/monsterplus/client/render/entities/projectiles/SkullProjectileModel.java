package github.nitespring.monsterplus.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHeroSkull;
import github.nitespring.monsterplus.common.entity.projectiles.SkullProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SkullProjectileModel<T extends SkullProjectile> extends EntityModel<T> {

	private final ModelPart head;
	private final ModelPart mouth;


	public SkullProjectileModel(ModelPart part) {
		head = part.getChild("head");
		mouth = head.getChild("mouth");
	}
	
	
	public static LayerDefinition createBodyLayer() {
	    MeshDefinition meshdefinition = new MeshDefinition();
	    PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	   }

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.xRot = 180*(float) (Math.PI / 180.0) - entity.getXRot()*(float) (Math.PI / 180.0);
		head.yRot = entity.getYRot()*(float) (Math.PI / 180.0);
		mouth.y = 3.0f + (float) (2.5f * Math.cos(ageInTicks*0.25));


	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
	}
}
