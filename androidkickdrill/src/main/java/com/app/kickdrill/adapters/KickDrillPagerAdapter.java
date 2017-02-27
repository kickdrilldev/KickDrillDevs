package com.app.kickdrill.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.kickdrill.adaptermaker.InitFragment;


/**
 * Created by venki on 2/9/16.
 */
public class KickDrillPagerAdapter extends FragmentPagerAdapter
{


     InitFragment  initFragment;

    public void addInitFragment(InitFragment initFragment)
    {
        this.initFragment = initFragment;
    }

//    final int PAGE_COUNT = 3;
    public String[] tabTitles;
    public KickDrillPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;

    }

    @Override
    public Fragment getItem(int position) {
        return initFragment.initFragment(position);

    }
    @Override
    public int getCount() {
        return tabTitles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
