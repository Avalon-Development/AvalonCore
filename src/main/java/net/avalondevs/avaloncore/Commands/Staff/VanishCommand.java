package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static net.avalondevs.avaloncore.Main.staffSQL;
import static net.avalondevs.avaloncore.Utils.Utils.PREFIX;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class VanishCommand {

    Plugin plugin = Main.getPlugin(Main.class);

    @Command(name = "vanish", aliases = {"v"}, permission = "core.staff.vanish")
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();

        if (staffSQL.isVanishedGetter(player.getUniqueId())) {
            player.sendMessage(chat(PREFIX + " &7Disabled vanish"));
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.showPlayer(plugin, player);
            }
        } else {
            staffSQL.isVanishedSetter(player.getUniqueId(), true);
            player.sendMessage(chat(PREFIX + " &7Enabled vanish"));
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission("core.staff.*")) {
                    all.showPlayer(plugin, player);
                } else {
                    all.hidePlayer(plugin, player);
                }
            }
        }
    }
}
