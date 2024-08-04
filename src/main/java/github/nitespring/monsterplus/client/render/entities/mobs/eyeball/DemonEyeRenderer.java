package github.nitespring.monsterplus.client.render.entities.mobs.eyeball;


import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeModel;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeOverlayLayer;
import github.nitespring.monsterplus.common.entity.DemonEye;
import github.nitespring.monsterplus.common.entity.Eye;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class DemonEyeRenderer<T extends DemonEye> extends MobRenderer<T, DemonEyeModel<T>>{


	public static final ResourceLocation OVERLAY = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_translucent.png");
	public static final ResourceLocation RED = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_red.png");
	public static final ResourceLocation PURPLE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_purple.png");
	public static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_green.png");
	public static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_yellow.png");
	public static final ResourceLocation BLUE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_blue.png");
	public static final ResourceLocation TEETH_OPEN = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_teeth.png");
	public static final ResourceLocation TEETH_CLOSED = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_teeth_closed.png");
	 public DemonEyeRenderer(Context context) {
	     super(context, new DemonEyeModel<T>(context.bakeLayer(ClientListener.DEMON_EYE_LAYER)), 0.75f);
		 this.addLayer(new DemonEyeIrisLayer<>(this, context.getModelSet()));
		 this.addLayer(new DemonEyeOverlayLayer<>(this, context.getModelSet()));

	   }

	public DemonEyeRenderer(Context context, DemonEyeModel<T> e, float f) {
		super(context, e, f);
	}

	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		 pPoseStack.scale(pEntity.getDimensionScale(),pEntity.getDimensionScale(),pEntity.getDimensionScale());
		 super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
		switch(e.getEyeType()) {
			case 1:
				return PURPLE;
			case 2:
				return GREEN;
			case 3:
				return YELLOW;
			case 4:
				return BLUE;
			case 5:
				return (e.getAnimationState()==1)?TEETH_CLOSED:TEETH_OPEN;
			default:
				return RED;

		}
	}
	
	@Override
	protected int getBlockLightLevel(T e, BlockPos pos) {
		
		return Math.max(super.getBlockLightLevel(e,pos),3);
	}

	@Override
	protected float getShadowRadius(T pEntity) {
		return super.getShadowRadius(pEntity) * pEntity.getDimensionScale();
	}
}
