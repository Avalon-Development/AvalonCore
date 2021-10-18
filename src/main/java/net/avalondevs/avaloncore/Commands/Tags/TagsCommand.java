package net.avalondevs.avaloncore.Commands.Tags;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.SQLGetter;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static net.avalondevs.avaloncore.Main.SQL;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class TagsCommand implements CommandExecutor {

    public TagsCommand() {
        Main.getPlugin(Main.class).getCommand("tags").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(chat("&CPlayer only command!"));
        }

        Player player = (Player) sender;

        if (!SQL.isConnected()) {
            player.sendMessage(chat("&cSorry, the database is not connected check console...."));
            player.sendMessage(chat("&cPlease message DaatUserName or ashh on discord if you encounter any errors"));
        } else {
            switch (args[0]) {
                case "create":
                    String name = args[2];
                    String prefix = args[3];
                    SQLGetter.createTag(name, prefix);
                    player.sendMessage(chat("&fPrefix &7a tag with the name " + name + " prefix: " + prefix));
                    break;
                case "list":
                    try {
                        PreparedStatement ps;
                        ps = SQL.getConnection().prepareStatement("SELECT * FROM tags");
                        player.sendMessage((Component) ps);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "delete":
                    break;
                case "help":
                    break;
            }
        }

        return true;
    }
}
