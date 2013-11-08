package com.mcprohosting.plugins.gth.mission;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.mission.objective.Objective;
import com.mcprohosting.plugins.gth.mission.team.MTeam;
import org.bukkit.ChatColor;
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
public class Mission {

    private final GrandTheftHorse plugin;
    private int missionTime;
    private final MTeam enforcers;
    private final MTeam criminals;
    private ArrayList<Objective> objectives = new ArrayList<>();

    public Mission(GrandTheftHorse plugin) {
        this.plugin = plugin;
        enforcers = new MTeam();
        criminals = new MTeam();
    }

    public ArrayList<Objective> getObjectives() {
        return objectives;
    }

    public MTeam getCriminals() {
        return criminals;
    }

    public MTeam getEnforcers() {
        return enforcers;
    }

    public GrandTheftHorse getPlugin() {
        return plugin;
    }

    public int getMissionTime() {
        return missionTime;
    }

    public void setMissionTime(int missionTime) {
        this.missionTime = missionTime;
    }

    public MTeam getOtherTeam(MTeam team) {
        if (team == enforcers) {
            return criminals;
        } else {
            return enforcers;
        }
    }

    public void generateScoreboard() {
        Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();

        org.bukkit.scoreboard.Objective sbObjective = scoreboard.registerNewObjective("mission", "dummy");
        String time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(missionTime),
                TimeUnit.MILLISECONDS.toSeconds(missionTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(missionTime)));
        sbObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        sbObjective.setDisplayName(ChatColor.AQUA + "Mission " + time);
        for (Objective objective : objectives) {
            objective.generateScoreboardInfo(sbObjective);
        }
        //loop through all players in mission and create scoreboard based off objectives
    }


}
