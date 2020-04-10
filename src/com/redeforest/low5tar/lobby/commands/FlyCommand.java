package com.redeforest.low5tar.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("�cApenas jogadores podem fazer isto!");
      return true;
    } 
    
    Player p = (Player)sender;
    if (!p.hasPermission("com.redelua.lobby.fly")) {
      p.sendMessage("�c�LERRO: �fVoc� n�o possui permiss�o �b�lVIP �fpara executar este comando.");
      return true;
    } 
    
    if (p.getAllowFlight()) {
      p.setAllowFlight(false);
      p.sendMessage("�b�lFLY: �fVoc� �c�lDESABILITOU�f seu fly.");
      return true;
    } 
    
    p.setAllowFlight(true);
    p.sendMessage("�b�lFLY: �fVoc� �3�lHABILITOU�f seu fly.");
    return true;
  }

}
