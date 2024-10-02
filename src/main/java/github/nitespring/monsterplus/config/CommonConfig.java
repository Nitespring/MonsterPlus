package github.nitespring.monsterplus.config;


import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {

	

		
		public static  ModConfigSpec.BooleanValue spawn_abyssologer;
		public static  ModConfigSpec.BooleanValue spawn_crystal_zombie;
		public static  ModConfigSpec.BooleanValue spawn_swamp_zombie;
		public static  ModConfigSpec.BooleanValue spawn_glow_skeleton;
		public static  ModConfigSpec.BooleanValue spawn_lava_squid;
		public static  ModConfigSpec.BooleanValue spawn_mother_lava_squid;
		public static  ModConfigSpec.BooleanValue spawn_overgrown_skeleton;
		public static  ModConfigSpec.BooleanValue spawn_demon_eye;
		public static  ModConfigSpec.BooleanValue spawn_ender_eye;
		public static  ModConfigSpec.BooleanValue spawn_ancient_hero;
		public static  ModConfigSpec.BooleanValue spawn_wisp;
		public static  ModConfigSpec.BooleanValue spawn_soul;
		public static  ModConfigSpec.BooleanValue spawn_spectral_skull;
		public static  ModConfigSpec.BooleanValue spawn_desert_acolyte;
		public static  ModConfigSpec.BooleanValue spawn_desert_sorceress;
		public static  ModConfigSpec.BooleanValue do_abyssologer_darkness;
		
		
		
	public CommonConfig(final  ModConfigSpec.Builder server) {
			
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
		spawn_demon_eye = server
				.comment("Will the Demon Eye spawn?")
				.define("spawnconfig.spawn_demon_eye", true);
		spawn_ender_eye = server
				.comment("Will the Ender Eye spawn?")
				.define("spawnconfig.spawn_ender_eye", true);
		spawn_ancient_hero = server
				.comment("Will the Corrupted Ancient Hero spawn?")
				.define("spawnconfig.spawn_ancient_hero", true);
		spawn_wisp = server
				.comment("Will the Wisp spawn?")
				.define("spawnconfig.spawn_wisp", true);
		spawn_soul = server
				.comment("Will the Soul spawn?")
				.define("spawnconfig.spawn_soul", true);
		spawn_spectral_skull = server
				.comment("Will the Spectral Skull spawn?")
				.define("spawnconfig.spawn_spectral_skull", true);
		spawn_desert_acolyte = server
				.comment("Will the Desert Acolyte spawn?")
				.define("spawnconfig.spawn_desert_acolyte", true);
		spawn_desert_sorceress = server
				.comment("Will the Desert Sorceress spawn?")
				.define("spawnconfig.spawn_desert_sorceress", true);


		}


	
}
