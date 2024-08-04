package github.nitespring.monsterplus;

import github.nitespring.monsterplus.common.entity.*;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.levelgen.Heightmap.Types;

import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = MonsterPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SubscribeEvent
    public static void registerEntitySpawn(RegisterSpawnPlacementsEvent event) {
		event.register(EntityInit.SWAMP_ZOMBIE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				SwampZombie::checkSwampZombieSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.ABYSSOLOGER.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				Abyssologer::checkAbyssologerSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.GLOW_SKELETON.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				GlowSkeleton::checkGlowSkeletonSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.LAVA_SQUID.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				LavaSquid::checkLavaSquidSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.MOTHER_LAVA_SQUID.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				MotherLavaSquid::checkMotherLavaSquidSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.CRYSTAL_ZOMBIE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				CrystalZombie::checkCrystalZombieSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.OVERGROWN_SKELETON.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING,
				OvergrownSkeleton::checkOvergrownSkeletonSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.DEMON_EYE.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				DemonEye::checkDemonEyeSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityInit.ENDER_EYE.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				EnderEye::checkEnderEyeSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
	
	
	

}
