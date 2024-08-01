package github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieModel;
import github.nitespring.monsterplus.common.entity.CrystalZombie;
import github.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class OvergrownSkeletonEmissiveLayer<T extends OvergrownSkeleton> extends RenderLayer<T, OvergrownSkeletonModel<T>> {
	

	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/overgrown_skeleton_emissive.png");

	   private final OvergrownSkeletonModel<T> model;
	   
	   public OvergrownSkeletonEmissiveLayer(RenderLayerParent<T, OvergrownSkeletonModel<T>> p_117346_, EntityModelSet p_174491_) {
			super(p_117346_);
			this.model = new OvergrownSkeletonModel<>(p_174491_.bakeLayer(ClientListener.OVERGROWN_SKELETON_LAYER));
			
		}

	   public void render(PoseStack stack, MultiBufferSource buffer, int i, T entity, float f1,
				float f2, float f3, float f4, float f5, float f6)  {
		   Minecraft minecraft = Minecraft.getInstance();
		      boolean flag = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
		      if (!entity.isInvisible() || flag) {
		         VertexConsumer vertexconsumer;
		         if (flag) {
		            vertexconsumer = buffer.getBuffer(RenderType.outline(this.getTextureLocation(entity)));
		         } else {
		            vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE_LOCATION));
		         }

		         this.getParentModel().copyPropertiesTo(this.model);
		         this.model.prepareMobModel(entity, f1, f2, f3);
		         this.model.setupAnim(entity, f1, f2, f4, f5, f6);
		         this.model.renderToBuffer(stack, vertexconsumer, 200, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
		       
		      }
		   
	      
	   }
	   
	   
}
