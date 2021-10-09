package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.StaffSQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.staffSQL;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class VanishCommand implements CommandExecutor {

    Plugin plugin = Main.getPlugin(Main.class);

    public VanishCommand() {
        Main.getPlugin(Main.class).getCommand("vanish").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(chat("&cOnly players can type staff commands"));
        }

        Player player = (Player) sender;

        if(player.hasPermission("core.vanish")) {
            if(staffSQL.isVanishedGetter(player.getUniqueId())) {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    all.showPlayer(plugin, player);
                }
                player.sendMessage(chat("&3&lAVA&b&lLON &c&ndisabled &7vanish"));
            } else {
                staffSQL.isVanishedSetter(player.getUniqueId(), true);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.hasPermission("core.admin") || all.hasPermission("core.*")) {
                        all.showPlayer(plugin, player);
                    } else {
                        all.hidePlayer(plugin, player);
                    }
                }
                player.sendMessage(chat("&3&Lava&b&lLON &7a&nEnabled &7vanish"));
            }
        }
        return true;
    }
}
