package io.c0nnector.github.paradise.ui.person;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.f2prateek.dart.InjectExtra;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.SearchItem;
import io.c0nnector.github.paradise.ui.BaseActivity;
import io.c0nnector.github.paradise.util.Intents;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;

public class ActivityPersonDetails extends BaseActivity {

    @Nullable
    @InjectExtra
    Role role;

    @Nullable
    @InjectExtra
    SearchItem searchItem;


    @Bind(R.id.recyclerView)
    LeastView leastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_person);

    }


    @Override
    protected void afterViews() {
        super.afterViews();

        setupTitle();

        LeastAdapter adapter = new LeastAdapter.Builder()
                .binder(PersonDetailsViewBinder.instance())
                .item(new PersonDetailsViewItem(role, searchItem))
                .build(this);

        leastView.setAdapter(adapter);
    }

    /**
     * Toolbar title
     */
    private void setupTitle(){
        if (Val.notNull(role)) setToolbarTitle(role.getTagged().getName());
        else if (Val.notNull(searchItem)) setToolbarTitle(searchItem.getName());
    }


    /*****************************************************
     * ---------------- * Intents * --------------------
     *
     *
     *
     ****************************************************/

    public static void start(Context context, Role role) {

        Intents.with(context, ActivityPersonDetails.class)
                .extraParcel("role", role)
                .open();
    }


    public static void start(Context context, SearchItem searchItem) {

        Intents.with(context, ActivityPersonDetails.class)
                .extraParcel("searchItem", searchItem)
                .open();
    }

}
