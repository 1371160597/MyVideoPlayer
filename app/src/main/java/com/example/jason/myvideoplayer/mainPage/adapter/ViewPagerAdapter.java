package com.example.jason.myvideoplayer.mainPage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/2
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> titles;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragmentList = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
