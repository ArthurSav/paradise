package io.c0nnector.github.paradise.application;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import io.c0nnector.github.paradise.BuildConfig;
import io.c0nnector.github.paradise.modules.Modules;
import timber.log.Timber;

/**
 * Application class
 */
public class Application extends DaggerApplication {

    private static Bus bus;


    @Override
    public void onCreate() {
        super.onCreate();

        setupLogging();

        //otto
        initBus();

        //dagger
        setupDagger(Modules.list(this));
    }



    /*****************************************************
     * ---------------- * Logging * --------------------
     *
     *
     *
     ****************************************************/

    private void setupLogging(){

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    /*****************************************************
     * ---------------- * Dagger * --------------------
     *
     *
     *
     ****************************************************/

    public static void inject(Activity activity) {
        get(activity).getObjectGraph().inject(activity);
    }

    public static void inject(Fragment fragment){
        get(fragment.getActivity()).getObjectGraph().inject(fragment);
    }

    /*****************************************************
     * ---------------- * Bus * --------------------
     *
     *
     *
     ****************************************************/

    private void initBus(){
        bus = new Bus(ThreadEnforcer.ANY);
    }

    public static Bus getBus(){
        return bus;
    }

    public static void post(Object object){
        bus.post(object);
    }


    /*****************************************************
     * ---------------- * Helpers * --------------------
     *
     *
     *
     ****************************************************/

    public static Application get(Context context){
        return (Application) context.getApplicationContext();
    }
}
