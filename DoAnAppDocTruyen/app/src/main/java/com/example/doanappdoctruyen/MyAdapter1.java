package com.example.doanappdoctruyen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyAdapter1 extends FragmentStatePagerAdapter {
    private  String listTab2[]= {"Thông Tin","Chương"};
    private FragmentThongTin fragmentThongTin;
    private  FragmentChuong fragmentChuong;
    public MyAdapter1(FragmentManager fm) {
        super(fm);
        fragmentThongTin = new FragmentThongTin();
        fragmentChuong = new FragmentChuong();
    }
    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return  fragmentThongTin;
        }
        else if(position==1){
            return  fragmentChuong;
        }
        else{

        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab2.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab2[position];
    }
}
