package net.avalondevs.avaloncore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListeners implements Listener {

    @EventHandler
    public void guiListener(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        player.sendMessage(player.getName());

    }
}
