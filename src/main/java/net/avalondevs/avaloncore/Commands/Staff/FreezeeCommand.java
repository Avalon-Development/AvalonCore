package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.getPlugin;
import static net.avalondevs.avaloncore.Main.playerData;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class FreezeeCommand implements CommandExecutor {

    public FreezeeCommand() {
        getPlugin().getCommand("freeze").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(chat("&cOnly players can execute staff commands!"));
        }

        Player player = (Player) sender;

        if(player.hasPermission("core.staff.freeze")) {
            if(args.length < 0) {
                player.sendMessage("&cInvalid args...");
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(target.isOnline()) {
                    if(playerData.isFreezeGetter(target.getUniqueId())) {
                        playerData.isFreezeSetter(target.getUniqueId(), false);
                        player.sendMessage(target.getName() + " has been unfrozen");
                    } else {
                        playerData.isFreezeSetter(target.getUniqueId(), true);
                        target.sendMessage("You have been frozen by " + player.getName());
                        player.sendMessage(target.getName() + " has been frozen!");
                    }
                } else {
                    player.sendMessage(target.getName() + " is not online");
                }
            }
        } else {
            player.sendMessage(chat("You don't have permission for this command!"));
        }
        return true;
    }
}
