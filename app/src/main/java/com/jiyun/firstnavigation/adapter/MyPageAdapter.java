package com.jiyun.firstnavigation.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager fm;
    List<Fragment> mFragent;
    List<String> title;

    public MyPageAdapter(FragmentManager fm, List<Fragment> mFragent, List<String> title) {
        super(fm);
        this.fm = fm;
        this.mFragent = mFragent;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragent.get(position);
    }

    @Override
    public int getCount() {
        return mFragent.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = mFragent.get(position);
        fm.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }
}