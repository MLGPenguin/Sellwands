package penguin.sellwands.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import penguin.sellwands.Main;
import penguin.sellwands.files.sellableitems;
import penguin.sellwands.objects.Config;
import penguin.sellwands.utils.m;
import penguin.sellwands.utils.u;

public class SellWandShopGuiInteract implements Listener{


	@SuppressWarnings("unused")
	private Main plugin;
	public SellWandShopGuiInteract(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);	}



	@EventHandler
	public void itemlisthandler(InventoryInteractEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "SellWand Shop")){
			e.setCancelled(true); }	}


	@EventHandler
	public void deleteitem(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "SellWand Shop")){ 
			if (e.getClick().equals(ClickType.RIGHT)) {
				if (e.getRawSlot() < 45) {
					if (e.getCurrentItem() != null) {
						ItemStack clickeditem = e.getCurrentItem();
						String iname = clickeditem.getType().toString();
						Config.removeWorth(clickeditem.getType());
						Player p = (Player) e.getWhoClicked();

						for (int hey = 0 ; hey < 45 ; hey++) {
							e.getView().getTopInventory().setItem(hey, null); }

						HashMap<Material, Double> map = Config.getSellables();
						if (map.size() < 46) {
							int hi = 0;
							for (Material newmat : map.keySet()) {
								ItemStack yeah = new ItemStack(newmat, 1);
								ItemMeta yeahmeta = yeah.getItemMeta();
								List<String> lore = new ArrayList<>();
								lore.add(u.cc("&a$" + u.dc(map.get(newmat))));
								lore.add(u.cc("&cRight Click to remove this item."));
								yeahmeta.setLore(lore);
								yeah.setItemMeta(yeahmeta);
								e.getView().getTopInventory().setItem(hi, yeah);
								hi = hi + 1;
							} }


						e.getWhoClicked().sendMessage(m.removedWorth(clickeditem.getType()));
					}
				}
			}		
		}
	}


}
