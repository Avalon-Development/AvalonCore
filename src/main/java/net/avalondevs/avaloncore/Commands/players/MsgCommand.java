package net.avalondevs.avaloncore.Commands.players;

import net.avalondevs.avaloncore.Commands.Staff.SocialSpyCommand;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static net.avalondevs.avaloncore.Utils.Utils.PREFIX;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class MsgCommand {

    public static Map<Player, Player> KnownSender = new HashMap<>();

    @Command(name = "msg", aliases = {"message"})
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();
        if (adapter.length() >= 2) {
            Player target = Bukkit.getPlayer(adapter.getArgs(0));
            String message = adapter.range(1);
            assert target != null;
            if (target.isOnline()) {
                target.sendMessage(chat("&fFrom " + "&b" + LuckPermsAdapter.getPrefix(player.getUniqueId()) + player.getName() + "&8: " + "&f" + message));
                player.sendMessage(chat("&fTo " + "&b" + LuckPermsAdapter.getPrefix(target.getUniqueId()) + target.getName() + "&8: " + "&f" + message));
                KnownSender.put(target, player);
                KnownSender.put(player, target);
                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.hasPermission("core.staff.socialspy")) {
                        if (SocialSpyCommand.sp.contains(staff)) {
                            staff.sendMessage(chat(PREFIX + "&6[SOCIAL SPY] &f" + LuckPermsAdapter.getPrefix(player.getUniqueId()) + player.getName() + "&6> &f" + LuckPermsAdapter.getPrefix(target.getUniqueId()) + target.getName() + "&9" + message));
                        }
                    }
                }
            } else {
                player.sendMessage(chat("&c Player " + target.getName() + " is not online!"));
            }
        } else {
            player.sendMessage(chat(PREFIX + " &7Invalid arguments!"));
        }
    }
}
