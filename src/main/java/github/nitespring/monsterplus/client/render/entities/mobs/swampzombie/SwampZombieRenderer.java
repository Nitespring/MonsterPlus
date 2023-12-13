package github.nitespring.monsterplus.client.render.entities.mobs.swampzombie;

import com.mojang.blaze3d.vertex.PoseStack;

import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.SwampZombie;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SwampZombieRenderer extends AbstractZombieRenderer<SwampZombie, SwampZombieModel<SwampZombie>> {
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/swamp_zombie.png");

	   public SwampZombieRenderer(Context p_173964_) {
	      super(p_173964_, new SwampZombieModel<>(p_173964_.bakeLayer(ModelLayers.PLAYER)), new SwampZombieModel<>(p_173964_.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new SwampZombieModel<>(p_173964_.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)));
	      this.addLayer(new SwampZombieEyesLayer<>(this, p_173964_.getModelSet()));
	      this.addLayer(new SwampZombieOuterLayer<>(this, p_173964_.getModelSet()));
	   }

	   @Override
	   public ResourceLocation getTextureLocation(Zombie p_114115_) {
	      return TEXTURE_LOCATION;
	   }

	   
	   /*
	   protected void setupRotations(Drowned p_114109_, PoseStack p_114110_, float p_114111_, float p_114112_, float p_114113_) {
	      super.setupRotations(p_114109_, p_114110_, p_114111_, p_114112_, p_114113_);
	      float f = p_114109_.getSwimAmount(p_114113_);
	      if (f > 0.0F) {
	         p_114110_.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(f, p_114109_.getXRot(), -10.0F - p_114109_.getXRot())));
	      }

	   }
	   
	   */
	}