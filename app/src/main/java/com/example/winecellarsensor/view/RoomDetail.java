package com.example.winecellarsensor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.winecellarsensor.R;

import androidx.appcompat.app.AppCompatActivity;

public class RoomDetail extends AppCompatActivity {

    private Button buttonStatistics;
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        buttonSettings = findViewById(R.id.btn_Settings);
        buttonStatistics = findViewById(R.id.btn_Statistics);

        buttonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStatistics(view);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettings(view);
            }
        });

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
