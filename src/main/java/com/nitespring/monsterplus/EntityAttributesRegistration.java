package com.nitespring.monsterplus;




import com.nitespring.monsterplus.common.entity.Abyssologer;
import com.nitespring.monsterplus.common.entity.CrystalZombie;
import com.nitespring.monsterplus.common.entity.Eye;
import com.nitespring.monsterplus.common.entity.GlowSkeleton;
import com.nitespring.monsterplus.common.entity.LavaSquid;
import com.nitespring.monsterplus.common.entity.MotherLavaSquid;
import com.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import com.nitespring.monsterplus.common.entity.SpectralSkeleton;
import com.nitespring.monsterplus.common.entity.SwampZombie;
import com.nitespring.monsterplus.core.init.EntityInit;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributesRegistration {

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		event.put(EntityInit.GLOW_SKELETON.get(), GlowSkeleton.setCustomAttributes().build());
		event.put(EntityInit.LAVA_SQUID.get(), LavaSquid.setCustomAttributes().build());
		event.put(EntityInit.MOTHER_LAVA_SQUID.get(), MotherLavaSquid.setCustomAttributes().build());
		event.put(EntityInit.SPECTRAL_SKELETON.get(), SpectralSkeleton.setCustomAttributes().build());
		event.put(EntityInit.EYE.get(), Eye.setCustomAttributes().build());
		event.put(EntityInit.ABYSSOLOGER.get(), Abyssologer.createAttributes().build());
		event.put(EntityInit.SWAMP_ZOMBIE.get(), SwampZombie.createAttributes().build());
		event.put(EntityInit.CRYSTAL_ZOMBIE.get(), CrystalZombie.createAttributes().build());
		event.put(EntityInit.OVERGROWN_SKELETON.get(), OvergrownSkeleton.setCustomAttributes().build());
		
	
	}
}
