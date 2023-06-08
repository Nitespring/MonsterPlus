package com.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton;

import com.nitespring.monsterplus.ClientListener;
import com.nitespring.monsterplus.MonsterPlus;
import com.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieCrystalLayer;
import com.nitespring.monsterplus.common.entity.OvergrownSkeleton;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;

public class OvergrownSkeletonRenderer<T extends OvergrownSkeleton> extends HumanoidMobRenderer<T, OvergrownSkeletonModel<T>> {

	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/overgrown_skeleton_new.png");
	
	
	public OvergrownSkeletonRenderer(EntityRendererProvider.Context p_174169_) {
		this(p_174169_, ClientListener.OVERGROWN_SKELETON_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
		
	}
	
	public OvergrownSkeletonRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
	      super(p_174382_, new OvergrownSkeletonModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
	      this.addLayer(new HumanoidArmorLayer<>(this, new OvergrownSkeletonModel<T>(p_174382_.bakeLayer(p_174384_)), new OvergrownSkeletonModel<T>(p_174382_.bakeLayer(p_174385_)),p_174382_.getModelManager()));
	      this.addLayer(new OvergrownSkeletonEmissiveLayer<>(this, p_174382_.getModelSet()));
	   }
	
	 @Override
	   public ResourceLocation getTextureLocation(T p_115941_) {
	      return TEXTURE_LOCATION;
	   }

}
