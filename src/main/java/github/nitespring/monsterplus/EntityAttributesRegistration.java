package github.nitespring.monsterplus;




import github.nitespring.monsterplus.common.entity.Abyssologer;
import github.nitespring.monsterplus.common.entity.CrystalZombie;
import github.nitespring.monsterplus.common.entity.Eye;
import github.nitespring.monsterplus.common.entity.GlowSkeleton;
import github.nitespring.monsterplus.common.entity.LavaSquid;
import github.nitespring.monsterplus.common.entity.MotherLavaSquid;
import github.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import github.nitespring.monsterplus.common.entity.SpectralSkeleton;
import github.nitespring.monsterplus.common.entity.SwampZombie;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;


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
		
	
	}
}
