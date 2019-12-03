package com.tp.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.myapplication.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 2 * 1000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        checkNextScreenAfterDelay();

    }

    private void checkNextScreenAfterDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()) {
                    openMainActivity();
                } else {
                    openLoginActivity();
                }
            }
        }, AUTO_HIDE_DELAY_MILLIS);

    }

    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private boolean isConnected() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean("IS_CONNECTED", false);

    }

    private void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }


}
