package github.nitespring.monsterplus.client.render.entities.mobs.sorceress;

import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.jetbrains.annotations.Nullable;

public class DesertAcolyteRenderer<T extends SpellcasterIllager> extends IllagerRenderer<T> {

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/desert_acolyte.png");

	 public DesertAcolyteRenderer(Context context) {
		super(context, new DesertAcolyteModel<>(context.bakeLayer(ClientListener.DESERT_ACOLYTE)), 0.5F);
		this.addLayer(new DesertAcolyeEmissiveLayer(this, context.getModelSet()));

	}

	@Nullable
	@Override
	protected RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
		return RenderType.entityCutoutNoCull(getTextureLocation(pLivingEntity));
	}

	@Override
		public ResourceLocation getTextureLocation(T p_114482_) {
			
			return TEXTURE_LOCATION;
		}
}
