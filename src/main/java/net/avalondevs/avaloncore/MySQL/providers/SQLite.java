package net.avalondevs.avaloncore.MySQL.providers;

import lombok.SneakyThrows;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.Database;
import net.avalondevs.avaloncore.Utils.FileUtil;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite extends Database {

    /**
     * Transform platform related syntax like AUTOINCREMENT to platform syntax
     *
     * @param query the query to transform
     * @return the transformed query
     */
    @Override
    public String translate(String query) {
        return query.replace("AUTO_INCREMENT", "AUTOINCREMENT");
    }

    @Override
    @SneakyThrows
    public void connect() throws SQLException {

        Class.forName("org.sqlite.JDBC").newInstance(); // create sqlite driver | This will execute the static {} body in the class which inits the driver

        File file = new File(getParent().getDataFolder(), getParent().getConfig().getString("sqlite.file"));

        FileUtil.ensureFile(file);

        setConnection(DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath()));


    }
}
