package io.c0nnector.github.paradise.ui.startup;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.dart.InjectExtra;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.bus.ScreenshotSelectedEvent;
import io.c0nnector.github.paradise.ui.BaseActivity;
import io.c0nnector.github.paradise.ui.screenshot.ActivityScreenshot;
import io.c0nnector.github.paradise.util.Intents;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;

public class ActivityStartupDetails extends BaseActivity {

    /**
     * We could use a NestedScrollview to display the startup details but at this point is pretty
     * buggy. So we show a single item in a recyclerview
     */
    @Bind(R.id.recyclerView)
    LeastView leastView;


    @Nullable
    @InjectExtra
    Startup startup;

    @Nullable
    @InjectExtra
    Role role;

    @Nullable
    @InjectExtra
    Integer startupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startup);

        //delay transition until we add the leastview items
        postponeEnterTransition();
    }


    @Override
    protected void afterViews() {
        super.afterViews();

        LeastAdapter adapter = new LeastAdapter.Builder()

                .binder(StartupDetailsViewBinder.instance(getSupportFragmentManager()))
                .item(new StartupDetailsViewItem(startup, role, startupId))
                .build(this);

        leastView.setAdapter(adapter);
        leastView.setOnHierarchyChangeListener(leastViewChangeListener);
    }


    /**
     * Called when we upgrade a partial startup object into a full one
     */
    @Subscribe
    public void onStartupObjectUpdated(final Startup startup) {
        this.startup = startup;
    }

    /**
     * Called when the user clicks a screenshot
     *
     * @param event
     */
    @Subscribe
    public void onPhotoClick(ScreenshotSelectedEvent event) {

        //open screenshot gallery
        ActivityScreenshot.start(this, (ArrayList<Screenshot>) startup.getScreenshots(), event.getPosition(), event.getImgScreenshot());
    }


    /**
     * Starts activity transition after we add the leastview items
     */
    private ViewGroup.OnHierarchyChangeListener leastViewChangeListener = new ViewGroup.OnHierarchyChangeListener() {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onChildViewAdded(View parent, View child) {

            startPostponedEnterTransition();
            leastView.setOnHierarchyChangeListener(null);
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {

        }
    };

    /*****************************************************
     * ---------------- * Intents * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Start
     *
     * @param context
     * @param startup startup to display
     */
    public static void start(Context context, Startup startup) {

        Intents.with(context, ActivityStartupDetails.class)
                .extraParcel("startup", startup)
                .open();
    }

    /**
     * Start, with shared element transition
     *
     * @param activity
     * @param startup  startup to display
     */
    public static void start(Activity activity, Startup startup, View startupProfileView) {

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, startupProfileView, "startupProfile");

        Intents.with(activity, ActivityStartupDetails.class)
                .extraParcel("startup", startup)
                .open(options.toBundle());
    }

    /**
     * Start
     *
     * @param context
     * @param role    startup object(light)
     */
    public static void start(Context context, Role role) {

        Intents.with(context, ActivityStartupDetails.class)
                .extraParcel("role", role)
                .open();
    }

    /**
     * Start
     *
     * @param context
     * @param startupId startupid to load
     */
    public static void start(Context context, Integer startupId) {

        Intents.with(context, ActivityStartupDetails.class)
                .extra("startupId", startupId)
                .open();
    }
}
