package info.emeraldworld.mc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
	
	ConfigManager settings = ConfigManager.getInstance();

	public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if (cmd.getName().equalsIgnoreCase("prcreload")){
			if (!(sender instanceof Player)){
				if (args.length > 0){
					sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
					return true;
				}
				else {
					settings.reloadConfig();
					sender.sendMessage(ChatColor.AQUA + "Config Reloaded!");
					return true;
				}
			}
			Player player = (Player) sender;
			if (!(player.hasPermission("pex.configreload"))){
				player.sendMessage(ChatColor.DARK_RED + settings.getConfig().getString("permissiondenied"));
				return true;
			}
			if (player.hasPermission("pex.configreload")){
				if (args.length > 0){
					sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
					return true;
				}
				else {
					settings.reloadConfig();
					sender.sendMessage(ChatColor.AQUA + "Config Reloaded!");
					return true;
				}
			}
		}
		return false;
	}

}
