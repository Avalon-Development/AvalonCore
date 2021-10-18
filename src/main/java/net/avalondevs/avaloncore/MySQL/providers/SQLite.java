package net.avalondevs.avaloncore.MySQL.providers;

import lombok.SneakyThrows;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.MySQL.Database;

import java.io.File;
import java.sql.SQLException;

public class SQLite extends Database {

    Main main = Main.getInstance();
    String databaseName = main.getConfig().getString("sqlite.file");

    @Override
    @SneakyThrows
    public void connect() throws SQLException {

        File file = new File(main.getDataFolder(), databaseName);

        if(!file.exists())
            file.createNewFile();



    }
}
