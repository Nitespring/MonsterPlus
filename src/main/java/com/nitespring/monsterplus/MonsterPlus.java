package com.nitespring.monsterplus;

import com.mojang.logging.LogUtils;
import com.nitespring.monsterplus.config.Config;
import com.nitespring.monsterplus.core.init.EntityInit;
import com.nitespring.monsterplus.core.init.ItemInit;
import com.nitespring.monsterplus.networking.MonsterPlusPacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MonsterPlus.MODID)
public class MonsterPlus
{
   
    public static final String MODID = "monsterplus";

    public static final Logger LOGGER = LogUtils.getLogger();
    
   
    public MonsterPlus()
    {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
    	
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonsetup);
        EntityInit.ENTITIES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        

        MinecraftForge.EVENT_BUS.register(this);
    }

	private void commonsetup(final FMLCommonSetupEvent event){
    	MonsterPlusPacketHandler.registerMessages();
    	
    	
    }

    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
       
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
