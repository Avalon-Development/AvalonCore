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

    public final UUID banner;
    public final long until;
    public final String reason;

    public BanEntry(UUID user, UUID banner, long until, String reason) {
        super(user);
        this.banner = banner;
        this.until = until;
        this.reason = reason;
    }

    public void revoke() {

        Punishments.revoke(this);

    }

    @Override
    public boolean isExpired() {
        if(until < 0)
            return false;

        return System.currentTimeMillis() - timestamp >= until;
    }
}
