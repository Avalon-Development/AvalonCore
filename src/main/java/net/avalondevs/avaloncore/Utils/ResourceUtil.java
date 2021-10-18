package net.avalondevs.avaloncore.Utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;

/**
 * Class for dynamically loading libraries at runtime
 */
@UtilityClass
public class ResourceUtil {

    public final String LIB_FOLDER = "libs";
    public Plugin parent;

    @SneakyThrows
    public static void init() {
        Thread.currentThread().setContextClassLoader(new JarInJarClassLoader(new URL[0], Thread.currentThread().getContextClassLoader()));
    }

    private String getFileName(String pathName) {

        int lastSlash = pathName.lastIndexOf('/');

        if(lastSlash <= 0)
            return pathName;

        return pathName.substring(lastSlash);

    }

    @SneakyThrows
    public void downloadLib(String url) {

        URL uri = new URL(url);
        String name = getFileName(uri.getFile());

        File file = new File(parent.getDataFolder(), LIB_FOLDER + "/" + name);
        if(file.exists()) {
            loadLib(file);
            return;
        }

        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();

        InputStream stream = connection.getInputStream();

        parent.getLogger().log(Level.INFO, "Downloading " + name + " from " + url);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        parent.getLogger().log(Level.INFO, "Done.");

        FileUtil.ensureFile(file);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(buffer.toByteArray());
        }

        parent.getLogger().log(Level.INFO, "Loading " + name);

        loadLib(file);

    }

    @SneakyThrows
    public void loadLib(File file) {
        JarInJarClassLoader classLoader = (JarInJarClassLoader) Thread.currentThread().getContextClassLoader();

        URL uri = file.toURI().toURL();

        classLoader.addURL(uri);

        parent.getLogger().log(Level.INFO, "Successfully injected library: " + file.getName());
    }

}
