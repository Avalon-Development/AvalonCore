package net.avalondevs.avaloncore.punishments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class MuteEntry extends PunishmentEntry{

    public MuteEntry(UUID user, UUID source, long until, String reason) {
        super(user, source, until, reason);
    }

    public MuteEntry(UUID id, long timestamp, UUID user, UUID source, long until, String reason) {
        super(id, timestamp, user, source, until, reason);
    }
}
