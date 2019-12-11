package com.example.winecellarsensor.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.fragments.DailyFragment;
import com.example.winecellarsensor.fragments.StatisticsFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;


public class StatisticsActivity extends AppCompatActivity {

  //  StatisticsFragmentAdapter statisticsFragmentPagerAdapter;
  private static final int NUM_PAGES = 3;
  private BottomNavigationView bottomNavigationMenuView;


    //This is our tablayout
  //private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = findViewById(R.id.toolStatistics);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("Statistic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Initializing the tablayout
       /* tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Your Tab Title"));
        tabLayout.addTab(tabLayout.newTab().setText("Your Tab Title"));
        tabLayout.addTab(tabLayout.newTab().setText("Your Tab Title"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

       /* viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));*/

        StatisticsFragmentAdapter adapter = new StatisticsFragmentAdapter(getSupportFragmentManager()) ;
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        //tabLayout.setOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) this);
        //setPagerAdapter();

       // bottomNavigationMenuView = (BottomNavigationView) findViewById(R.id.top_menu_nav);
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


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    /*private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DailyFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }*/

   /* @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }*/
   /* private void setPagerAdapter(){
        statisticsFragmentPagerAdapter = new StatisticsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(statisticsFragmentPagerAdapter);
    }*/

    /*public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }*/

}
