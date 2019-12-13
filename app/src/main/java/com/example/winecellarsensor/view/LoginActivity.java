package com.example.winecellarsensor.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.ViewModel.CellarViewModel;
import com.example.winecellarsensor.model.Room;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText mCellarID;
    private CheckBox box;
    private CellarViewModel cellarViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verifyIfAlreadySignedIn();

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);
        btn_login = findViewById(R.id.btn_login);
        mCellarID = findViewById(R.id.cellarID);
        box = findViewById(R.id.checkbox);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationCellarID();
            }
        });
    }

    private void verifyIfAlreadySignedIn() {
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String id = prefs.getString("cellarID", null);
        if(id != null)
        {
            initMainActivity();
        }
    }

    public void authenticationCellarID() {

        //verify if  cellar id exist
        cellarViewModel.updateCellar(mCellarID.getText().toString());
        cellarViewModel.getRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                if(rooms != null || rooms.size() > 0)
                {
                    SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("cellarID", mCellarID.getText().toString());
                    editor.apply();

                    cellarViewModel.getRooms().removeObserver(this);

                    initMainActivity();
                }
            }
        });
    }

    private void initMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
