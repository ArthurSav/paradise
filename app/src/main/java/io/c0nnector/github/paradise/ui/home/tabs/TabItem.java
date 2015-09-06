package io.c0nnector.github.paradise.ui.home.tabs;

import io.c0nnector.github.paradise.api.model.misc.StartupFilter;

/**
 * Created by arthur on 7/31/15.
 */
public class TabItem {

    String name;

    StartupFilter filter;

    public TabItem(String name, StartupFilter filter) {

        this.name = name;
        this.filter = filter;
    }

    public String getName() {

        return name;
    }

    public StartupFilter getFilter() {

        return filter;
    }
}
