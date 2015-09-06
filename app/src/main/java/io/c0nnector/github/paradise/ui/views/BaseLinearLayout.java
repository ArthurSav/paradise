package io.c0nnector.github.paradise.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Adds butterknife functionality
 */
public class BaseLinearLayout extends LinearLayout {

    public BaseLinearLayout(Context context) {

        super(context);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //inject butterknife
        ButterKnife.bind(this);
    }
}
