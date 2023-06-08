package com.nitespring.monsterplus.core.events;

import com.nitespring.monsterplus.MonsterPlus;
import com.nitespring.monsterplus.common.item.CrystalArmour;

import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID)
public class DamageEvents {
	
	@SubscribeEvent
	public static void applyMagicProtection(LivingDamageEvent event) {
		
	DamageSource damageType = event.getSource();	
		if(damageType.is(DamageTypes.MAGIC)||damageType.is(DamageTypes.INDIRECT_MAGIC)||damageType.is(DamageTypes.LIGHTNING_BOLT)||damageType.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
	    //if(event.getSource().is(DamageTypeTags.WITCH_RESISTANT_TO)) {
			float damageIn = event.getAmount();
	

			if(event.getEntity().hasItemInSlot(EquipmentSlot.HEAD) 
					&& event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() 
					instanceof CrystalArmour) {
			        damageIn = damageIn*(1-0.15f);
			}
			if(event.getEntity().hasItemInSlot(EquipmentSlot.CHEST) 
					&& event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem() 
					instanceof CrystalArmour) {
			        damageIn = damageIn*(1-0.15f);
			}
			if(event.getEntity().hasItemInSlot(EquipmentSlot.LEGS) 
					&& event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem() 
					instanceof CrystalArmour) {
			        damageIn = damageIn*(1-0.15f);
			}
			if(event.getEntity().hasItemInSlot(EquipmentSlot.FEET) 
					&& event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() 
					instanceof CrystalArmour) {
			        damageIn = damageIn*(1-0.15f);
			}
			
			event.setAmount(damageIn);
	
		}
	}

}
