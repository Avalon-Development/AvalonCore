package net.avalondevs.avaloncore.Utils;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Dynamic class loader for loading .jar files inside a .jar executed at runtime
 */
public class JarInJarClassLoader extends URLClassLoader {
    public JarInJarClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
