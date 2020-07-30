package com.example.doanappdoctruyen;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyAdapter extends FragmentStatePagerAdapter {
    private  String listTab1[]= {"Truyen","Like","History"};
    private TruyenFragment mTruyenFragment;
    private HistoryFragment mHistoryFragment;
    public MyAdapter(FragmentManager fm) {
        super(fm);
        mTruyenFragment = new TruyenFragment();
        mHistoryFragment = new HistoryFragment();
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return  mTruyenFragment;
        }
        else if(position==1){
            return  mHistoryFragment;
        }
        else{

        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab1.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab1[position];
    }
}
