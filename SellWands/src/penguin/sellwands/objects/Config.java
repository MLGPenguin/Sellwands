package penguin.sellwands.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import penguin.sellwands.Main;
import penguin.sellwands.files.sellableitems;
import penguin.sellwands.utils.u;

public class Config {
	
	private static Main plugin = Main.getPlugin();
	private static FileConfiguration cfg = plugin.getConfig();
	
	public static Material getWandMaterial() { return Material.matchMaterial(cfg.getString("wandItem")); }
	public static String getWandName() { return u.cc(cfg.getString("wandName")); }
	public static boolean getWandGlowing() { return cfg.getBoolean("glowing"); }
	public static void setWorth(Material type, double amount) { sellableitems.get().set(type.toString(), amount); sellableitems.save(); }
	public static String prefix() { return u.cc(cfg.getString("PluginPrefix")); }
	public static List<String> getSellableNames() { List<String> list = new ArrayList<>(); list.addAll(sellableitems.get().getConfigurationSection("").getKeys(false)); return list; }
	
	public static void removeWorth(Material type) {
		if (sellableitems.get().contains(type.toString())) {
			sellableitems.get().set(type.toString(), null);
			sellableitems.save();
		}
	}
	
	public static List<String> getWandLore(boolean infinite, int uses) { 
		List<String> lore = cfg.getStringList("wandLore"); 
		List<String> endlore = new ArrayList<>();
		for (String x: lore) {
			endlore.add(u.cc(x.replaceAll("%uses%", infinite ? "Infinite" : String.valueOf(uses))));
		}
		return endlore;
	}

	public static HashMap<Material, Double> getSellables() { 
		HashMap<Material, Double> map = new HashMap<>();
		ConfigurationSection cs = sellableitems.get().getConfigurationSection("");
		if (cs == null) return new HashMap<>();
		for (String x : cs.getKeys(false)) {
			Material m = Material.matchMaterial(x);
			if (m == null) {
				Bukkit.getConsoleSender().sendMessage(u.cc(prefix() + "&cError in folder ItemList.yml, Unknown Material: " + x));
				continue;
			}
			map.put(m, cs.getDouble(x));
		}
		return map;
	}

}
