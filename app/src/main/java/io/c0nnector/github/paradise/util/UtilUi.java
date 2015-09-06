package io.c0nnector.github.paradise.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


public class UtilUi {

    /*****************************************************
     * ---------------- * Soft keyboard * --------------------
     *
     *
     *
     ****************************************************/

    public static void hideKeyboard(Activity activity){
        if (activity !=null) {
            InputMethodManager imm = (InputMethodManager) activity. getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void showKeyBoard(Activity activity){
        if (activity !=null) {
            InputMethodManager imm = (InputMethodManager) activity. getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }
}
