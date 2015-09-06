package io.c0nnector.github.paradise.ui.startup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.ui.views.BaseRelativeLayout;
import io.c0nnector.github.paradise.ui.views.ExpandableTextView;
import io.c0nnector.github.paradise.util.Strings;
import io.c0nnector.github.paradise.util.UtilView;

/**
 * Startup description view
 */
public class StartupDescriptionView extends BaseRelativeLayout {

    private static final int MAX_EXPAND = 60;

    private static final int MAX_COLLAPSE = 3;



    @Bind(R.id.txtDescription)
    ExpandableTextView txtDescription;

    @Bind(R.id.btnReaMore)
    TextView btnReadMore;


    public StartupDescriptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(String description){

        if (UtilView.show(this, Strings.isNotBlank(description))) {

            validateOnEllipsized();

            txtDescription.setText(description);
        }
    }

    /**
     * Toggles lines
     */
    public void toggle(){

        //animate max line change

        int maxLines = txtDescription.getMaxLines();

        if (maxLines == MAX_EXPAND) btnReadMore.setText("show more");

        else btnReadMore.setText("show less");

        txtDescription.toggle(MAX_EXPAND, MAX_COLLAPSE);
    }

    /**
     * Decides when to show the 'show more' button
     */
    private void validateOnEllipsized(){

        ViewTreeObserver vto = txtDescription.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                UtilView.show(txtDescription, UtilView.isTextEllipsized(txtDescription));

                txtDescription.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


}
