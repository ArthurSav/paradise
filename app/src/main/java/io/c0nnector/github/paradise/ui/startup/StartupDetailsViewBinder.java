package io.c0nnector.github.paradise.ui.startup;

import android.support.v4.app.FragmentManager;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.Binder;

/**
 * Recyclerview binder for the startup details view. Not really intended for multiple list entries, just one.
 */
public class StartupDetailsViewBinder extends Binder<StartupDetailsViewHolder, StartupDetailsViewItem> {

    FragmentManager fragmentManager;

    public StartupDetailsViewBinder(Class<StartupDetailsViewItem> startupViewItemClass, Class<StartupDetailsViewHolder> cls, int layoutId, FragmentManager fragmentManager) {
        super(startupViewItemClass, cls, layoutId);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onBindViewHolder(StartupDetailsViewHolder startupViewHolder, StartupDetailsViewItem item, int i) {

        if (Val.notNull(item.startup)) {
            startupViewHolder.startupDetailsView.bind(fragmentManager, item.startup);
        }

        else if (Val.notNull(item.role)) {
            startupViewHolder.startupDetailsView.bind(fragmentManager, item.role);
        }

        else if (Val.notNull(item.startupId)) {
            startupViewHolder.startupDetailsView.bind(fragmentManager, item.startupId);
        }

        else throw new RuntimeException("No arguments passed to the startup details view");
    }

    public static StartupDetailsViewBinder instance(FragmentManager fragmentManager){
        return new StartupDetailsViewBinder(StartupDetailsViewItem.class, StartupDetailsViewHolder.class, R.layout.layout_startup_details, fragmentManager);
    }

}
