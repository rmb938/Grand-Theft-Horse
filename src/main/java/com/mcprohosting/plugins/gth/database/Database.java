package com.mcprohosting.plugins.gth.database;

import com.mcprohosting.plugins.gth.GrandTheftHorse;
import com.mcprohosting.plugins.gth.mission.Mission;
import com.mcprohosting.plugins.gth.user.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Database {

    private final GrandTheftHorse plugin;
    private final String databaseAddress;
    private final String databaseName;
    private final String tablePrefix;
    private final String databaseUserName;
    private final String databasePassword;
    private final HashMap<String, User> users;
    private final ArrayList<Mission> runningMissions;

    public Database(GrandTheftHorse plugin) {
        DbUtils.loadDriver("com.mysql.jdbc.Driver");
        this.plugin = plugin;
        createTables();
        this.databaseAddress = plugin.getConfig().getString("mysql.address");
        this.databaseName = plugin.getConfig().getString("mysql.database");
        this.tablePrefix = plugin.getConfig().getString("mysql.table_prefix");
        this.databaseUserName = plugin.getConfig().getString("mysql.username");
        this.databasePassword = plugin.getConfig().getString("mysql.password");
        this.users = new HashMap<>();
        this.runningMissions = new ArrayList<>();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"+databaseAddress+"/"+databaseName,
                databaseUserName, databasePassword);
    }

    private void createTables() {
        //create user table
        //    create user inventory table
        //    create user horse info table
        //        create horse inv table

        //create objective type table
        //    create objectives table
        //    create objective scoreboard info table

    }

    public User getUser(String name) {
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    public void createUser(String name) {

    }

    public void saveUser(String name, boolean remove) {
        User user = users.get(name);
        if (user == null) {
            return;
        }

        if (remove == true) {
            users.remove(name);
        }
    }

    public Mission generateMission() {
        return null;
    }

    public void updateQueryPS(Connection conn, String s, Object... params) {
        try {
            QueryRunner run = new QueryRunner();
            run.update(conn, s, params);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateQuery(Connection conn, String s) {
        try {
            QueryRunner run = new QueryRunner();
            if (s.startsWith("SELECT") == false || s.startsWith("select") == false) {
                run.update(conn, s);
            } else {
                plugin.getLogger().info("UpdateQuery can not be used to select!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
