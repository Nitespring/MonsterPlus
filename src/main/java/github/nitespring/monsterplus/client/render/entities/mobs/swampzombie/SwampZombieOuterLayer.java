package github.nitespring.monsterplus.client.render.entities.mobs.swampzombie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.SwampZombie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Drowned;



public class SwampZombieOuterLayer<T extends SwampZombie> extends RenderLayer<T, SwampZombieModel<T>> {
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/swamp_zombie_outer.png");

   private final DrownedModel<T> model;

   public SwampZombieOuterLayer(RenderLayerParent<T, SwampZombieModel<T>> p_174490_, EntityModelSet p_174491_) {
      super(p_174490_);
      this.model = new DrownedModel<>(p_174491_.bakeLayer(ModelLayers.DROWNED_OUTER_LAYER));
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
	         this.model.renderToBuffer(stack, vertexconsumer, i, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
	      }
	   
      //coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, TEXTURE_LOCATION, p_116924_, p_116925_, p_116926_, p_116927_, p_116928_, p_116929_, p_116931_, p_116932_, p_116933_, p_116930_, 1.0F, 1.0F, 1.0F);
   }
}
