package github.nitespring.monsterplus.client.render.entities;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class EmissiveThrownItemRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T>{

	public EmissiveThrownItemRenderer(Context p_174414_) {
		super(p_174414_, 2.0f, true);
		
	}

}
