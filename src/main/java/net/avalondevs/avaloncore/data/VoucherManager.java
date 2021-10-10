package net.avalondevs.avaloncore.data;

import lombok.Getter;
import lombok.Setter;
import net.avalondevs.avaloncore.Main;
import net.avalondevs.avaloncore.Utils.ConfigUtil;
import net.avalondevs.avaloncore.Utils.dataprovider.DataProvider;
import org.bukkit.configuration.Configuration;

import java.io.File;
import java.io.IOException;

public class VoucherManager extends DataProvider<String, Voucher> {

    @Getter
    public static VoucherManager instance = new VoucherManager();

    @Override
    public boolean has(String key) {
        return false;
    }

    public void add(String key, Voucher v) {

        this.getCache().put(key, v); // force cache add

    }

    public void remove(String key) {

        this.getCache().remove(key); // force cache remove

    }

    @Override
    public Voucher databaseGet(String key) {
        return null;
    }

    /**
     * Will mirror the contents of the {@link #getCache()} into config files
     */
    public void write() {

        File configFolder = new File(Main.getInstance().getDataFolder(), "tags");

        configFolder.mkdirs();

        getCache().forEach((key, voucher) -> {

            File voucherFile = new File(configFolder, voucher.getName() + ".yml");

            try {
                voucherFile.createNewFile();

                Configuration configuration = ConfigUtil.ensureConfig(voucherFile); // load the config for the voucher



            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
