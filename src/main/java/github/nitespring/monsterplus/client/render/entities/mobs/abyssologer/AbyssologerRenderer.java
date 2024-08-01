package github.nitespring.monsterplus.client.render.entities.mobs.abyssologer;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.SpellcasterIllager;

public class AbyssologerRenderer<T extends SpellcasterIllager> extends IllagerRenderer<T>{
	
	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "textures/entities/abyssologer.png");
	
	 public AbyssologerRenderer(Context p_174108_) {
		super(p_174108_, new AbyssologerModel<>(p_174108_.bakeLayer(ModelLayers.EVOKER)), 0.5F);
		this.addLayer(new AbyssologerCrystalLayer(this, p_174108_.getModelSet()));
	}
	 
	 public AbyssologerRenderer(Context p_174304_, AbyssologerModel<T> p_174305_, float p_174306_) {
			super(p_174304_, p_174305_, p_174306_);
		}

	 @Override
		public ResourceLocation getTextureLocation(T p_114482_) {
			
			return TEXTURE_LOCATION;
		}
}
