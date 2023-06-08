package com.nitespring.monsterplus.client.render.entities.mobs.abyssologer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class EyeModel<E extends Entity> extends HierarchicalModel<E>{

	private final ModelPart root;
	
	 public EyeModel(ModelPart part) {
	      this.root = part;
	   }
	
	
	@Override
	public ModelPart root() {
		
		return this.root;
	}

	@Override
	public void setupAnim(E p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_,
			float p_102623_) {
		
		
		
	}
	
	
	public static LayerDefinition createInnerBodyLayer() {
		
		
		  MeshDefinition meshdefinition = new MeshDefinition();
	      PartDefinition partdefinition = meshdefinition.getRoot();
	      
	      partdefinition.addOrReplaceChild("body", 
	    		  CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F)), 
	    		  PartPose.offset(0.0F, 24.0F, 0.0F));

	      
	      return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	public static LayerDefinition createOuterBodyLayer() {
		
		
		  MeshDefinition meshdefinition = new MeshDefinition();
	      PartDefinition partdefinition = meshdefinition.getRoot();
	      
	      partdefinition.addOrReplaceChild("body", 
	    		  CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), 
	    		  PartPose.offset(0.0F, 24.0F, 0.0F));

	      
	      return LayerDefinition.create(meshdefinition, 64, 32);
	}

}
