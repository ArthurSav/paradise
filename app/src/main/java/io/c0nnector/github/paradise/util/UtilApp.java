package io.c0nnector.github.paradise.util;

import android.os.Build;

/**
 * General helpers
 */
public class UtilApp {

    /**
     * @return true, if the running sdk is api 21 or greater
     */
    public static boolean isLollipopAndGreater(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
