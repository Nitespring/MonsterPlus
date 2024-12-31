package github.nitespring.monsterplus.core.events;



import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.core.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = MonsterPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabsRegistration {
	
	
	 public static CreativeModeTab ITEM_TAB;
	 public static CreativeModeTab ENTITY_TAB;

	@SubscribeEvent
	public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.COMBAT)
		{
			event.accept(ItemInit.CRYSTAL_SWORD.get());
			event.accept(ItemInit.CRYSTAL_ARROW.get());
			event.accept(ItemInit.CRYSTAL_CLUMP.get());
			event.accept(ItemInit.CRYSTAL_HELMET.get());
			event.accept(ItemInit.CRYSTAL_CHESTPLATE.get());
			event.accept(ItemInit.CRYSTAL_LEGGINGS.get());
			event.accept(ItemInit.CRYSTAL_BOOTS.get());
			event.accept(ItemInit.SKULL_STAFF.get());
			event.accept(ItemInit.DARK_HELMET.get());
			event.accept(ItemInit.DARK_CHESTPLATE.get());
			event.accept(ItemInit.GNAWING_JAW.get());
			event.accept(ItemInit.DARKSTAR.get());
			event.accept(ItemInit.FIRE_SCROLL_FLAME.get());
			event.accept(ItemInit.FIRE_SCROLL_FIREBALL_BARRAGE.get());
			event.accept(ItemInit.FIRE_SCROLL_FIREBALL.get());
			event.accept(ItemInit.CURSED_SCROLL_FLAME.get());
			event.accept(ItemInit.SOUL_SCROLL_FLAME.get());
			event.accept(ItemInit.DARK_SCROLL_CRYSTAL_LINE.get());
			event.accept(ItemInit.DARK_SCROLL_CRYSTAL_CIRCLE.get());
		}
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
		{
			event.accept(ItemInit.CRYSTAL_SHARD.get());
			event.accept(ItemInit.SPECTRAL_ESSENCE.get());
			event.accept(ItemInit.DEMONIC_ESSENCE.get());
			event.accept(ItemInit.DARK_ESSENCE.get());
			event.accept(ItemInit.CURSEFLAME_POWDER.get());
			event.accept(ItemInit.SOULFLAME_POWDER.get());
			event.accept(ItemInit.ANCIENT_CRYSTAL.get());
			event.accept(ItemInit.DARK_GEM.get());
			event.accept(ItemInit.ANCIENT_BROKEN_SWORD.get());
			event.accept(ItemInit.ANCIENT_SCROLL.get());
			event.accept(ItemInit.ENCHANTED_SCROLL.get());
		}
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
		{
			event.accept(ItemInit.ABYSSOLOGER_SPAWN_EGG.get());
			event.accept(ItemInit.SPECTRAL_SKELETON_SPAWN_EGG.get());
			event.accept(ItemInit.EYE_SPAWN_EGG.get());
			event.accept(ItemInit.GLOW_SKELETON_SPAWN_EGG.get());
			event.accept(ItemInit.OVERGROWN_SKELETON_SPAWN_EGG.get());
			event.accept(ItemInit.SWAMP_ZOMBIE_SPAWN_EGG.get());
			event.accept(ItemInit.CRYSTAL_ZOMBIE_SPAWN_EGG.get());
			event.accept(ItemInit.LAVA_SQUID_SPAWN_EGG.get());
			event.accept(ItemInit.MOTHER_LAVA_SQUID_SPAWN_EGG.get());
			event.accept(ItemInit.DEMON_EYE_SPAWN_EGG.get());
			event.accept(ItemInit.ENDER_EYE_SPAWN_EGG.get());
			event.accept(ItemInit.ANCIENT_HERO_SPAWN_EGG.get());
			event.accept(ItemInit.ANCIENT_HERO_SKULL_SPAWN_EGG.get());
			event.accept(ItemInit.WISP_SPAWN_EGG.get());
			event.accept(ItemInit.SOUL_SPAWN_EGG.get());
			event.accept(ItemInit.SPECTRAL_SKULL_SPAWN_EGG.get());
			event.accept(ItemInit.DESERT_ACOLYTE_SPAWN_EGG.get());
			event.accept(ItemInit.DESERT_SORCERESS_SPAWN_EGG.get());
		}
	}
	
	
	

}
