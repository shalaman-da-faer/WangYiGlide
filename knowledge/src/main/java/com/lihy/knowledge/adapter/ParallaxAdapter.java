package com.lihy.knowledge.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lihy.knowledge.fragment.ParallaxFragment;

import java.util.ArrayList;

/**
 * Created by lihy on 2019/4/15.
 */

public class ParallaxAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public ParallaxAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
