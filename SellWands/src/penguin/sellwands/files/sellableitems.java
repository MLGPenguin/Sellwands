package penguin.sellwands.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import penguin.sellwands.Main;

public class sellableitems {

	private static File ItemList = new File("plugins/SellWands/ItemList.yml");
	private static FileConfiguration ItemListconfig = YamlConfiguration.loadConfiguration(ItemList);
		
		
		
		public static void setup() {
					
			if (!ItemList.exists()) {
				Main.getPlugin().saveResource("ItemList.yml", false);
				}
			
		ItemListconfig = YamlConfiguration.loadConfiguration(ItemList);
		
		}
	public static FileConfiguration get() {
		return ItemListconfig;
	}


	public static void save() {
		try {
			ItemListconfig.save(ItemList);
		} catch (IOException e) {
			System.out.println("couldn't save");
		}
	}

	public static void reload() {
		ItemListconfig = YamlConfiguration.loadConfiguration(ItemList);
	}
	
	public static void mydefaults() {
		
	}
	
}

