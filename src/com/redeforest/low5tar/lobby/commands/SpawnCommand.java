package com.redeforest.low5tar.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.redeforest.low5tar.lobby.Main;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("spawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas jogadores in-game!");
				return true;
			}
			Player p = (Player) sender;
			World world = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("spawn.world"));
			double x = Main.getInstance().getConfig().getDouble("spawn.x");
			double y = Main.getInstance().getConfig().getDouble("spawn.y");
			double z = Main.getInstance().getConfig().getDouble("spawn.z");
			float yaw = Float.valueOf(Main.getInstance().getConfig().getString("spawn.yaw"));
			float pitch = Float.valueOf(Main.getInstance().getConfig().getString("spawn.pitch"));
			Location loc = new Location(world, x, y, z, yaw, pitch);
			p.sendMessage("§4§lSPAWN: §fVocê foi teleportado para o §3§lSPAWN");
			p.teleport(loc);
			return true;
		} else if (command.getName().equalsIgnoreCase("setspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas jogadores in-game!");
				return true;
			}
			Player p = (Player) sender;
			if (p.hasPermission("com.redeforest.lobby.setspawn")) {
				Location loc = p.getLocation();
				ConfigurationSection cs = Main.getInstance().getConfig().createSection("spawn");
				cs.set("world", loc.getWorld().getName());
				cs.set("x", loc.getX());
				cs.set("y", loc.getY());
				cs.set("z", loc.getZ());
				cs.set("yaw", loc.getYaw());
				cs.set("pitch", loc.getPitch());
				Main.getInstance().saveConfig();
				p.sendMessage("§4§lSPAWN: §fVocê definiu o spawn de servidor!");
			}
		}
		return false;
	}
}
