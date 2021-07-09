package penguin.sellwands.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Penguin.shop.shop;
import penguin.sellwands.objects.Config;

public class sellables {
	
	public static boolean HasSGP() { return shop.hasShopGuiPlus(); }
	
	public static boolean CanSell(Material m) {
		if (HasSGP()) return shop.canSell(m);
		else return Config.getSellables().containsKey(m);
	}
	
	public static double getPrice(ItemStack i) {
		if (HasSGP()) return shop.getSellPrice(i);
		else return (Config.getSellables().get(i.getType()) * i.getAmount());
	}
	
	

}
