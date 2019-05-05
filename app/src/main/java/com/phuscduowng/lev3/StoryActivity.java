package com.phuscduowng.lev3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.phuscduowng.lev3.intf.StoryInterface;

public class StoryActivity extends AppCompatActivity implements StoryInterface {

    StoryFragment storyFragment;
    StoryDetailFragment storyDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyFragment = new StoryFragment();
        storyDetailFragment = new StoryDetailFragment();

        loadFragment(storyFragment, true);
    }

    // Replace fragment Dict
    private void loadFragment(Fragment fragment, boolean isTop) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }


    @Override
    public void onDataStory(String value) {
//        Log.d("s--","Value actvt: " + value);

        StoryPagerEnFragment storyPagerEnFragment = new StoryPagerEnFragment();

        storyPagerEnFragment.clickStoryDetail(value);


        Bundle bundle = new Bundle();
        bundle.putString("data", "From Activity");
        storyPagerEnFragment.setArguments(bundle);

    }
}
