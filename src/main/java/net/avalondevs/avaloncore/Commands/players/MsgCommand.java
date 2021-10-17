package net.avalondevs.avaloncore.Commands.players;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static net.avalondevs.avaloncore.Utils.Utils.PREFIX;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class MsgCommand  {

    public static Map<Player, Player> KnownSender = new HashMap<>();
    
    @Command(name = "msg", aliases = {"message"})
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();
        if(adapter.length() == 2) {
            Player target = Bukkit.getPlayer(adapter.getArgs(0));
            String message = getMessage(adapter.getArgs(), 0);
            assert target != null;
            if(target.isOnline()) {
                target.sendMessage(chat("&fFrom " + "&b" + player.getName() + "&8: " + "&f" + message));
                player.sendMessage(chat("&fTo " + "&b" + target.getName() + "&8: " + "&f" + message));
                KnownSender.put(target, player);
                KnownSender.put(player, target);
            } else {
                player.sendMessage(chat("&c Player "+ target.getName() + " is not online!"));
            }
        } else {
            player.sendMessage(chat(PREFIX + " &7Invalid arguments!"));
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
