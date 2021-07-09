package penguin.sellwands.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.wargamer2010.signshop.SignShop;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public class seperms {
	
	
	private static boolean hasPlugin(String name) {
		return Bukkit.getPluginManager().getPlugin(name) != null;
	}
	
	private static boolean hasGriefPrevention() {
		return Bukkit.getPluginManager().getPlugin("GriefPrevention") != null;		
	}
	
	private static boolean hasWorldGuard() {
		return Bukkit.getPluginManager().getPlugin("WorldGuard") != null;
	}
	
	private static boolean hasSignShop() {
		return hasPlugin("SignShop");
	}
	
	public static CoreProtectAPI getCoreProtect() {
		
	        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CoreProtect");
	     
	        // Check that CoreProtect is loaded
	        if (plugin == null || !(plugin instanceof CoreProtect)) {
	            return null;
	        }

	        // Check that the API is enabled
	        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
	        if (CoreProtect.isEnabled() == false) {
	            return null;
	        }

	        // Check that a compatible version of the API is loaded
	        if (CoreProtect.APIVersion() < 6) {
	            return null;
	        }

	        return CoreProtect;	
	}
	

	public static boolean canBuild(Player p, Location loc) {
		boolean canBuildGP = true;
		boolean canBuildWG = true;
		boolean canBuildSS = true;
		
		//SignShop
		if (hasSignShop()) {
			SignShop ss = SignShop.getInstance();
			// TODO do something. and add it to base plugin.
		}
		
		// GriefPrevention
		if (hasGriefPrevention()) {
			GriefPrevention gp = GriefPrevention.instance;
			if (gp.allowBuild(p, loc) != null) {
				canBuildGP = false;
			}
		}
		
		// WorldGuard
		if (hasWorldGuard()) {
		//	WorldGuard wg = WorldGuard.getInstance();
			LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
			com.sk89q.worldedit.util.Location loc2 = BukkitAdapter.adapt(loc);
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionQuery query = container.createQuery();
			if (!query.testState(loc2, localPlayer, Flags.BUILD)) {
			    canBuildWG = false;
			}
		}



		return (canBuildGP && canBuildWG && canBuildSS);


	}

}
