package github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.CrystalZombie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.LightLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;


public class CrystalZombieRenderer extends AbstractZombieRenderer<CrystalZombie, CrystalZombieModel<CrystalZombie>>{
	 private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/crystal_zombie.png");
	 
	 public CrystalZombieRenderer(Context p_173964_) {
	      super(p_173964_, new CrystalZombieModel<>(p_173964_.bakeLayer(ClientListener.CRYSTAL_ZOMBIE_LAYER)), new CrystalZombieModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new CrystalZombieModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
	      this.addLayer(new CrystalZombieCrystalLayer<>(this, p_173964_.getModelSet()));
	   }
	
	  @Override
	   public ResourceLocation getTextureLocation(Zombie p_115941_) {
	      return TEXTURE_LOCATION;
	   }
	  
	  @Override
	protected int getSkyLightLevel(CrystalZombie z, BlockPos pos) {
		if(z.level().getBrightness(LightLayer.SKY, pos)>=12) {
		return 15;
		}else {
		return z.level().getBrightness(LightLayer.SKY, pos)+3;
		}
	}
	  @Override
		protected int getBlockLightLevel(CrystalZombie z, BlockPos pos) {
			if(z.level().getBrightness(LightLayer.BLOCK, pos)>=12) {
			return 15;
			}else {
			return z.level().getBrightness(LightLayer.BLOCK, pos)+3;
			}
		}
}
