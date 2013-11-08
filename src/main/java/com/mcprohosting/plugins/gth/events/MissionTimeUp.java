package com.mcprohosting.plugins.gth.events;

import com.mcprohosting.plugins.gth.mission.Mission;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/8/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class MissionTimeUp extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Mission mission;

    public MissionTimeUp(Mission mission) {
        this.mission = mission;
    }

    public Mission getMission() {
        return mission;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
