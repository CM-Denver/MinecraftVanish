package com.william.plugin;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	//Stores list of players that are in vanish mode:
	List<Player> vanished = new ArrayList<>();
	
	@Override
	public void onEnable() {
		System.out.println("Vanish plugin has worked (sorta)");
	}
	
	@Override
	public void onDisable() {
		System.out.println("vanish plugin disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Checks if sender of command is a player:
		if (sender instanceof Player) {
			Player player = (Player) sender;
			//Checks if command is /vanish:
			if (cmd.getName().equalsIgnoreCase("vanish")) {
				//Checks if player is in 'vanished' arraylist (aka: if they're in vanish mode or not):
				if (vanished.contains(player)) {
					//Toggle off:
					vanished.remove(player);
					//Loops through every player on the server and makes the unvanished player visible to them:
					for (Player target : Bukkit.getOnlinePlayers()) {
						target.showPlayer(player);
					}
					
					player.sendMessage("You are unvanished");
				} 
				else {
					//toggle on:
					vanished.add(player);
					//Loops through every player on the server and hides the vanished player from them:
					for (Player target : Bukkit.getOnlinePlayers()) {
						target.hidePlayer(player);
					}
					
					player.sendMessage("You are vanished");
				}
			}
 		}
		
		return false;
	}
	
}
