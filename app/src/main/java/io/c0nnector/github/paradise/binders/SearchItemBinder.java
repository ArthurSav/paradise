package io.c0nnector.github.paradise.binders;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.SearchItem;
import io.c0nnector.github.paradise.holdr.Holdr_PartStartupPerson;
import io.c0nnector.github.least.Binder;


public class SearchItemBinder extends Binder<Holdr_PartStartupPerson, SearchItem> {

    public SearchItemBinder(Class<SearchItem> searchItemClass, Class<Holdr_PartStartupPerson> cls, int layoutId) {
        super(searchItemClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(Holdr_PartStartupPerson holder, SearchItem searchItem, int i) {

        holder.txtName.setText(searchItem.getName());

        if (searchItem.isTypeStartup()) holder.imgAvatar.loadCompanyLogo(searchItem.getPic());

        else holder.imgAvatar.loadAvatarImage(searchItem.getPic());
    }

    public static SearchItemBinder instance(){
        return new SearchItemBinder(SearchItem.class, Holdr_PartStartupPerson.class, R.layout.part_startup_person);
    }
}
