package com.nitespring.monsterplus.client.render.entities.mobs.lavasquid;

import com.nitespring.monsterplus.common.entity.LavaSquid;

import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MotherLavaSquidModel<T extends LavaSquid> extends SquidModel<T>{

	public MotherLavaSquidModel(ModelPart p_170989_) {
		super(p_170989_);
		
	}
	
	 private static String createTentacleName(int p_170992_) {
	      return "tentacle" + p_170992_;
	   }
	
	public static LayerDefinition createBodyLayer() {
	      MeshDefinition meshdefinition = new MeshDefinition();
	      PartDefinition partdefinition = meshdefinition.getRoot();
	      
	      partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 24.0F, -6.0F, 12.0F, 16.0F, 12.0F), PartPose.offset(0.0F, -8.0F, 0.0F));
	      
	      int j = 8;
	      CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F);

	      for(int k = 0; k < j; ++k) {
	         double d0 = (double)k * Math.PI * 2.0D / j;
	         float f = (float)Math.cos(d0) * 5.0F;
	         float f1 = 31.0F;
	         float f2 = (float)Math.sin(d0) * 5.0F;
	         d0 = (double)k * Math.PI * -2.0D / j + (Math.PI / 2D);
	         float f3 = (float)d0;
	         partdefinition.addOrReplaceChild(createTentacleName(k), cubelistbuilder, PartPose.offsetAndRotation(f, f1, f2, 0.0F, f3, 0.0F));
	      }
	      

	      return LayerDefinition.create(meshdefinition, 64, 32);
	   }

}
