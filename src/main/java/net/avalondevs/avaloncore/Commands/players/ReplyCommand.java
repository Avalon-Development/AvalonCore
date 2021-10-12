package net.avalondevs.avaloncore.Commands.players;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class ReplyCommand implements CommandExecutor {

    public ReplyCommand() {
        //getPlugin().getCommand("reply").setExecutor(this);
    }

    Plugin plugin = Main.getPlugin(Main.class);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 1) {
            Player target = MsgCommand.KnownSender.get(player);
            String message = getMessage(args);
            if(target != null) {
                if(target.isOnline()) {
                    target.sendMessage(Utils.chat("&fFrom " + "&b" + player.getName() + "&8: " + "&f" + message));
                    sender.sendMessage(Utils.chat("&fTo " + "&b" + target.getName() + "&8: " + "&f" + message));
                } else {
                    sender.sendMessage(Utils.chat("&cPlayer not found!"));
                }
            } else {
                sender.sendMessage(Utils.chat("&cNo messages found"));
            }

        }
        return false;
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        MsgCommand.KnownSender.remove(e.getPlayer());
    }

    private static String getMessage(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
