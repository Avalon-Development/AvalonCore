package net.avalondevs.avaloncore.MySQL;

import net.avalondevs.avaloncore.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static net.avalondevs.avaloncore.Main.SQL;

public class SQLGetter {

    private final Main plugin;
    public SQLGetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tags (INT ID NOT NULL AUTO_INCREMENT,NAME VARCHAR(100),PREFIX VARCHAR(100), PRIMARY KEY(ID)");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTag(String name, String prefix) {
        PreparedStatement ps;
        try {
            ps = SQL.getConnection().prepareStatement("INSERT INTO tags (NAME,PREFIX)");
            ps.setString(1,name);
            ps.setString(2,prefix);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listTags() {
        PreparedStatement ps;
        try {
            ps = SQL.getConnection().prepareStatement("SELECT * FROM tags");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTag() {

    }
}
