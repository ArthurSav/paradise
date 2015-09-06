package io.c0nnector.github.paradise.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * An easier way to build intents
 */
public class Intents {

    /**
     * Default code for opening activity with openActivityForResult()
     */
    private static int DEFAULT_REQUEST_CODE = 544;

    @NonNull
    Context context;

    @NonNull
    Intent intent;

    Bundle bundle = new Bundle();

    /**
     * Constructor
     * @param activity
     * @param gotoClass
     */
    private Intents(Activity activity, Class gotoClass) {
        this.context = activity;
        this.intent = new Intent(activity, gotoClass);
    }

    private Intents(Context context, Class gotoClass) {
        this.context = context;
        this.intent = new Intent(this.context, gotoClass);

        //required for intent started by non activity context
        this.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * Instance creator
     *
     * @param activity
     * @param gotoClass
     * @return
     */
    public static Intents with(Activity activity, Class gotoClass){
        return new Intents(activity, gotoClass);
    }

    public static Intents with(Context context, Class gotoClass){
        return new Intents(context, gotoClass);
    }

    /**
     * Open activity
     */
    public void open(){

        intent.putExtras(bundle);

        getContext().startActivity(intent);
    }

    /**
     * Open activity
     *
     * @param options bundle with extras
     */
    public void open(Bundle options){

        intent.putExtras(bundle);

        getContext().startActivity(intent, options);
    }


    /**
     * Open activity for result
     * @param REQUEST_CODE
     */
    public void openForResult(Activity activity, int REQUEST_CODE){

        intent.putExtras(bundle);

        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public void openForResult(Activity activity){
        openForResult(activity, DEFAULT_REQUEST_CODE);
    }

    /**
     * Close an activity
     * @param activity
     */
    public static void close(Activity activity) {
        activity.finish();
    }


    /*****************************************************
     * ---------------- * Flags * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Cleans any previously open activities before opening the new activity
     */
    public Intents cleanBackStack(){

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return this;
    }

    /*****************************************************
     * ---------------- * Setters * --------------------
     *
     *
     *
     ****************************************************/

    public Intents extra(String key, String data){
        bundle.putString(key, data);
        return this;
    }

    public Intents extra(String key, Integer data){
        bundle.putInt(key, data);
        return this;
    }

    public Intents extra(String key, Boolean data){
        bundle.putBoolean(key, data);
        return this;
    }

    public Intents extra(String key, Parcelable data){
        bundle.putParcelable(key, data);
        return this;
    }

    public Intents extra(String key, Parcelable[] data){
        bundle.putParcelableArray(key, data);
        return this;
    }

    public Intents extra(String key, ArrayList<? extends Parcelable> list){
        bundle.putParcelableArrayList(key, list);
        return this;
    }


    /**
     * Replaces the default bundle
     * @param bundle
     * @return
     */
    public Intents bundle(Bundle bundle){
        this.bundle = bundle;

        return this;
    }


    /*****************************************************
     * ---------------- * @Parcel * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Wraps @parcel objects
     * @return
     */
    public Intents extraParcel(String key, Object object){
        extra(key, Parcels.wrap(object));

        return this;
    }

    /*****************************************************
     * ---------------- * Getters * --------------------
     *
     *
     *
     ****************************************************/

    private Context getContext() {
        return context;
    }

    private Intent getIntent() {
        return intent;
    }

    private Bundle getBundle() {
        return bundle;
    }

}
