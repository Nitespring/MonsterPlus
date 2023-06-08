package com.nitespring.monsterplus.config;

import java.io.File;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.nitespring.monsterplus.MonsterPlus;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

	private static final ForgeConfigSpec.Builder common_builder = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder server_builder = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec common_config;
	public static final ForgeConfigSpec server_config;
	public static final ForgeConfigSpec client_config;
	
	public static final CommonConfig common;
	
	static
	{
		
		
		
		final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        common = specPair.getLeft();
        common_config = specPair.getRight();
		
		
		
		
		server_config = server_builder.build();
		client_config = client_builder.build();	
		//common_config = common_builder.build();
		
	}
	
	
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		
		MonsterPlus.LOGGER.info("Loading config" + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		MonsterPlus.LOGGER.info("Built config" + path);
		file.load();
		MonsterPlus.LOGGER.info("Loaded config" + path);
		config.setConfig(file);
		
	}
	
	
}

