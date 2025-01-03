package github.nitespring.monsterplus;



import github.nitespring.monsterplus.client.render.entities.EmissiveThrownItemRenderer;
import github.nitespring.monsterplus.client.render.entities.SquareTextureEntityModel;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.AbyssologerRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.CrystalSpikesModel;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.CrystalSpikesRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeModel;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.EyeRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.SpectralSkeletonRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.abyssologer.SpikeCountdownRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroModel;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroSkullModel;
import github.nitespring.monsterplus.client.render.entities.mobs.ancienthero.AncientHeroSkullRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieModel;
import github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.eyeball.DemonEyeModel;
import github.nitespring.monsterplus.client.render.entities.mobs.eyeball.DemonEyeRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.eyeball.EnderEyeRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.flyingskull.FlyingSkullModel;
import github.nitespring.monsterplus.client.render.entities.mobs.flyingskull.FlyingSkullRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.glowskeleton.GlowSkeletonModel;
import github.nitespring.monsterplus.client.render.entities.mobs.glowskeleton.GlowSkeletonRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.LavaSquidRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.MotherLavaSquidModel;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.MotherLavaSquidRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton.OvergrownSkeletonModel;
import github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton.OvergrownSkeletonRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.sorceress.DesertAcolyteModel;
import github.nitespring.monsterplus.client.render.entities.mobs.sorceress.DesertAcolyteRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.sorceress.DesertSorceressModel;
import github.nitespring.monsterplus.client.render.entities.mobs.sorceress.DesertSorceressRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.swampzombie.SwampZombieRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.wisp.SoulRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.wisp.WispModel;
import github.nitespring.monsterplus.client.render.entities.mobs.wisp.WispRenderer;
import github.nitespring.monsterplus.client.render.entities.projectiles.*;
import github.nitespring.monsterplus.client.render.entities.specialeffects.BloodySlashRenderer;
import github.nitespring.monsterplus.client.render.equipment.armour.CrystalArmourModel;
import github.nitespring.monsterplus.client.render.equipment.armour.DarkArmourModel;

import github.nitespring.monsterplus.common.particles.CurseflameParticle;
import github.nitespring.monsterplus.core.init.EntityInit;
import github.nitespring.monsterplus.core.init.ParticleInit;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;


@EventBusSubscriber(modid = MonsterPlus.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

	public static final ModelLayerLocation GLOW_SKELETON_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "glow_skeleton"), "main");
	public static final ModelLayerLocation LAVA_SQUID_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "lava_squid"), "main");
	public static final ModelLayerLocation MOTHER_LAVA_SQUID_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "mother_lava_squid"), "main");
	public static final ModelLayerLocation EYE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "opalescent_eye"), "main");
	public static final ModelLayerLocation EYE_OVERLAY_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "opalescent_eye"), "overlay");
	public static final ModelLayerLocation SPIKES_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "crystal_spikes"), "main");
	public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "square_texture"), "main");
	public static final ModelLayerLocation CRYSTAL_ZOMBIE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "crystal_zombie"), "main");
	public static final ModelLayerLocation CRYSTAL_ARMOUR_MAIN_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "crystal_armour"), "main");
	public static final ModelLayerLocation OVERGROWN_SKELETON_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "overgrown_skeleton"), "main");
	public static final ModelLayerLocation DEMON_EYE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "demon_eye"), "main");
	public static final ModelLayerLocation ANCIENT_HERO_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "ancient_hero"), "main");
	public static final ModelLayerLocation ANCIENT_HERO_SKULL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "ancient_hero_skull"), "main");
	public static final ModelLayerLocation DARK_ARMOUR_MAIN_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "dark_armour"), "main");
	public static final ModelLayerLocation SKULL_PROJECTILE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "skull"), "main");
	public static final ModelLayerLocation WISP_INNER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "wisp"), "inner");
	public static final ModelLayerLocation WISP_OUTER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "wisp"), "outer");
	public static final ModelLayerLocation DESERT_SORCERESS = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "desert_sorceress"), "main");
	public static final ModelLayerLocation FLYING_SKULL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "flying_skull"), "main");
	public static final ModelLayerLocation DESERT_ACOLYTE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "desert_acolyte"), "main");

	//public static final ModelLayerLocation EYE_MASK = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, "eye_mask"), "main");
	@SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
		
		event.registerLayerDefinition(GLOW_SKELETON_LAYER, GlowSkeletonModel::createBodyLayer);
		event.registerLayerDefinition(LAVA_SQUID_LAYER, SquidModel::createBodyLayer);
		event.registerLayerDefinition(MOTHER_LAVA_SQUID_LAYER, MotherLavaSquidModel::createBodyLayer);
		event.registerLayerDefinition(EYE_LAYER, EyeModel::createInnerBodyLayer);
		event.registerLayerDefinition(EYE_OVERLAY_LAYER, EyeModel::createOuterBodyLayer);
		event.registerLayerDefinition(SPIKES_LAYER, CrystalSpikesModel::createBodyLayer);
		event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);
		event.registerLayerDefinition(CRYSTAL_ZOMBIE_LAYER, CrystalZombieModel::createBodyLayer);
		event.registerLayerDefinition(OVERGROWN_SKELETON_LAYER, OvergrownSkeletonModel::createBodyLayer);
		event.registerLayerDefinition(DEMON_EYE_LAYER, DemonEyeModel::createBodyLayer);
		event.registerLayerDefinition(ANCIENT_HERO_LAYER, AncientHeroModel::createBodyLayer);
		event.registerLayerDefinition(ANCIENT_HERO_SKULL_LAYER, AncientHeroSkullModel::createBodyLayer);
		event.registerLayerDefinition(SKULL_PROJECTILE_LAYER, SkullProjectileModel::createBodyLayer);
		event.registerLayerDefinition(WISP_INNER_LAYER, WispModel::createInnerLayer);
		event.registerLayerDefinition(WISP_OUTER_LAYER, WispModel::createOuterLayer);
		event.registerLayerDefinition(CRYSTAL_ARMOUR_MAIN_LAYER, 
				() -> LayerDefinition.create(CrystalArmourModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 64));
		event.registerLayerDefinition(DARK_ARMOUR_MAIN_LAYER, DarkArmourModel::createBodyLayer);
		event.registerLayerDefinition(DESERT_SORCERESS, DesertSorceressModel::createDesertSorceressLayer);
		event.registerLayerDefinition(FLYING_SKULL_LAYER, FlyingSkullModel::createBodyLayer);
		event.registerLayerDefinition(DESERT_ACOLYTE, DesertAcolyteModel::createDesertAcolyteLayer);

//		event.registerLayerDefinition(EYE_MASK, SkullModel::createMobHeadLayer);
    }
	//LayerDefinitions
	
	@SubscribeEvent
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		
		event.registerEntityRenderer(EntityInit.GLOW_SKELETON.get(), GlowSkeletonRenderer::new);
		event.registerEntityRenderer(EntityInit.LAVA_SQUID.get(), LavaSquidRenderer::new);
		event.registerEntityRenderer(EntityInit.MOTHER_LAVA_SQUID.get(), MotherLavaSquidRenderer::new);
		event.registerEntityRenderer(EntityInit.SPECTRAL_SKELETON.get(), SpectralSkeletonRenderer::new);
		event.registerEntityRenderer(EntityInit.EYE.get(), EyeRenderer::new);
		event.registerEntityRenderer(EntityInit.ABYSSOLOGER.get(), AbyssologerRenderer::new);
		event.registerEntityRenderer(EntityInit.CRYSTAL_SPIKES.get(), CrystalSpikesRenderer::new);
		event.registerEntityRenderer(EntityInit.PURPLE_FIREBALL.get(), EmissiveThrownItemRenderer::new);
		event.registerEntityRenderer(EntityInit.SPIKE_COUNTDOWN.get(), SpikeCountdownRenderer::new);
		event.registerEntityRenderer(EntityInit.SWAMP_ZOMBIE.get(), SwampZombieRenderer::new);
		event.registerEntityRenderer(EntityInit.CRYSTAL_ZOMBIE.get(), CrystalZombieRenderer::new);
		event.registerEntityRenderer(EntityInit.CRYSTAL_CLUMP.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(EntityInit.CRYSTAL_ARROW.get(), CrystalArrowRenderer::new);
		event.registerEntityRenderer(EntityInit.OVERGROWN_SKELETON.get(), OvergrownSkeletonRenderer::new);
		event.registerEntityRenderer(EntityInit.BLOODY_SLASH.get(), BloodySlashRenderer::new);
		event.registerEntityRenderer(EntityInit.DEMON_EYE.get(), DemonEyeRenderer::new);
		event.registerEntityRenderer(EntityInit.ENDER_EYE.get(), EnderEyeRenderer::new);
		event.registerEntityRenderer(EntityInit.ANCIENT_HERO.get(), AncientHeroRenderer::new);
		event.registerEntityRenderer(EntityInit.ANCIENT_HERO_SKULL.get(), AncientHeroSkullRenderer::new);
		event.registerEntityRenderer(EntityInit.SPECTRAL_SKULL_PROJECTILE.get(), SkullProjectileRenderer::new);
		event.registerEntityRenderer(EntityInit.WISP.get(), WispRenderer::new);
		event.registerEntityRenderer(EntityInit.CURSEFLAME_FIREBALL.get(), SmallThrownItemRenderer::new);
		event.registerEntityRenderer(EntityInit.SOUL.get(), SoulRenderer::new);
		event.registerEntityRenderer(EntityInit.SOULFLAME_FIREBALL.get(), SmallThrownItemRenderer::new);
		event.registerEntityRenderer(EntityInit.DESERT_SORCERESS.get(), DesertSorceressRenderer::new);
		event.registerEntityRenderer(EntityInit.FLYING_SKULL.get(), FlyingSkullRenderer::new);
		event.registerEntityRenderer(EntityInit.DESERT_ACOLYTE.get(), DesertAcolyteRenderer::new);
		event.registerEntityRenderer(EntityInit.FLAME.get(), SmallThrownItemRenderer::new);
		event.registerEntityRenderer(EntityInit.FLAME_SUMMONER.get(), InvisibleProjectileRenderer::new);
	}
	@SubscribeEvent
	public static void registerRenderers(final RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ParticleInit.CURSEFLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(ParticleInit.SMALL_CURSEFLAME.get(), FlameParticle.SmallFlameProvider::new);
		event.registerSpriteSet(ParticleInit.SMALL_SOULFLAME.get(), FlameParticle.SmallFlameProvider::new);
	}



	
}
