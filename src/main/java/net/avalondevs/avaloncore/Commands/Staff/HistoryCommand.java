package net.avalondevs.avaloncore.Commands.Staff;

import net.avalondevs.avaloncore.Utils.I18N;
import net.avalondevs.avaloncore.Utils.command.Command;
import net.avalondevs.avaloncore.Utils.command.CommandAdapter;
import net.avalondevs.avaloncore.punishments.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryCommand {

    public void sendBanEntry(BanEntry banEntry, CommandAdapter adapter, boolean active) {

        String banner;
        if (banEntry.getBanner().equals(Punishments.consoleUUID))
            banner = "CONSOLE";
        else {
            OfflinePlayer user = Bukkit.getOfflinePlayer(banEntry.getBanner());
            banner = user.getName();
        }

        if (banEntry.getUntil() == -1) {

            adapter.sendFMessageNoPrefix("command.history.entries.ban"
                    , I18N.getInstance().formatDate(new Date(banEntry.getTimestamp()))
                    , banner
                    , banEntry.getReason()
                    , active);

        } else {

            String until = I18N.instance.formatPeriod(banEntry.getUntil());

            adapter.sendFMessageNoPrefix("command.history.entries.tempban"
                    , I18N.getInstance().formatDate(new Date(banEntry.getTimestamp()))
                    , banner
                    , until
                    , banEntry.getReason()
                    , active);

        }

    }

    public void sendMuteEntry(MuteEntry muteEntry, CommandAdapter adapter, boolean active) {

        String banner;
        if (muteEntry.getBanner().equals(Punishments.consoleUUID))
            banner = "CONSOLE";
        else {
            OfflinePlayer user = Bukkit.getOfflinePlayer(muteEntry.getBanner());
            banner = user.getName();
        }

        if (muteEntry.getUntil() == -1) {

            adapter.sendFMessageNoPrefix("command.history.entries.mute"
                    , I18N.getInstance().formatDate(new Date(muteEntry.getTimestamp()))
                    , banner
                    , muteEntry.getReason()
                    , active);

        } else {

            String until = I18N.instance.formatPeriod(muteEntry.getUntil());

            adapter.sendFMessageNoPrefix("command.history.entries.tempmute"
                    , I18N.getInstance().formatDate(new Date(muteEntry.getTimestamp()))
                    , banner
                    , until
                    , muteEntry.getReason()
                    , active);

        }

    }

    public void sendKickEntry(KickEntry entry, CommandAdapter adapter) {

        String banner;
        if (entry.getSource().equals(Punishments.consoleUUID))
            banner = "CONSOLE";
        else {
            OfflinePlayer user = Bukkit.getOfflinePlayer(entry.getSource());
            banner = user.getName();
        }

        adapter.sendFMessageNoPrefix("command.history.entries.kick"
                , I18N.getInstance().formatDate(new Date(entry.getTimestamp()))
                , banner
                , entry.getReason());


    }

    @Command(name = "history", permission = "core.staff.history")
    public void onCommand(CommandAdapter adapter) {

        if (adapter.length() < 1)
            adapter.fail();
        else {

            String target = adapter.getArgs(0);

            OfflinePlayer player = Bukkit.getOfflinePlayer(String.valueOf(target));

            final List<PunishmentEntry>[] active = new List[]{Punishments.resolveUserActive(player.getUniqueId())};
            final List<PunishmentEntry>[] archived = new List[]{Punishments.resolveUserArchived(player.getUniqueId())};

            adapter.optionalArg(1, (sorting) -> {

                List<PunishmentEntry> newActive = new ArrayList<>();
                List<PunishmentEntry> newArchived = new ArrayList<>();

                String[] individual = sorting.split(",");

                for (String s : individual) {

                    switch (s) {
                        case "mute" -> {

                            for (PunishmentEntry punishmentEntry : active[0]) {

                                if (punishmentEntry instanceof MuteEntry)
                                    newActive.add(punishmentEntry);

                            }
                            for (PunishmentEntry punishmentEntry : archived[0]) {

                                if (punishmentEntry instanceof MuteEntry)
                                    newActive.add(punishmentEntry);

                            }

                        }
                        case "ban" -> {

                            for (PunishmentEntry punishmentEntry : active[0]) {

                                if (punishmentEntry instanceof BanEntry)
                                    newActive.add(punishmentEntry);

                            }
                            for (PunishmentEntry punishmentEntry : archived[0]) {

                                if (punishmentEntry instanceof BanEntry)
                                    newActive.add(punishmentEntry);

                            }

                        }
                        case "kick" -> {

                            for (PunishmentEntry punishmentEntry : active[0]) {

                                if (punishmentEntry instanceof KickEntry)
                                    newActive.add(punishmentEntry);

                            }
                            for (PunishmentEntry punishmentEntry : archived[0]) {

                                if (punishmentEntry instanceof KickEntry)
                                    newActive.add(punishmentEntry);

                            }

                        }
                    }

                }
                active[0] = newActive;
                archived[0] = newArchived;


            });

            adapter.sendFMessage("command.history.header", target);

            for (PunishmentEntry entry : active[0]) {

                if (entry instanceof BanEntry banEntry) {

                    sendBanEntry(banEntry, adapter, true);

                }

                if (entry instanceof MuteEntry muteEntry) {

                    sendMuteEntry(muteEntry, adapter, true);

                }

                if(entry instanceof KickEntry kickEntry) {

                    sendKickEntry(kickEntry, adapter);

                }

            }

            for (PunishmentEntry entry : archived[0]) {

                if (entry instanceof BanEntry banEntry) {

                    sendBanEntry(banEntry, adapter, false);

                }

                if (entry instanceof MuteEntry muteEntry) {

                    sendMuteEntry(muteEntry, adapter, true);

                }

                if(entry instanceof KickEntry kickEntry) {

                    sendKickEntry(kickEntry, adapter);

                }

            }

        }

    }

}
