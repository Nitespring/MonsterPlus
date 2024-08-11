package github.nitespring.monsterplus;




import github.nitespring.monsterplus.common.entity.*;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHero;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHeroSkull;
import github.nitespring.monsterplus.core.init.EntityInit;
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
		event.put(EntityInit.DEMON_EYE.get(), DemonEye.setCustomAttributes().build());
		event.put(EntityInit.ENDER_EYE.get(), EnderEye.setCustomAttributes().build());
		event.put(EntityInit.ANCIENT_HERO.get(), AncientHero.setCustomAttributes().build());
		event.put(EntityInit.ANCIENT_HERO_SKULL.get(), AncientHeroSkull.setCustomAttributes().build());


	}
}
