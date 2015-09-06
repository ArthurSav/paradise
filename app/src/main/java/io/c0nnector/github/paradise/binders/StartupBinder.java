package io.c0nnector.github.paradise.binders;

import android.content.Context;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartup;
import io.c0nnector.github.paradise.util.UtilView;
import io.c0nnector.github.least.Binder;


public class StartupBinder extends Binder<Holdr_ListItemStartup, Startup> {


    public StartupBinder(Context context, Class<Startup> startupClass, Class<Holdr_ListItemStartup> cls, int layoutId) {
        super(context, startupClass, cls, layoutId);
    }


    @Override
    public void onBindViewHolder(Holdr_ListItemStartup holder, Startup startup, int i) {

        holder.imgLogo.loadCompanyLogo(startup.getLogo_url());

        holder.txtName.setText(startup.getName());

        holder.txtHightConcept.setText(startup.getHigh_concept());

        holder.txtFollowers.setText(startup.getFollowerCountFormatted());


        //show/hide view depending if we have the company size info
        if (UtilView.show(holder.txtCompanySize, !startup.getCompanySizeFormatted().isEmpty())) holder.txtCompanySize.setText(startup.getCompanySizeFormatted());

        if (UtilView.show(holder.txtLocation, !startup.getFirtLocationName().isEmpty())) holder.txtLocation.setText(startup.getFirtLocationName());
    }


    /**
     * Creates a new instance
     * @return
     */
    public static StartupBinder instance(Context context){
        return new StartupBinder(context, Startup.class, Holdr_ListItemStartup.class, R.layout.list_item_startup);
    }

    /**
     * Single position bind. Used for views that are not within a list
     */
    public static StartupBinder bind(Context context, Holdr_ListItemStartup holder, Startup startup){

        StartupBinder binder = instance(context);

        //set position as 0
        binder.onBindViewHolder(holder, startup, 0);

        return binder;
    }
}
