package io.c0nnector.github.paradise.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.f2prateek.dart.Dart;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.ui.views.ToolbarBack;
import io.c0nnector.github.paradise.util.UtilApp;
import io.c0nnector.github.paradise.util.Val;

/**
 * Base class that adds default behaviour
 *
 * Functionality:
 *
 * - Dagger init
 * - Toolbar setup if not null
 * - Annotated @extras
 * - helper methods for common actions
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Initialized with onCreate(). Use it to setup your variables
     */
    protected void initVariables(){}

    /**
     * Initialized with setContentView(), after we bind our views with butterknife.
     */
    protected void afterViews(){}

    /**
     * Initialized with setContentView(). Use it to validate you have the right data.
     * E.g check for @extras or get data from cache/net etc...
     */
    protected void validate(){}

    /**
     * @return root view for activity
     */
    public View getContentView(){
        return this.findViewById(android.R.id.content);
    }


    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //dagger
        Application.inject(this);

        //inject extras
        Dart.inject(this);

        initVariables();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //butterknife
        bindViews();

        validate();

        afterViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        otto(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        otto(false);
    }

    /**
     * Bind views with butterknife
     */
    private void bindViews() {

        ButterKnife.bind(this);

        setupToolbar();
    }

    /*****************************************************
     * ---------------- * Bus * --------------------
     *
     *
     *
     ****************************************************/

    private void otto(boolean register){

        if (register) Application.getBus().register(this);

        else Application.getBus().unregister(this);
    }

    /*****************************************************
     * ---------------- * Toolbar * --------------------
     *
     *
     *
     ****************************************************/

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar(){

        if (Val.notNull(toolbar)) {

            setSupportActionBar(toolbar);

            setDefaultMenuIconBehaviour(toolbar);
        }
    }



    /**
     * Toolbar navigation icon behaviour
     * @param toolbar
     */
    private void setDefaultMenuIconBehaviour(final Toolbar toolbar){

        if (toolbar instanceof ToolbarBack) {

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //close current activity
                    supportFinishAfterTransition();
                }
            });
        }
    }

    /**
     * Toolbar title
     * @param title
     */
    protected void setToolbarTitle(String title){

        if (Val.notNull(toolbar)) {
            getToolbar().setTitle(title);
        }

        setTitle(title);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    /*****************************************************
     * ---------------- * Api support * --------------------
     *
     *
     *
     * These methods will work with api 21 and greater.
     * Lower versions will be ignored
     ****************************************************/


    @Override
    public void postponeEnterTransition() {

        //delay transition until we add the leastview items
        if (UtilApp.isLollipopAndGreater()) {
            super.postponeEnterTransition();
        }
    }

    @Override
    public void startPostponedEnterTransition() {

        if (UtilApp.isLollipopAndGreater()) {
            super.startPostponedEnterTransition();
        }
    }
}
