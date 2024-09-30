package github.nitespring.monsterplus.client.render.entities.mobs.sorceress;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.monsterplus.ClientListener;
import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.AbyssologerCrystalLayer;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.AbyssologerModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.jetbrains.annotations.Nullable;

public class DesertSorceressRenderer<T extends SpellcasterIllager> extends IllagerRenderer<T> {

	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/desert_sorceress.png");

	 public DesertSorceressRenderer(Context context) {
		super(context, new DesertSorceressModel<>(context.bakeLayer(ClientListener.DESERT_SORCERESS)), 0.5F);
		this.addLayer(new DesertSorceressEmissiveLayer(this, context.getModelSet()));

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
