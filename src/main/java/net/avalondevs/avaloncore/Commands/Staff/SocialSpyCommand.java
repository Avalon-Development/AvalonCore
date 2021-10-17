package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static net.avalondevs.avaloncore.Utils.Utils.PREFIX;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class SocialSpyCommand {

    public static List<Player> sp = new ArrayList<>();

    @Command(name = "socialspy", aliases = {"ss"}, permission = "core.staff.socialspy")
    public void onCommand(CommandAdapter adapter) {
        Player player = adapter.getPlayer();

        if(sp.contains(player)) {
            sp.remove(player);
            player.sendMessage(chat(PREFIX + "&7Disabled socialspy"));
        } else {
            sp.add(player);
            player.sendMessage(chat(PREFIX + "&7Enabled socialspy"));
        }
    }
}
