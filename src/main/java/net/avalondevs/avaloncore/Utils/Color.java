package net.avalondevs.avaloncore.Utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class Color {

    public String fmt(String input) {

        return ChatColor.translateAlternateColorCodes('&', input);

    }

}
