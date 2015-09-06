package io.c0nnector.github.paradise.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.ui.BaseActivity;
import io.c0nnector.github.paradise.ui.dialogs.FeedbackDialog;
import io.c0nnector.github.paradise.ui.search.ActivitySearch;
import io.c0nnector.github.paradise.util.Intents;
import io.c0nnector.github.paradise.util.TokenCheck;
import io.c0nnector.github.paradise.util.UtilConnection;

public class MainActivity extends BaseActivity {

    UtilConnection connection;


    @Inject
    Application application;

    @Bind(R.id.mainView)
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_tabs);

        mainView.bind(getSupportFragmentManager());
    }

    @Override
    protected void validate() {
        super.validate();

        //check api token is defined
        TokenCheck.validateApiToken(this);
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        connection = new UtilConnection(this)
                .subscribe();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connection.unsubscribe();
    }

    /*****************************************************
     * ---------------- * Toolbar * --------------------
     *
     *
     *
     ****************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {

            ActivitySearch.start(this);
            return true;
        }

        else if (id == R.id.action_feedback) {
            FeedbackDialog.show(this);
        }

        return super.onOptionsItemSelected(item);
    }


    /*****************************************************
     * ---------------- * Intents * --------------------
     *
     *
     *
     ****************************************************/

    public static void start(Activity activity) {

        Intents.with(activity, MainActivity.class)
                .cleanBackStack()
                .open();
    }

}

