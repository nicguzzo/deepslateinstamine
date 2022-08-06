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
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Config {

	private static Config INSTANCE = null;
	public boolean enable_renewable_deepslate = true;
	public int renewable_deepslate_below_level = 11;
	public boolean enable_logs_instamine = true;
	public float speed_factor=1000f;
	public String[] pickaxe_instamine_blocks={"minecraft:deepslate","minecraft:cobblestone","minecraft:end_stone"};
	public String[] pickaxes_that_can_instamine={"minecraft:netherite_pickaxe"};
	public String[] axes_that_can_instamine={"minecraft:netherite_axe"};
	static public List<Block> pickaxe_instamine_blk=new ArrayList<>();
	static public List<Item> pickaxes_item=new ArrayList<>();
	static public List<Item> axes_item=new ArrayList<>();
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");
    public static void load_config() {
		INSTANCE = new Config();
		Gson gson = new Gson();
		File configFile = new File(DeepslateInstamineExpectPlatform.getConfigDirectory().toString(), "deepslate_instamine.json");
		try (FileReader reader = new FileReader(configFile)) {
			INSTANCE = gson.fromJson(reader, Config.class);
			LOGGER.info("Config: " + INSTANCE);
			try (FileWriter writer = new FileWriter(configFile)) {
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(INSTANCE));
				LOGGER.info("Config updated!");
			} catch (IOException e2) {
				LOGGER.error("Failed to update config file!");
			}
			LOGGER.info("Config loaded!");

		} catch (IOException e) {
			LOGGER.info("No config found, generating!");
			INSTANCE = new Config();
			try (FileWriter writer = new FileWriter(configFile)) {
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(INSTANCE));
			} catch (IOException e2) {
				LOGGER.error("Failed to generate config file!");
			}
		}
		if(INSTANCE!=null) {
			for (String id : INSTANCE.pickaxe_instamine_blocks) {
				LOGGER.info("trying: " + id);
				ResourceLocation res = ResourceLocation.tryParse(id);
				if (res != null) {
					Item item = Registry.ITEM.get(res);
					if (item != null && item != Items.AIR) {
						Block blk = Block.byItem(item);
						if (blk != null) {
							pickaxe_instamine_blk.add(blk);
							LOGGER.info("Instamine block: "+blk);
						}else{
							LOGGER.info("This item: " + item+" can't be instamined, not a Block");
						}
					}else{
						LOGGER.info("item not found: " + id);
					}
				}else{
					LOGGER.info("resource not found: " + id);
				}
			}
			for (String id : INSTANCE.pickaxes_that_can_instamine) {
				LOGGER.info("trying: " + id);
				ResourceLocation res = ResourceLocation.tryParse(id);
				if (res != null) {
					Item item = Registry.ITEM.get(res);
					if (item != null && item != Items.AIR ) {
						if(item instanceof DiggerItem) {
							pickaxes_item.add(item);
							LOGGER.info("Instamine pickaxe: " + item);
						}else{
							LOGGER.info("This item: " + item+" can't be used to instamine, not a DiggerItem");
						}
					}else{
						LOGGER.info("item not found: " + id);
					}
				}else{
					LOGGER.info("resource not found: " + id);
				}
			}
			for (String id : INSTANCE.axes_that_can_instamine) {
				LOGGER.info("trying: " + id);
				ResourceLocation res = ResourceLocation.tryParse(id);
				if (res != null) {
					Item item = Registry.ITEM.get(res);
					if (item != null && item != Items.AIR) {
						if(item instanceof DiggerItem) {
							axes_item.add(item);
							LOGGER.info("Instamine axe: " + item);
						}else{
							LOGGER.info("This item: " + item+" can't be used to instamine, not a DiggerItem");
						}
					}else{
						LOGGER.info("item not found: " + id);
					}
				}else{
					LOGGER.info("resource not found: " + id);
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
