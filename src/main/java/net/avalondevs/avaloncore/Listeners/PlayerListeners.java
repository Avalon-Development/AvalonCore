package net.avalondevs.avaloncore.Listeners;

import net.avalondevs.avaloncore.Utils.Color;
import net.avalondevs.avaloncore.Utils.I18N;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.avalondevs.avaloncore.punishments.BanEntry;
import net.avalondevs.avaloncore.punishments.MuteEntry;
import net.avalondevs.avaloncore.punishments.Punishments;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        ItemStack stack = event.getItem();

        Player player = event.getPlayer();

        if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()) {

            String displayName = stack.getItemMeta().getDisplayName();

            Voucher voucher = VoucherManager.getInstance().getWithName(displayName);

            if(voucher == null)
                return;

            User user = LuckPermsAdapter.obtainByAnyMeans(event.getPlayer().getUniqueId());

            Group currentGroup = LuckPermsAdapter.getPrimaryGroup(user);

            if(currentGroup.getWeight().orElse(0) >= voucher.group().getWeight().orElse(0)) {

                player.sendMessage(
                        Color.fmt(
                                Utils.PREFIX
                                        + " &cYou have a higher rank then this voucher, the item has been returned to your inventory!"
                        )
                );

            }else {

                String name = LuckPermsAdapter.getDefiniteName(voucher.group());

                player.sendMessage(Color.fmt(Utils.PREFIX + " &7You have claimed the " + name + " &7rank"));

                LuckPermsAdapter.setPrimaryGroup(user, voucher.group());

                player.getInventory().remove(stack);
            }

        }

    }

    @EventHandler
    public void onJoinEvent(PlayerLoginEvent event) {

        BanEntry entry = Punishments.resolveBanUser(event.getPlayer().getUniqueId());

        System.out.println(entry);

        if (entry != null) {

            if (Punishments.checkExpiry(entry)) {

                String reason;
                String banner;
                if (entry.getBanner().equals(Punishments.consoleUUID))
                    banner = "CONSOLE";
                else {
                    OfflinePlayer user = Bukkit.getOfflinePlayer(entry.getBanner());
                    banner = user.getName();
                }

                if (entry.until == -1) {

                    reason = I18N.getInstance().format("punishments.response.ban", banner, entry.getReason());

                } else {

                    String until = I18N.instance.formatPeriod(entry.getUntil());

                    reason = I18N.getInstance().format("punishments.response.tempban", banner, until, entry.getReason());

                }

                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, reason);

            }


        }

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        MuteEntry entry = Punishments.resolveUser(event.getPlayer().getUniqueId(), MuteEntry.class);

        if(entry != null) {

            if(Punishments.checkExpiry(entry)) {

                String reason;
                String banner;
                if (entry.getBanner().equals(Punishments.consoleUUID))
                    banner = "CONSOLE";
                else {
                    OfflinePlayer user = Bukkit.getOfflinePlayer(entry.getBanner());
                    banner = user.getName();
                }

                if (entry.until == -1) {

                    reason = I18N.getInstance().format("punishments.response.mute",  "&4permanently",
                            entry.getReason(), banner);

                } else {

                    String until = I18N.instance.formatPeriod(entry.getUntil());

                    reason = I18N.getInstance().format("punishments.response.mute", "for &e" + until,
                            entry.getReason(), banner);

                }

                event.getPlayer().sendMessage(Color.fmt(reason));

                event.setCancelled(true);

            }

        }

    }

}
