package github.nitespring.monsterplus.client.render.entities.specialeffects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.SquareTextureEntityModel;
import github.nitespring.monsterplus.common.entity.projectiles.BloodySlashEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BloodySlashRenderer<T extends BloodySlashEntity> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION_0 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/particles/bloody_slash_0.png");
	public static final ResourceLocation TEXTURE_LOCATION_1 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/particles/bloody_slash_1.png");
	public static final ResourceLocation TEXTURE_LOCATION_2 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/particles/bloody_slash_2.png");
	public static final ResourceLocation TEXTURE_LOCATION_3 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/particles/bloody_slash_3.png");
	public static final ResourceLocation TEXTURE_LOCATION_4 = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/particles/bloody_slash.png");
	 private final SquareTextureEntityModel<T> model;
	
	//EvokerFangsRenderer
	
	
	public BloodySlashRenderer(Context context) {
		super(context);
		this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
		
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
		
		switch(e.getAnimationState()) {
		case 1:
			return TEXTURE_LOCATION_1;
		case 2:
			return TEXTURE_LOCATION_2;
		case 3:
			return TEXTURE_LOCATION_3;
		case 4:
			return TEXTURE_LOCATION_4;
		default:
			return TEXTURE_LOCATION_0;
		}
	}
	
	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
	     
		 VertexConsumer vertexconsumer;
	      stack.pushPose();
	      
	      stack.mulPose(Axis.YP.rotationDegrees(90-entity.getYRot()));
	      stack.mulPose(Axis.ZP.rotationDegrees(180));
	      stack.mulPose(Axis.XP.rotationDegrees(-80));
	      //stack.translate(0, -4.5, -0.5);
	      if(entity.getAnimationState()==4) {
		  stack.translate(0, -4.75, -0.5);
	      stack.scale(3.0f, 3.0f, 3.0f);
	      vertexconsumer = p_114532_.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
	      }else {
		  stack.translate(0, -4.0, -0.5);
	      stack.scale(2.5f, 2.5f, 2.5f);  
	      vertexconsumer = p_114532_.getBuffer(RenderType.eyes(getTextureLocation(entity)));
	      }
	         
	         this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, -1);
	      stack.popPose();
	         super.render(entity, p_114529_, p_114530_, stack, p_114532_, 255);
	      
	}

	

	
}
