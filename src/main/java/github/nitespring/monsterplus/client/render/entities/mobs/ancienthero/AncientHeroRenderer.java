package github.nitespring.monsterplus.client.render.entities.mobs.ancienthero;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton.OvergrownSkeletonModel;
import github.nitespring.monsterplus.common.entity.AncientHero;
import github.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.WitherSkeleton;

public class AncientHeroRenderer<T extends AncientHero> extends HumanoidMobRenderer<T, AncientHeroModel<T>> {

	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/ancient_hero.png");
	
	
	public AncientHeroRenderer(EntityRendererProvider.Context p_174169_) {
		this(p_174169_, ClientListener.ANCIENT_HERO_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
		
	}
	
	public AncientHeroRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
	      super(p_174382_, new AncientHeroModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
	      //this.addLayer(new HumanoidArmorLayer<>(this, new OvergrownSkeletonModel<T>(p_174382_.bakeLayer(p_174384_)), new OvergrownSkeletonModel<T>(p_174382_.bakeLayer(p_174385_)),p_174382_.getModelManager()));
	      this.addLayer(new AncientHeroEmissiveLayer<>(this, p_174382_.getModelSet()));
	   }
	@Override
	public ResourceLocation getTextureLocation(T p_115941_) {
	      return TEXTURE_LOCATION;
	   }

	@Override
	protected int getSkyLightLevel(T pEntity, BlockPos pPos) {
		return super.getSkyLightLevel(pEntity, pPos);
	}

	@Override
	protected int getBlockLightLevel(T pEntity, BlockPos pPos) {
		return Math.min(15,super.getBlockLightLevel(pEntity, pPos)+10);
	}

	@Override
	protected float getBob(T pLivingBase, float pPartialTick) {
		return super.getBob(pLivingBase, pPartialTick);
	}
	@Override
	protected void scale(T pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
		pPoseStack.scale(1.1F, 1.1F, 1.1F);
	}


}
