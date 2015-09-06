package io.c0nnector.github.paradise.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.ui.BaseActivity;
import rx.Subscription;
import rx.android.network.RxNetwork;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Network state monitor with RxJava
 */
public class UtilConnection<A extends BaseActivity> {

    private Subscription subscription;

    A activity;

    /**
     * Constructor
     * @param activity
     */
    public UtilConnection(A activity) {
        this.activity = activity;
    }

    /**
     * Monitors network changes inside and activity. Displays a message if there's no internet connection*
     */
    public UtilConnection subscribe(){

        if (Val.isNull(subscription)) {

            subscription = RxNetwork.stream(activity)
                    .subscribe(subscriber);
        }
        return this;
    }

    /**
     * Unregister subscription onDestroy() to avoid leaks
     */
    public void unsubscribe(){

        if (Val.notNull(subscription)) {

            subscription.unsubscribe();
            subscription = null;
        }
    }


    private Action1<RxNetwork.State> subscriber = new Action1<RxNetwork.State>() {

        @Override
        public void call(RxNetwork.State state) {

            Timber.e("network state: " + state.name());

            //show snackbar when not connected
            if (state == RxNetwork.State.NOT_CONNECTED) {
                showMessage(activity, "No internet connection");
            }
        }
    };


    /**
     * Shows snackbar message
     *
     * @param baseActivity
     * @param message
     */
    private static void showMessage(BaseActivity baseActivity, String message){

        Snackbar snackbar = Snackbar.make(baseActivity.getContentView(), message, Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.BLACK)
                .setAction("OK", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });

        snackbar.getView().setBackgroundResource(R.color.amber_600);
        snackbar.show();
    }
}
