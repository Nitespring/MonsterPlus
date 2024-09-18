package github.nitespring.monsterplus.core.init;


import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.*;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHero;
import github.nitespring.monsterplus.common.entity.ancienthero.AncientHeroSkull;
import github.nitespring.monsterplus.common.entity.projectiles.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EntityInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,
			 MonsterPlus.MODID);


	
	//EntityType<Entity>
	
	
	public static final DeferredHolder<EntityType<?>,EntityType<GlowSkeleton>> GLOW_SKELETON = ENTITIES.register("glow_skeleton",
			() -> EntityType.Builder.<GlowSkeleton>of(GlowSkeleton::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F)
			.build("glow_skeleton"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<LavaSquid>> LAVA_SQUID = ENTITIES.register("lava_squid",
			() -> EntityType.Builder.<LavaSquid>of(LavaSquid::new, MobCategory.MONSTER)
			.sized(0.8F, 0.8F)
			.build("lava_squid"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<MotherLavaSquid>> MOTHER_LAVA_SQUID = ENTITIES.register("mother_lava_squid",
			() -> EntityType.Builder.<MotherLavaSquid>of(MotherLavaSquid::new, MobCategory.MONSTER)
			.sized(5.2F, 3.6F)
			.build("mother_lava_squid"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<SpectralSkeleton>> SPECTRAL_SKELETON = ENTITIES.register("spectral_skeleton", 
            ()-> EntityType.Builder.<SpectralSkeleton>of(SpectralSkeleton::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("spectral_skeleton"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<Eye>> EYE = ENTITIES.register("opalescent_eye",
			() -> EntityType.Builder.<Eye>of(Eye::new, MobCategory.MONSTER)
			.sized(0.5F, 0.5F)
			.build("opalescent_eye"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<Abyssologer>> ABYSSOLOGER = ENTITIES.register("abyssologer", 
            ()-> EntityType.Builder.<Abyssologer>of(Abyssologer::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("abyssologer"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<SwampZombie>> SWAMP_ZOMBIE = ENTITIES.register("swamp_zombie", 
            ()-> EntityType.Builder.<SwampZombie>of(SwampZombie::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("swamp_zombie"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<CrystalZombie>> CRYSTAL_ZOMBIE = ENTITIES.register("crystal_zombie", 
            ()-> EntityType.Builder.<CrystalZombie>of(CrystalZombie::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("crystal_zombie"));
	
	public static final DeferredHolder<EntityType<?>,EntityType<OvergrownSkeleton>> OVERGROWN_SKELETON = ENTITIES.register("overgrown_skeleton", 
            ()-> EntityType.Builder.<OvergrownSkeleton>of(OvergrownSkeleton::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("overgrown_skeleton"));
	public static final DeferredHolder<EntityType<?>,EntityType<DemonEye>> DEMON_EYE = ENTITIES.register("demon_eye",
			() -> EntityType.Builder.<DemonEye>of(DemonEye::new, MobCategory.MONSTER)
					.sized(0.75F, 0.75F)
					.build("demon_eye"));
	public static final DeferredHolder<EntityType<?>,EntityType<EnderEye>> ENDER_EYE = ENTITIES.register("ender_eye",
			() -> EntityType.Builder.<EnderEye>of(EnderEye::new, MobCategory.MONSTER)
					.sized(0.75F, 0.75F)
					.build("ender_eye"));
	public static final DeferredHolder<EntityType<?>,EntityType<AncientHero>> ANCIENT_HERO = ENTITIES.register("ancient_hero",
			()-> EntityType.Builder.<AncientHero>of(AncientHero::new,MobCategory.MONSTER)
					.sized(0.6F, 2.0F)
					.build("ancient_hero"));
	public static final DeferredHolder<EntityType<?>,EntityType<AncientHeroSkull>> ANCIENT_HERO_SKULL = ENTITIES.register("ancient_hero_skull",
			()-> EntityType.Builder.<AncientHeroSkull>of(AncientHeroSkull::new,MobCategory.MONSTER)
					.sized(0.6F, 0.6F)
					.build("ancient_hero_skull"));
	public static final DeferredHolder<EntityType<?>,EntityType<Wisp>> WISP = ENTITIES.register("wisp",
			()-> EntityType.Builder.<Wisp>of(Wisp::new,MobCategory.valueOf("MONSTERPLUS_WISP"))
					.sized(0.75F, 0.6F)
					.build("wisp"));
	public static final DeferredHolder<EntityType<?>,EntityType<Soul>> SOUL = ENTITIES.register("soul",
			()-> EntityType.Builder.<Soul>of(Soul::new,MobCategory.valueOf("MONSTERPLUS_WISP"))
					.sized(0.75F, 0.6F)
					.build("soul"));

	
	public static final DeferredHolder<EntityType<?>,EntityType<CrystalSpikes>> CRYSTAL_SPIKES = ENTITIES.register("crystal_spikes", 
            ()-> EntityType.Builder.<CrystalSpikes>of(CrystalSpikes::new,MobCategory.MISC)
            .sized(1.0F, 2.5F)
			.build("crystal_spikes"));
	public static final DeferredHolder<EntityType<?>,EntityType<PurpleFireball>> PURPLE_FIREBALL = ENTITIES.register("purple_fireball", 
            ()-> EntityType.Builder.<PurpleFireball>of(PurpleFireball::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("purple_fireball"));
	public static final DeferredHolder<EntityType<?>,EntityType<SpikeCountdown>> SPIKE_COUNTDOWN = ENTITIES.register("spike_countdown", 
            ()-> EntityType.Builder.<SpikeCountdown>of(SpikeCountdown::new,MobCategory.MISC)
            .sized(1.0F, 0.1F)
			.build("spike_countdown"));
	public static final DeferredHolder<EntityType<?>,EntityType<CrystalClump>> CRYSTAL_CLUMP = ENTITIES.register("crystal_clump", 
            ()-> EntityType.Builder.<CrystalClump>of(CrystalClump::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("crystal_clump"));
	public static final DeferredHolder<EntityType<?>,EntityType<CrystalArrow>> CRYSTAL_ARROW = ENTITIES.register("crystal_arrow", 
            ()-> EntityType.Builder.<CrystalArrow>of(CrystalArrow::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("crystal_arrow"));
	public static final DeferredHolder<EntityType<?>,EntityType<BloodySlashEntity>> BLOODY_SLASH = ENTITIES.register("bloody_slash", 
            ()-> EntityType.Builder.<BloodySlashEntity>of(BloodySlashEntity::new,MobCategory.MISC)
            .sized(3.5F, 1.5F)
			.build("bloody_slash"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkullProjectile>> SPECTRAL_SKULL_PROJECTILE = ENTITIES.register("spectral_skull_projectile",
			()-> EntityType.Builder.<SkullProjectile>of(SkullProjectile::new,MobCategory.MISC)
					.sized(0.6F, 0.6F)
					.build("spectral_skull_projectile"));
	public static final DeferredHolder<EntityType<?>,EntityType<CurseflameFireball>> CURSEFLAME_FIREBALL = ENTITIES.register("curseflame_fireball",
			()-> EntityType.Builder.<CurseflameFireball>of(CurseflameFireball::new,MobCategory.MISC)
					.sized(0.35F, 0.35F)
					.build("curseflame_fireball"));
	public static final DeferredHolder<EntityType<?>,EntityType<SoulflameFireball>> SOULFLAME_FIREBALL = ENTITIES.register("soulflame_fireball",
			()-> EntityType.Builder.<SoulflameFireball>of(SoulflameFireball::new,MobCategory.MISC)
					.sized(0.35F, 0.35F)
					.build("soulflame_fireball"));
	
	
}
