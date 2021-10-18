package net.avalondevs.avaloncore.Utils;

import lombok.experimental.UtilityClass;
import net.avalondevs.avaloncore.Commands.Admin.EatCommand;
import net.avalondevs.avaloncore.Commands.Admin.HealCommand;
import net.avalondevs.avaloncore.Commands.Admin.SetspawnCommand;
import net.avalondevs.avaloncore.Commands.Kits.Kits;
import net.avalondevs.avaloncore.Commands.Kits.Resetcooldowns;
import net.avalondevs.avaloncore.Commands.Staff.*;
import net.avalondevs.avaloncore.Commands.Tags.TagsCommand;
import net.avalondevs.avaloncore.Commands.players.*;
import net.avalondevs.avaloncore.Commands.voucher.VoucherCommand;
import net.avalondevs.avaloncore.Utils.command.CommandFramework;

@UtilityClass
public class CommandRegistry {

    public void registerModuleCommands(CommandFramework framework) {

        framework.registerCommands(new MsgCommand());
        framework.registerCommands(new ReplyCommand());
        framework.registerCommands(new VoucherCommand()); // load VoucherCommand into the framework
        framework.registerCommands(new GamemodeCommand()); // load the GamemodeCommand

        // moderation

        framework.registerCommands(new TempBanCommand()); // load the tempban command

        framework.registerCommands(new UnbanCommand()); // load the unban command

        framework.registerCommands(new HistoryCommand());


        framework.registerCommands(new SetspawnCommand());
        framework.registerCommands(new MuteCommand());
        framework.registerCommands(new TempMuteCommand());
        framework.registerCommands(new KickCommand());
        framework.registerCommands(new SocialSpyCommand());
        framework.registerCommands(new VanishCommand());
        framework.registerCommands(new FreezeeCommand());
        framework.registerCommands(new UnFreeze());
        framework.registerCommands(new HealCommand());
        framework.registerCommands(new EatCommand());

        framework.registerCommands(new TpaCommand());
        framework.registerCommands(new TpAccept());
        framework.registerCommands(new TpaDenyCommand());
        framework.registerCommands(new Kits());
        framework.registerCommands(new Resetcooldowns());

    }

    public void registerCommands(CommandFramework framework) {

        new TagsCommand();
        framework.registerCommands(new VanishCommand());
        framework.registerCommands(new FreezeeCommand());
        framework.registerCommands(new UnFreeze());

    }

}
