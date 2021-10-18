package net.avalondevs.avaloncore.Utils.dataprovider;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Template classing for simple databasing, and caching data manager which I use a lot
 *
 * @param <K> a key to key against
 * @param <T> the data class to infer
 */
public abstract class DataProvider<K, T> {

    @Getter
    Map<K, T> cache = new HashMap<>();

    public T get(K key) {

        T data = cache.get(key);

        if (data == null) {

            data = databaseGet(key);
            cache.put(key, data);

        }

        return data;

    }

    public abstract boolean has(K key);

    /**
     * Implementation based method, that must return T
     * if none found null must be returned
     *
     * @return T or null
     */
    public abstract T databaseGet(K key);


}
