package com.redeforest.low5tar.lobby.score;

import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.event.player.*;

public class ScorePlayer implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(final PlayerJoinEvent e) {
    	ManagementScoreboard.getPlayers().put(e.getPlayer().getUniqueId(), new ScoreCreating(e.getPlayer().getName()));
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChangeWorld(final PlayerChangedWorldEvent e) {
        e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onKick(final PlayerKickEvent e) {
    	ManagementScoreboard.getPlayers().remove(e.getPlayer().getUniqueId());
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(final PlayerQuitEvent e) {
    	ManagementScoreboard.getPlayers().remove(e.getPlayer().getUniqueId());
    }

}
