package github.nitespring.monsterplus;

import github.nitespring.monsterplus.common.entity.*;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHero;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SubscribeEvent
    public static void registerEntitySpawn(SpawnPlacementRegisterEvent event) {
		event.register(EntityInit.SWAMP_ZOMBIE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				SwampZombie::checkSwampZombieSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.ABYSSOLOGER.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				Abyssologer::checkAbyssologerSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.GLOW_SKELETON.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				GlowSkeleton::checkGlowSkeletonSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.LAVA_SQUID.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				LavaSquid::checkLavaSquidSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.MOTHER_LAVA_SQUID.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				MotherLavaSquid::checkMotherLavaSquidSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.CRYSTAL_ZOMBIE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				CrystalZombie::checkCrystalZombieSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.OVERGROWN_SKELETON.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING,
				OvergrownSkeleton::checkOvergrownSkeletonSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.DEMON_EYE.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				DemonEye::checkDemonEyeSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.ENDER_EYE.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Types.MOTION_BLOCKING,
				EnderEye::checkEnderEyeSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityInit.ANCIENT_HERO.get(),
				SpawnPlacementTypes.ON_GROUND,
				Types.MOTION_BLOCKING_NO_LEAVES,
				AncientHero::checkAncientHeroSpawnRules,
				SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
	
	
	

}
