package net.avalondevs.avaloncore.punishments;

import lombok.Getter;

import java.util.UUID;

@Getter
public class KickEntry extends PunishmentEntry {

    public final UUID source;
    public final String reason;

    public KickEntry(UUID user, UUID source, String reason) {
        super(user);
        this.source = source;
        this.reason = reason;
    }

    @Override
    public boolean isExpired() {
        return true;
    }
}
