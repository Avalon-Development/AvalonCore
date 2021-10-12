package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class UnbanCommand {

    @Command(name = "unban")
    public void onCommand(CommandAdapter adapter) {

        if (adapter.length() < 1)
            adapter.fail();
        else {

            OfflinePlayer player = Bukkit.getOfflinePlayer(adapter.getArgs(0));

            if (Punishments.unban(player.getUniqueId())) {

                adapter.sendMessage("command.unban.execute", adapter.getArgs(0));

            } else {

                adapter.sendFMessage("error.player.not-banned");

            }

        }

    }

}
