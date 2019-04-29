package com.phuscduowng.lev3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.phuscduowng.lev3.StoryPagerEnFragment;
import com.phuscduowng.lev3.StoryPagerViFragment;
import com.phuscduowng.lev3.StoryPagerVoFragment;

public class StoryPagerAdapter extends FragmentStatePagerAdapter {

    public StoryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        switch (i){
            case 0:
                frag = new StoryPagerViFragment();
                break;
            case 1:
                frag = new StoryPagerEnFragment();
                break;
            case 2:
                frag = new StoryPagerVoFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        String title = "";
        switch (i) {
            case 0:
                title = "Vietnamese";
                break;
            case 1:
                title = "English";
                break;
            case 2:
                title = "Vocabulary";
                break;
        }
        return title;
    }
}
