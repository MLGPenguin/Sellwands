package penguin.sellwands.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import penguin.sellwands.Main;
import penguin.sellwands.utils.u;

public class unstackable implements Listener{
	
	private Main plugin;
	
	public unstackable(Main plugin) {
		this.plugin	= plugin; Bukkit.getPluginManager().registerEvents(this, plugin);	}
	
	@EventHandler
// Just for manually stacking.	
		public void clickstack(InventoryClickEvent e) {
if (e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.LEFT)) {
if (e.getCursor() != null && e.getCurrentItem() != null) {
	ItemStack ci = e.getCursor();
	ItemStack si  = e.getCurrentItem();
if (ci.hasItemMeta() && si.hasItemMeta()) {
	PersistentDataContainer cip = ci.getItemMeta().getPersistentDataContainer();
	PersistentDataContainer sip = si.getItemMeta().getPersistentDataContainer();
	NamespacedKey key = new NamespacedKey(plugin, "sellwands");
if (cip.has(key, PersistentDataType.STRING) || cip.has(key, PersistentDataType.INTEGER)) {
if (sip.has(key, PersistentDataType.STRING) || sip.has(key, PersistentDataType.INTEGER)) {
if (cip.equals(sip)) {
	e.setCancelled(true); } } } } } 

//now we're gonna try and talk about shift click stacking of all forms.	

} else {

if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) { /////// elseable
	ItemStack yeeye = e.getCurrentItem();
if (yeeye.hasItemMeta()) {
	PersistentDataContainer cip = yeeye.getItemMeta().getPersistentDataContainer();
	NamespacedKey key = new NamespacedKey(plugin, "sellwands");
if (cip.has(key, PersistentDataType.STRING) || cip.has(key, PersistentDataType.INTEGER)) {
	Player p = (Player) e.getWhoClicked();
	
	
	
// here we check if they clicked inside their own inventory with no other inventory open.
	// and this chunk is for checking if they clicked in their crafting inv.
if (e.getView().getTopInventory().getType().equals(InventoryType.CRAFTING)) { ///////////////// this needs else
	p.sendMessage(u.dc(e.getRawSlot()));
if (0 < e.getRawSlot() && 6 > e.getRawSlot()) { //////////// else check if it's within widths of normal inv.
if (!(e.getView().getBottomInventory().firstEmpty() == -1)) {	
int ohno = 0;
for (ItemStack yes : e.getView().getBottomInventory().getContents()) {
	if (yes != null ) {
		PersistentDataContainer yesc = yes.getItemMeta().getPersistentDataContainer();
	if (yesc.has(key, PersistentDataType.INTEGER) || yesc.has(key, PersistentDataType.STRING)) {
	ohno = ohno + 1; } } }

if (ohno > 0) {
	e.setCancelled(true);
} 

} else { e.setCancelled(true); }


//here we have the chunk for when they actually click inside their inventory.

} else {
	if (e.getRawSlot() > 8 && e.getRawSlot() < 45) {
		p.sendMessage("this is a player inventory.");
	}
}

}}}
		// if target inventory contains a similar item ^^^
		// find first empty and make that the destination slot
		///// spigot api offers getclickedinventory, getinventory AND TELLS WHICH ONE FROM RAWSLOT!
	// also convert slot/.
		/* so basically check if they have multiple inventories open.
		 * if yes > figure out whether it was top or bottom (getclickedinventory? ( == player inv?))
		 * if no > check whether it's in the hot bar or not, then check if slots not in hotbar or
		 * in hotbar (opposite) contain the same item, if so, *if applicable* move to first null (for loop)
		 * if there is no null, cancel event.
		 */
	}
}
	}
	


	
	
	
	}
