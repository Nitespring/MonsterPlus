package github.nitespring.monsterplus.client.render.entities.mobs.wisp;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.Soul;
import github.nitespring.monsterplus.common.entity.Wisp;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class SoulRenderer<T extends Soul> extends MobRenderer<T, WispModel<T>>{


	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/soul.png");
	public static final ResourceLocation OVERLAY = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/soul_outer.png");

	 public SoulRenderer(Context context) {
	     super(context, new WispModel<T>(context.bakeLayer(ClientListener.WISP_INNER_LAYER)), 0.75f);
		 this.addLayer(new SoulOverlayLayer<>(this, context.getModelSet()));

	   }

	public SoulRenderer(Context context, WispModel<T> e, float f) {
		super(context, e, f);
	}

	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		 pPoseStack.pushPose();
		 float scaleFactor = 0.4f;
		 pPoseStack.scale(scaleFactor,scaleFactor,scaleFactor);
		 super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
		 pPoseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
				return TEXTURE;
	}
	
	@Override
	protected int getBlockLightLevel(T e, BlockPos pos) {

		return Math.min(super.getBlockLightLevel(e,pos)+13,15);
	}

	@Override
	protected float getShadowRadius(T pEntity) {
		return 0;
	}

	@Override
	protected void setupRotations(T p_116035_, PoseStack p_116036_, float p_116037_, float p_116038_, float p_116039_, float p_335360_) {
		float $$6 = Mth.lerp(p_116039_, p_116035_.xBodyRotO, p_116035_.xBodyRot);
		float $$7 = Mth.lerp(p_116039_, p_116035_.zBodyRotO, p_116035_.zBodyRot);
		p_116036_.translate(0.0F, 0.5F, 0.0F);
		p_116036_.mulPose(Axis.YP.rotationDegrees(180.0F - p_116038_));
		p_116036_.mulPose(Axis.XP.rotationDegrees($$6));
		p_116036_.mulPose(Axis.YP.rotationDegrees($$7));
		p_116036_.translate(0.0F, -1.2F, 0.0F);
	}

	@Override
	protected float getBob(T p_116032_, float p_116033_) {
		return Mth.lerp(p_116033_, p_116032_.oldTentacleAngle, p_116032_.tentacleAngle);
	}

	@Nullable
	@Override
	protected RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
		//return RenderType.entityTranslucent(getTextureLocation(pLivingEntity));
		if (pTranslucent) {
			return RenderType.itemEntityTranslucentCull(getTextureLocation(pLivingEntity));
		} else if (pBodyVisible) {
			return RenderType.entityTranslucent(getTextureLocation(pLivingEntity));
		} else {
			return pGlowing ? RenderType.outline(getTextureLocation(pLivingEntity)) : null;
		}
	}
}
