package com.mcprohosting.plugins.gth;

import com.mcprohosting.plugins.gth.database.Database;
import com.mcprohosting.plugins.gth.defaultlisteners.GTHBlockListener;
import com.mcprohosting.plugins.gth.defaultlisteners.GTHPlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 11/7/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrandTheftHorse extends JavaPlugin {

    private GTHBlockListener blockListener;
    private GTHPlayerListener playerListener;
    private Database database;

    public void onEnable() {
        this.saveDefaultConfig();
        this.blockListener = new GTHBlockListener(this);
        this.playerListener = new GTHPlayerListener(this);
        this.database = new Database(this);
    }

    public void onDisable() {

    }

    /**
     * Gets the GTH database
     * @return Database
     */
    public Database getDb() {
        return database;
    }
}
