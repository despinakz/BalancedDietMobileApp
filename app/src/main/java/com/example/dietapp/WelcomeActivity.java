package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    //This value is useful in order to define how much time we want to show the welcome screen ( = the WelcomeActivity layout)
    private static int SPLASH_TIME_OUT = 2000; //the welcome screen is showing for about 2 secs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_welcome);


        if (getSupportActionBar() != null){ //This IF-Statement exists in order to prevent Null Pointer Exception
            getSupportActionBar().hide(); //we hide action bar from layout
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //create an intent which will be sent the whole layout (= the welcome screen) as context to MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);

                //and we don't forget to start the intent
                startActivity(intent);

                finish();
            }
        },SPLASH_TIME_OUT); // we are showing up the welcome screen for 2 secs (as we defined before)
    }
}
