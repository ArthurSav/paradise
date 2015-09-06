package io.c0nnector.github.paradise.ui.person;

import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.SearchItem;

/**
 * Items passed to the person detail view
 */
public class PersonDetailsViewItem {

    public Role role;

    public SearchItem searchItem;

    public PersonDetailsViewItem(Role role, SearchItem searchItem) {

        this.role = role;
        this.searchItem = searchItem;
    }
}
