package net.nicguzzo.deepslateinstamine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;


public class Config {

	private static Config INSTANCE = null;
	public boolean enable_renewable_deepslate = true;
	public int renewable_deepslate_below_level = 11;
	public boolean enable_logs_instamine = true;
	public float speed_factor=1000f;
	public String[] pickaxe_instamine={"minecraft:deepslate","minecraft:cobblestone","minecraft:end_stone"};
	static public List<Block> pickaxe_instamine_blk=new ArrayList<>();

    public static void load_config() {
		INSTANCE = new Config();
		Gson gson = new Gson();
		File configFile = new File(DeepslateInstamineExpectPlatform.getConfigDirectory().toString(), "deepslate_instamine.json");
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
		if(INSTANCE!=null) {
			for (String id : INSTANCE.pickaxe_instamine) {
				ResourceLocation res = ResourceLocation.tryParse(id);
				if (res != null) {
					Item item = Registry.ITEM.get(res);
					if (item != null && item != Items.AIR) {
						Block blk = Block.byItem(item);
						if (blk != null) {
							pickaxe_instamine_blk.add(blk);
						}
					}
				}
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
