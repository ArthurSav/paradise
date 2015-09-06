package io.c0nnector.github.paradise.api.model.misc;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Startup filters
 *
 * P.S we made it parcelable in order to pass it with prettybundle
 */
@Parcel
public enum StartupFilter {

    TRENDING("trending"),

    NEW("new"),

    HIRING("hiring"),

    RAISING("raising");


    final String filter;

    @ParcelConstructor
    StartupFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return filter;
    }
}
