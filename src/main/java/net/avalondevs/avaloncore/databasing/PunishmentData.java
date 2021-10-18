package net.avalondevs.avaloncore.databasing;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.providers.MySQL;
import net.avalondevs.avaloncore.punishments.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class for dynamically caching the punishment entries from {@link net.avalondevs.avaloncore.punishments.Punishments}
 */
@UtilityClass
public class PunishmentData {

    public MySQL database;
    public final String schemaActive =
            "CREATE TABLE IF NOT EXISTS punishments_active(id Varchar(33)," +
                    " type ENUM('Kick', 'Ban', 'Mute')," +
                    " timestamp BIGINT," +
                    " user Varchar(33)," +
                    " source Varchar(33)," +
                    " until BIGINT," +
                    " reason Varchar(255)";
    public final String schemaArchive =
            "CREATE TABLE IF NOT EXISTS punishments_archive(id Varchar(33)," +
                    " type ENUM('Kick', 'Ban', 'Mute')," +
                    " timestamp BIGINT," +
                    " user Varchar(33)," +
                    " source Varchar(33)," +
                    " until BIGINT," +
                    " reason Varchar(255)";

    public final boolean ENABLE_ARCHIVE = false; // for big servers, this might be viable due to high archive read/write overhead

    public PreparedStatement cachedActiveWriteStatement;
    public PreparedStatement cachedArchiveWriteStatement;

    @SneakyThrows
    public void init() {

        database = Main.getSQL();

        if(!database.isConnected()) {

            Main.getInstance().getLogger().severe("Cannot severe punishment remote database");

        }else { // database is connected, read data

            database.executeStatement(schemaArchive);
            database.executeStatement(schemaActive);

            readPunishmentEntries(ENABLE_ARCHIVE);

            cachedActiveWriteStatement = database.prepareStatement(
                    "INSERT IGNORE INTO punishments_active(id, type, timestamp, user, source, until, reason)" +
                            "Values(?,?,?,?,?,?,?)"
            ); // aggressive insert into the database, ignore all other entries

            cachedActiveWriteStatement = database.prepareStatement(
                    "INSERT IGNORE INTO punishments_archived(id, type, timestamp, user, source, until, reason)" +
                            "Values(?,?,?,?,?,?,?)"
            ); // aggressive insert into the database, ignore all other entries

        }

    }

    public void save() {

        if(database.isConnected())
        writePunishmentEntries(ENABLE_ARCHIVE);

    }

    public PunishmentEntry readEntry(ResultSet set) throws SQLException {

        UUID id = UUID.fromString(set.getString("id"));
        long timestamp = set.getLong("timestamp");
        UUID user = UUID.fromString(set.getString("id"));
        UUID source = UUID.fromString(set.getString("id"));
        long until = set.getLong("until");
        String reason = set.getString("reason");

        String type = set.getString("type");

        switch(type) {

            case "Kick" -> {
                return new KickEntry(id, timestamp, user, source, until, reason);
            }
            case "Mute" -> {
                return new MuteEntry(id, timestamp, user, source, until, reason);
            }
            case "Ban" -> {
                return new BanEntry(id, timestamp, user, source, until, reason);
            }

        }

        return null;


    }

    @SneakyThrows
    public void writePunishmentEntries(boolean archive) {

        for (PunishmentEntry activeEntry : Punishments.activeEntries) {
            writePunishmentEntry(activeEntry, false);
        }

        if(archive) {

            for (PunishmentEntry archivedEntry : Punishments.archivedEntries) {
                writePunishmentEntry(archivedEntry, true);
            }

        }

    }

    public void writePunishmentEntry(PunishmentEntry entry, boolean archive) throws SQLException {

        PreparedStatement effectiveStatement = archive ? cachedArchiveWriteStatement : cachedActiveWriteStatement;

        effectiveStatement.clearParameters(); // clear previous values

        String type = entry.getClass().getSimpleName().replace("Entry", "");

        effectiveStatement = database.prepareStatement(effectiveStatement,
                entry.id.toString(),
                type,
                entry.timestamp,
                entry.user.toString(),
                entry.source.toString(),
                entry.until,
                entry.reason);

        database.executeStatement(effectiveStatement);

    }


    public void readPunishmentEntries(boolean archive) throws SQLException {


        ResultSet set = database.executeQuery("SELECT * FROM punishments_active");

        while(set.next()) {

            PunishmentEntry entry = readEntry(set);

            Punishments.activeEntries.add(entry);


        }

        if(archive) {

            set = database.executeQuery("SELECT * FROM punishments_archive");

            while(set.next()) {

                PunishmentEntry entry = readEntry(set);

                Punishments.archivedEntries.add(entry);


            }

        }

    }

}
