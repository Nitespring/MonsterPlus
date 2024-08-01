package github.nitespring.monsterplus.client.render.entities.mobs.lavasquid;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.LavaSquid;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;

public  class LavaSquidRenderer<T extends LavaSquid> extends MobRenderer<T, LavaSquidModel<T>> {
	   private static final ResourceLocation SQUID_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/lava_squid.png");

	   //SquidRenderer
	   
	   
	   public  LavaSquidRenderer(EntityRendererProvider.Context p_174406_) {
	     super(p_174406_, new LavaSquidModel<T>(p_174406_.bakeLayer(ClientListener.LAVA_SQUID_LAYER)), 0.7f);
		  
	   }
	   
	   @Override
	   public ResourceLocation getTextureLocation(T p_116030_) {
	      return SQUID_LOCATION;
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
	   
	   
	   @Override
	   protected int getBlockLightLevel(T p_174146_, BlockPos p_174147_) {
		      
		      return 12;
		   }
	   
	   
	   
	}
