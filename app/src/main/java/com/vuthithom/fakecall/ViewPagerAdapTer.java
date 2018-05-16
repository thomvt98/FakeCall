package com.vuthithom.fakecall;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapTer extends FragmentPagerAdapter {
   private final List<Fragment>listFragment=new ArrayList<>();
   private final List<String>stringList=new ArrayList<>();
    public ViewPagerAdapTer(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
    public void AddFragment(Fragment fragment,String title){
        listFragment.add(fragment);
        stringList.add(title);
    }
}
