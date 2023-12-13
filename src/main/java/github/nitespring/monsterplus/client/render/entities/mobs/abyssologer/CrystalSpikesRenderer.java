package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.projectiles.CrystalSpikes;
import net.minecraft.client.model.EvokerFangsModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.EvokerFangs;

public class CrystalSpikesRenderer<T extends CrystalSpikes> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/crystal_spike.png");
	 private final CrystalSpikesModel<CrystalSpikes> model;
	
	//EvokerFangsRenderer
	
	
	public CrystalSpikesRenderer(Context context) {
		super(context);
		this.model = new CrystalSpikesModel<>(context.bakeLayer(ClientListener.SPIKES_LAYER));
		
	}
	
	
	
	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
	      float f = entity.getAnimationProgress(p_114530_);
	      
	      if (f != 0.0F) {
	         float f1 = 2.0F;
	         if (f > 0.9F) {
	            f1 *= (1.0F - f) / 0.1F;
	         }
	         
           
	         stack.pushPose();
	         stack.scale(0.75f, 0.75f, 0.75f);
	         stack.scale(-f1, -f1, f1);
	         stack.mulPose(Axis.XP.rotationDegrees(entity.xTilt));
	         stack.mulPose(Axis.ZP.rotationDegrees(entity.zTilt));
	         
	         
	         this.model.setupAnim(entity, f, 0.0F, 0.0F, entity.getYRot(), entity.getXRot());
	         VertexConsumer vertexconsumer = p_114532_.getBuffer(RenderType.entityTranslucent(TEXTURE_LOCATION));
	         this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	         stack.popPose();
	         super.render(entity, p_114529_, p_114530_, stack, p_114532_, p_114533_);
	      }
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
