package github.nitespring.monsterplus.core.enums;


import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomArmourMaterials extends ArmorMaterials {
	   public static final Holder<ArmorMaterial> CRYSTAL;
	   /*CRYSTAL("crystal", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC.get(), 2.0F, 0.0F, () -> {
	      return Ingredient.of(Items.AMETHYST_SHARD);
	   });*/


	private static Holder<ArmorMaterial> register(String p_334359_, EnumMap<ArmorItem.Type, Integer> p_329993_, int p_332696_, Holder<SoundEvent> p_333975_, float p_329381_, float p_334853_, Supplier<Ingredient> p_333678_) {
		List<ArmorMaterial.Layer> $$7 = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(p_334359_)));
		return register(p_334359_, p_329993_, p_332696_, p_333975_, p_329381_, p_334853_, p_333678_, $$7);
	}
	private static Holder<ArmorMaterial> register(String p_332406_, EnumMap<ArmorItem.Type, Integer> p_331524_, int p_331490_, Holder<SoundEvent> p_331648_, float p_327988_, float p_328616_, Supplier<Ingredient> p_334412_, List<ArmorMaterial.Layer> p_330855_) {
		   EnumMap<ArmorItem.Type, Integer> $$8 = new EnumMap(ArmorItem.Type.class);
		   ArmorItem.Type[] var9 = Type.values();
		   int var10 = var9.length;

		   for(int var11 = 0; var11 < var10; ++var11) {
			   ArmorItem.Type $$9 = var9[var11];
			   $$8.put($$9, (Integer)p_331524_.get($$9));
		   }

		   return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.withDefaultNamespace(p_332406_), new ArmorMaterial($$8, p_331490_, p_331648_, p_334412_, p_330855_, p_327988_, p_328616_));
	   }
	  static{
		  CRYSTAL = register("crystal", (EnumMap) Util.make(new EnumMap(ArmorItem.Type.class), (p_327098_) -> {
			  p_327098_.put(Type.BOOTS, 3);
			  p_327098_.put(Type.LEGGINGS, 6);
			  p_327098_.put(Type.CHESTPLATE, 8);
			  p_327098_.put(Type.HELMET, 3);
			  p_327098_.put(Type.BODY, 4);
		  }), 12, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.0F, () -> {
			  return Ingredient.of(new ItemLike[]{ItemInit.ANCIENT_CRYSTAL.get()});
		  });
	  }
}