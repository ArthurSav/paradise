package io.c0nnector.github.paradise.cache;


import java.util.List;

import au.com.gridstone.grex.GRexAndroidPersister;
import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.api.model.results.Startup;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Cache helper for startup objects
 * @see io.c0nnector.github.paradise.api.model.results.Startup
 */
public class StartupCache extends BaseCache {

    StartupFilter filter;

    public StartupCache(GRexAndroidPersister persister, StartupFilter filter) {
        super(persister);
        this.filter = filter;
    }

    @Override
    public String getKey() {
        return "startup_" + filter.toString();
    }

    /**
     * Observable that tries to retrieve a cached object
     * @return
     */
    public Observable<List<Startup>> get(){

        return persister.getList(getKey(), Startup.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Observable that caches an object
     * @param startups
     * @return
     */
    public Observable<List<Startup>> put(List<Startup> startups){
        return persister.putList(getKey(), startups, Startup.class);
    }
}
