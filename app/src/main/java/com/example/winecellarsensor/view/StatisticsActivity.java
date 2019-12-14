package com.example.winecellarsensor.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.fragments.DailyFragment;
import com.example.winecellarsensor.fragments.MonthlyFragment;
import com.example.winecellarsensor.adapters.StatisticsFragmentAdapter;
import com.example.winecellarsensor.fragments.WeeklyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;



public class StatisticsActivity extends AppCompatActivity  {

    private StatisticsFragmentAdapter statisticsFragmentAdapter;
    private Fragment fragment;
    private TabLayout tabLayout;

    private TabItem tabItemDaily;
    private TabItem tabItemWeekly;
    private TabItem tabItemMonthly;
    private Fragment selectedFragment = null;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = findViewById(R.id.toolStatistics);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

      //  BottomNavigationView bottomNavigationView = findViewById(R.id.top_nav);
      //  bottomNavigationView.setOnNavigationItemSelectedListener(navListenerStatistics);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);
        /*--------*/   tabLayout.setupWithViewPager(viewPager);
        //  tabItemDaily =findViewById(R.id.tabItemDaily);
       //  tabItemWeekly =findViewById(R.id.tabItemWeekly);
      //  tabItemDaily =findViewById(R.id.tabItemMonthly);

        StatisticsFragmentAdapter adapter = new StatisticsFragmentAdapter(getSupportFragmentManager()) ;
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case R.id.nav_daily:
                        switchToFragmentDaily();
                        tab.getText();
                        break;
                    case R.id.nav_weekly:
                        switchToFragmentWeekly();
                        break;
                    case R.id.nav_monthly:
                        switchToFragmentMonthly();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

   private BottomNavigationView.OnNavigationItemSelectedListener navListenerStatistics =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int count;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_daily:
                            switchToFragmentDaily();

                            break;
                        case R.id.nav_weekly:
                            switchToFragmentWeekly();

                            break;
                        case R.id.nav_monthly:
                            switchToFragmentMonthly();

                            break;
                    }
                 //   getSupportFragmentManager().beginTransaction().replace(R.id.pager,selectedFragment ).commit();
                    return loadFragment(fragment);
                }
            };

    public void switchToFragmentDaily() {
        fragment = new DailyFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.pager, fragment).commit();
        Toast.makeText(getApplicationContext(), "Daily fragment", Toast.LENGTH_SHORT).show();
    }

    public void switchToFragmentWeekly() {
        fragment = new WeeklyFragment();
                FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.pager, fragment).commit();
        Toast.makeText(getApplicationContext(), "Weekly fragment", Toast.LENGTH_SHORT).show();
    }

    public void switchToFragmentMonthly() {
        fragment = new MonthlyFragment();
                FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.pager, fragment).commit();
        Toast.makeText(getApplicationContext(), "Monthly fragment", Toast.LENGTH_SHORT).show();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pager, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


}
