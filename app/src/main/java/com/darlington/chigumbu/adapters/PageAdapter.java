package com.darlington.chigumbu.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.darlington.chigumbu.tabs.IQLeaders;
import com.darlington.chigumbu.tabs.LearningLeaders;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LearningLeaders();
            case 1:
                return new IQLeaders();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return  numOfTabs;
    }

}
