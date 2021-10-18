package net.avalondevs.avaloncore.Utils.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager implements Listener {

    static Map<UUID, Gui> openGuis = new HashMap<>();

    /**
     * Open a gui
     *
     * @param gui    the gui to open
     * @param player the player to display it to
     */
    public static void openGui(Gui gui, Player player) {

        gui.setHolder(player);

        Inventory inventory = gui.gen();

        player.openInventory(inventory);

        gui.open();

        openGuis.put(player.getUniqueId(), gui);

    }

    /**
     * Close a gui (only use if gui needs to be force closed)
     *
     * @param gui gui to force close
     */
    public static void closeGui(Gui gui) {

        gui.close();

        openGuis.remove(gui.getHolder().getUniqueId());

    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {

        if (openGuis.containsKey(event.getPlayer().getUniqueId())) {

            closeGui(openGuis.get(event.getPlayer().getUniqueId()));

        }

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {

        if (openGuis.containsKey(event.getWhoClicked().getUniqueId())) {

            Gui gui = openGuis.get(event.getWhoClicked().getUniqueId());

            gui.onInvClick(event);

        }

    }

}
