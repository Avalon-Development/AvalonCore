package net.avalondevs.avaloncore.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static net.avalondevs.avaloncore.Main.playerData;
import static net.avalondevs.avaloncore.Main.staffSQL;
import static net.avalondevs.avaloncore.Utils.Utils.chat;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if(playerData.isMutedGetter(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(chat("&7You have been muted!"));
        } else {
            event.setCancelled(false);
        }
    }
}
