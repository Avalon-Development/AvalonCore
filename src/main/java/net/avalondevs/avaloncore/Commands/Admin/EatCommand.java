package net.avalondevs.avaloncore.Commands.Admin;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.entity.Player;

public class EatCommand {

    @Command(name = "eat", permission = "core.admin.eat")
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();

        player.setSaturation(20);
        adapter.sendMessage("&eYou &7have been fed");
    }
}
