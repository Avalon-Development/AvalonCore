package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.playerData;
import static net.avalondevs.avaloncore.Utils.Utils.chat;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class MuteCommand implements CommandExecutor {

    public MuteCommand() {
        Main.getPlugin().getCommand("mute").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player)sender;

        if(player.hasPermission("core.staff.mute")) {
            if(args.length < 1) {
                player.sendMessage(Utils.PREFIX + " &7You must provide a player to mute");
            }

            String reason = args[1];
            Player target = Bukkit.getPlayer(args[0]);

            if(target.isOnline()) {
                if(!target.hasPermission("core.staff.mute.bypass") || !player.hasPermission("core.staff.*")) {
                    if(playerData.isMutedGetter(target.getUniqueId())) {
                        player.sendMessage(chat(Utils.PREFIX + " &7Player is already muted"));
                    } else {
                        playerData.isMutedSetter(target.getUniqueId(), true);
                        player.sendMessage(Utils.PREFIX + " &7You have muted &7 " + target.getName());
                        target.sendMessage(Utils.PREFIX + " &7You have been muted by &7 " + player.getName() + " &7For &7 " + reason);
                    }
                } else {
                    player.sendMessage(chat(Utils.PREFIX + " &7You can't mute &7" + target.getName()));
                }
            }
        } else {

        }
        return true;
    }
}
