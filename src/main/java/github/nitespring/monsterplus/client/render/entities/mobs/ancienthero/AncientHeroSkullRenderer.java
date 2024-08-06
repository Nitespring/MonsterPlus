package github.nitespring.monsterplus.client.render.entities.mobs.ancienthero;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHero;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHeroSkull;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class AncientHeroSkullRenderer<T extends AncientHeroSkull> extends MobRenderer<T, AncientHeroSkullModel<T>> {

	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/ancient_hero.png");


	public AncientHeroSkullRenderer(EntityRendererProvider.Context context) {
		this(context, ClientListener.ANCIENT_HERO_SKULL_LAYER);

	}

	public AncientHeroSkullRenderer(EntityRendererProvider.Context context, ModelLayerLocation model) {
	      super(context, new AncientHeroSkullModel<>(context.bakeLayer(model)), 0.5F);
	      this.addLayer(new AncientHeroSkullEmissiveLayer<>(this, context.getModelSet()));
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
