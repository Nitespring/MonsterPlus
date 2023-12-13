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
import github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieModel;
import github.nitespring.monsterplus.client.render.entities.mobs.crystalzombie.CrystalZombieRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.glowskeleton.GlowSkeletonModel;
import github.nitespring.monsterplus.client.render.entities.mobs.glowskeleton.GlowSkeletonRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.LavaSquidRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.MotherLavaSquidModel;
import github.nitespring.monsterplus.client.render.entities.mobs.lavasquid.MotherLavaSquidRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton.OvergrownSkeletonModel;
import github.nitespring.monsterplus.client.render.entities.mobs.overgrownskeleton.OvergrownSkeletonRenderer;
import github.nitespring.monsterplus.client.render.entities.mobs.swampzombie.SwampZombieRenderer;
import github.nitespring.monsterplus.client.render.entities.projectiles.CrystalArrowRenderer;
import github.nitespring.monsterplus.client.render.entities.specialeffects.BloodySlashRenderer;
import github.nitespring.monsterplus.client.render.equipment.crystalarmour.CrystalArmourModel;
import github.nitespring.monsterplus.client.render.equipment.crystalarmour.CrystalArmourModelNew;
import github.nitespring.monsterplus.core.init.EntityInit;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonsterPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

	public static final ModelLayerLocation GLOW_SKELETON_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "glow_skeleton"), "main");
	public static final ModelLayerLocation LAVA_SQUID_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "lava_squid"), "main");
	public static final ModelLayerLocation MOTHER_LAVA_SQUID_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "mother_lava_squid"), "main");
	public static final ModelLayerLocation EYE_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "opalescent_eye"), "main");
	public static final ModelLayerLocation EYE_OVERLAY_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "opalescent_eye"), "overlay");
	public static final ModelLayerLocation SPIKES_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "crystal_spikes"), "main");
	public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "square_texture"), "main");
	public static final ModelLayerLocation CRYSTAL_ZOMBIE_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "crystal_zombie"), "main");
	public static final ModelLayerLocation CRYSTAL_ARMOUR_MAIN_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "crystal_armour"), "main");
	public static final ModelLayerLocation OVERGROWN_SKELETON_LAYER = new ModelLayerLocation(new ResourceLocation(MonsterPlus.MODID, "overgrown_skeleton"), "main");
	
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
		
		event.registerLayerDefinition(CRYSTAL_ARMOUR_MAIN_LAYER, 
				() -> LayerDefinition.create(CrystalArmourModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 64));
		//event.registerLayerDefinition(CRYSTAL_ARMOUR_MAIN_LAYER, 
		//		() -> LayerDefinition.create(CrystalArmourModelNew.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 64));
		
    }
	
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
		
	}
	
}
