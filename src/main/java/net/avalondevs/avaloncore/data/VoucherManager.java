package net.avalondevs.avaloncore.data;

import lombok.Getter;
import lombok.Setter;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.Color;
import net.avalondevs.avaloncore.Utils.ConfigUtil;
import net.avalondevs.avaloncore.Utils.LuckPermsAdapter;
import net.avalondevs.avaloncore.Utils.dataprovider.DataProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.avalondevs.avaloncore.data.Voucher.TEMPLATE_NAME;

public class VoucherManager {

    @Getter
    public static VoucherManager instance = new VoucherManager();

    public Map<String, Voucher> cache = new HashMap<>();

    public Voucher get(Group group) {

        Voucher voucher = new Voucher(group);

        if(!cache.values().contains(voucher))
            cache.put(voucher.buildDisplayName(), voucher);

        return voucher;

    }

    public Voucher getWithName(String name) {

       return cache.get(name);

    }

    public void cache() {

        LuckPermsAdapter.luckperms.getGroupManager().loadAllGroups();

        LuckPermsAdapter.luckperms.getGroupManager().getLoadedGroups().forEach(group -> {

            String name = group.getDisplayName();
            if(name == null)
                name = group.getFriendlyName();

            cache.put(Color.fmt(TEMPLATE_NAME.replace("%rank%", name)), new Voucher(group));

        });

    }
}
