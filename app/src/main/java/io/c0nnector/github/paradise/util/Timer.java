package io.c0nnector.github.paradise.util;

import android.os.CountDownTimer;

/**
 * Countdown timer
 */
public class Timer {

    /**
     * If no countdown time is supplied, this will be the default value
     */
    private static final int DEFAULT_COUNTDOWN = 3;


    public static CountDownTimer getDefaultTimer(final TimerListener timerListener){
        return getDefaultTimer(timerListener, DEFAULT_COUNTDOWN);
    }

    public static CountDownTimer getDefaultTimer(final TimerListener timerListener, int seconds){

        return new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timerListener.onFinish();
            }
        }.start();
    }

    public static CountDownTimer getDefaultTimer(int seconds, final TimerListener timerListener){

        return new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timerListener.onFinish();
            }
        }.start();
    }

    public interface TimerListener {
        void onFinish();
    }
}
