package penguin.sellwands.commands;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import penguin.sellwands.Main;
import penguin.sellwands.files.messages;
import penguin.sellwands.files.sellableitems;
import penguin.sellwands.objects.Config;
import penguin.sellwands.objects.SellWand;
import penguin.sellwands.utils.m;
import penguin.sellwands.utils.u;

public class sellwand implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	
	public sellwand(Main plugin) {
		this.plugin = plugin; 
		plugin.getCommand("sellwand").setExecutor(this); 
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender.hasPermission("sellwands.reload"))) { sender.sendMessage(m.noPermission()); return true; }	

		if (args.length == 1) {
			String arg = args[0];
			if (arg.equalsIgnoreCase("removeworth")) {		
				if (u.isPlayer(sender)) {
					Player p = (Player) sender;
					ItemStack item = p.getInventory().getItemInMainHand();
					if (u.isItem(item)) {
						if (Config.getSellableNames().contains(item.getType().toString())) {
							Config.removeWorth(item.getType());
							sender.sendMessage(m.removedWorth(item.getType()));
						} else sender.sendMessage(m.itemNotInConfig(item.getType().toString()));
					} else sender.sendMessage(m.invalidItem());
				} else sender.sendMessage(m.invalidPlayerSelf());
			} else if (arg.equalsIgnoreCase("reload")) {
				messages.reload();
				sellableitems.reload();
				sender.sendMessage(m.reloaded());
			} else if (arg.equalsIgnoreCase("list")) {
				HashMap<Material, Double> map = Config.getSellables();
				if (map.keySet().size() > 0) {
					for (Material m : map.keySet()) sender.sendMessage(u.cc("&3" + m.toString() + ": &6" + map.get(m)));
				} else sender.sendMessage(m.noItemsOnTheList());
			} else sender.sendMessage(m.unknownComand());
			
		} else if (args.length == 3) {
			String o = args[1];
			String t = args[2];
			if (args[0].equalsIgnoreCase("give")) {
				if (u.isPlayer(o)) {
					if (u.isInt(t) || t.equalsIgnoreCase("infinite")) {
						SellWand wand = new SellWand(u.isInt(t) ? u.getInt(t) : -1);
						Player target = u.getPlayer(o);
						if (u.hasInventorySpace(target)) {
							ItemStack wanditem = wand.getItem();
							target.getInventory().addItem(wanditem);
						//	target.sendMessage(m.receivedWand(wand.getUses(), wand.isInfinite()));
							if (sender instanceof Player && target != (Player) sender) sender.sendMessage(m.givenWand(target, wand.getUses(), wand.isInfinite()));
							return true;
						} else sender.sendMessage(m.fullInvOther(target));
					} else sender.sendMessage(m.invalidUses(t));
				} else sender.sendMessage(m.invalidPlayerOther(o));
			} else sender.sendMessage(m.unknownComand());
			return true;
			
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("setworth")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					ItemStack it = p.getInventory().getItemInMainHand();
					if (it == null || it.getType() == Material.AIR) { sender.sendMessage(m.invalidItem()); return true; } 
					try { 
						double yeet = Double.parseDouble(args[1]); 
						Material type = p.getInventory().getItemInMainHand().getType();
						Config.setWorth(type, yeet);
						sender.sendMessage(m.setworth(sender, yeet, type.toString()));
						return true;
					} 
					catch (NumberFormatException e)  {
						sender.sendMessage(m.notANumber(args[1]));
					}					
				} else sender.sendMessage(m.invalidPlayerSelf());
			} else if (args[0].equalsIgnoreCase("removeworth")) {
				if (u.isMaterial(args[1])) {
					if (Config.getSellables().keySet().contains(u.getMaterial(args[1]))) {
						Config.removeWorth(u.getMaterial(args[1]));
						sender.sendMessage(m.removedWorth(u.getMaterial(args[1])));
					} else sender.sendMessage(m.itemNotInConfig(args[1]));
				} else sender.sendMessage(m.invalidItem());
			} else sender.sendMessage(m.unknownComand());
		} else sender.sendMessage(m.unknownComand());
		return true;
	}





} 





