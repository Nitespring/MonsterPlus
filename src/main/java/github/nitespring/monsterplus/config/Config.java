package github.nitespring.monsterplus.config;

import java.io.File;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import github.nitespring.monsterplus.MonsterPlus;


public class Config {

	private static final ModConfigSpec.Builder common_builder = new  ModConfigSpec.Builder();
	private static final  ModConfigSpec.Builder server_builder = new  ModConfigSpec.Builder();
	private static final  ModConfigSpec.Builder client_builder = new  ModConfigSpec.Builder();
	
	public static final  ModConfigSpec common_config;
	public static final  ModConfigSpec server_config;
	public static final  ModConfigSpec client_config;
	
	public static final CommonConfig common;
	
	static
	{
		
		
		
		final Pair<CommonConfig,  ModConfigSpec> specPair = new  ModConfigSpec.Builder().configure(CommonConfig::new);
        common = specPair.getLeft();
        common_config = specPair.getRight();
		
		
		
		
		server_config = server_builder.build();
		client_config = client_builder.build();	
		//common_config = common_builder.build();
		
	}
	
	
	
	public static void loadConfig( ModConfigSpec config, String path) {
		
		MonsterPlus.LOGGER.info("Loading config" + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		MonsterPlus.LOGGER.info("Built config" + path);
		file.load();
		MonsterPlus.LOGGER.info("Loaded config" + path);
		config.correct(file);
		
	}
	
	
}

