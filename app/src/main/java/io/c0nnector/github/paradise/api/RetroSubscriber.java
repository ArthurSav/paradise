package io.c0nnector.github.paradise.api;

import retrofit.RetrofitError;
import rx.Subscriber;
import timber.log.Timber;

/**
 *  Custom retrofit subscriber.
 *  Use it to subscribe all your retrofit calls
 */
public abstract class RetroSubscriber<T> extends Subscriber<T>{


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        handleRetrofitError(e);
    }

    @Override
    public void onNext(T t) {

    }


    /**
     * Converts the exception into a retrofit error
     * @param e
     */
    private void handleRetrofitError(Throwable e){

        if (e instanceof RetrofitError) {

            RetrofitError error = (RetrofitError) e;

            Timber.e("retrofit error: " + error.getMessage());
        }
    }
}
