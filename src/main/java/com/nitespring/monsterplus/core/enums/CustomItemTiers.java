package com.nitespring.monsterplus.core.enums;

import java.util.function.Supplier;

import com.nitespring.monsterplus.core.init.ItemInit;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum CustomItemTiers implements Tier{
	CRYSTAL(3, 1561, 0.0F, 0.0F, 20, () -> {
	      return Ingredient.of(ItemInit.CRYSTAL_SHARD.get());
	   });

	
	 private final int level;
	   private final int uses;
	   private final float speed;
	   private final float damage;
	   private final int enchantmentValue;
	   private final Ingredient repairIngredient;

	   private CustomItemTiers(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
	      this.level = p_43332_;
	      this.uses = p_43333_;
	      this.speed = p_43334_;
	      this.damage = p_43335_;
	      this.enchantmentValue = p_43336_;
	      this.repairIngredient = p_43337_.get();
	   }
	
	
	
	@Override
	public int getUses() {
		
		return uses;
	}

	@Override
	public float getSpeed() {
		
		return speed;
	}

	@Override
	public float getAttackDamageBonus() {
		
		return damage;
	}

	@Override
	public int getLevel() {
		
		return level;
	}

	@Override
	public int getEnchantmentValue() {
		
		return enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		
		return repairIngredient;
	}

}
