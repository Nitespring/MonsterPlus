package github.nitespring.monsterplus.core.init;


import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.item.*;
import github.nitespring.monsterplus.core.enums.CustomArmourMaterials;
import github.nitespring.monsterplus.core.enums.CustomItemTiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
	
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
			 MonsterPlus.MODID);
	
	
	

	
	public static final DeferredHolder<Item,Item> GLOW_SKELETON_SPAWN_EGG = ITEMS.register("glow_skeleton_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.GLOW_SKELETON.get(), 24668, 7471060, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> LAVA_SQUID_SPAWN_EGG = ITEMS.register("lava_squid_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.LAVA_SQUID.get(), 14876672, 16762908, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> MOTHER_LAVA_SQUID_SPAWN_EGG = ITEMS.register("mother_lava_squid_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.MOTHER_LAVA_SQUID.get(), 14876672, 16753408, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> SPECTRAL_SKELETON_SPAWN_EGG = ITEMS.register("spectral_skeleton_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.SPECTRAL_SKELETON.get(), 10855355, 12306907, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> EYE_SPAWN_EGG = ITEMS.register("opalescent_eye_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.EYE.get(), 15001057, 12306907, new Item.Properties()
					));

	public static final DeferredHolder<Item,Item> ABYSSOLOGER_SPAWN_EGG = ITEMS.register("abyssologer_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.ABYSSOLOGER.get(), 2889509, 9568373, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> SWAMP_ZOMBIE_SPAWN_EGG = ITEMS.register("swamp_zombie_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.SWAMP_ZOMBIE.get(), 3819572, 3816756, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> CRYSTAL_ZOMBIE_SPAWN_EGG = ITEMS.register("crystal_zombie_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.CRYSTAL_ZOMBIE.get(), 4276545, 9568373, new Item.Properties()
					));
	
	public static final DeferredHolder<Item,Item> OVERGROWN_SKELETON_SPAWN_EGG = ITEMS.register("overgrown_skeleton_spawn_egg", 
			() -> new SpawnEggItem(EntityInit.OVERGROWN_SKELETON.get(), 6895154, 3130417, new Item.Properties()
					));
	public static final DeferredHolder<Item,Item> DEMON_EYE_SPAWN_EGG = ITEMS.register("demon_eye_spawn_egg",
			() -> new SpawnEggItem(EntityInit.DEMON_EYE.get(), 12700073, 9647674, new Item.Properties()
			));
	public static final DeferredHolder<Item,Item> ENDER_EYE_SPAWN_EGG = ITEMS.register("ender_eye_spawn_egg",
			() -> new SpawnEggItem(EntityInit.ENDER_EYE.get(), 1717060, 6989398, new Item.Properties()
			));
	public static final DeferredHolder<Item,Item> ANCIENT_HERO_SPAWN_EGG = ITEMS.register("ancient_hero_spawn_egg",
			() -> new SpawnEggItem(EntityInit.ANCIENT_HERO.get(), 7893628, 4603992, new Item.Properties()
			));
	public static final DeferredHolder<Item,Item> ANCIENT_HERO_SKULL_SPAWN_EGG = ITEMS.register("ancient_hero_skull_spawn_egg",
			() -> new SpawnEggItem(EntityInit.ANCIENT_HERO_SKULL.get(), 10266542, 2434082, new Item.Properties()
			));
	public static final DeferredHolder<Item,Item> WISP_SPAWN_EGG = ITEMS.register("wisp_spawn_egg",
			() -> new SpawnEggItem(EntityInit.WISP.get(), 3145631, 14090135, new Item.Properties()
			));
	public static final DeferredHolder<Item,Item> SOUL_SPAWN_EGG = ITEMS.register("soul_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SOUL.get(), 3140095, 2162687, new Item.Properties()
			));
	
	
	public static final DeferredHolder<Item,Item> CRYSTAL_CLUMP = ITEMS.register("crystal_clump", 
			() -> new CrystalClumpItem(new Item.Properties()));
	public static final DeferredHolder<Item,CrystalArrowItem> CRYSTAL_ARROW = ITEMS.register("crystal_arrow",
			() -> new CrystalArrowItem(new Item.Properties()));

	public static final DeferredHolder<Item,Item> SPECTRAL_ESSENCE = ITEMS.register("spectral_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> DEMONIC_ESSENCE = ITEMS.register("demonic_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> DARK_ESSENCE = ITEMS.register("dark_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> CRYSTAL_SHARD = ITEMS.register("crystal_shard", 
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> ANCIENT_CRYSTAL = ITEMS.register("ancient_crystal", 
			() -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item,Item> ANCIENT_BROKEN_SWORD = ITEMS.register("ancient_broken_sword",
			() -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
	public static final DeferredHolder<Item,Item> DARK_GEM = ITEMS.register("dark_gem",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> CURSEFLAME_POWDER = ITEMS.register("curseflame_powder",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> SOULFLAME_POWDER = ITEMS.register("soulflame_powder",
			() -> new Item(new Item.Properties()));

	public static final DeferredHolder<Item,Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword", 
			() -> new CrystalSword(CustomItemTiers.CRYSTAL, new Item.Properties()));
	
	public static final DeferredHolder<Item,Item> DARKSTAR = ITEMS.register("darkstar", 
			() -> new Darkstar(CustomItemTiers.CRYSTAL, new Item.Properties()));
	public static final DeferredHolder<Item,Item> GNAWING_JAW = ITEMS.register("gnawing_jaw", 
			() -> new GnawingJaw(CustomItemTiers.CRYSTAL, new Item.Properties()));
	
	
	public static final DeferredHolder<Item,Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.BOOTS, new Item.Properties()));

	public static final DeferredHolder<Item,Item> DARK_HELMET = ITEMS.register("dark_helmet",
			() -> new DarkArmour(ArmorMaterials.NETHERITE, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredHolder<Item,Item> DARK_CHESTPLATE = ITEMS.register("dark_chestplate",
			() -> new DarkArmour(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	
	
	public static final DeferredHolder<Item,Item> PURPLE_FIREBALL = ITEMS.register("purple_fireball", 
			() -> new Item(new Item.Properties()));
	

}
