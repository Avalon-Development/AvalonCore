package net.avalondevs.avaloncore.Commands.voucher;

import lombok.extern.java.Log;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.*;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.Utils.command.Completer;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class VoucherCommand {

    @Command(name = "voucher", permission = "core.voucher", completions = {"info", "add", "get"})
    public void onRootCommand(CommandAdapter adapter) {

        adapter.sendMessage("");



    }

    // TODO make error messages

    @Command(name = "voucher.info", permission = "core.voucher.info")
    public void onInfoCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (name) -> {

            Voucher voucher = VoucherManager.getInstance().get(name);

            adapter.sendMessage(Utils.PREFIX + " Info for voucher:");
            adapter.sendMessage("Rank: " + LuckPermsAdapter.getDefiniteName(voucher.group()));

        });

    }

    @Command(name = "voucher.add", permission = "core.voucher.add")
    public void onAddCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (rank) -> {

           Group group = LuckPermsAdapter.getByName(rank);


           if(group == null) {

                adapter.sendMessage(Utils.PREFIX + " &aThe rank " + rank + " does not exist");

           }else {

               String name = adapter.range(1);

               Voucher voucher = new Voucher(group, Color.fmt(name));

               VoucherManager.getInstance().add(voucher.group().getName(), voucher);

           }

        });

    }

    @Command(name = "voucher.get", permission = "core.voucher.get")
    public void onGetCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (rank) -> {

            Voucher voucher = VoucherManager.getInstance().get(rank);

            ItemStack stack = voucher.create();

            adapter.getPlayer().getInventory().addItem(stack);


        });

    }

}
