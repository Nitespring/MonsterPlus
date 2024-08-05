package github.nitespring.monsterplus;




import github.nitespring.monsterplus.common.entity.*;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;


@EventBusSubscriber(modid = MonsterPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
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
		event.put(EntityInit.DEMON_EYE.get(), DemonEye.setCustomAttributes().build());
		event.put(EntityInit.ENDER_EYE.get(), EnderEye.setCustomAttributes().build());
		event.put(EntityInit.ANCIENT_HERO.get(), AncientHero.setCustomAttributes().build());
		
	
	}


}
