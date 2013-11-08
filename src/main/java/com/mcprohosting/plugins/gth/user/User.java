package com.mcprohosting.plugins.gth.user;

import com.mcprohosting.plugins.gth.faction.Faction;
import com.mcprohosting.plugins.gth.mission.objective.Objective;
import com.mcprohosting.plugins.gth.mission.team.MTeam;
import org.bukkit.Location;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String userName;
    private Faction faction;
    private MTeam missionTeam;
    private Location chosenSpawn;
    private Objective objectiveCompass;

    /**
     * The current active objective to have the compass point to
     * @return Active Objective
     */
    public Objective getObjectiveCompass() {
        return objectiveCompass;
    }

    /**
     * Sets the current active objective
     * @param objectiveCompass Objective to be Active
     */
    public void setObjectiveCompass(Objective objectiveCompass) {
        this.objectiveCompass = objectiveCompass;
    }

    /**
     * Gets the current spawn location
     * @return Spawn Location
     */
    public Location getChosenSpawn() {
        return chosenSpawn;
    }

    /**
     * Sets the current spawn location for the player
     * @param chosenSpawn New Spawn Location
     */
    public void setChosenSpawn(Location chosenSpawn) {
        this.chosenSpawn = chosenSpawn;
    }

    /**
     * Gets which faction the user is in
     * @return User Faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Sets the user faction
     * @param faction New Faction
     */
    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    /**
     * Gets the current mission team the user is in
     * @return Current Mission Team
     */
    public MTeam getMissionTeam() {
        return missionTeam;
    }

    /**
     * Sets a new mission team to the server
     * @param missionTeam New Mission Team
     */
    public void setMissionTeam(MTeam missionTeam) {
        this.missionTeam = missionTeam;
    }

    /**
     * Gets the user name
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name
     * @param userName New user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
