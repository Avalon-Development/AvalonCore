package net.avalondevs.avaloncore.Commands.voucher;

import net.avalondevs.avaloncore.Utils.Utils;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.data.Voucher;
import net.avalondevs.avaloncore.data.VoucherManager;
import org.bukkit.inventory.ItemStack;

public class VoucherCommand {

    @Command(name = "voucher", permission = "core.voucher")
    public void onRootCommand(CommandAdapter adapter) {

        adapter.sendMessage("");

    }

    // TODO make error messages

    @Command(name = "voucher.info", permission = "core.voucher.info")
    public void onInfoCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (name) -> {

            Voucher voucher = VoucherManager.getInstance().get(name);

            adapter.sendMessage(Utils.PREFIX + " Info for voucher:");
            adapter.sendMessage("Rank: " + voucher.getRank());

        });

    }

    @Command(name = "voucher.add", permission = "core.voucher.add")
    public void onAddCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (rank) -> {

           String name = adapter.range(1);

           Voucher voucher = new Voucher(rank, name);

           VoucherManager.getInstance().add(voucher.rank, voucher);


        });

    }

    @Command(name = "voucher.get", permission = "core.voucher.get")
    public void onGetCommand(CommandAdapter adapter) {

        adapter.requireArg(0, (rank) -> {

            Voucher voucher = VoucherManager.getInstance().get(rank);

            ItemStack stack; // TODO Generate item for voucher



        });

    }

}
