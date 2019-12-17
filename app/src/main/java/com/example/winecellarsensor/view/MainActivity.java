package com.example.winecellarsensor.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.viewModel.CellarViewModel;
import com.example.winecellarsensor.fragments.HomeFragment;
import com.example.winecellarsensor.fragments.WarningFragment;
import com.example.winecellarsensor.fragments.ContactFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CellarViewModel cellarViewModel;
    private Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String id = prefs.getString("cellarID", null);
        cellarViewModel.updateCellar(id);


        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(MainActivity.this,cellarViewModel)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment(MainActivity.this,cellarViewModel);
                        break;
                    case R.id.nav_contact:
                        selectedFragment =  new ContactFragment();
                        break;
                    case R.id.nav_notifications:
                        selectedFragment = new WarningFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem reload = menu.findItem(R.id.reload);
        return true;
    }
}

