package github.nitespring.monsterplus.client.render.entities.mobs.ancienthero;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.monsterplus.common.entity.AncientHero;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AncientHeroSkullModel<T extends AncientHero> extends SkeletonModel<T> {

	private final ModelPart head;
	private final ModelPart mouth;


	public AncientHeroSkullModel(ModelPart part) {
		super(part);
		head = part.getChild("head");
		mouth = head.getChild("mouth");
	}
	
	
	public static LayerDefinition createBodyLayer() {
	    MeshDefinition meshdefinition = new MeshDefinition();
	    PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	   }

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if(entity.getTarget()!=null){
			head.y = -2.0f;
			hat.y = -2.0f;
			mouth.y = 3.0f + 1.5f*limbSwingAmount+ (float) (1.5f * Math.cos(ageInTicks*0.25));
		}else{
			mouth.y = 1.0f + 1.25f*limbSwingAmount+ (float) (0.5f * Math.cos(ageInTicks*0.1));
		}

	}



	

}
