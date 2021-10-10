package net.avalondevs.avaloncore.data;

import lombok.Data;
import lombok.Getter;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Color;
import net.avalondevs.avaloncore.Utils.DataParser;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.luckperms.api.model.group.Group;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public record Voucher(Group group, String name) {



    public static ItemStack VOUCHER_TEMPLATE;
    public static List<String> TEMPLATE_LORE;

    public static void cache() {

        VOUCHER_TEMPLATE = DataParser.readItem(Main.getInstance().getConfig().getString("vouchers.default.material"));
        TEMPLATE_LORE = Main.getInstance().getConfig().getStringList("vouchers.default.lore");

        TEMPLATE_LORE = TEMPLATE_LORE.stream().map(Color::fmt).collect(Collectors.toList());

    }

    public ItemStack create() {

        ItemMeta metadata = VOUCHER_TEMPLATE.getItemMeta();

        metadata.setDisplayName(name);

        String name = group.getDisplayName();
        if(name == null)
            name = group.getFriendlyName();

        String finalName = name;
        metadata.setLore(TEMPLATE_LORE.stream().map(s -> s.replace("%rank%", Color.fmt(finalName))).collect(Collectors.toList()));

        ItemStack newItem = VOUCHER_TEMPLATE.clone();

        newItem.setItemMeta(metadata);

        return newItem;

    }
}
