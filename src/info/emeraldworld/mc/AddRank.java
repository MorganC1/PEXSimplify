package info.emeraldworld.mc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddRank implements CommandExecutor {
	
	ConfigManager settings = ConfigManager.getInstance();
	
	public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("addrank")){
			if (!(sender instanceof Player)){
				if (settings.getConfig().getString("useinconsole") == "false"){
					sender.sendMessage(ChatColor.DARK_RED + settings.getConfig().getString("deniedinconsole"));
					return true;
				}
				if (settings.getConfig().getString("useinconsole") == "true"){
					if (args.length <= 1){
						return false;
					}
					if (args.length == 2){
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
						return true;
					}
					if (args.length >= 3){
						return false;
					}
				}
				else {
					sender.sendMessage(ChatColor.DARK_RED + "You have used an invalid argument for the string " + ChatColor.AQUA + "useinconsole. " + ChatColor.DARK_RED + "Set this option to true or false to continue.");
				}
			}
			Player player = (Player) sender;
			if (settings.getConfig().getString("useingame") == "false"){
				sender.sendMessage(ChatColor.DARK_RED + settings.getConfig().getString("deniedingame"));
				return true;
			}
			if (settings.getConfig().getString("useingame") == "true"){
				if(!(player.hasPermission("pex.addrank"))){
					player.sendMessage(ChatColor.DARK_RED + settings.getConfig().getString("permissiondenied"));
				}
				if(player.hasPermission("pex.addrank")){
					if (args.length <= 1){
						return false;
					}
					if (args.length >= 3){
						return false;
					}
					if (args.length == 2){
						Player target = Bukkit.getPlayerExact(args[0]);
						if (target == null) {
							player.sendMessage(ChatColor.DARK_RED + "Target player does not exist!");
							return true;
						}
						if (target != null) {
						    if (target.hasPermission("pex.immune")){
						    	player.sendMessage(ChatColor.DARK_RED + "Target player is immune!");
						    	return true;
						    }
						    else {
						    	Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group add " + args[1]);
								player.sendMessage(ChatColor.AQUA + settings.getConfig().getString("rankadded"));
								return true;
						    }
						}
					}
				}
			}
			else{
				player.sendMessage(ChatColor.DARK_RED + "You have used an invalid argument for the string " + ChatColor.AQUA + "useingame. " + ChatColor.DARK_RED + "Set this option to true or false to continue.");
			}
		}
		return false;
	}
}