package info.emeraldworld.mc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class ConfigManager {

	private ConfigManager() { }
	
	static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	Plugin p;
	FileConfiguration config;
	File cfile;
	
	public void setup(Plugin p) {
		config = p.getConfig();
		config.options().copyDefaults(true);
		cfile = new File(p.getDataFolder(), "config.yml");
		saveConfig();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void saveConfig() {
		try {
			config.save(cfile);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
		}
	}
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cfile);
	}
	
	public PluginDescriptionFile getDesc() {
		return p.getDescription();
	}
}
