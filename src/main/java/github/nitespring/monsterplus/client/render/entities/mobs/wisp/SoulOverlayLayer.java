package github.nitespring.monsterplus.client.render.entities.mobs.wisp;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.common.entity.Soul;
import github.nitespring.monsterplus.common.entity.Wisp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class SoulOverlayLayer<T extends Soul> extends RenderLayer<T, WispModel<T>>{

	 private final WispModel<T> model;
	 //SlimeOuterLayer

	public SoulOverlayLayer(RenderLayerParent<T, WispModel<T>> p_117346_, EntityModelSet p_174491_) {
		super(p_117346_);
		this.model = new WispModel<T>(p_174491_.bakeLayer(ClientListener.WISP_OUTER_LAYER));
		
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int i, T entity, float f1,
			float f2, float f3, float f4, float f5, float f6) {
		
		 
		      Minecraft minecraft = Minecraft.getInstance();
		      boolean flag = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
		      if (!entity.isInvisible() || flag) {
		         VertexConsumer vertexconsumer;
		         if (flag) {
		            vertexconsumer = buffer.getBuffer(RenderType.outline(this.getTextureLocation(entity)));
		         } else {
		            vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
		         }

		         this.getParentModel().copyPropertiesTo(this.model);
		         this.model.prepareMobModel(entity, f1, f2, f3);
		         this.model.setupAnim(entity, f1, f2, f4, f5, f6);
		         this.model.renderToBuffer(stack, vertexconsumer, i, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
		      }
		   
	}

	
	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		
		return SoulRenderer.OVERLAY;
	}


}
