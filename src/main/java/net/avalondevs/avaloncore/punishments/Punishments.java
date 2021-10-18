package net.avalondevs.avaloncore.punishments;

import lombok.experimental.UtilityClass;
import net.avalondevs.avaloncore.Utils.I18N;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class Punishments {

    public static final UUID consoleUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static String defaultReason = "You have been banned from the server";
    public static String defaultKickReason = "You have been kicked from the server";
    public static String defaultMuteReason = "You have been muted";
    public List<PunishmentEntry> activeEntries = new ArrayList<>();
    public List<PunishmentEntry> archivedEntries = new ArrayList<>();

    public void load() {

        defaultReason = I18N.getInstance().format("punishments.default.ban");
        defaultKickReason = I18N.getInstance().format("punishments.default.kick");
        defaultMuteReason = I18N.getInstance().format("punishments.default.mute");

    }

    public BanEntry ban(@NotNull OfflinePlayer player, @NotNull UUID source) {


        return ban(player, source, defaultReason, -1);

    }

    public BanEntry ban(@NotNull OfflinePlayer player, @NotNull UUID source, long until) {

        return ban(player, source, defaultReason, until);

    }


    public BanEntry ban(@NotNull OfflinePlayer player, @NotNull UUID source, String reason) {

        return ban(player, source, reason, -1);

    }

    public BanEntry ban(@NotNull OfflinePlayer player, UUID source, String reason, long until) {

        if (player.isOnline()) {
            player.getPlayer().kickPlayer(reason);
        }

        return ban(player.getUniqueId(), source, reason, until);

    }

    /**
     * Ban a UUID from the server
     *
     * @param toBan  the uuid to ban
     * @param source the uuid which executed the ban
     * @param reason the reason of the ban
     * @param until  when does the ban expire (set to -1 for permanent)
     * @return the resulting ban entry
     */
    public BanEntry ban(UUID toBan, UUID source, String reason, long until) {

        BanEntry oldBan = resolveBanUser(toBan);

        // ban already exists -> revoke it
        if (oldBan != null) {

            revoke(oldBan);

        }

        BanEntry newBan = new BanEntry(toBan, source, until, reason);

        activeEntries.add(newBan);

        return newBan;

    }

    /**
     * unban a uuid
     *
     * @param uuid the player to unban
     * @return if unban was successful
     */
    public boolean unban(UUID uuid) {

        BanEntry entry = resolveBanUser(uuid);

        if (entry != null) {

            revoke(entry);
            return true;

        }

        return false;

    }


    public KickEntry kick(@NotNull Player player) {

        return kick(player, consoleUUID);

    }

    public KickEntry kick(@NotNull Player user, @NotNull UUID source) {

        return kick(user, source, defaultKickReason);

    }

    public KickEntry kick(@NotNull Player user, @NotNull UUID source, @NotNull String reason) {

        user.kickPlayer(reason);

        KickEntry kick = new KickEntry(user.getUniqueId(),
                source,
                System.currentTimeMillis(), // kick ends as soon at its created
                reason);

        archivedEntries.add(kick);

        return kick;
    }

    public MuteEntry mute(@NotNull OfflinePlayer player, @NotNull UUID source) {

        return mute(player, source, defaultReason, -1);

    }

    public MuteEntry mute(@NotNull OfflinePlayer player, @NotNull UUID source, long until) {

        return mute(player, source, defaultReason, until);

    }


    public MuteEntry mute(@NotNull OfflinePlayer player, @NotNull UUID source, String reason) {

        return mute(player, source, reason, -1);

    }

    public MuteEntry mute(@NotNull OfflinePlayer player, UUID source, String reason, long until) {

        return mute(player.getUniqueId(), source, reason, until);

    }

    /**
     * Ban a UUID from the server
     *
     * @param toMute the uuid to ban
     * @param source the uuid which executed the ban
     * @param reason the reason of the ban
     * @param until  when does the ban expire (set to -1 for permanent)
     * @return the resulting ban entry
     */
    public MuteEntry mute(UUID toMute, UUID source, String reason, long until) {

        MuteEntry oldBan = resolveUser(toMute, MuteEntry.class);

        // ban already exists -> revoke it
        if (oldBan != null) {

            revoke(oldBan);

        }

        MuteEntry newMute = new MuteEntry(toMute, source, until, reason);

        activeEntries.add(newMute);

        return newMute;

    }

    public <T extends PunishmentEntry> List<T> resolveActive(Class<T> clazz) {

        List<T> entries = new ArrayList<T>();

        for (PunishmentEntry activeEntry : activeEntries) {

            if (clazz.isInstance(activeEntry))
                entries.add((T) activeEntry);

        }

        return entries;

    }

    public BanEntry resolveBanUser(UUID uuid) {

        for (BanEntry activeBan : resolveActive(BanEntry.class)) {

            if (activeBan.getUser().equals(uuid))
                return activeBan;

        }

        return null;

    }

    public BanEntry resolveBanId(UUID uuid) {

        for (BanEntry activeBan : resolveActive(BanEntry.class)) {

            if (activeBan.getId() == uuid)
                return activeBan;

        }

        return null;

    }

    public <T extends PunishmentEntry> T resolveUser(UUID uuid, Class<T> clazz) {

        for (T activeBan : resolveActive(clazz)) {

            if (activeBan.getUser().equals(uuid))
                return activeBan;

        }

        return null;
    }

    public List<PunishmentEntry> resolveUserArchived(UUID uuid) {

        List<PunishmentEntry> result = new ArrayList<>();

        for (PunishmentEntry archivedEntry : archivedEntries) {
            if (archivedEntry.getUser().equals(uuid))
                result.add(archivedEntry);
        }

        return result;

    }

    public List<PunishmentEntry> resolveUserActive(UUID uuid) {

        List<PunishmentEntry> result = new ArrayList<>();

        for (PunishmentEntry archivedEntry : activeEntries) {
            if (archivedEntry.getUser().equals(uuid))
                result.add(archivedEntry);
        }

        return result;

    }

    public void revoke(PunishmentEntry entry) {

        for (PunishmentEntry activeBan : activeEntries) {

            if (activeBan.getId().equals(entry.getId())) {

                activeEntries.remove(activeBan);
                archivedEntries.add(activeBan);
                return;

            }

        }

    }

    /**
     * Will check for any expired entries
     */
    public void checkExpiry() {

        for (PunishmentEntry activeEntry : activeEntries) {

            if (activeEntry.isExpired()) {

                revoke(activeEntry);

            }

        }

    }

    public boolean checkExpiry(PunishmentEntry entry) {

        if (entry.isExpired()) {

            revoke(entry);
            return false;

        }

        return true;

    }

}
