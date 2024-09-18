package github.nitespring.monsterplus.core.init;



import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleInit {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE,
			 MonsterPlus.MODID);

	public static final DeferredHolder<ParticleType<?>,SimpleParticleType> CURSEFLAME = PARTICLES.register("curseflame",
			() -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>,SimpleParticleType> SMALL_CURSEFLAME = PARTICLES.register("small_curseflame",
			() -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>,SimpleParticleType> SMALL_SOULFLAME = PARTICLES.register("small_soulflame",
			() -> new SimpleParticleType(false));


}
