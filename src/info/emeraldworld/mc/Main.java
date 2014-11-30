package info.emeraldworld.mc;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	protected final Logger logger = Logger.getLogger("Minecraft");
	ConfigManager settings = ConfigManager.getInstance();
	
	public final void onEnable(){
		if (!hookPermissions() ) {
			getLogger().severe(String.format("Disabled due to no PermissionsEx dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
	        }
		settings.setup(this);
		loadCommands();
		this.logger.info("[PEXRankChange] Plugin has been successfully enabled!");
	}
	
	private final void loadCommands(){
		getCommand("addrank").setExecutor(new AddRank());
		getCommand("removerank").setExecutor(new RemoveRank());
		getCommand("changerank").setExecutor(new ChangeRank());
		getCommand("prcreload").setExecutor(new Reload());
        this.logger.info("[PEXRankChange] Commands have been successfuly loaded!");
	}
	
	private final boolean hookPermissions(){
		if (getServer().getPluginManager().getPlugin("PermissionsEx") == null) {
		return false;
		}
		this.logger.info("[PEXRankChange] Plugin has been successfully hooked into PEX!");
		return true;
	}
	
	public final void onDisable(){
		this.logger.info("[PEXRankChange] Plugin has been successfully disabled!");
		saveConfig();
		this.getServer().getScheduler().cancelTasks(this);
	}
	

}
