package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;


import github.nitespring.monsterplus.common.entity.SpectralSkeleton;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import org.jetbrains.annotations.Nullable;

public class SpectralSkeletonRenderer extends SkeletonRenderer{

	
	private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/spectre.png");

	public SpectralSkeletonRenderer(Context p_174380_) {
		super(p_174380_);
		//this.addLayer(new SpectralSkeletonOuterLayer<>(this, p_174380_.getModelSet()));
	}



	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
		return TEXTURE_LOCATION;
	}

	@Override
	protected int getBlockLightLevel(Entity e, BlockPos pos) {
		return 15;
	}

	//SlimeRenderer


	@Nullable
	@Override
	protected RenderType getRenderType(LivingEntity e, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
		return RenderType.entityTranslucent(this.getTextureLocation((AbstractSkeleton) e));
	}
}
