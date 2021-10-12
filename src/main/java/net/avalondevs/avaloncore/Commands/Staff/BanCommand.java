package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BanCommand {

    @Command(name = "ban", permission = "core.staff.ban")
    public void onCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 1)
            adapter.fail();
        else {

            String targetName = adapter.getArgs(0);

            OfflinePlayer player = Bukkit.getOfflinePlayer(targetName);

            if (adapter.optionalArg(1, (ignored) -> {

                String reason = adapter.range(1);

                Punishments.ban(player, banner, reason);

                adapter.sendMessage("command.ban.execute", targetName, reason);

            })) {

                Punishments.ban(player, banner);

                adapter.sendMessage("command.ban.execute", targetName, Punishments.defaultReason);

            }

        }

    }

}
