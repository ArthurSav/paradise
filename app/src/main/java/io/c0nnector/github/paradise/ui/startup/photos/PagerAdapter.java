package io.c0nnector.github.paradise.ui.startup.photos;

import android.support.v4.app.FragmentManager;

import java.util.List;

import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.ui.screenshot.ScreenshotAdapter;


public class PagerAdapter extends ScreenshotAdapter {


    public PagerAdapter(FragmentManager fm, List<Screenshot> list) {
        super(fm, list);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.4f;
    }
}
