package com.orozbek.quiz.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.orozbek.quiz.ui.hitstory.HistoryFragment;
import com.orozbek.quiz.ui.main.MainFragment;
import com.orozbek.quiz.ui.settings.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            case 2:
                fragment = new SettingsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
