package com.mcprohosting.plugins.gth.mission;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.mission.team.MTeam;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Mission {

    private final GrandTheftHorse plugin;
    private int missionTime;
    private final MTeam enforcers;
    private final MTeam criminals;

    public Mission(GrandTheftHorse plugin) {
        this.plugin = plugin;
        enforcers = new MTeam();
        criminals = new MTeam();
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
}
