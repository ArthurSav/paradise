package io.c0nnector.github.paradise.util;


import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.ui.BaseActivity;

public class TokenCheck {

    /**
     * If no api token is defined we show a snackbar alerting the user
     * @param activity
     * @param <T>
     */
    public static <T extends BaseActivity>void validateApiToken(T activity){

        Resources resources = activity.getResources();
        String token = resources.getString(R.string.token_angelist_api);

        if (Strings.isBlank(token)) {

            //show error message
            Snackbar snackbar = Snackbar.make(activity.getContentView(), resources.getString(R.string.message_error_no_api_token), Snackbar.LENGTH_INDEFINITE);

            snackbar.getView().setBackgroundColor(Color.RED);

            snackbar.setAction("OK", new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });

            snackbar.show();
        }
    }
}
