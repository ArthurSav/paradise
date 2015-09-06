package io.c0nnector.github.paradise.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Util class to measure stuff
 */
public class UtilMeasure {

    private static int screenWidth = 0;

    private static int screenHeight = 0;

    /*****************************************************
     * ---------------- * Pixels * --------------------
     *
     *
     *
     ****************************************************/

    public static int dpToPx(int dp) {

        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /*****************************************************
     * ---------------- * screen * --------------------
     *
     *
     *
     ****************************************************/

    public static int getScreenHeight(Context c) {

        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {

        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }
}
