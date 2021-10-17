package net.avalondevs.avaloncore;

import lombok.Getter;
import lombok.Setter;
import net.avalondevs.avaloncore.Commands.Staff.*;
import net.avalondevs.avaloncore.Commands.Tags.TagsCommand;
import net.avalondevs.avaloncore.Commands.players.MsgCommand;
import net.avalondevs.avaloncore.Commands.players.ReplyCommand;
import net.avalondevs.avaloncore.Commands.voucher.VoucherCommand;
import net.avalondevs.avaloncore.Listeners.PlayerListeners;
import net.avalondevs.avaloncore.MySQL.MySQL;
import net.avalondevs.avaloncore.MySQL.PlayerData;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import net.avalondevs.avaloncore.MySQL.StaffSQL;
import net.avalondevs.avaloncore.Utils.ConfigUtil;
import net.avalondevs.avaloncore.Utils.I18N;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.command.CommandFramework;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.avalondevs.avaloncore.punishments.Punishments;
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
    CommandFramework framework = new CommandFramework(this); // initialize a new framework


    @Getter
    @Setter
    public static Main instance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("===============");
        Bukkit.getConsoleSender().sendMessage("§b* Name: &f" + getDescription().getName());
        Bukkit.getConsoleSender().sendMessage("===============");

        setInstance(this);
        plugin = this;

        LuckPermsAdapter.init();

        saveDefaultConfig();

        ConfigUtil.updateConfig(this, "config.yml"); // update config file

        saveResource("messages.yml", false);

        ConfigUtil.updateConfig(this, "messages.yml");

        I18N i18n = new I18N();

        // Init MySQL Database
        SQL = new MySQL();
        data = new SQLGetter(this);
        staffSQL = new StaffSQL(this);
        playerData = new PlayerData(this);

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
            //loadCommands();
        }

        loadModuleCommands();

        Voucher.cache();
        VoucherManager.instance.cache();

        Punishments.load();

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("===============");
        Bukkit.getConsoleSender().sendMessage("§cPlugin disabled");
        Bukkit.getConsoleSender().sendMessage("===============");
    }


    /**
     * This method will load the commands that don't depend on databasing to be enabled
     */
    void loadModuleCommands() {
        framework.registerCommands(new MsgCommand());
        framework.registerCommands(new ReplyCommand());
        framework.registerCommands(new VoucherCommand()); // load VoucherCommand into the framework
        framework.registerCommands(new GamemodeCommand()); // load the GamemodeCommand

        // moderation

        framework.registerCommands(new TempBanCommand()); // load the tempban command

        framework.registerCommands(new UnbanCommand()); // load the unban command

        framework.registerCommands(new HistoryCommand());

        /*
        framework.registerCommands(new MuteCommand());
        framework.registerCommands(new TempmuteCommand());
        */
        framework.registerCommands(new KickCommand());
        framework.registerCommands(new SocialSpyCommand());
        framework.registerCommands(new VanishCommand());
        framework.registerCommands(new FreezeeCommand());
    }

    public static Main getPlugin() {
        return plugin;
    }
}
