package github.nitespring.monsterplus.client.render.entities.mobs.flyingskull;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroSkullEmissiveLayer;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroSkullModel;
import github.nitespring.monsterplus.common.entity.FlyingSkull;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHeroSkull;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class FlyingSkullRenderer<T extends FlyingSkull> extends MobRenderer<T, FlyingSkullModel<T>> {

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/spectral_skull.png");


	public FlyingSkullRenderer(EntityRendererProvider.Context context) {
		this(context, ClientListener.FLYING_SKULL_LAYER);

	}

	public FlyingSkullRenderer(EntityRendererProvider.Context context, ModelLayerLocation model) {
	      super(context, new FlyingSkullModel(context.bakeLayer(model)), 0.5F);
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
		pPoseStack.scale(1.0F, 1.0F, 1.0F);
	}


}
