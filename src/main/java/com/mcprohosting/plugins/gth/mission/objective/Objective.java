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

    /**
     * Creates a generic mission objective
     * @param plugin GTH Plugin
     * @param mission Current Mission
     */
    public Objective(GrandTheftHorse plugin, Mission mission) {
        this.plugin = plugin;
        this.mission = mission;
    }

    /**
     * Gets the current mission
     * @return Current Mission
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * A description of the objective to send to the players
     * @return Objective start message
     */
    public abstract String startObjectiveMessage();

    /**
     * Generate information to show on the scoreboard
     * @param objective Scoreboard Objective
     */
    public abstract void generateScoreboardInfo(org.bukkit.scoreboard.Objective objective);

    /**
     * Sets the compass direction for the user
     * @param user User to update
     */
    public abstract void setCompass(User user);

    /**
     * Ticks ones every second to update information with the objective
     */
    public abstract void objectiveTick();

}
