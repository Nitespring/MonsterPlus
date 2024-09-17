package github.nitespring.monsterplus.client.render.entities.projectiles;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;

public class SmallThrownItemRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T>{

	public SmallThrownItemRenderer(Context p_174414_) {
		super(p_174414_, 0.8f, true);
		
	}

}
