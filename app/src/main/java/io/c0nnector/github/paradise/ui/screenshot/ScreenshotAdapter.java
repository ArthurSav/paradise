package io.c0nnector.github.paradise.ui.screenshot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.ui.startup.photos.FragmentStartupGalleryImage;
import io.c0nnector.github.paradise.ui.views.BaseViewPagerAdapter;


/**
 * Adapter for screenshots
 */
public class ScreenshotAdapter extends BaseViewPagerAdapter<Screenshot> {


    public ScreenshotAdapter(FragmentManager fm, List<Screenshot> list) {
        super(fm, list);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentStartupGalleryImage.newInstance(getListItem(position), position);
    }
}
