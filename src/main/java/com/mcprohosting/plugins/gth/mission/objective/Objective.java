package com.mcprohosting.plugins.gth.mission.objective;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.mission.Mission;
import com.mcprohosting.plugins.gth.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Objective {

    private final GrandTheftHorse plugin;
    private final Mission mission;

    public Objective(GrandTheftHorse plugin, Mission mission) {
        this.plugin = plugin;
        this.mission = mission;
    }

    public Mission getMission() {
        return mission;
    }

    public abstract String startObjectiveMessage();

    public abstract void generateScoreboardInfo(org.bukkit.scoreboard.Objective objective);

    public abstract void setCompus(User user);

}
