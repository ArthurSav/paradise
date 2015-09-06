package io.c0nnector.github.paradise.ui.person;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.Binder;

/**
 * Person details view binder for the recyclerview
 */
public class PersonDetailsViewBinder extends Binder<PersonDetailsViewHolder, PersonDetailsViewItem>{

    public PersonDetailsViewBinder(Class<PersonDetailsViewItem> personDetailsViewItemClass, Class<PersonDetailsViewHolder> cls, int layoutId) {
        super(personDetailsViewItemClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(PersonDetailsViewHolder holder, PersonDetailsViewItem item, int i) {

        if (Val.notNull(item.role))holder.personDetailsView.bind(item.role);

        else if (Val.notNull(item.searchItem)) holder.personDetailsView.bind(item.searchItem);
    }

    public static PersonDetailsViewBinder instance(){
        return new PersonDetailsViewBinder(PersonDetailsViewItem.class, PersonDetailsViewHolder.class, R.layout.layout_person_details);
    }
}
