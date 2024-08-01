package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;


import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.SkeletonRenderer;

public class SpectralSkeletonRenderer extends SkeletonRenderer{

	
	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/spectre.png");

	public SpectralSkeletonRenderer(Context p_174380_) {
		super(p_174380_);
		//this.addLayer(new SpectralSkeletonOuterLayer<>(this, p_174380_.getModelSet()));
	}

	
	
	//WitherSkeletonRenderer
	
	
	
	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
		
		return TEXTURE_LOCATION;
	}
	
	@Override
	protected int getBlockLightLevel(AbstractSkeleton p_114496_, BlockPos p_114497_) {
		
		return 15;
	}
	
	
	@Override
	protected RenderType getRenderType(AbstractSkeleton p_115322_, boolean p_115323_, boolean p_115324_,
			boolean p_115325_) {
		
		return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
	}
	//SlimeRenderer
	

}
