package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.SquareTextureEntityModel;
import github.nitespring.monsterplus.common.entity.projectiles.CrystalSpikes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SpikeCountdownRenderer<T extends Entity> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/fireball_purple.png");
	 private final SquareTextureEntityModel<T> model;
	
	
	public SpikeCountdownRenderer(Context context) {
		super(context);
		this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
	}
	
	@Override
	 public void render(T p_114528_, float p_114529_, float p_114530_, PoseStack p_114531_, MultiBufferSource p_114532_, int p_114533_) {
	      
          
	         p_114531_.pushPose();
	         p_114531_.translate(0.0f, 0.1, -1.5f);
	         p_114531_.mulPose(Axis.XP.rotationDegrees(90));
	        
	         VertexConsumer vertexconsumer = p_114532_.getBuffer(RenderType.entityTranslucentCull(TEXTURE_LOCATION));
	         this.model.renderToBuffer(p_114531_, vertexconsumer, 255, OverlayTexture.NO_OVERLAY, -1);
	         p_114531_.popPose();
	         super.render(p_114528_, p_114529_, p_114530_, p_114531_, p_114532_, p_114533_);
	      
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	@Override
	protected int getBlockLightLevel(T p_114496_, BlockPos p_114497_) {
		
		return 15;
	}

}
