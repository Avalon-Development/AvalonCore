package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {

    public GamemodeCommand() {
        Main.getPlugin(Main.class).getCommand("gamemode").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Player only command!");
        }

        Player player = (Player) sender;
        switch (args[0]) {
            case "c":
                if(player.hasPermission("core.gamemode.creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("&3&lAVA&B&LLON &7set your gamemode to &e&n" + args[0]);
                } else {
                    player.sendMessage("&3&lAVA&B&LLON &cYou do not have access to this command!");
                }
                break;
            case "s":
                if(player.hasPermission("core.gamemode.survival")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("&3&lAVA&B&LLON &7set your gamemode to &e&n" + args[0]);
                } else {
                    player.sendMessage("&3&lAVA&B&LLON &cYou do not have access to this command!");
                }
                break;
            case "sp":
                if(player.hasPermission("core.gamemode.spectator")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("&3&lAVA&B&LLON &7set your gamemode to &e&n" + args[0]);
                } else {
                    player.sendMessage("&3&lAVA&B&LLON &cYou do not have access to this command!");
                }
                break;
        }
        return true;
    }
}
