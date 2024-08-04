package github.nitespring.monsterplus.client.render.entities.mobs.eyeball;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.DemonEye;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class DemonEyeIrisLayer<T extends DemonEye> extends RenderLayer<T, DemonEyeModel<T>>{

	 private final DemonEyeModel<T> model;
	public static final ResourceLocation RED = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_red_iris.png");
	public static final ResourceLocation PURPLE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_purple_iris.png");
	public static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_green_iris.png");
	public static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_yellow_iris.png");
	public static final ResourceLocation BLUE = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/demon_eye_blue_iris.png");
	public static final ResourceLocation EMPTY = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/demon_eye/empty.png");

	public DemonEyeIrisLayer(RenderLayerParent<T, DemonEyeModel<T>> p_117346_, EntityModelSet p_174491_) {
		super(p_117346_);
		this.model = new DemonEyeModel<>(p_174491_.bakeLayer(ClientListener.DEMON_EYE_LAYER));
		
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int i, T entity, float f1,
			float f2, float f3, float f4, float f5, float f6) {

		      if (!entity.isInvisible()) {
		         VertexConsumer vertexconsumer;

				 vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));


		         this.getParentModel().copyPropertiesTo(this.model);
		         this.model.prepareMobModel(entity, f1, f2, f3);
		         this.model.setupAnim(entity, f1, f2, f4, f5, f6);
				 if(entity.getEyeType()==3){
					 this.model.renderToBuffer(stack, vertexconsumer, Math.max(50,i), LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
				 }else if(entity.getEyeType()==1||entity.getEyeType()==2){
					 this.model.renderToBuffer(stack, vertexconsumer, Math.max(100,i), LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
				 }else if(entity.getEyeType()==0){
					 this.model.renderToBuffer(stack, vertexconsumer, Math.max(200,i), LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
				 }else {
					 this.model.renderToBuffer(stack, vertexconsumer, Math.max(150,i), LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
				 }
		      }
		   
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
				return EMPTY;
			default:
				return RED;
		}
	}


}
