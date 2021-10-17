package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

import static net.avalondevs.avaloncore.Utils.Utils.chat;

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

}
