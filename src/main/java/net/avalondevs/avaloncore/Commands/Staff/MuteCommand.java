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
    public void onCommand(CommandAdapter adapter) {
        UUID banner = adapter.isPlayer() ? adapter.getPlayer().getUniqueId() : Punishments.consoleUUID;
        OfflinePlayer target = Bukkit.getPlayer(adapter.getArgs(0));
        String reason = adapter.range(1);


        if(adapter.length() < 1) {
            adapter.sendMessage(chat(Utils.PREFIX + " invalid args!"));
        } else {
            Punishments.mute(target, banner, reason);
            adapter.sendMessage("command.mute.execute", target, reason);
        }
    }
}
