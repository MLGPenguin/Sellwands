package penguin.sellwands.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import penguin.sellwands.Main;
import penguin.sellwands.files.messages;
import penguin.sellwands.objects.Config;

public class m {
	
	private static Main plugin;
	private static FileConfiguration mess = messages.get();
	private static String prefix = u.cc(Config.prefix());
	
	public m(Main plugin) {
		m.plugin = plugin;
	}
	
	public static String s() { return u.cc(prefix + mess.getString("")); }
	public static String noItemsToSell() { return u.cc(prefix + mess.getString("noItemsToSell")); }
	public static String noItemsOnTheList() { return u.cc(prefix + mess.getString("noItemsOnTheList")); }
	public static String itemNotInConfig(String materialname) { return u.cc(prefix + mess.getString("itemNotInConfig").replaceAll("%item%", materialname)); }
	public static String reloaded() { return u.cc(prefix + mess.getString("reloaded")); }
	public static String removedWorth(Material type) { return u.cc(prefix + mess.getString("removedItem").replaceAll("%item%", type.toString())); }
	public static String noPermission() { return u.cc(prefix + mess.getString("noPermission")); }
	public static String notANumber(String notNumber) { return u.cc(prefix + mess.getString("notANumber").replaceAll("%number%", notNumber)); }
	public static String invalidItem() { return u.cc(prefix + mess.getString("notAnItem")); }
	public static String invalidPlayerSelf() { return u.cc(prefix + mess.getString("invalidPlayerSelf")); }
	public static String invalidPlayerOther(String invalidName) { return u.cc(prefix + mess.getString("invalidPlayerOther").replaceAll("%player%", invalidName)); }
	public static String multiWand() { return u.cc(prefix + mess.getString("multiWanding")); }
	public static String noSellHere() { return u.cc(prefix + mess.getString("noSellHere")); }
	public static String sellwandbroke() { return u.cc(prefix + mess.getString("sellwandbroke")); }
	public static String unknownComand() { return u.cc(prefix + mess.getString("unknownCommand")); }
	public static String fullInvOther(Player p) { return u.cc(prefix + mess.getString("fullInvOther").replaceAll("%p%", p.getName())); }
	public static String invalidUses(String uses) { return u.cc(prefix + mess.getString("invalidUses").replaceAll("%uses%", uses)); }
	
	
	
	
	public static boolean error() {
		String x = "error";
		if (messages.get().contains(x)) {
			Bukkit.getLogger().severe(u.cc(messages.get().getString(x))); 
			return true; } else { return true; } }	
	
	
	public static String moneyreceived(Player p, double Money, int items) {
		String x = "receivedmoney";
		if (mess.contains(x)) {
			return u.cc(prefix + mess.getString(x).replaceAll("%p%", p.getName()).replaceAll("%money%", u.dc(Money)).replaceAll("%items%", u.dc(items)));
		 } else return null; 			
	}
	
public static String setworth(CommandSender sender, double Price, String ItemName) { 
	return u.cc(prefix + mess.getString("setworth").replaceAll("%p%", sender.getName()).replaceAll("%price%", u.dc(Price)).replaceAll("%item%", ItemName));
}
	
	public static String givenWand(Player p, int uses, boolean infinite) {
		String x = mess.getString("givenWand");
		return u.cc(prefix + x.replaceAll("%p%", p.getName()).replaceAll("%uses%", infinite ? "Infinite" : String.valueOf(uses)));
	}
	
	public static String receivedWand(int uses, boolean infinite) {
		String x = mess.getString("receivedWand");
		return u.cc(prefix + x.replaceAll("%uses%", infinite ? "Infinite" : String.valueOf(uses)));
	}
	
	//////////////////////////////
			
	public static boolean returnmessage(String message, CommandSender sender) {
		String x = message;
		if (mess.contains(x)) {
		sender.sendMessage(u.cc(mess.getString(x).replaceAll("%p%", sender.getName())));
		return true; }	else {return true;}	}
	
////////////////////////////////////
	
	
				
	
	public static Material getWandMaterial() {
		String x = "wanditem";
		if (messages.get().contains("config." + x)) {
			String mat = messages.get().getString("config." + x);
			if (Material.getMaterial(mat) != null) {
		return Material.getMaterial(mat);				
		} else { m.error(); return Material.BLAZE_ROD; }
		} else { return Material.BLAZE_ROD;} }

	public static String getWandName() {
	String x = "wandname";
	if (messages.get().contains("config." + x)) {
		return messages.get().getString("config." + x);
	} else { return u.cc("&r" + m.getWandMaterial().toString()); } }

	
	public static List<String> getWandLore(ItemStack wand){
		String x = "wandlore";
	if (messages.get().contains("config." + x)) {
		List<String> yes = messages.get().getStringList("config." + x);
		List<String> hia = new ArrayList<>();
		PersistentDataContainer yeck = wand.getItemMeta().getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(plugin, "sellwands");	
		String uses = new String();
	if (yeck.has(key, PersistentDataType.STRING)) {
		uses = yeck.get(key, PersistentDataType.STRING); } 
	else {
	if (yeck.has(key, PersistentDataType.INTEGER)) {
		uses = u.dc(yeck.get(key, PersistentDataType.INTEGER)); } }
		for (String h : yes ) {
		hia.add(u.cc(h).replaceAll("%uses%", uses));	}
		return hia;
		} else {return null;} } 
	
	public static List<String> getWandLore(ItemStack wand, int setuses){
		String x = "wandlore";
	if (messages.get().contains("config." + x)) {
		List<String> yes = messages.get().getStringList("config." + x);
		List<String> hia = new ArrayList<>();
		for (String h : yes ) {
		hia.add(u.cc(h).replaceAll("%uses%", u.dc(setuses)));	}
		return hia;
		} else {return null;} } 
	
	public static List<String> getWandLore(ItemStack wand, String infinite){
		String x = "wandlore";
	if (messages.get().contains("config." + x)) {
		List<String> yes = messages.get().getStringList("config." + x);
		List<String> hia = new ArrayList<>();
		for (String h : yes ) {
		hia.add(u.cc(h).replaceAll("%uses%", "infinite"));	}
		return hia;
		} else {return null;} }



}
