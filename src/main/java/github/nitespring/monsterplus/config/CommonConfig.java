package github.nitespring.monsterplus.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

	

		
		public static ForgeConfigSpec.BooleanValue spawn_abyssologer;
		public static ForgeConfigSpec.BooleanValue spawn_crystal_zombie;
		public static ForgeConfigSpec.BooleanValue spawn_swamp_zombie;
		public static ForgeConfigSpec.BooleanValue spawn_glow_skeleton;
		public static ForgeConfigSpec.BooleanValue spawn_lava_squid;
		public static ForgeConfigSpec.BooleanValue spawn_mother_lava_squid;
		public static ForgeConfigSpec.BooleanValue spawn_overgrown_skeleton;
		public static ForgeConfigSpec.BooleanValue do_abyssologer_darkness;
		
		
		
	public CommonConfig(final ForgeConfigSpec.Builder server) {
			
		server.comment("Spawn configs:");
			
			
		do_abyssologer_darkness = server
				.comment("Will the Abyssologer affect players with Darkness?")
				.define("spawnconfig.do_abyssologer_darkness", true);
			
		spawn_abyssologer = server
				.comment("Will the Abyssologer spawn?")
				.define("spawnconfig.spawn_abyssologer", true);
			      
		spawn_crystal_zombie = server
				.comment("Will the Crystal Zombie spawn?")
				.define("spawnconfig.spawn_crystal_zombie", true);
			
		spawn_swamp_zombie = server
				.comment("Will the Swamp Zombie spawn?")
				.define("spawnconfig.spawn_swamp_zombie", true);
		
		spawn_glow_skeleton = server
				.comment("Will the Glow Skeleton spawn?")
				.define("spawnconfig.spawn_glow_skeleton", true);
		
		spawn_lava_squid = server
				.comment("Will the Lava Squid spawn?")
				.define("spawnconfig.spawn_lava_squid", true);
	
		spawn_mother_lava_squid = server
				.comment("Will the Mother Lava Squid spawn?")
				.define("spawnconfig.spawn_mother_lava_squid", true);
		
		spawn_overgrown_skeleton = server
				.comment("Will the Overgrown Skeleton spawn?")
				.define("spawnconfig.spawn_overgrown_skeleton", true);
	
			
			
		}

	
}
