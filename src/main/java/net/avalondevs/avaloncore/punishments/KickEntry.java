package net.avalondevs.avaloncore.punishments;

import lombok.Getter;

import java.util.UUID;

@Getter
public class KickEntry extends PunishmentEntry {


    public KickEntry(UUID user, UUID source, long until, String reason) {
        super(user, source, until, reason);
    }

    public KickEntry(UUID id, long timestamp, UUID user, UUID source, long until, String reason) {
        super(id, timestamp, user, source, until, reason);
    }
}
