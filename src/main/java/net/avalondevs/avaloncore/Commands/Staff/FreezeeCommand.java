package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import static net.avalondevs.avaloncore.Main.playerData;
import static net.avalondevs.avaloncore.Utils.Utils.PREFIX;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class FreezeeCommand {

    @Command(name = "freeze", permission = "core.staff.freeze")
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();
        OfflinePlayer target = Bukkit.getPlayer(adapter.getArgs(0));

        if (adapter.length() < 1) {
            adapter.fail();
        }

        if (playerData.isFreezeGetter(target.getUniqueId())) {
            player.sendMessage(chat(PREFIX + " &e&n " + target.getName() + "&7Is already frozen"));
        } else {
            playerData.isFreezeSetter(target.getUniqueId(), true);
            player.sendMessage(chat(PREFIX + " &7You've freezed &e&N" + target.getName()));
        }
    }
}
