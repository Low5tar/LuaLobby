package com.redeforest.low5tar.lobby.score;

import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.plugin.*;

import com.redeforest.low5tar.lobby.Main;

@SuppressWarnings("all")
public class ManagementScoreboard
{
    private static ConcurrentHashMap<UUID, ScoreCreating> players;
    
    static {
        ManagementScoreboard.players = new ConcurrentHashMap<UUID, ScoreCreating>();
    }
    
    public static ConcurrentHashMap<UUID, ScoreCreating> getPlayers() {
        return ManagementScoreboard.players;
    }
    
	public static void onEnable() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            ManagementScoreboard.players.put(player.getUniqueId(), new ScoreCreating(player.getName()));
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)Main.getPlugin(), (Runnable)new Runnable() {
            @Override
            public synchronized void run() {
                for (final ScoreCreating player : ManagementScoreboard.players.values()) {
                    player.updateScoreboard();
                }
            }
        }, 10L, 10L);
    }

}
