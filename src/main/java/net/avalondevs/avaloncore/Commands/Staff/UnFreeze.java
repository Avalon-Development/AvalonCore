package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import static net.avalondevs.avaloncore.Main.playerData;

public class UnFreeze {

    @Command(name = "unfreeze", permission = "core.staff.unfreeze")
    public void onComand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();
        OfflinePlayer target = Bukkit.getPlayer(adapter.getArgs(0));

        if (adapter.length() < 1) {
            adapter.fail();
        }

        if (playerData.isFreezeGetter(target.getUniqueId())) {
            playerData.isFreezeSetter(target.getUniqueId(), false);
            adapter.sendMessage("&e&n" + target.getName() + " &7has been unfrozen");
        } else {
            adapter.sendMessage("&e&n" + target.getName() + " &7is not frozen");
        }
    }
}
