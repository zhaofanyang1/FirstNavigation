package com.jiyun.firstnavigation.adapter;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class ViewPaterAdapter2 extends FragmentStatePagerAdapter {
    private FragmentManager fm;
    private List<Fragment> fragments;
    private List<String> title;

    public ViewPaterAdapter2(FragmentManager fm, List<Fragment> fragments, List<String> title) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
        this.title = title;
    }

    public ViewPaterAdapter2(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragments.get(position);
        fm.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }
}
