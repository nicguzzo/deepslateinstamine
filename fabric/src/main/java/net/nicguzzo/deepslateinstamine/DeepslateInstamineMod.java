package net.nicguzzo.deepslateinstamine;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.loader.api.FabricLoader;

public class DeepslateInstamineMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");

	public static final Config CONFIG = Config.get_instance();

	@Override
	public void onInitialize() {

	}
	static public String get_config_dir(){
		return FabricLoader.getInstance().getConfigDir().toString();
	}
}
