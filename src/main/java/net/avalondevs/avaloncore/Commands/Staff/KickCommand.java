package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class KickCommand {

    @Command(name = "kick", permission = "core.staff.kick")
    public void onCommand(CommandAdapter adapter) {

        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;

        if (adapter.length() < 1)
            adapter.fail();
        else {

            String targetName = adapter.getArgs(0);

            Player player = Bukkit.getPlayer(String.valueOf(targetName));

            if (player == null) {

                adapter.sendFMessage("error.player.not-found");
                return;

            }

            if (adapter.optionalArg(1, (ignored) -> {

                String reason = adapter.range(1);

                Punishments.kick(player, banner, reason);

                adapter.sendMessage("command.kick.execute", targetName, reason);

            })) {

                Punishments.kick(player, banner);

                adapter.sendMessage("command.kick.execute", targetName, Punishments.defaultReason);

            }

        }

    }
}
