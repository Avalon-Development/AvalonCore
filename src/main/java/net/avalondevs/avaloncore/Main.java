package net.avalondevs.avaloncore;

import net.avalondevs.avaloncore.Listeners.GuiListeners;
import net.avalondevs.avaloncore.MySQL.MySQL;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {
    public static MySQL SQL;
    public static SQLGetter data;


    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Init MySQL Database
        this.SQL = new MySQL();
        data = new SQLGetter(this);

        try {
            SQL.connect();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MYSQL DATABASE FAILED TO CONNECT!!!");
        }

        if (SQL.isConnected()) {
            data.createTable();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL DATABASE CONNECTED SUCCESSFULLY");
            getCommand("tags").setExecutor(new TagsCommand());

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
