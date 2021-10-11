package net.avalondevs.avaloncore;

import net.avalondevs.avaloncore.Commands.Staff.FreezeeCommand;
import net.avalondevs.avaloncore.Commands.Staff.GamemodeCommand;
import net.avalondevs.avaloncore.Commands.Staff.MuteCommand;
import net.avalondevs.avaloncore.Commands.Staff.VanishCommand;
import net.avalondevs.avaloncore.Commands.Tags.TagsCommand;
import lombok.Getter;
import lombok.Setter;
import net.avalondevs.avaloncore.Commands.voucher.VoucherCommand;
import net.avalondevs.avaloncore.Listeners.PlayerListeners;
import net.avalondevs.avaloncore.MySQL.MySQL;
import net.avalondevs.avaloncore.MySQL.PlayerData;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import net.avalondevs.avaloncore.MySQL.StaffSQL;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.command.CommandFramework;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.luckperms.api.LuckPermsProvider;
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


    @Getter
    @Setter
    public static Main instance;

    @Override
    public void onEnable() {

        instance = this;

        LuckPermsAdapter.init();

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

        loadModuleCommands();

        Voucher.cache();
        VoucherManager.instance.cache();

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    /**
     * This method will load the commands that don't depend on databasing to be enabled
     */
    void loadModuleCommands() {

        CommandFramework framework = new CommandFramework(this); // initialize a new framework

        framework.registerCommands(new VoucherCommand()); // load VoucherCommand into the framework
        framework.registerCommands(new GamemodeCommand()); // load the GamemodeCommand

    }

    void loadCommands() {
        new TagsCommand();
        new VanishCommand();
        new FreezeeCommand();
        new MuteCommand();
    }

    public static Main getPlugin() {
        return plugin;
    }
}
