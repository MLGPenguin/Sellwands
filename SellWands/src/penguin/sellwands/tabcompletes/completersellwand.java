package penguin.sellwands.tabcompletes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import penguin.sellwands.Main;
import penguin.sellwands.objects.Config;
import penguin.sellwands.utils.u;

public class completersellwand implements TabCompleter{

	public completersellwand(Main plugin) { plugin.getCommand("sellwand").setTabCompleter(this); }
	
	@Override
	public List<String> onTabComplete( CommandSender sender,  Command cmd, String label,  String[] args) {
	
		List<String> x1 = new ArrayList<>();

	
		if (args.length == 1) { 
			if (sender.hasPermission("sellwands.give")) x1.add("give");
			if (sender.hasPermission("sellwands.reload")) x1.addAll(Arrays.asList("reload", "setworth", "removeworth", "list", "gui"));	
			return penguin.sellwands.utils.u.TabCompleter(x1, args[0]);		
		} else if (args[0].equalsIgnoreCase("give")) {
			if (args.length == 2) return u.TabCompleter(u.getAllPlayersNames(), args[1]);
			else if (args.length == 3 && u.isPlayer(args[1])) return u.TabCompleter(Arrays.asList("infinite", "1", "5"), args[2]); 
		} else if (args[0].equalsIgnoreCase("removeworth") && args.length == 2) return u.TabCompleter(Config.getSellableNames(), args[1]);		
			return null;
	}



}
