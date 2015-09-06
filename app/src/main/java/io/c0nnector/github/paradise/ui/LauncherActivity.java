package io.c0nnector.github.paradise.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.ui.home.MainActivity;
import io.c0nnector.github.paradise.util.Timer;
import io.c0nnector.github.paradise.util.UtilTransitions;

public class LauncherActivity extends BaseActivity {

    //todo - add angellist authentication check here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Timer.getDefaultTimer(2, new Timer.TimerListener() {

            @Override
            public void onFinish() {

                MainActivity.start(LauncherActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        UtilTransitions.setFadeEnterAnimation(this);
    }


    @Override
    protected void onPause() {
        super.onPause();

        UtilTransitions.setFadeExitAnimation(this);
    }

    public static void start(Context context) {

        Intent starter = new Intent(context, LauncherActivity.class);
        context.startActivity(starter);
    }

}
