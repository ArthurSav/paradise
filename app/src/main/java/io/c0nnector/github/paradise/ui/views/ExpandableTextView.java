package io.c0nnector.github.paradise.ui.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Animate textview expansion(max lines)
 */
public class ExpandableTextView extends TextView {

    private static final int DEFAULT_MAX_LINES_COLLAPSE = 3;

    private static final int DEFAULT_MAX_LINES_EXPAND = 50;


    public ExpandableTextView(Context context) {

        super(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    /**
     * Will animate the max lines allowed in the textview
     * @param lines
     */
    private void animateMaxLines(int lines){
        ObjectAnimator animation = ObjectAnimator.ofInt(this, "maxLines", lines);
        animation.setDuration(200).start();
    }

    public void expand(){
        expand(DEFAULT_MAX_LINES_EXPAND);
    }

    public void expand(int lines){
        animateMaxLines(lines);
    }

    public void collapse(){
        collapse(DEFAULT_MAX_LINES_COLLAPSE);
    }

    public void collapse(int lines){
        animateMaxLines(lines);
    }


    /**
     * Toggles argument values.
     * E.g if the lines currently set are maxLinesExpand, then we set the lines to maxLinesCollapse
     * @param maxLinesExpand
     * @param maxLinesCollapse
     */
    public void toggle(int maxLinesExpand, int maxLinesCollapse){

        if (this.getMaxLines() == maxLinesExpand) {
            collapse(maxLinesCollapse);
        }

        else if (this.getMaxLines() == maxLinesCollapse) {
            collapse(maxLinesExpand);
        }
    }
}
