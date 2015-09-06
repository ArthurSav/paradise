package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import io.c0nnector.github.paradise.util.UtilMeasure;

/**
 * Viewpager for screenshots
 */
public class ScreenshotViewPager extends ViewPager {

    public ScreenshotViewPager(Context context) {
        super(context);
    }

    public ScreenshotViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        init();
    }


    public void init(){

        //default margin
        setPageMargin(UtilMeasure.dpToPx(10));
    }
}
