package com.mcprohosting.plugins.gth.mission;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.events.MissionTimeUp;
import com.mcprohosting.plugins.gth.faction.Faction;
import com.mcprohosting.plugins.gth.mission.objective.Objective;
import com.mcprohosting.plugins.gth.mission.team.MTeam;
import com.mcprohosting.plugins.gth.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mission extends BukkitRunnable {

    private final GrandTheftHorse plugin;
    private final MTeam enforcers;
    private final MTeam criminals;
    private long missionTime;
    private Scoreboard scoreboard;
    private ArrayList<Objective> objectives = new ArrayList<>();

    public Mission(GrandTheftHorse plugin) {
        this.plugin = plugin;
        enforcers = new MTeam(Faction.Enforcers);
        criminals = new MTeam(Faction.Criminals);
    }

    public MTeam getOtherTeam(MTeam team) {
        if (team == enforcers) {
            return criminals;
        } else {
            return enforcers;
        }
    }

    private void generateScoreboard() {
        scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();

        org.bukkit.scoreboard.Objective sbObjective = scoreboard.registerNewObjective("mission", "dummy");
        long miliTime = missionTime - System.currentTimeMillis();
        String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(miliTime),
                TimeUnit.MILLISECONDS.toSeconds(miliTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(miliTime)));
        sbObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        sbObjective.setDisplayName(ChatColor.AQUA + "Mission " + time);
        for (Objective objective : objectives) {
            objective.generateScoreboardInfo(sbObjective);
        }

        for (User user : enforcers.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.setScoreboard(scoreboard);
            }
        }
        for (User user : criminals.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.setScoreboard(scoreboard);
            }
        }
    }

    private void updateScoreboard() {
        org.bukkit.scoreboard.Objective sbObjective = scoreboard.getObjective("mission");
        long miliTime = missionTime - System.currentTimeMillis();
        String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(miliTime),
                TimeUnit.MILLISECONDS.toSeconds(miliTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(miliTime)));
        sbObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        sbObjective.setDisplayName(ChatColor.AQUA + "Mission " + time);
        for (Objective objective : objectives) {
            objective.generateScoreboardInfo(sbObjective);
        }

        for (User user : enforcers.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.setScoreboard(scoreboard);
            }
        }
        for (User user : criminals.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.setScoreboard(scoreboard);
            }
        }
    }

    public void openObjectiveCompus(User user) {
        Player player = plugin.getServer().getPlayer(user.getUserName());
        if (objectives.size() == 1) {
            player.sendMessage(ChatColor.RED+"There is only one objective in this mission.");
        }
        //open inventory gui to allow player to choose which objective they want their compus to point to
    }

    public void missionOver(MTeam winningTeam) {
        final Mission toRemove = this;

        try {
            this.cancel();
        } catch (IllegalStateException ex) {
            System.out.println("Error mission not running.");
        }

        updateScoreboard();
        for (User user : enforcers.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.sendMessage(ChatColor.GOLD + "The " + winningTeam.getFacion().name() + " have won!");
            }
        }
        for (User user : criminals.getUsers()) {
            Player p = Bukkit.getPlayer(user.getUserName());
            if (p != null) {
                p.sendMessage(ChatColor.GOLD + "The " + winningTeam.getFacion().name() + " have won!");
            }
        }

        plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.getDb().removeMission(toRemove);
            }
        }, 200L);
    }

    public void startMission() {
        missionTime = System.currentTimeMillis() + 900000;//15 minutes
        generateScoreboard();

        for (User user : enforcers.getUsers()) {
            user.setObjectiveCompus(objectives.get(0));
            Player p = plugin.getServer().getPlayer(user.getUserName());
            if (p != null) {
                p.sendMessage(objectives.get(0).startObjectiveMessage());
            }
        }
        for (User user : criminals.getUsers()) {
            user.setObjectiveCompus(objectives.get(0));
            Player p = plugin.getServer().getPlayer(user.getUserName());
            if (p != null) {
                p.sendMessage(objectives.get(0).startObjectiveMessage());
            }
        }

        //check if players are close to the mission objectives. if not teleport them closer

        this.runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        if (missionTime != -1) {
            return;
        }
        updateScoreboard();
        for (User user : enforcers.getUsers()) {
            user.getObjectiveCompus().setCompus(user);
        }
        for (User user : criminals.getUsers()) {
            user.getObjectiveCompus().setCompus(user);
        }
        if (System.currentTimeMillis() >= missionTime) {
            missionTime = -1;
            MissionTimeUp event = new MissionTimeUp(this);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }
}
