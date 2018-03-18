package com.webapp.effectiveness.common.cacheutils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Cache<K, V> {
    private final Map<K, V> cache = new HashMap<>();
    private final int maxCacheSize;

    public Cache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public V create(K deliveredKey, Supplier<V> supplier) {
        if (cache.containsKey(deliveredKey)) {
            return cache.get(deliveredKey);
        }

        if ((this.cache.size() + 1) > maxCacheSize) {
            throw new CacheExceededBoundariesException();
        }

        V newInstance = supplier.get();
        this.cache.put(deliveredKey, newInstance);

        return newInstance;
    }

    public boolean isFull() {
        return this.cache.size() == maxCacheSize;
    }

    public void clear() {
        this.cache.clear();
    }
}
