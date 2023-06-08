package com.nitespring.monsterplus.common.item;

import java.util.List;
import java.util.Random;

import com.nitespring.monsterplus.common.entity.projectiles.CrystalSpikes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalSword extends SwordItem{

	public CrystalSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
		super(p_43269_, p_43270_, p_43271_, p_43272_);
	}
	
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity user) {
	
		double d = Math.sqrt((target.getX()-user.getX())*(target.getX()-user.getX()) + (target.getY()-user.getY())*(target.getY()-user.getY()) + (target.getZ()-user.getZ())*(target.getZ()-user.getZ()));
		Vec3 aim = new Vec3((target.getX()-user.getX())/d, (target.getY()-user.getY())/d, (target.getZ()-user.getZ())/d);
		
		for(int k = 1; k<=2; k++) {
		target.level().addFreshEntity(new CrystalSpikes(target.level(), 3, target.position().x() + aim.x*(1.5*k - 1) + 0.5*(new Random().nextFloat()*0.5 - 0.25), target.position().y(), target.position().z() + aim.z *(  1.5*k - 1)+ 0.5*(new Random().nextFloat()*0.5 - 0.25), user.yHeadRot , 10 + k, user));
		}
		return super.hurtEnemy(stack, target, user);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
		
		String string = "\u00A77\u00A7oSummons Crystal Spikes from the ground when you hit enemies";
		tooltip.add(Component.literal(string));
	}
	
	@Override
	public Rarity getRarity(ItemStack p_41461_) {
		
		return Rarity.EPIC;
	}
	
	
	

}
