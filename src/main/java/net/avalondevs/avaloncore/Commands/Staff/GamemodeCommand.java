package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class GamemodeCommand {


    @Command(name = "gamemode", aliases = {"gm"}, permission = "core.gamemode")
    public void onCommand(@NotNull CommandAdapter adapter) {

        Player player = adapter.getPlayer();

        switch (adapter.getArgs(0)) {
            case "c" -> {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Utils.PREFIX + chat(" &7Set your gamemode to &e&N" + adapter.getArgs(0)));
            }
            case "sp" -> {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Utils.PREFIX + chat(" &7Set your gamemode to &e&N" + adapter.getArgs(0)));
            }
            case "s" -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Utils.PREFIX + chat(" &7Set your gamemode to &e&N" + adapter.getArgs(0)));
            }
            case "a" -> {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(Utils.PREFIX + chat(" &7Set your gamemode to &e&N" + adapter.getArgs(0)));
            }
        }
    }
}
