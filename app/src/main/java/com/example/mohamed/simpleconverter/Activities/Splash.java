package com.example.mohamed.simpleconverter.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mohamed.simpleconverter.R;

import gr.net.maroulis.library.EasySplashScreen;

public class Splash extends AppCompatActivity {

    EasySplashScreen splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        splashScreen = new EasySplashScreen(Splash.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withLogo(R.drawable.ic_launcher)
                .withFooterText(getResources().getString(R.string.app_name));

        selectColor();

        splashScreen.getFooterTextView().setPadding(8,8,8,64);
        splashScreen.getFooterTextView().setTextSize(22);
        splashScreen.getFooterTextView().setTextColor(Color.parseColor("#B1B1B1"));
        splashScreen.getFooterTextView().setTypeface(null, Typeface.BOLD);
        splashScreen.getLogo().setPadding(16,16,16,16);

        View view = splashScreen.create();

        setContentView(view);

    }

    public void selectColor() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = prefs.getString(getString(R.string.pref_appear_key),
                getString(R.string.pref_appear_light));

        switch (theme) {
            case "light":
            default:
                splashScreen.withBackgroundColor(Color.parseColor("#F1F1F1"));
                break;
            case "dark":
                splashScreen.withBackgroundColor(Color.parseColor("#333333"));
                break;
        }
    }
}
