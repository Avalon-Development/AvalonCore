package net.avalondevs.avaloncore.Listeners;

import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Color;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
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

}
