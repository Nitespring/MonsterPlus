package github.nitespring.monsterplus.client.render.entities.mobs.eyeball;


import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.DemonEye;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class EnderEyeRenderer<T extends DemonEye> extends MobRenderer<T, DemonEyeModel<T>>{


	public static final ResourceLocation OVERLAY = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/ender_eye_overlay.png");
	public static final ResourceLocation OVERLAY1 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/ender_eye_overlay1.png");
	public static final ResourceLocation ENDER = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/ender_eye.png");

	 public EnderEyeRenderer(Context context) {
	     super(context, new DemonEyeModel<T>(context.bakeLayer(ClientListener.DEMON_EYE_LAYER)), 0f);
		 this.addLayer(new EnderEyeIrisLayer<>(this, context.getModelSet()));
		 this.addLayer(new EnderEyeOverlayLayer<>(this, context.getModelSet()));
	   }

	public EnderEyeRenderer(Context context, DemonEyeModel<T> e, float f) {
		super(context, e, f);
	}

	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		 pPoseStack.scale(pEntity.getDimensionScale(),pEntity.getDimensionScale(),pEntity.getDimensionScale());
		 super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
			return ENDER;
	}
	
	@Override
	protected int getBlockLightLevel(T e, BlockPos pos) {
		return 5;
	}

	@Override
	protected float getShadowRadius(T pEntity) {
		return 0;
	}
}
