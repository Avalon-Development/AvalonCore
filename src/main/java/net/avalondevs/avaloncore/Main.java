package net.avalondevs.avaloncore;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.avalondevs.avaloncore.Listeners.PlayerListeners;
import net.avalondevs.avaloncore.MySQL.Database;
import net.avalondevs.avaloncore.MySQL.PlayerData;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import net.avalondevs.avaloncore.MySQL.StaffSQL;
import net.avalondevs.avaloncore.MySQL.providers.MySQL;
import net.avalondevs.avaloncore.MySQL.providers.SQLite;
import net.avalondevs.avaloncore.Utils.*;
import net.avalondevs.avaloncore.Utils.command.CommandFramework;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.avalondevs.avaloncore.databasing.PunishmentData;
import net.avalondevs.avaloncore.punishments.Punishments;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static Database SQL;
    public static SQLGetter data;
    public static StaffSQL staffSQL;
    public static PlayerData playerData;
    @Getter
    @Setter
    public static Main instance;
    @Getter
    static Database database;
    CommandFramework framework = new CommandFramework(this); // initialize a new framework
    public static Main getPlugin() {
        return plugin;
    }
    private Economy econ;

    @SneakyThrows
    public void onEnable() {

        setInstance(this);
        plugin = this;

        Logger.init();

        Bukkit.getConsoleSender().sendMessage("===============");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "* Name: &f" + getDescription().getName());
        Bukkit.getConsoleSender().sendMessage("===============");

        Logger.info(ChatColor.AQUA + "Loading libraries ...");

        ResourceUtil.parent = this;
        ResourceUtil.init();

        for (String string : getConfig().getStringList("dependencies")) {

            ResourceUtil.downloadLib(string);

        }

        LuckPermsAdapter.init();

        saveDefaultConfig();

        ConfigUtil.updateConfig(this, "config.yml"); // update config file

        saveResource("messages.yml", false);

        ConfigUtil.updateConfig(this, "messages.yml");

        I18N i18n = new I18N();

        // Init MySQL Database

        String option = getConfig().getString("database");

        Database.setParent(this);
        Database database;

        Logger.info("Using " + option + " database");

        if (Objects.equals(option, "mysql")) {
            database = new MySQL();
        } else {
            database = new SQLite();
        }

        database.connect();

        if (!database.isConnected()) {

            if(Objects.equals(option, "mysql")) { // fallback: sqlite

                database = new SQLite();

                Logger.warn("Could not connect to mysql, fallback to local database. Changes wont be synced");

                database.connect();

            }

            if(!database.isConnected())
                Logger.error("Could not establish any valid database connection");

        }else {

            SQL = database; // DarkNet 10/18/2021: TODO please change over from primitive MySQL to generic Database

            data = new SQLGetter(this);
            staffSQL = new StaffSQL(this);
            playerData = new PlayerData(this);

            data.createTable();
            staffSQL.createTable();
            playerData.createTable();

            CommandRegistry.registerCommands(framework);

            PunishmentData.init();

        }

        if (!setupEconomy()) {
            getLogger().severe("VAULT IS NOT ENABLED -- ADD IT OR BREAKAGE MAY OCCUR");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        CommandRegistry.registerModuleCommands(framework);

        Voucher.cache();
        VoucherManager.instance.cache();

        Punishments.load();

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    public void onDisable() {

        if(database != null && database.isConnected()) {
            database.disconnect();
            PunishmentData.save();
        }

        Bukkit.getConsoleSender().sendMessage("===============");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Plugin disabled");
        Bukkit.getConsoleSender().sendMessage("===============");
    }

    public boolean setupEconomy () {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        econ = rsp.getProvider();
        return true;
    }

}
