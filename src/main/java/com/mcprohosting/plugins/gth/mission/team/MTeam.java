package com.mcprohosting.plugins.gth.mission.team;

import com.mcprohosting.plugins.gth.faction.Faction;
import com.mcprohosting.plugins.gth.user.User;
import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MTeam {

    private ArrayList<Location> spawns = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private final Faction facion;

    public MTeam(Faction faction) {
        this.facion = faction;
    }

    public Faction getFacion() {
        return facion;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Location getSafestSpawn() {
        return null;
    }

}
