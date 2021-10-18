package net.avalondevs.avaloncore.Commands.Kits;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.entity.Player;

public class Resetcooldowns {

    @Command(name = "resetcooldowns", permission = "core.kits.resetcooldowns")
    public void onCommand(CommandAdapter adapter) {
        Player p = adapter.getPlayer();
        p.sendMessage(Utils.chat(Utils.PREFIX + "Clearing you from all cooldowns!"));
        Config.KitSpecialCooldownTime.remove(p.getUniqueId());
        Config.KitHeroCooldownTime.remove(p.getUniqueId());
        Config.KitZeusCooldownTime.remove(p.getUniqueId());
        Config.KitEternalCooldownTime.remove(p.getUniqueId());
        Config.KitImmortalCooldownTime.remove(p.getUniqueId());
        Config.KitLegendCooldownTime.remove(p.getUniqueId());
    }
}
