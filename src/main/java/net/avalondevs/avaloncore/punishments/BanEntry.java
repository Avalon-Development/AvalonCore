package net.avalondevs.avaloncore.punishments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * These ban entries are like the ones in the Bukkit implementation except that they operate via UUIDs instead of names
 */
@Getter
@Setter
@ToString
public class BanEntry extends PunishmentEntry {


    public BanEntry(UUID user, UUID source, long until, String reason) {
        super(user, source, until, reason);
    }

    public BanEntry(UUID id, long timestamp, UUID user, UUID source, long until, String reason) {
        super(id, timestamp, user, source, until, reason);
    }
}
