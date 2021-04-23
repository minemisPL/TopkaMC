package me.minemis.topkamc;

import me.minemis.topkamc.commands.TopkaDeathCommand;
import me.minemis.topkamc.commands.TopkaKillCommand;
import me.minemis.topkamc.listeners.OnPlayerDeath;
import me.minemis.topkamc.system.DataManager;
import me.minemis.topkamc.system.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public final class TopkaMC extends JavaPlugin {

    private DataManager dataManager;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        fileManager = new FileManager(this);
        dataManager = new DataManager(this);

        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new OnPlayerDeath(this),this);

        this.getCommand("topkakills").setExecutor(new TopkaKillCommand(this));
        this.getCommand("topkadeaths").setExecutor(new TopkaDeathCommand(this));

        fileManager.loadTopkaJSON();

        //Runs save function asynchronously
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> fileManager.saveTopkaJsonAsync());


    }

    @Override
    public void onDisable() {
        fileManager.saveTopkaJSON();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
