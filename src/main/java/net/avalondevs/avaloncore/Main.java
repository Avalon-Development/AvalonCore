package net.avalondevs.avaloncore;

import net.avalondevs.avaloncore.Commands.Staff.FreezeeCommand;
import net.avalondevs.avaloncore.Commands.Staff.GamemodeCommand;
import net.avalondevs.avaloncore.Commands.Staff.VanishCommand;
import net.avalondevs.avaloncore.Commands.Tags.TagsCommand;
import net.avalondevs.avaloncore.MySQL.MySQL;
import net.avalondevs.avaloncore.MySQL.PlayerData;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import net.avalondevs.avaloncore.MySQL.StaffSQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static MySQL SQL;
    public static SQLGetter data;
    public static StaffSQL staffSQL;
    public static PlayerData playerData;


    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Init MySQL Database
        SQL = new MySQL();
        data = new SQLGetter(this);

        try {
            SQL.connect();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MYSQL DATABASE FAILED TO CONNECT!!!");
        }

        if (SQL.isConnected()) {
            data.createTable();
            staffSQL.createTable();
            playerData.createTable();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL DATABASE CONNECTED SUCCESSFULLY");
            loadCommands();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    void loadCommands() {
        new TagsCommand();
        new VanishCommand();
        new GamemodeCommand();
        new FreezeeCommand();
    }

    public static Main getPlugin() {
        return plugin;
    }
}
