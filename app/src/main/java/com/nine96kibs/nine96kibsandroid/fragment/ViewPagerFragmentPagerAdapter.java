package com.nine96kibs.nine96kibsandroid.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerFragmentPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ViewPagerFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
