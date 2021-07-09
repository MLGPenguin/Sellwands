package penguin.sellwands.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import penguin.sellwands.Main;

public class messages {

	private static File messages = new File("plugins/SellWands/messages.yml");;
	private static FileConfiguration messagesconfig;
		
		
		
		public static void setup() {	
			if ((!messages.exists())) {
				Main.getPlugin().saveResource("messages.yml", false);
				}			
		messagesconfig = YamlConfiguration.loadConfiguration(messages);
		
		}
	public static FileConfiguration get() {
		return messagesconfig;
	}


	public static void save() {
		try {
			messagesconfig.save(messages);
		} catch (IOException e) {
			System.out.println("couldn't save");
		}
	}

	public static void reload() {
		messagesconfig = YamlConfiguration.loadConfiguration(messages);
	}
	
	public static void mydefaults() {
		
	}
	
}

