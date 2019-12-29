package me.OneUp5hroom.fly;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import me.OneUp5hroom.SimpleFly.Main;
import me.OneUp5hroom.Watcher.Watcher;
import net.md_5.bungee.api.ChatColor;

public class Fly implements CommandExecutor {
	 static Main plugin;
	 public Fly(Main instance) {
		 plugin = instance;
	 }
	 
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (label.equalsIgnoreCase("fly")) {
			 if (!(sender.hasPermission("SimpleFly.use"))) {
				 sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
				 return true;
			 }
			 if (!(sender instanceof Player)) {
				 sender.sendMessage(ChatColor.DARK_RED + "You are not a Player!");
				 return true;
			 }
			 
			 // initializing Variables
			 Player player = (Player) sender;
			 PlayerInventory playerInventory = player.getInventory(); 
			 Material m = Material.getMaterial(plugin.getConfig().getString("fly-resource"));
			 Boolean SafeFallBool = plugin.getConfig().getBoolean("safe-fall.Enabled");
			 int SafeFallDuration = plugin.getConfig().getInt("safe-fall.Duration-Of-Potion-Effect-in-Seconds") * 20;
			 
			 if (player.getAllowFlight()) {
				 player.setAllowFlight(false);
				 if (SafeFallBool) {
					 player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,SafeFallDuration,1), true);
				 }
				 sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("messages.Ended"));
				 return true;
			 }

			 int count = plugin.getConfig().getInt("consumption-count-per-interval");
			 int interval = plugin.getConfig().getInt("interval-in-seconds") * 20;
			 if (playerInventory.contains(m)) {
				 player.setAllowFlight(true);
			 } else {
				 sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("messages.Denied"));
				 return true;
			 }
			 
			 sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.Approved"));
			 BukkitTask task = new Watcher(player, m, count, plugin.getConfig().getString("messages.Ended"), SafeFallBool, SafeFallDuration).runTaskTimer(Fly.plugin, interval, interval);
			 System.out.println(task);
			 return true;
		 }
		 
	 return false;
	}

}
