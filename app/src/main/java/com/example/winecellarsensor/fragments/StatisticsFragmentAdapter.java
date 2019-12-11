package com.example.winecellarsensor.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.winecellarsensor.R;

public class StatisticsFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    public StatisticsFragmentAdapter(FragmentManager fm) {
        super(fm);
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
                return null;
        }
    }

    @Override
    public int getCount() {
       return NUM_PAGES;
    }

}
