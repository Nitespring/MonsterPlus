package github.nitespring.monsterplus.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.projectiles.SkullProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class SkullProjectileRenderer<T extends SkullProjectile> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/spectral_skull.png");
	 private final SkullProjectileModel<T> model;


	public SkullProjectileRenderer(Context context) {
		super(context);
		this.model = new SkullProjectileModel<T>(context.bakeLayer(ClientListener.SKULL_PROJECTILE_LAYER));
		
	}


	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
		model.setupAnim(pEntity,0, 0, pEntity.tickCount, pEntityYaw, pEntity.getXRot());
		model.renderToBuffer(pPoseStack,pBufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(pEntity))),pPackedLight,OverlayTexture.NO_OVERLAY,-1);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	@Override
	protected int getBlockLightLevel(T p_114496_, BlockPos p_114497_) {
		
		return 10;
	}
	
	

}
