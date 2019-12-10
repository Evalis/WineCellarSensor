package com.example.winecellarsensor.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.winecellarsensor.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

        private Button btn_login;
        private EditText mCellarID;
        private CheckBox box;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




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

    public void authenticationCellarID()
    {

        //verify if  cellar id exist


        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cellarID", mCellarID.getText().toString());
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

}
