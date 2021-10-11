package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.getPlugin;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class KickCommand implements CommandExecutor {

    public KickCommand() {
        getPlugin().getCommand("kick").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if(player.hasPermission("core.staff.kick")) {
            if(args.length < 1) {
                player.sendMessage(chat(getPlugin().getConfig().getString("kick.usage")));
                return true;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                String reason = args[1];
                if(target.isOnline()) {
                    if(reason.length() < 1) {
                        player.sendMessage(Utils.PREFIX + " &7You must provide a reason");
                    } else {
                        target.kickPlayer("You have been kicked for " + reason);
                    }
                }
            }
        }
        return true;
    }
}
