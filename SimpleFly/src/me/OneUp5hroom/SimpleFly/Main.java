package me.OneUp5hroom.SimpleFly;

import org.bukkit.plugin.java.JavaPlugin;
import me.OneUp5hroom.fly.Fly;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		registerCmds();
		loadDefaultConfigFile();
	}
	
	@Override
	public void onDisable() {
	}
	
	public void registerCmds() {
		this.getCommand("fly").setExecutor(new Fly(this));
	}
    private void loadDefaultConfigFile() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.reloadConfig();
    }
}