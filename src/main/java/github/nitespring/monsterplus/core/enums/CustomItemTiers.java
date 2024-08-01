package github.nitespring.monsterplus.core.enums;

import java.util.Objects;
import java.util.function.Supplier;


import com.google.common.base.Suppliers;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public enum CustomItemTiers implements Tier{
	/*CRYSTAL(3, 1561, 0.0F, 0.0F, 20, () -> {
	      return Ingredient.of(ItemInit.CRYSTAL_SHARD.get());
	   });*/
	CRYSTAL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 9.0F, 5.0F, 20, () -> {
		return Ingredient.of(new ItemLike[]{ItemInit.CRYSTAL_SHARD.get()});
	});

	private final TagKey<Block> incorrectBlocksForDrops;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Supplier<Ingredient> repairIngredient;

	private CustomItemTiers(final TagKey p_334032_, final int p_43332_, final float p_43334_, final float p_43335_, final int p_43333_, final Supplier<Ingredient> supplier) {
		this.incorrectBlocksForDrops = p_334032_;
		this.uses = p_43332_;
		this.speed = p_43334_;
		this.damage = p_43335_;
		this.enchantmentValue = p_43333_;
		Objects.requireNonNull(supplier);
		this.repairIngredient = Suppliers.memoize(supplier::get);
	}

	public int getUses() {
		return this.uses;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAttackDamageBonus() {
		return this.damage;
	}

	public TagKey<Block> getIncorrectBlocksForDrops() {
		return this.incorrectBlocksForDrops;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredient.get();
	}


}
