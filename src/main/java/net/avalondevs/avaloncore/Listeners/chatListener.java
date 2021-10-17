package net.avalondevs.avaloncore.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

import static net.avalondevs.avaloncore.Main.getPlugin;
import static net.avalondevs.avaloncore.Utils.Utils.getPrefix;

public class chatListener implements Listener, EventExecutor {

    @Override
    public void execute(Listener listener, Event e) throws EventException {
        final CompatPlayerChat event = new CompatPlayerChat(e);

        onPlayerChat(event);
        event.install(e);
    }

    public void onPlayerChat(CompatPlayerChat e) {
        String message = e.getMessage();
        Player player = e.getPlayer();
        e.setFormat(getPlugin().getConfig().getString("chat.format").replace("%prefix%", getPrefix(player.getUniqueId())).replace("%player%",player.getName()).replace("%message%",message));
    }

//ignore
}
