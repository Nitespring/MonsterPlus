package com.nitespring.monsterplus.core.events;



import com.nitespring.monsterplus.MonsterPlus;
import com.nitespring.monsterplus.core.init.ItemInit;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabsRegistration {
	
	
	 public static CreativeModeTab ITEM_TAB;
	 public static CreativeModeTab ENTITY_TAB;

	 @SubscribeEvent
	  public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event)
	    {
	        if (event.getTabKey() == CreativeModeTabs.COMBAT)
	        {
	            event.accept(ItemInit.CRYSTAL_SWORD);
	            event.accept(ItemInit.CRYSTAL_ARROW);
	            event.accept(ItemInit.CRYSTAL_CLUMP);
	            event.accept(ItemInit.CRYSTAL_HELMET);
	            event.accept(ItemInit.CRYSTAL_CHESTPLATE);
	            event.accept(ItemInit.CRYSTAL_LEGGINGS);
	            event.accept(ItemInit.CRYSTAL_BOOTS);
	            event.accept(ItemInit.GNAWING_JAW);
	            event.accept(ItemInit.DARKSTAR);
	        }
	        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
	        {

	            event.accept(ItemInit.ANCIENT_CRYSTAL);
	            event.accept(ItemInit.CRYSTAL_SHARD);
	        }
	        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
	        {
	            event.accept(ItemInit.ABYSSOLOGER_SPAWN_EGG);
	            event.accept(ItemInit.SPECTRAL_SKELETON_SPAWN_EGG);
	            event.accept(ItemInit.EYE_SPAWN_EGG);
	            event.accept(ItemInit.GLOW_SKELETON_SPAWN_EGG);
	            event.accept(ItemInit.OVERGROWN_SKELETON_SPAWN_EGG);
	            event.accept(ItemInit.SWAMP_ZOMBIE_SPAWN_EGG);
	            event.accept(ItemInit.CRYSTAL_ZOMBIE_SPAWN_EGG);
	            event.accept(ItemInit.LAVA_SQUID_SPAWN_EGG);
	            event.accept(ItemInit.MOTHER_LAVA_SQUID_SPAWN_EGG);
	        }
	    }
	
	
	

}
