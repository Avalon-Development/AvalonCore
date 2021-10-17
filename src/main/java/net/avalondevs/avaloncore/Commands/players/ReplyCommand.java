package net.avalondevs.avaloncore.Commands.players;

import net.avalondevs.avaloncore.Commands.Staff.SocialSpyCommand;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.avalondevs.avaloncore.Utils.Utils.*;

public class ReplyCommand {

    @Command(name = "r", aliases = { "reply" })
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();

        if(adapter.length() >= 1) {
            Player target = MsgCommand.KnownSender.get(player);
            String message = adapter.range(0);
            if(target != null) {
                if(target.isOnline()) {
                    target.sendMessage(chat("&fFrom " + "&b" + getPrefix(player.getUniqueId()) + player.getName() + "&8: " + "&f" + message));
                    player.sendMessage(chat("&fTo " + "&b" + getPrefix(target.getUniqueId()) + target.getName() + "&8: " + "&f" + message));
                    for(Player staff : Bukkit.getOnlinePlayers()) {
                        if(staff.hasPermission("core.staff.socialspy")) {
                            if(SocialSpyCommand.sp.contains(staff)) {
                                staff.sendMessage(chat(PREFIX + "&6[SOCIAL SPY] &f" + getPrefix(player.getUniqueId()) + player.getName() +"&6> &f" + getPrefix(target.getUniqueId()) + target.getName() + "&9" + message));
                            }
                        }
                    }
                } else {
                    player.sendMessage("player not found");
                }
            } else {
                player.sendMessage(chat("&cNo messages found"));
            }
        } else {
            player.sendMessage(chat(PREFIX + " &7Invalid arguments"));
        }
    }


    private static String getMessage(String[] args, int index) {
        StringBuilder sb = new StringBuilder();
        for(int i = index; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
