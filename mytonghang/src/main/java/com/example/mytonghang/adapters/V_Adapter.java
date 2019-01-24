package com.example.mytonghang.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class V_Adapter extends FragmentStatePagerAdapter {
    private List<Fragment> fs;
    private List<String> title;

    public V_Adapter(FragmentManager fm, List<Fragment> fs) {
        super(fm);
        this.fs = fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
