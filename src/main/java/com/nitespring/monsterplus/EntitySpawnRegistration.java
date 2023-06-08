package com.nitespring.monsterplus;

import com.nitespring.monsterplus.common.entity.Abyssologer;
import com.nitespring.monsterplus.common.entity.CrystalZombie;
import com.nitespring.monsterplus.common.entity.GlowSkeleton;
import com.nitespring.monsterplus.common.entity.LavaSquid;
import com.nitespring.monsterplus.common.entity.MotherLavaSquid;
import com.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import com.nitespring.monsterplus.common.entity.SwampZombie;
import com.nitespring.monsterplus.core.init.EntityInit;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            
            SpawnPlacements.register(EntityInit.SWAMP_ZOMBIE.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		SwampZombie::checkSwampZombieSpawnRules);
            SpawnPlacements.register(EntityInit.ABYSSOLOGER.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		Abyssologer::checkAbyssologerSpawnRules);
            SpawnPlacements.register(EntityInit.GLOW_SKELETON.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		GlowSkeleton::checkGlowSkeletonSpawnRules);
            SpawnPlacements.register(EntityInit.LAVA_SQUID.get(), 
            		SpawnPlacements.Type.NO_RESTRICTIONS, 
            		Types.MOTION_BLOCKING, 
            		LavaSquid::checkLavaSquidSpawnRules);
            SpawnPlacements.register(EntityInit.MOTHER_LAVA_SQUID.get(), 
            		SpawnPlacements.Type.NO_RESTRICTIONS, 
            		Types.MOTION_BLOCKING, 
            		MotherLavaSquid::checkMotherLavaSquidSpawnRules);
            SpawnPlacements.register(EntityInit.CRYSTAL_ZOMBIE.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		CrystalZombie::checkCrystalZombieSpawnRules);
            SpawnPlacements.register(EntityInit.OVERGROWN_SKELETON.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING, 
            		OvergrownSkeleton::checkOvergrownSkeletonSpawnRules);
      
        });
    }
	
	
	

}
