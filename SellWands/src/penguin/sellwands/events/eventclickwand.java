package penguin.sellwands.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Penguin.permissions.seperms;
import net.milkbowl.vault.economy.Economy;
import penguin.sellwands.Main;
import penguin.sellwands.objects.SellWand;
import penguin.sellwands.utils.m;
import penguin.sellwands.utils.sellables;

public class eventclickwand implements Listener{
	
	private Main plugin;
	
	public eventclickwand(Main plugin) {
		this.plugin	= plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);	}
	

	@EventHandler
	public void sellInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc = e.getClickedBlock().getLocation();
		if (SellWand.isSellWand(p.getInventory().getItemInMainHand())) {
			ItemStack item = p.getInventory().getItemInMainHand();
			SellWand wand = new SellWand(item);
			if (e.getClickedBlock().getType() == Material.CHEST) {
				e.setCancelled(true);
				Chest c = (Chest) e.getClickedBlock().getState();
				if (seperms.canBuild(p, c.getLocation())) {
					if (seperms.getCoreProtect() != null) seperms.getCoreProtect().logContainerTransaction(p.getName() + ": Sellwand", loc);
					if (item.getAmount() == 1) {
						Inventory chest = c.getInventory();
						List<ItemStack> toremove = new ArrayList<>();
						int items = 0;
						double total = 0;
						for (ItemStack i : chest.getContents()) {
							if (i == null) continue;
							Material type = i.getType();
							if (sellables.CanSell(type)) {
								items += i.getAmount();
								total += (sellables.getPrice(i));
								toremove.add(i);
							}
						}
						if (items > 0) {
							Economy eco = Main.getPlugin().eco;			
							for (ItemStack i : toremove) chest.remove(i); 
							eco.depositPlayer(p, total);
							item = wand.takeUse();
							p.getInventory().setItemInMainHand(item);						
						p.sendMessage(m.moneyreceived(p, total, items));
						if (item.getType() == Material.AIR) p.sendMessage(m.sellwandbroke());
						} else p.sendMessage(m.noItemsToSell());
					} else p.sendMessage(m.multiWand());
				} else p.sendMessage(m.noSellHere());
			}
		}
	}




}








			
	
