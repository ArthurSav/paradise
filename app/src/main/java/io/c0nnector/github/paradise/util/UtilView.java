package io.c0nnector.github.paradise.util;

import android.graphics.Color;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transitionseverywhere.TransitionManager;

/**
 * Util to handle view states
 */
public class UtilView {

    public static void show(View v){
        if (v!=null) v.setVisibility(View.VISIBLE);
    }


    public static boolean show(View v, boolean show){
        if (show) show(v);
        else hide(v);

        return show;
    }

    public static void show(View... views){
        for (View v: views){
             show(v);
        }
    }


    public static void hide(View... views){
        for (View v: views){
             hide(v);
        }
    }

    public static void hide(View v){
        if (v!=null) v.setVisibility(View.GONE);
    }


    public static void toggleVisibility(View... views) {

        for (View view : views) {

            if (view !=null) {

                boolean isVisible = view.getVisibility() == View.VISIBLE;
                show(view, !isVisible);
            }
        }
    }


    public static void makeInvisible(View v){v.setVisibility(View.INVISIBLE);}

    public static boolean makeInvisible(View v, boolean show){
        if (show) show(v);
        else makeInvisible(v);

        return show;
    }



    public static boolean isVisible(View v){
        return  v.getVisibility() == View.VISIBLE;
    }

    public static boolean isInvisible(View v) {
        return v.getVisibility() == View.INVISIBLE;
    }

    public static boolean isNotNull(View v){
        return v !=null;
    }



    /******************************************************
     * ------------  *  animations  * ----------
     *
     *
     *
     *
     *
     *
     *
     ****************************************************/

    /**
     * Helper class to animate multiple views with the transition manager
     * @param views
     */
    public static void delayedTransition(ViewGroup... views){
        for (ViewGroup v: views) {
            if (isNotNull(v)) TransitionManager.beginDelayedTransition(v);
        }
    }


    /*****************************************************
     * ---------------- * Textview * --------------------
     *
     *
     *
     ****************************************************/

    public static boolean setText(TextView textView, String text, boolean set){
        if (set) setText(textView, text);

        return set;
    }

    public static void setText(TextView textView, String text){
        if (textView !=null) textView.setText(text);
    }

    /**
     * Will parse a color and set it as text color
     * @param color a string that can be converted into a hex color e.g #000fff
     * @param v textview
     */
    public static void setTextColor(String color, TextView v){
        v.setTextColor(Color.parseColor(color.isEmpty() ? "#000000" : color));
    }


    /**
     * Returns true if the textview is ellipsized
     * @param textView
     * @return
     */
    public static boolean isTextEllipsized(TextView textView){

        Layout textViewLayout = textView.getLayout();

        if (textViewLayout != null) {

            int lines = textViewLayout.getLineCount();

            if (lines > 0) {

                if (textViewLayout.getEllipsisCount(lines-1) > 0) return true;
            }
        }

        return false;
    }


    /*****************************************************
     * ---------------- * Layout params * --------------------
     *
     *
     *
     ****************************************************/

    public static ViewGroup.LayoutParams getLayoutParams(int width, int height){
        return new ViewGroup.LayoutParams(width, height);
    }

}
