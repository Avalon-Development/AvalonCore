package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.DataParser;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class MuteCommand {

    @Command(name = "mute", permission = "core.staff.mute")
    public void onPermCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 1)
            adapter.fail();
        else {

            String targetName = adapter.getArgs(0);

            OfflinePlayer player = Bukkit.getOfflinePlayer(targetName);

            if (adapter.optionalArg(1, (ignored) -> {

                String reason = adapter.range(1);

                Punishments.mute(player, banner, reason);

                adapter.sendMessage("command.mute.execute", targetName, reason);

            })) {

                Punishments.mute(player, banner);

                adapter.sendMessage("command.mute.execute", targetName, Punishments.defaultReason);

            }

        }

    }

    @Command(name = "tempmute", permission = "core.staff.tempmute")
    public void onCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 2)
            adapter.fail();
        else {

            String targetName = adapter.getArgs(0);
            String time = adapter.getArgs(1);

            long until = DataParser.readTime(time);

            if(time.equals("perm")) {
                until = -1;
                time = "permanently";
            }

            OfflinePlayer player = Bukkit.getOfflinePlayer(targetName);

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
