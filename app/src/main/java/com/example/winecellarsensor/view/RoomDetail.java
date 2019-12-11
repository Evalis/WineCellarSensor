package com.example.winecellarsensor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.winecellarsensor.R;

import androidx.appcompat.app.AppCompatActivity;

public class RoomDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);


    }

    public void showStatistics(View view) {
        Intent i = new Intent(RoomDetail.this, StatisticsActivity.class);
        startActivity(i);
    }

    public void showSettings(View view) {
        Intent i = new Intent(RoomDetail.this, SettingsActivity.class);
        startActivity(i);
    }
}
