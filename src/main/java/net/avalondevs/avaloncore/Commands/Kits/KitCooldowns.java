package net.avalondevs.avaloncore.Commands.Kits;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KitCooldowns {

    public static void KitSpecialcooldownTimer(Player p) {
        Config.KitSpecialCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitSpecialCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitSpecialCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitSpecialCooldownTimeSccondsInt * 20);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void KitHerocooldownTimer(Player p) {
        Config.KitHeroCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitHeroCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitHeroCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitSpecialCooldownTimeSccondsInt * 20);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void KitZeuscooldownTimer(Player p) {
        Config.KitZeusCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitZeusCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitZeusCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitZeusCooldownTimeSccondsInt * 20);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void KitEternalcooldownTimer(Player p) {
        Config.KitEternalCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitEternalCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitEternalCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitEternalCooldownTimeSccondsInt * 20);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void KitImmortalcooldownTimer(Player p) {
        Config.KitImmortalCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitImmortalCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitImmortalCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitImmortalCooldownTimeSccondsInt * 20);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void KitLegendcooldownTimer(Player p) {
        Config.KitImmortalCooldownTime.add(p.getUniqueId());
        p.sendMessage(Utils.chat(Utils.PREFIX + "This kit will be on cooldown for the next &c"
                + Config.KitLegendCooldownTimeSccondsInt + " &7seconds!"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Config.KitLegendCooldownTime.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getPlugin(Main.class), Config.KitLegendCooldownTimeSccondsInt * 20);
    }
}
