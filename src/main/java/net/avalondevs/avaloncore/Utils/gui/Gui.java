package net.avalondevs.avaloncore.Utils.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class Gui {

    /**
     * Owner of the gui
     */
    @Getter
    @Setter
    Player holder;

    /**
     * This function needs to return the generated inventory
     * @return the inventory
     */
    public abstract Inventory gen();

    /**
     * This will pass on the events only for this gui
     * @param event the event
     */
    public abstract void onInvClick(InventoryClickEvent event);

    /**
     * This code will be called when the inventory opens
     */
    public abstract void open();

    /**
     * This code will be called before the inventory closes
     */
    public abstract void close();

}
