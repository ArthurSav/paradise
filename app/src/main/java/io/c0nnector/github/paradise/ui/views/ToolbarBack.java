package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import io.c0nnector.github.paradise.R;

/**
 * Toolbar with back button
 */
public class ToolbarBack extends Toolbar{


    public ToolbarBack(Context context) {

        super(context);
    }

    public ToolbarBack(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public ToolbarBack(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (!isInEditMode()) {

            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }
    }
}
