package me.OneUp5hroom.Watcher;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Watcher extends BukkitRunnable{
	private Player player;
	private Material item;
	private int consumptionCountPerFiveSeconds;
	private String endMessage;
	public Watcher(Player player, Material item, int count, String endMessage) {
		this.player = player;
		this.item = item;
		this.consumptionCountPerFiveSeconds = count;
		this.endMessage = endMessage;
	}
	@Override
	public void run() {
		if ((player.getAllowFlight() == false)) {
			this.cancel();
			return;
		}
		PlayerInventory playerInventory = player.getInventory();
		if (playerInventory.contains(item)) {
			playerInventory.removeItem(new ItemStack[] {new ItemStack(item, consumptionCountPerFiveSeconds)});
			return;
		} else {
			player.sendMessage(ChatColor.DARK_RED + "No " + item.name() +" left to burn: " + endMessage);
			player.setAllowFlight(false);
			this.cancel();
		}
	}

}
