package penguin.sellwands.objects;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import penguin.sellwands.Main;
import penguin.sellwands.utils.u;

public class SellWand {
	
	private int uses;
	private boolean infinite;
	
	private ItemStack item;
	
	private static Main plugin = Main.getPlugin();
	public static NamespacedKey key = new NamespacedKey(plugin, "sellwands");
	public static String locname = "seLLwand";
	
	
	public SellWand(int uses) {
		this.uses = uses;
		infinite = uses < 0;
	}
	
	public SellWand(ItemStack item) {
		if (isSellWand(item)) {
			if (isInfinite(item)) {
				infinite = true;
				uses = -1;
			} else {
				infinite = false;
				uses = getUses(item);
			}
			item = getItem();
		}
	}
	
	
	
	public void useWand(Inventory chest) {
		// TODO
	}
	
	public ItemStack getItem() {
		ItemStack wand = new ItemStack(Config.getWandMaterial());
		ItemMeta meta = wand.getItemMeta();
		if (infinite || uses < 0) meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "infinite");
		else meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, uses);
		meta.setLocalizedName(locname);
		meta.setLore(Config.getWandLore(infinite, uses));
		meta.setDisplayName(Config.getWandName());
		if (Config.getWandGlowing()) {
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		
		wand.setItemMeta(meta);
		this.item = wand;
		return wand;
	}
	
	public ItemStack takeUse() {
		if (isInfinite(item)) return item;
		uses -= 1;
		if (uses == 0) return new ItemStack(Material.AIR);
		ItemMeta meta = item.getItemMeta();
		if (!infinite) meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, uses);
		meta.setLore(Config.getWandLore(infinite, uses));
		item.setItemMeta(meta);
		return item;
	}
	
	public int getUses() { return uses; } 
	
	private int getUses(ItemStack item) {
		if (isSellWand(item)) {
			if (isInfinite(item)) return -1;
			PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
			if (c.has(key, PersistentDataType.INTEGER)) {
				return c.get(key, PersistentDataType.INTEGER);
			}			
		}
		return 0;
	}
	
	private boolean isInfinite(ItemStack item) {
		if (isSellWand(item)) {
			PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
			if (c.has(key, PersistentDataType.STRING)) {
				return (c.get(key, PersistentDataType.STRING).equalsIgnoreCase("infinite"));
			}
		}
		return false;
	}
	public boolean isInfinite() { return infinite; }
	
	public static boolean isSellWand(ItemStack item) {
		return (item != null && item.getItemMeta() != null && item.getItemMeta().hasLocalizedName() 
				&& item.getItemMeta().getLocalizedName().equals(locname));
	}

}
