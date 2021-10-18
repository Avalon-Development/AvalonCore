package net.avalondevs.avaloncore.Commands.Kits;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Kits implements Listener {

    @Command(name = "kits", permission = "")
    public void onCommand(CommandAdapter adapter) {
        Player p = adapter.getPlayer();
        Inventory kitSelectorGui = Bukkit.getServer().createInventory(p, 45, Utils.chat("&8Kit Selector"));

        ItemStack KitPvP = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitPvPMeta = KitPvP.getItemMeta();
        KitPvPMeta.setDisplayName(Utils.chat("&cPvP"));
        KitPvP.setItemMeta(KitPvPMeta);

        ItemStack KitSpecial = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitSpecialMeta = KitSpecial.getItemMeta();
        KitSpecialMeta.setDisplayName(Utils.chat(Config.KitSpecialKitName));
        if (Config.KitSpecialCooldownTime.contains(p.getUniqueId())) {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitSpecial.setItemMeta(KitSpecialMeta);


        ItemStack KitHero = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitHeroMeta = KitHero.getItemMeta();
        KitHeroMeta.setDisplayName(Utils.chat(Config.KitHeroKitName));
        if (Config.KitHeroCooldownTime.contains(p.getUniqueId())) {
            KitHeroMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitHero.setItemMeta(KitHeroMeta);


        ItemStack KitZeus = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitZeusMeta = KitZeus.getItemMeta();
        KitZeusMeta.setDisplayName(Utils.chat(Config.KitZeusKitName));
        if (Config.KitZeusCooldownTime.contains(p.getUniqueId())) {
            KitZeusMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitZeus.setItemMeta(KitZeusMeta);


        ItemStack KitEternal = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitEternalMeta = KitEternal.getItemMeta();
        KitEternalMeta.setDisplayName(Utils.chat(Config.KitEternalKitName));
        if (Config.KitEternalCooldownTime.contains(p.getUniqueId())) {
            KitEternalMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitEternal.setItemMeta(KitEternalMeta);


        ItemStack KitImmortal = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitImmortalMeta = KitImmortal.getItemMeta();
        KitImmortalMeta.setDisplayName(Utils.chat(Config.KitImmortalKitName));
        if (Config.KitImmortalCooldownTime.contains(p.getUniqueId())) {
            KitImmortalMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitImmortal.setItemMeta(KitImmortalMeta);


        ItemStack KitLegend = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta KitLegendMeta = KitLegend.getItemMeta();
        KitLegendMeta.setDisplayName(Utils.chat(Config.KitLegendKitName));
        if (Config.KitLegendCooldownTime.contains(p.getUniqueId())) {
            KitLegendMeta.setLore(Arrays.asList("", Utils.chat("&cThis kit is on cooldown")));
        } else {
            KitSpecialMeta.setLore(Arrays.asList("", Utils.chat("&aThis kit is not on cooldown")));
        }
        KitLegend.setItemMeta(KitLegendMeta);


        ItemStack Glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta Glass1 = Glass.getItemMeta();
        Glass.setItemMeta(Glass1);
        Glass.setDurability((short) 7);


        kitSelectorGui.setItem(0, Glass);
        kitSelectorGui.setItem(1, Glass);
        kitSelectorGui.setItem(2, Glass);
        kitSelectorGui.setItem(3, Glass);
        kitSelectorGui.setItem(4, Glass);
        kitSelectorGui.setItem(5, Glass);
        kitSelectorGui.setItem(6, Glass);
        kitSelectorGui.setItem(7, Glass);
        kitSelectorGui.setItem(8, Glass);
        kitSelectorGui.setItem(9, Glass);
        kitSelectorGui.setItem(17, Glass);
        kitSelectorGui.setItem(18, Glass);
        kitSelectorGui.setItem(26, Glass);
        kitSelectorGui.setItem(27, Glass);
        kitSelectorGui.setItem(35, Glass);
        kitSelectorGui.setItem(36, Glass);
        kitSelectorGui.setItem(37, Glass);
        kitSelectorGui.setItem(38, Glass);
        kitSelectorGui.setItem(39, Glass);
        kitSelectorGui.setItem(40, Glass);
        kitSelectorGui.setItem(41, Glass);
        kitSelectorGui.setItem(42, Glass);
        kitSelectorGui.setItem(43, Glass);
        kitSelectorGui.setItem(44, Glass);
        kitSelectorGui.setItem(10, KitPvP);
        kitSelectorGui.setItem(11, KitSpecial);
        if (p.hasPermission(Config.HeroRankPermission) || p.hasPermission(Config.ZeusRankPermission)
                || p.hasPermission(Config.EternalRankPermission) || p.hasPermission(Config.ImmortalRankPermission)
                || p.hasPermission(Config.LegendRankPermission)) {
            kitSelectorGui.setItem(12, KitHero);
        }
        if (p.hasPermission(Config.ZeusRankPermission) || p.hasPermission(Config.EternalRankPermission)
                || p.hasPermission(Config.ImmortalRankPermission) || p.hasPermission(Config.LegendRankPermission)) {
            kitSelectorGui.setItem(13, KitZeus);
        }
        if (p.hasPermission(Config.EternalRankPermission) || p.hasPermission(Config.ImmortalRankPermission)
                || p.hasPermission(Config.LegendRankPermission)) {
            kitSelectorGui.setItem(14, KitEternal);
        }
        if (p.hasPermission(Config.ImmortalRankPermission) || p.hasPermission(Config.LegendRankPermission)) {
            kitSelectorGui.setItem(15, KitImmortal);
        }
        if (p.hasPermission(Config.LegendRankPermission)) {
            kitSelectorGui.setItem(16, KitLegend);
        }
        p.openInventory(kitSelectorGui);
    }

    @EventHandler
    public void InventoryClickMethods(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector")) && e.getCurrentItem() != null)
            e.setCancelled(true);
        if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&fPvP"))) {
            p.getInventory().clear();
            SetKitItems.KitPvPKit(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitSpecialKitName))
                && (!Config.KitSpecialCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitSpecialKit(p);
            KitCooldowns.KitSpecialcooldownTimer(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitHeroKitName))
                && (!Config.KitHeroCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitHeroKit(p);
            KitCooldowns.KitHerocooldownTimer(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitZeusKitName))
                && (!Config.KitZeusCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitZeusKit(p);
            KitCooldowns.KitZeuscooldownTimer(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitEternalKitName))
                && (!Config.KitEternalCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitEternalKit(p);
            KitCooldowns.KitEternalcooldownTimer(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitImmortalKitName))
                && (!Config.KitImmortalCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitImmortalKit(p);
            KitCooldowns.KitImmortalcooldownTimer(p);


        } else if (e.getWhoClicked().getName().equalsIgnoreCase(Utils.chat("&8Kit Selector"))
                && e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat(Config.KitLegendKitName))
                && (!Config.KitLegendCooldownTime.contains(p.getUniqueId()))) {
            p.getInventory().clear();
            SetKitItems.KitLegendKit(p);
            KitCooldowns.KitLegendcooldownTimer(p);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.MUSHROOM_STEW)) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
                    || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (p.getHealth() <= 18) {
                    p.setHealth(p.getHealth() + 2);
                    p.getInventory().setItemInHand(null);
                    p.updateInventory();
                }
            }
        }
    }
}
