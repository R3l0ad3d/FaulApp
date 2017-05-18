package com.example.r3l0ad3d.tourmate.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.r3l0ad3d.tourmate.Fragments.ExpanceRecordFragment;
import com.example.r3l0ad3d.tourmate.Fragments.MomentShowELISfragment;

/**
 * Created by r3l0ad3d on 5/18/17.
 */

public class PageAdapterELIS extends FragmentPagerAdapter {

    public PageAdapterELIS(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ExpanceRecordFragment();
            case 1:
                return new MomentShowELISfragment();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Expence List";
            case 1:
                return "Moment Gallery";

        }
        return super.getPageTitle(position);
    }
}
