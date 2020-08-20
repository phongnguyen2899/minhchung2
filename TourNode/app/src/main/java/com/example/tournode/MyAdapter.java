package com.example.tournode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyAdapter extends FragmentStatePagerAdapter {
    private String [] list = {"Tham Quan","Ăn Uống","Mua Sắm"};
    private FragmentOne fragmentOne ;
    private FragmentThree fragmentThree;
    private FragmentTwo fragmentTwo;
    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fragmentOne;
            case 1:
                return fragmentTwo;
             default:
                 return fragmentThree;
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }
}
