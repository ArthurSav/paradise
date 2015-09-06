package io.c0nnector.github.paradise.binders;


import android.content.Context;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.Tagged;
import io.c0nnector.github.paradise.api.model.misc.TagType;
import io.c0nnector.github.paradise.holdr.Holdr_PartStartupPerson;
import io.c0nnector.github.least.Binder;

public class CompanyRoleBinder extends Binder<Holdr_PartStartupPerson, Role>{


    public CompanyRoleBinder(Context context, Class<Role> roleClass, Class<Holdr_PartStartupPerson> cls, int layoutId) {
        super(context, roleClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(Holdr_PartStartupPerson holder, Role role, int i) {

        Tagged tag = role.getTagged();

        //startup
        if (tag.isType(TagType.STARTUP)) {

            holder.imgAvatar.loadCompanyLogo(tag.getLogo_url());
            holder.txtName.setText(tag.getName());
            holder.txtBio.setText(tag.getHigh_concept());
        }

        //user
        else {

            holder.imgAvatar.loadAvatarImage(tag.getImage());
            holder.txtName.setText(tag.getName());
            holder.txtBio.setText(tag.getBio());
        }


    }

    public static CompanyRoleBinder instance(Context context){
        return new CompanyRoleBinder(context, Role.class, Holdr_PartStartupPerson.class, R.layout.part_startup_person);
    }
}
