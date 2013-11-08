package com.mcprohosting.plugins.gth.mission.team;

import com.mcprohosting.plugins.gth.faction.Faction;
import com.mcprohosting.plugins.gth.mission.Mission;
import com.mcprohosting.plugins.gth.user.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MTeam {

    private ArrayList<User> users = new ArrayList<>();
    private final Faction faction;
    private final Mission mission;

    /**
     * Create a team for a mission
     * @param faction Faction the Team is part of
     * @param mission Current Mission the team is part of
     */
    public MTeam(Faction faction, Mission mission) {
        this.faction = faction;
        this.mission = mission;
    }

    /**
     * Gets the current mission the team is part of
     * @return Current Mission
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * Get the faction of the team
     * @return Faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Get the team members
     * @return Team Members
     */
    public ArrayList<User> getUsers() {
        return users;
    }

}
