package github.nitespring.monsterplus.core.init;


import github.nitespring.monsterplus.MonsterPlus;
import github.nitespring.monsterplus.common.entity.Abyssologer;
import github.nitespring.monsterplus.common.entity.CrystalZombie;
import github.nitespring.monsterplus.common.entity.Eye;
import github.nitespring.monsterplus.common.entity.GlowSkeleton;
import github.nitespring.monsterplus.common.entity.LavaSquid;
import github.nitespring.monsterplus.common.entity.MotherLavaSquid;
import github.nitespring.monsterplus.common.entity.OvergrownSkeleton;
import github.nitespring.monsterplus.common.entity.SpectralSkeleton;
import github.nitespring.monsterplus.common.entity.SwampZombie;
import github.nitespring.monsterplus.common.entity.projectiles.BloodySlashEntity;
import github.nitespring.monsterplus.common.entity.projectiles.CrystalArrow;
import github.nitespring.monsterplus.common.entity.projectiles.CrystalClump;
import github.nitespring.monsterplus.common.entity.projectiles.CrystalSpikes;
import github.nitespring.monsterplus.common.entity.projectiles.PurpleFireball;
import github.nitespring.monsterplus.common.entity.projectiles.SpikeCountdown;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
			 MonsterPlus.MODID);


	
	//EntityType<Entity>
	
	
	public static final RegistryObject<EntityType<GlowSkeleton>> GLOW_SKELETON = ENTITIES.register("glow_skeleton",
			() -> EntityType.Builder.<GlowSkeleton>of(GlowSkeleton::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F)
			.build("glow_skeleton"));
	
	public static final RegistryObject<EntityType<LavaSquid>> LAVA_SQUID = ENTITIES.register("lava_squid",
			() -> EntityType.Builder.<LavaSquid>of(LavaSquid::new, MobCategory.MONSTER)
			.sized(0.8F, 0.8F)
			.build("lava_squid"));
	
	public static final RegistryObject<EntityType<MotherLavaSquid>> MOTHER_LAVA_SQUID = ENTITIES.register("mother_lava_squid",
			() -> EntityType.Builder.<MotherLavaSquid>of(MotherLavaSquid::new, MobCategory.MONSTER)
			.sized(5.2F, 3.6F)
			.build("mother_lava_squid"));
	
	public static final RegistryObject<EntityType<SpectralSkeleton>> SPECTRAL_SKELETON = ENTITIES.register("spectral_skeleton", 
            ()-> EntityType.Builder.<SpectralSkeleton>of(SpectralSkeleton::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("spectral_skeleton"));
	
	public static final RegistryObject<EntityType<Eye>> EYE = ENTITIES.register("opalescent_eye",
			() -> EntityType.Builder.<Eye>of(Eye::new, MobCategory.MONSTER)
			.sized(0.5F, 0.5F)
			.build("lava_squid"));
	
	public static final RegistryObject<EntityType<Abyssologer>> ABYSSOLOGER = ENTITIES.register("abyssologer", 
            ()-> EntityType.Builder.<Abyssologer>of(Abyssologer::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("abyssologer"));
	
	public static final RegistryObject<EntityType<SwampZombie>> SWAMP_ZOMBIE = ENTITIES.register("swamp_zombie", 
            ()-> EntityType.Builder.<SwampZombie>of(SwampZombie::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("swamp_zombie"));
	
	public static final RegistryObject<EntityType<CrystalZombie>> CRYSTAL_ZOMBIE = ENTITIES.register("crystal_zombie", 
            ()-> EntityType.Builder.<CrystalZombie>of(CrystalZombie::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("crystal_zombie"));
	
	public static final RegistryObject<EntityType<OvergrownSkeleton>> OVERGROWN_SKELETON = ENTITIES.register("overgrown_skeleton", 
            ()-> EntityType.Builder.<OvergrownSkeleton>of(OvergrownSkeleton::new,MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
			.build("overgrown_skeleton"));
	
	
	
	public static final RegistryObject<EntityType<CrystalSpikes>> CRYSTAL_SPIKES = ENTITIES.register("crystal_spikes", 
            ()-> EntityType.Builder.<CrystalSpikes>of(CrystalSpikes::new,MobCategory.MISC)
            .sized(1.0F, 2.5F)
			.build("crystal_spikes"));
	public static final RegistryObject<EntityType<PurpleFireball>> PURPLE_FIREBALL = ENTITIES.register("purple_fireball", 
            ()-> EntityType.Builder.<PurpleFireball>of(PurpleFireball::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("purple_fireball"));
	public static final RegistryObject<EntityType<SpikeCountdown>> SPIKE_COUNTDOWN = ENTITIES.register("spike_countdown", 
            ()-> EntityType.Builder.<SpikeCountdown>of(SpikeCountdown::new,MobCategory.MISC)
            .sized(1.0F, 0.1F)
			.build("spike_countdown"));
	public static final RegistryObject<EntityType<CrystalClump>> CRYSTAL_CLUMP = ENTITIES.register("crystal_clump", 
            ()-> EntityType.Builder.<CrystalClump>of(CrystalClump::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("crystal_clump"));
	public static final RegistryObject<EntityType<CrystalArrow>> CRYSTAL_ARROW = ENTITIES.register("crystal_arrow", 
            ()-> EntityType.Builder.<CrystalArrow>of(CrystalArrow::new,MobCategory.MISC)
            .sized(0.4F, 0.4F)
			.build("crystal_arrow"));
	public static final RegistryObject<EntityType<BloodySlashEntity>> BLOODY_SLASH = ENTITIES.register("bloody_slash", 
            ()-> EntityType.Builder.<BloodySlashEntity>of(BloodySlashEntity::new,MobCategory.MISC)
            .sized(3.5F, 1.5F)
			.build("bloody_slash"));
	
	
}
