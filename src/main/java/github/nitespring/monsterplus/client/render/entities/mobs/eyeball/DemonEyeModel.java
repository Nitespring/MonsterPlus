package github.nitespring.monsterplus.client.render.entities.mobs.eyeball;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.definitions.WardenAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;

public class DemonEyeModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart eye;
	private final ModelPart tendrils;
	private final ModelPart tendrils_child;

	public DemonEyeModel(ModelPart root) {
		this.eye = root.getChild("eye");
		this.tendrils = root.getChild("tendrils");
		this.tendrils_child = tendrils.getChild("tendrils_child");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eye = partdefinition.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition tendrils = partdefinition.addOrReplaceChild("tendrils", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 20.0F, 4.0F));

		PartDefinition tendrils_child = tendrils.addOrReplaceChild("tendrils_child", CubeListBuilder.create().texOffs(40, 14).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 10).addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.eye.yRot = 0.5f * netHeadYaw * (float) (Math.PI / 180.0);
		this.eye.xRot = 0.15f * headPitch * (float) (Math.PI / 180.0);
		this.tendrils_child.yRot = - 0.75f * netHeadYaw * (float) (Math.PI / 180.0) + 0.5f * (entity.getYRot() - entity.yRotO) * (float) (Math.PI / 180.0);
		this.tendrils_child.xRot = - 0.5f * headPitch * (float) (Math.PI / 180.0) + 0.25f * (entity.getXRot() - entity.xRotO) * (float) (Math.PI / 180.0);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
		eye.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
		tendrils.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
	}

}