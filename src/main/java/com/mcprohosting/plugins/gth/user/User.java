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
    private Objective objectiveCompus;

    public Objective getObjectiveCompus() {
        return objectiveCompus;
    }

    public void setObjectiveCompus(Objective objectiveCompus) {
        this.objectiveCompus = objectiveCompus;
    }

    public Location getChosenSpawn() {
        return chosenSpawn;
    }

    public void setChosenSpawn(Location chosenSpawn) {
        this.chosenSpawn = chosenSpawn;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public MTeam getMissionTeam() {
        return missionTeam;
    }

    public void setMissionTeam(MTeam missionTeam) {
        this.missionTeam = missionTeam;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
