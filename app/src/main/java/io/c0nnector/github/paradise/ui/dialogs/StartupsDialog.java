package io.c0nnector.github.paradise.ui.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import java.util.List;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.binders.StartupLightBinder;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartupLight;
import io.c0nnector.github.paradise.ui.startup.ActivityStartupDetails;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;
import io.c0nnector.github.least.ListItemListener;

public class StartupsDialog {

    /**
     * Shows a dialog with startups(light)
     *
     * @param context
     * @param roles
     * @return
     */
    public static AlertDialog show(final Context context, List<Role> roles){

        LeastView leastView = (LeastView) LayoutInflater.from(context).inflate(R.layout.layout_leastview_default, null);

        //list binder
        StartupLightBinder binder = StartupLightBinder.instance();
        binder.setListItemClickListener(new ListItemListener<Holdr_ListItemStartupLight, Role>() {

            @Override
            public void onListItemClick(Holdr_ListItemStartupLight holder, Role role, int i) {
                ActivityStartupDetails.start(context, role);
            }
        });

        //list adapter
        LeastAdapter leastAdapter = new LeastAdapter.Builder()
                .binder(binder)
                .items(roles)
                .build(context);

        leastView.setAdapter(leastAdapter);

        //show dialog
        return  new AlertDialog.Builder(context)
                .setView(leastView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
