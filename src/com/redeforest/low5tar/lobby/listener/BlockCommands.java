package com.redeforest.low5tar.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCommands implements Listener {
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage().toLowerCase();
		Player p = e.getPlayer();
		if (message.startsWith("enderchest")) {
			e.setCancelled(true);
			return;
		}
		if (message.startsWith("/help")
				|| e.getMessage().toLowerCase().startsWith("/bukkit:")
				|| e.getMessage().toLowerCase().startsWith("/ver")
				|| e.getMessage().toLowerCase().startsWith("/about")
				|| e.getMessage().toLowerCase().startsWith("/minecraft")) {
				e.setCancelled(true);
				p.sendMessage("§2§lINFO: §a90% dos plugins feitos pelo §bLow5tar");
				return;
		}
	}
	
	@EventHandler
	public void onPlugins(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage().toLowerCase();
		Player p = e.getPlayer();
		if (message.startsWith("/plugins")
				|| e.getMessage().toLowerCase().startsWith("/pl")
				|| e.getMessage().toLowerCase().startsWith("/plugin")
				|| e.getMessage().toLowerCase().startsWith("/pls")) {
				e.setCancelled(true);
				p.sendMessage("§fPlugins (3): §aRealmCommons, RealmGroups, RealmLobby");
				return;
		}
	}

}
