package io.c0nnector.github.paradise.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.OnClick;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.bus.SearchViewCloseEvent;
import io.c0nnector.github.paradise.ui.BaseActivity;
import io.c0nnector.github.paradise.util.Intents;
import io.c0nnector.github.paradise.util.UtilUi;

public class ActivitySearch extends BaseActivity {

    @Bind(R.id.vSearchContainer)
    LinearLayout vContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UtilUi.showKeyBoard(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        UtilUi.hideKeyboard(this);
    }

    /**
     * Click outside the listview will close the activity
     */
    @OnClick(R.id.vContainer)
    public void onContainerClick(){
        supportFinishAfterTransition();
    }

    /**
     * We get an event when the textview is empty and the user presses 'close'
     * @param event
     */
    @Subscribe
    public void onCloseSearch(SearchViewCloseEvent event){
        supportFinishAfterTransition();
    }


    /*****************************************************
     * ---------------- * Intents * --------------------
     *
     *
     *
     ****************************************************/

    public static void start(Activity activity) {
        Intents.with(activity, ActivitySearch.class)
                .open();
    }
}
