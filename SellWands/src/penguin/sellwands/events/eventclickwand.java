package penguin.sellwands.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.economy.Economy;
import penguin.sellwands.Main;
import penguin.sellwands.objects.Config;
import penguin.sellwands.objects.SellWand;
import penguin.sellwands.utils.m;
import penguin.sellwands.utils.seperms;

public class eventclickwand implements Listener{
	
	private Main plugin;
	
	public eventclickwand(Main plugin) {
		this.plugin	= plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);	}
	

	@EventHandler
	public void sellInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (SellWand.isSellWand(p.getInventory().getItemInMainHand())) {
			ItemStack item = p.getInventory().getItemInMainHand();
			SellWand wand = new SellWand(item);
			if (e.getClickedBlock().getType() == Material.CHEST) {
				e.setCancelled(true);
				Chest c = (Chest) e.getClickedBlock().getState();
				if (seperms.canBuild(p, c.getLocation())) {
					if (item.getAmount() == 1) {
						Inventory chest = c.getInventory();
						HashMap<Material, Double> map = Config.getSellables();
						List<ItemStack> toremove = new ArrayList<>();
						int items = 0;
						double total = 0;
						for (ItemStack i : chest.getContents()) {
							if (i == null) continue;
							if (map.keySet().contains(i.getType())) {
								int amount = i.getAmount();
								Material type = i.getType();
								items += amount;
								total += (amount * map.get(type));
								toremove.add(i);
							}
						}
						if (items > 0) {
						Economy eco = Main.getPlugin().eco;			
						for (ItemStack i : toremove) chest.remove(i); 
						eco.depositPlayer(p, total);
						//item = wand.isInfinite() ? wand.getItem() : wand.takeUse();
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








			
	
