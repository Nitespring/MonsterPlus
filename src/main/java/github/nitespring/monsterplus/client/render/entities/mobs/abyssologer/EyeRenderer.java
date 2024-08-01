package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;


import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.Eye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class EyeRenderer<T extends Eye> extends MobRenderer<T, EyeModel<T>>{
	
	
	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/opalescent_eye.png");

	 public  EyeRenderer(EntityRendererProvider.Context context) {
	     super(context, new EyeModel<T>(context.bakeLayer(ClientListener.EYE_LAYER)), 0.7f);  
	     this.addLayer(new EyeOverlayLayer<>(this, context.getModelSet()));
	   }
	
	public EyeRenderer(Context p_174304_, EyeModel<T> p_174305_, float p_174306_) {
		super(p_174304_, p_174305_, p_174306_);
	}

	
	
	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	@Override
	protected int getBlockLightLevel(T p_114496_, BlockPos p_114497_) {
		
		return 7;
	}

	
}
