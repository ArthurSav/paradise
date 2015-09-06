package io.c0nnector.github.paradise.ui.screenshot;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.f2prateek.dart.InjectExtra;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.ui.BaseActivity;
import io.c0nnector.github.paradise.ui.startup.photos.FragmentStartupGalleryImage;
import io.c0nnector.github.paradise.ui.views.ScreenshotViewPager;
import io.c0nnector.github.paradise.util.Intents;
import io.c0nnector.github.paradise.util.Val;

public class ActivityScreenshot extends BaseActivity {

    @Bind(R.id.vPager)
    ScreenshotViewPager viewPager;


    //Can't user @Extra for arraylist, so we need retrieve them the old fashioned way
    ArrayList<Screenshot> screenshots;

    @InjectExtra
    Integer position;



    @OnClick(R.id.vWrapper)
    public void onBtnClose(){
        supportFinishAfterTransition();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        postponeEnterTransition();
    }

    @Override
    protected void validate(){

        setScreenshotsFromExtras();

        if (Val.isNull(screenshots) ||  Val.isNull(position)) throw new RuntimeException("You need to supply a valid screenshot list");
    }

    @Override
    protected void afterViews() {
        super.afterViews();

        setupAdapter();
    }


    /**
     * Will try to retrieve passed screenshots
     */
    private void setScreenshotsFromExtras(){
        this.screenshots = getIntent().getExtras().getParcelableArrayList("screenshots");
    }

    /**
     * Screenshot adapter
     */
    private void setupAdapter(){

        ScreenshotAdapter adapter = new ScreenshotAdapter(getSupportFragmentManager(), screenshots);
        viewPager.setAdapter(adapter);

        //select the position clicked
        viewPager.setCurrentItem(position);
    }

    /*****************************************************
     * ---------------- * Intents * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Start activity
     *
     * @param context
     * @param screenshots a list of screenshots to show
     * @param position preselected position for the viewpager
     */
    public static void start(Context context, ArrayList<Screenshot> screenshots, int position) {

        Intents.with(context, ActivityScreenshot.class)
                .extra("position", position)
                .extra("screenshots", screenshots)
                .open();
    }


    /**
     * Start activity
     *
     * @param activity
     * @param screenshots a list of screenshots to show
     * @param position preselected position for the viewpager
     */
    public static void start(Activity activity, ArrayList<Screenshot> screenshots, int position, View sharedView) {

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView, FragmentStartupGalleryImage.TRANSITION_NAME_SCREENSHOT+position);

        Intents.with(activity, ActivityScreenshot.class)
                .extra("position", position)
                .extra("screenshots", screenshots)
                .open(options.toBundle());
    }
}
