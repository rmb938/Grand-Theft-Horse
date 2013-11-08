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

    /**
     * Holds all the data related to the GTH Plugin
     * @param plugin GTH Plugin
     */
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

    /**
     * Creates a new connection to the database
     * @return SQL Connection
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"+databaseAddress+"/"+databaseName,
                databaseUserName, databasePassword);
    }

    /**
     * Create Tables if needed on plugin start
     */
    private void createTables() {
        //TODO: auto create tables
        //create user table
        //    create user inventory table
        //    create user armour table
        //    create user horse info table
        //        create horse armour table
        //        create horse inv table
    }

    /**
     * Retrieves the user from the database or cache
     * @param name User Name
     * @return User (may be null)
     */
    public User getUser(String name) {
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    /**
     * Creates initial user info in database
     * @param name User Name
     */
    public void createUser(String name) {
        //TODO: create user in database
    }

    /**
     * Saves the user info to the database
     * @param name User Name
     * @param remove Remove the user from the cache
     */
    public void saveUser(String name, boolean remove) {
        User user = users.get(name);
        if (user == null) {
            return;
        }
        //TODO: save user to database
        if (remove == true) {
            users.remove(name);
        }
    }

    /**
     * Generates a mission using random objectives from the database
     * @return Mission (may be null)
     */
    public Mission generateMission() {
        //TODO: generate mission from random objectives
        return null;
    }

    /**
     * Remvoes a running mission from the cache
     * @param mission A running mission
     */
    public void removeMission(Mission mission) {
        runningMissions.remove(mission);
    }

    /**
     * Runs a query to the SQL database using prepared statements
     * @param conn SQL Connection
     * @param statement SQL Prepared Statement
     * @param params Prepared Statement Objects
     */
    public void updateQueryPS(Connection conn, String statement, Object... params) {
        try {
            QueryRunner run = new QueryRunner();
            if (statement.startsWith("SELECT") == false || statement.startsWith("select") == false) {
                run.update(conn, statement, params);
            } else {
                System.out.println("UpdateQuery can not be used to select!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Runs a query to the SQL database
     * @param conn SQL Connection
     * @param statement SQL Statement
     */
    public void updateQuery(Connection conn, String statement) {
        try {
            QueryRunner run = new QueryRunner();
            if (statement.startsWith("SELECT") == false || statement.startsWith("select") == false) {
                run.update(conn, statement);
            } else {
                System.out.println("UpdateQuery can not be used to select!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
