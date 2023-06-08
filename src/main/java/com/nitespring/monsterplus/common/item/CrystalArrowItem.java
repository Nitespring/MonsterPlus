package com.nitespring.monsterplus.common.item;

import java.util.List;

import com.nitespring.monsterplus.common.entity.projectiles.CrystalArrow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalArrowItem extends ArrowItem{

	public CrystalArrowItem(Properties p_40512_) {
		super(p_40512_);
	}
	
       @Override
	   public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_) {
	      return new CrystalArrow(p_43237_, p_43239_);
	   }
       
       
       //BowItem

    @OnlyIn(Dist.CLIENT)
   	@Override
   	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
   		
   		String string = "\u00A77\u00A7oSummons Crystal Spikes from the ground in the place where it hits";
   		tooltip.add(Component.literal(string));
   	}
   	
   	@Override
   	public Rarity getRarity(ItemStack p_41461_) {
   		
   		return Rarity.RARE;
   	}
       
}
