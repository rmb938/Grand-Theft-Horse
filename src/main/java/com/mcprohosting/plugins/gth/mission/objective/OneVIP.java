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

    /**
     * Creates a One VIP mission objective
     * @param plugin GTH Plugin
     * @param mission Current Mission
     * @param vip VIP User
     */
    public OneVIP(GrandTheftHorse plugin, Mission mission, User vip) {
        super(plugin, mission);
        this.vip = vip;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * A description of the objective to send to the players
     * @return Objective start message
     */
    @Override
    public String startObjectiveMessage() {
        return "Some message describing the one vip objective.";
    }

    /**
     * Generate information to show on the scoreboard
     * @param objective Scoreboard Objective
     */
    @Override
    public void generateScoreboardInfo(org.bukkit.scoreboard.Objective objective) {
        Score score = objective.getScore(Bukkit.getOfflinePlayer("VIP Target: "));
        score.setScore(1);
        score = objective.getScore(Bukkit.getOfflinePlayer(vip.getUserName()));
        score.setScore(vipLives);
    }

    /**
     * Sets the compass direction for the user
     * @param user User to update
     */
    @Override
    public void setCompass(User user) {
        Player player = Bukkit.getPlayer(user.getUserName());
        Player vipPlayer = Bukkit.getPlayer(vip.getUserName());
        player.setCompassTarget(vipPlayer.getLocation());
    }

    /**
     * Ticks ones every second to update information with the objective
     */
    @Override
    public void objectiveTick() {
        //not needed for this mission
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMissionTimeUp(MissionTimeUp event) {
        if (event.getMission() == getMission()) {
            getMission().missionOver(vip.getMissionTeam());
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
