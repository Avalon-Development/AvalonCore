package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.DataParser;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempBanCommand {

    @Command(name = "tempban", permission = "core.staff.tempban")
    public void onRootCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 2)
            adapter.fail();
        else {

            Player targetName = Bukkit.getPlayer(adapter.getArgs(0));
            String time = adapter.getArgs(1);

            long until = DataParser.readTime(time);


            OfflinePlayer player = Bukkit.getOfflinePlayer(adapter.getArgs(0));

            String finalTime = time;
            long finalUntil = until;
            if (adapter.optionalArg(2, (ignored) -> {

                String reason = adapter.range(2);

                Punishments.ban(player, banner, reason, finalUntil);

                adapter.sendMessage("command.tempban.execute", targetName, finalTime, reason);

            })) {

                Punishments.ban(player, banner, until);

                adapter.sendMessage("command.tempban.execute", targetName, time, Punishments.defaultReason);

            }

        }

    }


}