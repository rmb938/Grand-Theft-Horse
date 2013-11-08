package com.mcprohosting.plugins.gth.mission.objective;

import com.mcprohosting.plugins.gth.GrandTheftHorse;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Objective {

    private final GrandTheftHorse plugin;

    public Objective(GrandTheftHorse plugin) {
        this.plugin = plugin;
    }

    public abstract void generateScoreboardInfo(org.bukkit.scoreboard.Objective objective);

}
