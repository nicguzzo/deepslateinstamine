package net.nicguzzo.deepslateinstamine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;

public class Config {

	private static Config INSTANCE = null;
	public boolean enable_renewable_deepslate = true;
	public int renewable_deepslate_below_level = 11;
	public boolean enable_logs_instamine = true;
    public boolean enable_cobblestone_instamine=false;
	public boolean enable_endstone_instamine=false;

    public static void load_config() {
		INSTANCE = new Config();
		Gson gson = new Gson();
		File configFile = new File(DeepslateInstamineMod.get_config_dir(), "deepslate_instamine.json");
		try (FileReader reader = new FileReader(configFile)) {
			INSTANCE = gson.fromJson(reader, Config.class);
			System.out.println("Config: " + INSTANCE);
			try (FileWriter writer = new FileWriter(configFile)) {
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(INSTANCE));
				System.out.println("Config updated!");
			} catch (IOException e2) {
				System.out.println("Failed to update config file!");
			}
			System.out.println("Config loaded!");

		} catch (IOException e) {
			System.out.println("No config found, generating!");
			INSTANCE = new Config();
			try (FileWriter writer = new FileWriter(configFile)) {
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(INSTANCE));
			} catch (IOException e2) {
				System.out.println("Failed to generate config file!");
			}
		}
	}

	public static Config get_instance() {
		if (INSTANCE == null) {
			load_config();
		}
		return INSTANCE;
	}
}
