package com.example.winecellarsensor.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.winecellarsensor.R;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static int TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent welcomeIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(welcomeIntent);
                finish();
            }
        },TIME_OUT);
    }
}