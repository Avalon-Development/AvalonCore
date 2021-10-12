package net.avalondevs.avaloncore.punishments;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class PunishmentEntry {

    public final UUID id = UUID.randomUUID();
    public final long timestamp = System.currentTimeMillis();
    public final UUID user;

    public abstract boolean isExpired();

}
