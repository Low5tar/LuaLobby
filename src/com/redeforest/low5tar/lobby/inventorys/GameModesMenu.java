package com.redeforest.low5tar.lobby.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import com.redeforest.low5tar.lobby.utils.ItemBuilder;
import com.redeforest.low5tar.lobby.Main;

public class GameModesMenu implements Listener {
	
	public static void open(Player player) {
		Inventory inv = Bukkit.createInventory(player, 27, "§8§nEscolha um modo de jogo");
		
		ItemStack RankupRealm = new ItemBuilder().type(Material.ANVIL).name("§6§lRANKUP REALM §c§lEM BREVE")
				.lore("§aCompetitivo", "", "§7Colete recursos, minere e upe seu rank, seja o mais rico! ", "§7Aproveite a cidade e construa seu reinado, esse é o RankUP, ", "§7onde o seu objetivo é crescer.",
						"","§cDisponível em breve!")
				.build();
		
		ItemStack RankupOverpower = new ItemBuilder().type(Material.TNT).name("§c§lRANKUP OVERPOWER §c§lDESENVOLVIMENTO")
				.lore("§aCompetitivo", "", "§7Colete recursos, minere e upe seu rank, seja o mais rico! ", "§7Aproveite a cidade e construa seu reinado, esse é o RankUP, ", "§7onde o seu objetivo é crescer.",
						"","§cDisponível em breve!")
				.build();

		inv.setItem(12, RankupRealm);
		inv.setItem(14, RankupOverpower);

		player.openInventory(inv);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> inv.setItem(12, RankupRealm), 0, 20);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> inv.setItem(14, RankupOverpower), 0, 20);
	}

	public void connect(Player p, String s) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(s);
		p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			ItemStack item = event.getCurrentItem();
			String name = event.getInventory().getName();
			if (name.equalsIgnoreCase("§8§nEscolha um modo de jogo") && item != null) {
				event.setCancelled(true);
				if (item.getType() == Material.ANVIL) {
					player.closeInventory();

				}
				else if (item.getType() == Material.TNT) {
					player.closeInventory();
				}
			}
		}
	}

}
