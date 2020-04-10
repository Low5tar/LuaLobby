package com.redeforest.low5tar.lobby.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreCreating
{
    private String player;
    private SimpleScoreboard sb;
    
    public ScoreCreating(final String player) {
        this.player = player;
        (this.sb = new SimpleScoreboard()).setSlot(DisplaySlot.SIDEBAR);
        this.sb.setName("  §b§lLOBBY  ");
        this.sb.addLine(10, "§c ");
        this.sb.addLine(9, "§fCargo: §e§lLua");
        this.sb.addLine(8, "§fJogadores: ");
        this.sb.addLine(7, "§c ");
        this.sb.addLine(6, "§fServidor: §bLOBBY-1");
        this.sb.addLine(4, "§fVersão: §a1.8 a 1.14" + "  ");
        this.sb.addLine(2, "§c ");
        this.sb.addLine(1, "§7/score ");
        this.sb.addLine(0, "§bredelua.com       ");
    }
    
    @SuppressWarnings("unused")
	public synchronized void updateScoreboard() {
        if (!this.sb.hasBoard(this.getPlayer())) {
            this.sb.setForPlayer(this.getPlayer());
            
        }
        final Player p = Bukkit.getPlayer(player);
        this.sb.updateLine(7, "§fJogadores: §a" + Bukkit.getOnlinePlayers().size());
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.player);
    }

}
