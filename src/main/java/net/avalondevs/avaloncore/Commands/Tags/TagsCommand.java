package net.avalondevs.avaloncore.Commands.Tags;

import net.avalondevs.avaloncore.MySQL.SQLGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.SQL;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class TagsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(chat("&CPlayer only command!"));
        }

        Player player = (Player) sender;

        if(!SQL.isConnected()) {
            player.sendMessage(chat("&cSorry, the database is not connected check console...."));
            player.sendMessage(chat("&cPlease message DaatUserName or ashh on discord if you encounter any errors"));
        } else {
            switch (args[0]) {
                case "create":
                    String name = args[1];
                    String prefix = args[2];
                    SQLGetter.createTag(name, prefix);
                    player.sendMessage(chat("&"));
                    break;
                case "list":
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
