package github.nitespring.monsterplus;

import com.mojang.logging.LogUtils;

import github.nitespring.monsterplus.config.Config;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ItemInit;
import github.nitespring.monsterplus.core.init.ParticleInit;
import github.nitespring.monsterplus.networking.MonsterPlusPacketHandler;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(MonsterPlus.MODID)
public class MonsterPlus
{
   
    public static final String MODID = "monsterplus";

    public static final Logger LOGGER = LogUtils.getLogger();
    
   
    public MonsterPlus(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.common_config);
        modEventBus.addListener(MonsterPlusPacketHandler::onRegisterPayloadHandler);
        EntityInit.ENTITIES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        ParticleInit.PARTICLES.register(modEventBus);
        

        //NeoForge.EVENT_BUS.register(this);
    }

}
