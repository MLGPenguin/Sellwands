package penguin.sellwands.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import penguin.sellwands.Main;
import penguin.sellwands.objects.Config;
import penguin.sellwands.utils.ItemBuilder;
import penguin.sellwands.utils.u;

public class sellwandlistgui {

	private Main plugin = Main.getPlugin(Main.class);


	public void Ilist(Player p) {
		Inventory Ilist = plugin.getServer().createInventory(null, 54, ChatColor.LIGHT_PURPLE + "SellWand Shop");

		ItemStack glass = new ItemBuilder(Material.BLACK_STAINED_GLASS, 1).setTitle(u.cc("&r")).build();
		ItemStack nextpage = new ItemBuilder(Material.PAPER, 1).setTitle(u.cc("&5&lNext Page")).build();
		ItemStack previouspage = new ItemBuilder(Material.PAPER).setTitle(u.cc("&5&lPrevious Page")).build();


		Ilist.setItem(45, previouspage);
		Ilist.setItem(53, nextpage);
		for (int newi = 46 ; newi < 53 ; newi++) {
			Ilist.setItem(newi, glass); }


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
				Ilist.setItem(hi, yeah);
				hi++;
			} 
		}


		p.openInventory(Ilist);


	}

}
