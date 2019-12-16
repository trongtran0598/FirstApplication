package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.Fragment.FragmentComunity;
import com.example.myapplication.Fragment.FragmentHandBook;
import com.example.myapplication.Fragment.FragmentNews;
import com.example.myapplication.Fragment.FragmentTactics;

public class FragmentAdapter extends FragmentPagerAdapter {


    private final int NUM_TABS = 4;
    private final String[] listTitle = {"Tin tức", "Cẩm nang", "Chiến thuật", "Cộng đồng"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentNews.newInstance();
            case 1:
                return FragmentHandBook.newInstance();
            case 2:
                return FragmentTactics.newInstance();
            case 3:
                return FragmentComunity.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle[position];
    }
}
