package net.avalondevs.avaloncore.punishments;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class PunishmentEntry {

    public final UUID user;
    public final UUID source;
    public final long until;
    public final String reason;
    public UUID id = UUID.randomUUID();
    public long timestamp = System.currentTimeMillis();

    public PunishmentEntry(UUID user, UUID source, long until, String reason) {
        this.user = user;
        this.source = source;
        this.until = until;
        this.reason = reason;
    }

    public PunishmentEntry(UUID id, long timestamp, UUID user, UUID source, long until, String reason) {

        this(user, source, until, reason);
        this.id = id;
        this.timestamp = timestamp;

    }

    public void revoke() {

        Punishments.revoke(this);

    }

    public boolean isExpired() {
        if (until < 0)
            return false;

        return System.currentTimeMillis() - timestamp >= until;
    }


}
