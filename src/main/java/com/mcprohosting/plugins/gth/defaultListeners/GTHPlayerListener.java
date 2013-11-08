package com.mcprohosting.plugins.gth.defaultlisteners;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class GTHPlayerListener implements Listener {

    private final GrandTheftHorse plugin;

    public GTHPlayerListener(GrandTheftHorse plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = plugin.getDb().getUser(player.getName());
        if (user == null) {
            plugin.getDb().createUser(player.getName());
            //make user pick faction(lock user inside spawn area until picked)
            //ect..
        } else {

        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getDb().saveUser(event.getPlayer().getName(), true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        User user = plugin.getDb().getUser(player.getName());
        if (user.getMissionTeam() != null) {
            event.setRespawnLocation(user.getMissionTeam().getSafestSpawn());
        } else {
            event.setRespawnLocation(user.getChosenSpawn());
        }
    }
}
