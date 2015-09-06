package io.c0nnector.github.paradise.cache;

import au.com.gridstone.grex.GRexAndroidPersister;

/**
 * Extend this in your cache objects
 */
public abstract class BaseCache {

    /**
     * Cache helper with rxjava support
     */
    GRexAndroidPersister persister;

    public BaseCache(GRexAndroidPersister persister) {
        this.persister = persister;
    }

    /**
     * Key that will be used to store our object
     * @return
     */
    public abstract String getKey();
}
