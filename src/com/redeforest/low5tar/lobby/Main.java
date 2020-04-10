package com.redeforest.low5tar.lobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.redeforest.low5tar.lobby.commands.FlyCommand;
import com.redeforest.low5tar.lobby.commands.SpawnCommand;
import com.redeforest.low5tar.lobby.listener.BlockCommands;
import com.redeforest.low5tar.lobby.listener.EventsListener;
import com.redeforest.low5tar.lobby.score.ManagementScoreboard;
import com.redeforest.low5tar.lobby.score.ScorePlayer;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static Main getInstance() {
		return plugin;
	}
	
	public void onEnable() {
		plugin = this;
		Register_Events();
		Register_Commands();
		ManagementScoreboard.onEnable();
	}
	
	public void onDisable() {	
	}
	
	public void Register_Events() {
		
		PluginManager pm = Bukkit.getPluginManager();
	    
	    pm.registerEvents(new BlockCommands(), this);
	    pm.registerEvents(new EventsListener(), this);
	    pm.registerEvents(new ScorePlayer(), this);
		
	}
	
	public void Register_Commands() {
		getCommand("setspawn").setExecutor(new SpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		
	}
}
