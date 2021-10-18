package net.avalondevs.avaloncore.Utils;

import lombok.experimental.UtilityClass;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class FileUtil {

    public File ensureFile(File file) throws IOException {

        if(file.exists())
            return file;

        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        file.createNewFile();

        return file;

    }

    public FileConfiguration getConfig(Plugin plugin, String path) throws IOException {

        File file = new File(plugin.getDataFolder(), path);

        FileConfiguration config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return config;

    }

    public String getName(File file) {

        String name = file.getName();

        if(name.lastIndexOf('.') == 0 || name.lastIndexOf('.') < 0)
            return name;

        name = name.substring(0, name.lastIndexOf('.'));

        return name;

    }

}
