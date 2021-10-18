package net.avalondevs.avaloncore.MySQL.providers;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.Database;
import net.avalondevs.avaloncore.Utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.sql.DriverManager;
import java.sql.SQLException;

import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class MySQL extends Database {

    Plugin plugin = Main.getPlugin(Main.class);
    private final String host = plugin.getConfig().getString("MySQL.host");
    private final int port = plugin.getConfig().getInt("MySQL.port");
    private final String database = plugin.getConfig().getString("MySQL.database");
    private final String username = plugin.getConfig().getString("MySQL.username");
    private final String password = plugin.getConfig().getString("MySQL.password");

    /**
     * Transform platform related syntax like AUTO_INCREMENT to platform syntax
     *
     * @param query the query to transform
     * @return the transformed query
     */
    @Override
    public String translate(String query) {
        return query;
    }

    public void connect() throws SQLException {
        if (!plugin.getConfig().getBoolean("MySQL.enabled")) {
            Logger.error("MySQL is disabled... Please enabled it in the config.yml");
        } else {
            if (!isConnected()) {
                Logger.info("CONNECTING TO MYSQL DATABASE PLEASE WAIT....");
                setConnection(DriverManager.getConnection(
                        "jdbc:mysql://" +
                                host + ":" +
                                port + "/" +
                                database + "?useSSL=false"
                        , username, password));
            }
        }
    }

}
