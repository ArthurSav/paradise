package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Toolbar with back button
 */
public class ToolbarMain extends Toolbar {

    public ToolbarMain(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (!isInEditMode()) {
            //do something in the main toolbar
        }

    }
}
