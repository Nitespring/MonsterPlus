package com.nitespring.monsterplus.client.render.entities.projectiles;

import com.nitespring.monsterplus.MonsterPlus;
import com.nitespring.monsterplus.common.entity.projectiles.CrystalArrow;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class CrystalArrowRenderer extends ArrowRenderer<CrystalArrow>{

	
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MonsterPlus.MODID, "textures/entities/crystal_arrow.png");

	
	public CrystalArrowRenderer(Context p_173917_) {
		super(p_173917_);
		
	}

	@Override
	public ResourceLocation getTextureLocation(CrystalArrow p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	@Override
	protected int getBlockLightLevel(CrystalArrow p_114496_, BlockPos p_114497_) {
		
		return 10;
	}

}
