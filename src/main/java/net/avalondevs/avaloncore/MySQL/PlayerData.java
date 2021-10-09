package net.avalondevs.avaloncore.MySQL;

import net.avalondevs.avaloncore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static net.avalondevs.avaloncore.Main.SQL;

public class PlayerData {

    private final Main plugin;
    public PlayerData(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players (INT ID NOT NULL AUTO_INCREMENT,NAME VARCHAR(100),UUID VARCHAR(100),IP VARCHAR(100),LASTSEEN VARCHAR(100),PLAYTIME INT(100),FREEZED BOOLEAN,BANNED BOOLEAN,MUTED BOOLEAN,IPBANNED BOOLEAN,FREEZED BOOLEAN,PRIMARY KEY (ID))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(UUID uuid, Player player) {
        try {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            if (!playerExist(uuid)) {
                PreparedStatement ps2 = SQL.getConnection().prepareStatement("INSERT IGNORE INTO players (NAME,UUID,LASTSEEN,PLAYTIME,DEATH,WHITELISTED) VALUES (?,?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setString(3, format.format(now));
                ps2.setInt(4, 0);
                ps2.setInt(5, 0);
                ps2.setBoolean(6, false);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExist(UUID uuid) {
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void playerUpdate(UUID uuid, Player player) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("UPDATE players SET LASTSEEN=?, IP=? WHERE UUID=?");
            ps.setString(1, format.format(now));
            ps.setString(2, Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress());
            ps.setString(3, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String playerLastSeenGetter(UUID uuid) {
        String s = "NaN";

        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                s = String.valueOf(results.getString("LASTSEEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Long TotalTimePlayedGetter(UUID uuid) {
        long l = 0;

        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                l = results.getLong("PLAYTIME");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void TotalTimePlayedSetter(UUID uuid, long playtime) {
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("UPDATE players SET PLAYTIME=? WHERE UUID=?");
            ps.setLong(1, playtime);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TotalTimePlayedUpdate(UUID uuid) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String loggedOutAt = formatter.format(new Date());

        try {
            Date d1 = formatter.parse(playerLastSeenGetter(uuid));
            Date d2 = formatter.parse(loggedOutAt);

            long diff = d2.getTime() - d1.getTime();                      // in milliseconds
            long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);     // in seconds

            TotalTimePlayedSetter(uuid, TotalTimePlayedGetter(uuid) + diffSeconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public Boolean isFreezeGetter(UUID uuid) {
        boolean b = false;
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("VANISHED");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void isFreezeSetter(UUID uuid, boolean b) {
        PreparedStatement ps;
        try {
            ps = SQL.getConnection().prepareStatement("UPDATE players SET VANISHED=? WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.setBoolean(2,b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isBannedGetter(UUID uuid) {
        boolean b = false;
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("VANISHED");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }


    public Boolean isMutedGetter(UUID uuid) {
        boolean b = false;
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("VANISHED");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }



    public Boolean isIpBannedGetter(UUID uuid) {
        boolean b = false;
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("VANISHED");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }


    public Boolean isIpMutedGetter(UUID uuid) {
        boolean b = false;
        try {
            PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("VANISHED");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}
