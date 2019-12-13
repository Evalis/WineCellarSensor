package com.example.winecellarsensor.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.winecellarsensor.fragments.DailyFragment;
import com.example.winecellarsensor.fragments.MonthlyFragment;
import com.example.winecellarsensor.fragments.WeeklyFragment;

public class StatisticsFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;
    private String[] tabTitles = new String[]{"Daily", "Weekly", "Monthly"};
   // private Fragment fragment;
    FragmentStatePagerAdapter fragmentStatePagerAdapter;


    public StatisticsFragmentAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }


    @Override
   public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DailyFragment daily = new DailyFragment();
                return daily;
            case 1:
                WeeklyFragment weekly = new WeeklyFragment();
                return weekly;
            case 2:
                MonthlyFragment monthly = new MonthlyFragment();
                return monthly;
            default:
                throw new RuntimeException("Invalid tab position");
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public int getCount() {
       return NUM_PAGES;
    }

}
