package net.avalondevs.avaloncore.MySQL;

import net.avalondevs.avaloncore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class MySQL {


    Plugin plugin = Main.getPlugin(Main.class);
    private final String host = plugin.getConfig().getString("MySQL.host");
    private final int port = plugin.getConfig().getInt("MySQL.port");
    private final String database = plugin.getConfig().getString("MySQL.database");
    private final String username = plugin.getConfig().getString("MySQL.username");
    private final String password = plugin.getConfig().getString("MySQL.password");

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws SQLException {
        if(!plugin.getConfig().getBoolean("MySQL.enabled")) {
            Bukkit.getConsoleSender().sendMessage(chat("&cMySQL is disabled... Please enabled it in the config.yml"));
        } else {
            if (!isConnected()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "CONNECTING TO MYSQL DATABASE PLEASE WAIT....");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
