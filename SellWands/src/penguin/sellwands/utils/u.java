package penguin.sellwands.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.economy.Economy;
import penguin.sellwands.Main;


public class u {

	
	
	   public String translateHexColorCodes(String startTag, String endTag, String message) {
		    final char COLOR_CHAR = '\u00A7';
	        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
	        Matcher matcher = hexPattern.matcher(message);
	        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
	        while (matcher.find()) {
	            String group = matcher.group(1);
	            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
	                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
	                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
	                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
	                    );
	        }
	        return matcher.appendTail(buffer).toString();
	    }
	   
	public static String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);	}

	public static String dc(int value) {
		String pattern = "###,###,###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value); }

	public static String dc(double value) {
		String pattern = "###,###,###.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value); }

	public static void addmoney(Player p, double amount) {
		Economy eco = Main.getPlugin(Main.class).eco;	
		eco.depositPlayer(p, amount);
	}

	public static List<String> TabCompleter(List<String> commands, String Input){
		List<String> wordsThatStartWithArg = new ArrayList<>();
		for (String x : commands) {
			if (x.toLowerCase().startsWith(Input.toLowerCase())) {
				wordsThatStartWithArg.add(x);
			}
		}
		return wordsThatStartWithArg;	
	}
	
	public static String capitaliseFirstLetters(String s) {
		String[] words = s.split(" ");
		String returnable = "";
		for (String x : words) {
			String firstletter = x.substring(0, 1);
			String notfirstletter = x.substring(1, x.length()).toLowerCase();
			returnable = (returnable.length() == 0 ? returnable: returnable+ " ") + firstletter.toUpperCase() + notfirstletter;
		}
		return returnable;
	}

	public static void send(Player p, String s) { p.sendMessage(u.cc(s)); }
	public static boolean isMaterial(String s) { return Material.matchMaterial(s) != null; }	
	public static Material getMaterial(String s) { return Material.matchMaterial(s); }	
	public static boolean hasInventorySpace(Player p) { return (p.getInventory().firstEmpty() != -1); }	
	public static boolean isPlayer(CommandSender s) { return (s instanceof Player); }	
	public static boolean isPlayer(String name) { return Bukkit.getPlayer(name) != null; }	
	public static Player getPlayer(String s) { return Bukkit.getPlayer(s); }	
	public static String getNicerName(Material m) { return capitaliseFirstLetters(m.toString().toLowerCase().replaceAll("_", " ")); }	
	public static String getNicerName(ItemStack item) { return getNicerName(item.getType()); }	
	public static boolean isItem(ItemStack item) { return (item != null && item.getType() != Material.AIR); }
	public static int getInt(String s) { return Integer.parseInt(s); }
	public static String getNicerMaterialNameSingular(String materialName) { return removeFinalS(getNicerMaterialName(materialName)); }

	public static List<String> getAllPlayersNames(){
		List<String> names = new ArrayList<>();
		for (Player p : Bukkit.getOnlinePlayers()) names.add(p.getName());
		return names;
	}

	public static String getNicerMaterialName(String materialName) {
		if (isMaterial(materialName)) {
			return getNicerName(Material.matchMaterial(materialName));
		} else return null;
	}


	public static String removeFinalS(String s) {
		if (s.toLowerCase().endsWith("s")) {
			s = s.substring(0, s.length()-1);
		}
		return s;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}





}



