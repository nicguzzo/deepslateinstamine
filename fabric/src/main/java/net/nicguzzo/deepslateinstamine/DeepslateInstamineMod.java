package net.nicguzzo.deepslateinstamine;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.loader.api.FabricLoader;

public class DeepslateInstamineMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");

	public static final Config config = Config.get_instance();
	//static public ModConfig config=null;
	@Override
	public void onInitialize() {
		//AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		//config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}
	static public String get_config_dir(){
		return FabricLoader.getInstance().getConfigDir().toString();
	}
}
