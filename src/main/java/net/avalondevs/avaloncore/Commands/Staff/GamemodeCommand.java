package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.StrUtil;
import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand {

    public void commonStub(CommandAdapter adapter, GameMode mode) {

        if(adapter.getSender().hasPermission("core.gamemode." + mode.name().toLowerCase())) {
            adapter.getPlayer().setGameMode(GameMode.CREATIVE);
            adapter.sendMessage(Utils.PREFIX + " &7Set your gamemode to &e&n" + StrUtil.extractNameFromEnum(mode));
        } else {
            adapter.sendNoPermission();
        }

    }

    @Command(name = "gamemode", aliases = {"gm"}, permission = "core.gamemode")
    public void onCommand(@NotNull CommandAdapter adapter) {

        Player player = adapter.getPlayer();
        adapter.requireArg(0, (mode) -> {

            switch (mode) {
                case "c" -> commonStub(adapter, GameMode.CREATIVE);
                case "s" -> commonStub(adapter, GameMode.SURVIVAL);
                case "sp" -> commonStub(adapter, GameMode.SPECTATOR);
            }

        });
    }
}
