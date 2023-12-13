package github.nitespring.monsterplus.client.render.entities.mobs.glowskeleton;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.GlowSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class GlowSkeletonRenderer<T extends GlowSkeleton> extends HumanoidMobRenderer<T, GlowSkeletonModel<T>> {
	   private static final ResourceLocation GLOW_SKELETON_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/glow_skeleton.png");

	   //GlowSquidRenderer
	   
	   
	   
	   public GlowSkeletonRenderer(EntityRendererProvider.Context p_174380_) {
	      this(p_174380_, ClientListener.GLOW_SKELETON_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
	   }

	   public GlowSkeletonRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
	      super(p_174382_, new GlowSkeletonModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
	      this.addLayer(new HumanoidArmorLayer<>(this, new GlowSkeletonModel<T>(p_174382_.bakeLayer(p_174384_)), new GlowSkeletonModel<T>(p_174382_.bakeLayer(p_174385_)),p_174382_.getModelManager()));
	   }
       @Override
	   public ResourceLocation getTextureLocation(T p_115941_) {
	      return GLOW_SKELETON_LOCATION;
	   }
       
       @Override
       protected int getBlockLightLevel(T entity, BlockPos p_174147_) {
    	   int b = entity.getBrightTicksRemaining();
    	   int max = 15;
    	   
    	   int min = 10;
    	   
    	   if (b>=80) {
    		   return max;
    	   } else if (b>=10) {
    		   
    		   return max - (71-(b-10))/max;
    	   } else {
    		   return min;
    	   }
    	   
    	   
    	   
    	   
    	   /*
    	      int i = (int)Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float)p_174146_.getDarkTicksRemaining() / 10.0F);
    	      return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(p_174146_, p_174147_));
    	      */
    	   }
       
       

	   protected boolean isShaking(AbstractSkeleton p_174389_) {
	      return p_174389_.isShaking();
	   }
}
