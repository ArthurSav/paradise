package io.c0nnector.github.paradise.util;

import android.annotation.SuppressLint;
import android.app.Activity;

import io.c0nnector.github.paradise.R;

/**
 * Utility to animate activity transitions
 */
public class UtilTransitions {


    /*****************************************************
     * ---------------- * Fade * --------------------
     *
     *
     *
     ****************************************************/

    @SuppressLint("PrivateResource")
    public static void setFadeEnterAnimation(Activity activity){
        activity. overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @SuppressLint("PrivateResource")
    public static void setFadeExitAnimation(Activity activity){
        activity. overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
