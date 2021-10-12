package net.avalondevs.avaloncore.Commands.players;

import net.avalondevs.avaloncore.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class MsgCommand implements CommandExecutor, Listener {

    public MsgCommand() {
        //getPlugin().getCommand("msg").setExecutor(this);
    }

    public static Map<Player, Player> KnownSender = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length >= 2) {
            Player target = Bukkit.getPlayer(args[0]);
            String message = getMessage(args);
            assert target != null;
            if(target.isOnline()) {
                target.sendMessage(Utils.chat("&fFrom " + "&b" + player.getName() + "&8: " + "&f" + message));
                sender.sendMessage(Utils.chat("&fTo " + "&b" + target.getName() + "&8: " + "&f" + message));
                KnownSender.put(target, player);
                KnownSender.put(player, target);
            } else {
                player.sendMessage(Utils.chat("&c Spilleren "+ target.getName() + " Er ikke online!"));
            }
        }
        return false;
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        KnownSender.remove(e.getPlayer());
    }

    private static String getMessage(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
