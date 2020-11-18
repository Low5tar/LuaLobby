package com.redeforest.low5tar.lobby.listener;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import com.redeforest.low5tar.lobby.Main;
import com.redeforest.low5tar.lobby.inventorys.GameModesMenu;
import com.redeforest.low5tar.lobby.utils.ItemBuilder;
import com.redeforest.low5tar.lobby.utils.TitleAndAction;

public class EventsListener implements Listener
{
    @SuppressWarnings("unused")
	@EventHandler
    public static void aoEntrar(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        e.setJoinMessage(null);
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
        sendHeaderAndFooter(p, "\n§e§lREDE LUA\n   §7(§fLOBBY 1§7)\n", "\n   §8LOJA.REDELUA.COM\n§f   Um mundo totalmente mágico!   \n   "); 
        TitleAndAction.EnviarTitle(p, "§e§lLUA", "§fMais uma Lua no nosso servidor!", 55,55,55);
        TitleAndAction.EnviarActionbar(p, "§aBem Vindo à Rede Lua");
        p.getInventory().setHeldItemSlot(0);
    	
        for(int i = 1; i < 100; i++) {
    		p.sendMessage(" ");
    	}
    	p.sendMessage("§e§lREDE LUA");
    	p.sendMessage(" ");
    	p.sendMessage("§fSeja bem vindo ao §e§lSERVIDOR");
    	p.sendMessage("§fEntre no nosso §9§lDISCORD §fpara saber as novidades §e(§3discord.redelua.com§e)");
    	
    	ItemStack compass = new ItemBuilder().type(Material.COMPASS).name("§9§lModos de Jogo §7(Clique)").build();
    	p.getInventory().setItem(0, compass);
    	p.getInventory().setHeldItemSlot(0);
    	
    	p.updateInventory();
			
    	//Teleportando para o spawnn
    	World world = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("spawn.world"));
			double x = Main.getInstance().getConfig().getDouble("spawn.x");
			double y = Main.getInstance().getConfig().getDouble("spawn.y");
			double z = Main.getInstance().getConfig().getDouble("spawn.z");
			float yaw = Float.valueOf(Main.getInstance().getConfig().getString("spawn.yaw"));
			float pitch = Float.valueOf(Main.getInstance().getConfig().getString("spawn.pitch"));
			Location loc = new Location(world, x, y, z, yaw, pitch);
			p.teleport(loc);
			
        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
    }
    
	@EventHandler
	public void on(PlayerTeleportEvent event) {
		if (event.getPlayer().getWorld().getWorldBorder() != null && event.getCause() == TeleportCause.ENDER_PEARL) {
			double worldborder = event.getPlayer().getWorld().getWorldBorder().getSize() / 2.0D;
			if (event.getPlayer().getWorld().getWorldBorder().getCenter().getX() + worldborder < event.getTo().getX()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Você não pode usar enderpearl na borda do servidor!");
				return;
			} else if (event.getPlayer().getWorld().getWorldBorder().getCenter().getX() - worldborder > event.getTo().getX()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Você não pode usar enderpearl na borda do servidor!");
				return;
			} else if (event.getPlayer().getWorld().getWorldBorder().getCenter().getZ() + worldborder < event.getTo().getZ()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Você não pode usar enderpearl na borda do servidor!");
				return;
			} else if (event.getPlayer().getWorld().getWorldBorder().getCenter().getZ() - worldborder > event.getTo().getZ()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Você não pode usar enderpearl na borda do servidor!");
				return;
			}
		}
	}
	
	@EventHandler
	public void onAnsycPlayerChatFormatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("com.redelua.lobby.chatcolor")) {
			e.setFormat(p.getDisplayName() + "§b » §f" + e.getMessage().replace("%", "%%").replace("&", "§"));
		} else {
			e.setFormat(p.getDisplayName() + "§b » §7" + e.getMessage().replace("%", "%%"));
		}
	}
	
    @EventHandler
    private void aoSair(final PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
    
	@EventHandler
	public void onGameModes(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getType() == Material.COMPASS) {
			event.setCancelled(true);

			if (event.getAction().toString().contains("RIGHT")) {
				GameModesMenu.open(player);
			}
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.CUSTOM && e.getSpawnReason() != SpawnReason.SPAWNER_EGG) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPvP(PlayerDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
    public static void sendHeaderAndFooter(Player p, String head, String foot) {
        CraftPlayer craftplayer = (CraftPlayer)p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + head + "'}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + foot + "'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }

}
