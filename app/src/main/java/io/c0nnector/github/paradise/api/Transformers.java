package io.c0nnector.github.paradise.api;

import android.view.View;

import io.c0nnector.github.paradise.util.UtilView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * Custom transformers for rx observables
 */
public class Transformers {

    /**
     * Ties a view to a request lifecycle. Shows the view when the request starts and hides it when
     * the request ends.
     *
     * @param progressBar anything you want to show during the request
     * @param <T>
     *
     * @return
     */
    public static  <T> Observable.Transformer<T, T> addProgress(final View progressBar) {

        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> observable) {

                return observable

                        .observeOn(AndroidSchedulers.mainThread())

                        .doOnRequest(new Action1<Long>() {

                            @Override
                            public void call(Long aLong) {

                                UtilView.show(progressBar);
                            }
                        })
                        .doOnError(new Action1<Throwable>() {

                            @Override
                            public void call(Throwable throwable) {

                                UtilView.hide(progressBar);
                            }
                        })
                        .doOnCompleted(new Action0() {

                            @Override
                            public void call() {

                                UtilView.hide(progressBar);
                            }
                        });
            }
        };
    }




}
