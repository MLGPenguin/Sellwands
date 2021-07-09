package penguin.sellwands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import penguin.sellwands.commands.sellwand;
import penguin.sellwands.events.SellWandShopGuiInteract;
import penguin.sellwands.events.eventclickwand;
import penguin.sellwands.files.messages;
import penguin.sellwands.files.sellableitems;
import penguin.sellwands.tabcompletes.completersellwand;
import penguin.sellwands.utils.m;
import penguin.sellwands.utils.u;

public class Main extends JavaPlugin {
	
	public Economy eco;
	
	public static Main getPlugin() { return Main.getPlugin(Main.class); }
	
	@Override
	public void onEnable(){
		
		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "Vault and Economy are required!");
			getServer().getPluginManager().disablePlugin(this);
			}
	
messages.setup();
messages.get().options().copyDefaults(true);
messages.save();

sellableitems.setup();
sellableitems.get().options().copyDefaults(true);
sellableitems.save();


new sellwand(this);
new completersellwand(this);
new eventclickwand(this);
new m(this);
new SellWandShopGuiInteract(this);
//new unstackable(this);

getConfig().options().copyDefaults();
saveDefaultConfig();


Bukkit.getLogger().info(u.cc("&aSellWands Enabled!"));	

	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = 
		getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
			if (economy != null) {
					eco = economy.getProvider();
		}
		return (eco != null);
		}

}
