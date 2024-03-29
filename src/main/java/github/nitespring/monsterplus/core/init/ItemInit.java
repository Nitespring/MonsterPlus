package github.nitespring.monsterplus.core.init;


import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.item.CrystalArmour;
import github.nitespring.monsterplus.common.item.CrystalArrowItem;
import github.nitespring.monsterplus.common.item.CrystalClumpItem;
import github.nitespring.monsterplus.common.item.CrystalSword;
import github.nitespring.monsterplus.common.item.Darkstar;
import github.nitespring.monsterplus.common.item.GnawingJaw;
import github.nitespring.monsterplus.core.enums.CustomArmourMaterials;
import github.nitespring.monsterplus.core.enums.CustomItemTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			 MonsterPlus.MODID);
	
	
	
	
	
	public static final RegistryObject<Item> GLOW_SKELETON_SPAWN_EGG = ITEMS.register("glow_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.GLOW_SKELETON.get(), 1288644, 55269, new Item.Properties()
					));
	
	public static final RegistryObject<Item> LAVA_SQUID_SPAWN_EGG = ITEMS.register("lava_squid_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.LAVA_SQUID.get(), 14876672, 16762908, new Item.Properties()
					));
	
	public static final RegistryObject<Item> MOTHER_LAVA_SQUID_SPAWN_EGG = ITEMS.register("mother_lava_squid_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.MOTHER_LAVA_SQUID.get(), 14876672, 16753408, new Item.Properties()
					));
	
	public static final RegistryObject<Item> SPECTRAL_SKELETON_SPAWN_EGG = ITEMS.register("spectral_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.SPECTRAL_SKELETON.get(), 10855355, 12306907, new Item.Properties()
					));
	
	public static final RegistryObject<Item> EYE_SPAWN_EGG = ITEMS.register("opalescent_eye_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.EYE.get(), 15001057, 12306907, new Item.Properties()
					));

	public static final RegistryObject<Item> ABYSSOLOGER_SPAWN_EGG = ITEMS.register("abyssologer_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.ABYSSOLOGER.get(), 2889509, 9568373, new Item.Properties()
					));
	
	public static final RegistryObject<Item> SWAMP_ZOMBIE_SPAWN_EGG = ITEMS.register("swamp_zombie_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.SWAMP_ZOMBIE.get(), 3819572, 3816756, new Item.Properties()
					));
	
	public static final RegistryObject<Item> CRYSTAL_ZOMBIE_SPAWN_EGG = ITEMS.register("crystal_zombie_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.CRYSTAL_ZOMBIE.get(), 4276545, 9568373, new Item.Properties()
					));
	
	public static final RegistryObject<Item> OVERGROWN_SKELETON_SPAWN_EGG = ITEMS.register("overgrown_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(()->EntityInit.OVERGROWN_SKELETON.get(), 6895154, 3130417, new Item.Properties()
					));
	
	
	
	public static final RegistryObject<Item> CRYSTAL_CLUMP = ITEMS.register("crystal_clump", 
			() -> new CrystalClumpItem(new Item.Properties()));
	public static final RegistryObject<CrystalArrowItem> CRYSTAL_ARROW = ITEMS.register("crystal_arrow", 
			() -> new CrystalArrowItem(new Item.Properties()));
	
	
	public static final RegistryObject<Item> CRYSTAL_SHARD = ITEMS.register("crystal_shard", 
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> ANCIENT_CRYSTAL = ITEMS.register("ancient_crystal", 
			() -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

	public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword", 
			() -> new CrystalSword(CustomItemTiers.CRYSTAL, 5, -2.4f, new Item.Properties()
					));
	
	public static final RegistryObject<Item> DARKSTAR = ITEMS.register("darkstar", 
			() -> new Darkstar(CustomItemTiers.CRYSTAL, 13, -2.8f, new Item.Properties()
					));
	public static final RegistryObject<Item> GNAWING_JAW = ITEMS.register("gnawing_jaw", 
			() -> new GnawingJaw(CustomItemTiers.CRYSTAL, 11, -2.5f, new Item.Properties()
					));
	
	
	public static final RegistryObject<Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.HELMET, new Item.Properties()
					));
	public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
					));
	public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
					));
	public static final RegistryObject<Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots", 
			() -> new CrystalArmour(CustomArmourMaterials.CRYSTAL, ArmorItem.Type.BOOTS, new Item.Properties()
					));
	
	
	
	
	public static final RegistryObject<Item> PURPLE_FIREBALL = ITEMS.register("purple_fireball", 
			() -> new Item(new Item.Properties()));
	

}
