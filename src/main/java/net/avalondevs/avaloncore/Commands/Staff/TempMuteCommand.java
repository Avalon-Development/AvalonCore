package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.DataParser;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class TempMuteCommand {

    @Command(name = "tempmute", permission = "core.staff.tempmute")
    public void onCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 2)
            adapter.fail();
        else {

            String targetName = adapter.getArgs(0);
            String time = adapter.getArgs(1);

            long until = DataParser.readTime(time);

            OfflinePlayer player = Bukkit.getOfflinePlayer(String.valueOf(targetName));

            String finalTime = time;
            long finalUntil = until;
            if (adapter.optionalArg(2, (ignored) -> {

                String reason = adapter.range(2);

                Punishments.mute(player, banner, reason, finalUntil);

                adapter.sendMessage("command.tempmute.execute", targetName, finalTime, reason);

            })) {

                Punishments.mute(player, banner, until);

                adapter.sendMessage("command.tempmute.execute", targetName, time, Punishments.defaultReason);

            }

        }

    }
}
