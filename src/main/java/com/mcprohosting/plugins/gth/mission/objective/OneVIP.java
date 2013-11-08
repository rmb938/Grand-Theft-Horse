package com.mcprohosting.plugins.gth.mission.objective;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.events.MissionTimeUp;
import com.mcprohosting.plugins.gth.mission.Mission;
import com.mcprohosting.plugins.gth.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Score;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class OneVIP extends Objective implements Listener {

    private int vipLives = 25;
    private final User vip;

    public OneVIP(GrandTheftHorse plugin, Mission mission, User vip) {
        super(plugin, mission);
        this.vip = vip;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public String startObjectiveMessage() {
        return "Some message describing the one vip objective.";
    }

    @Override
    public void generateScoreboardInfo(org.bukkit.scoreboard.Objective objective) {
        Score score = objective.getScore(Bukkit.getOfflinePlayer("VIP Target: "));
        score.setScore(1);
        score = objective.getScore(Bukkit.getOfflinePlayer(vip.getUserName()));
        score.setScore(vipLives);
    }

    @Override
    public void setCompus(User user) {
        Player player = Bukkit.getPlayer(user.getUserName());
        Player vipPlayer = Bukkit.getPlayer(vip.getUserName());
        player.setCompassTarget(vipPlayer.getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMissionTimeUp(MissionTimeUp event) {
        if (event.getMission() == getMission()) {
            getMission().missionOver(getMission().getOtherTeam(vip.getMissionTeam()));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getName().equalsIgnoreCase(vip.getUserName())) {
            vipLives -= 1;
            if (vipLives == 0) {
                getMission().missionOver(getMission().getOtherTeam(vip.getMissionTeam()));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equalsIgnoreCase(vip.getUserName())) {
            getMission().missionOver(getMission().getOtherTeam(vip.getMissionTeam()));
        }
    }

}
