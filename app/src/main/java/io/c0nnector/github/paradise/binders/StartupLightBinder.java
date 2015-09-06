package io.c0nnector.github.paradise.binders;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartupLight;
import io.c0nnector.github.least.Binder;

/**
 * A light version of a startup list item. Show only logo, name and short description
 */
public class StartupLightBinder extends Binder<Holdr_ListItemStartupLight, Role>{

    public StartupLightBinder(Class<Role> roleClass, Class<Holdr_ListItemStartupLight> cls, int layoutId) {
        super(roleClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(Holdr_ListItemStartupLight holder, Role role, int i) {

        Startup startup = role.getStartup();

        holder.imgLogo.loadCompanyLogo(startup.getLogo_url());
        holder.txtName.setText(startup.getName());
        holder.txtDescription.setText(startup.getHigh_concept());
    }

    public static StartupLightBinder instance(){
        return new StartupLightBinder(Role.class, Holdr_ListItemStartupLight.class, R.layout.list_item_startup_light);
    }
}
