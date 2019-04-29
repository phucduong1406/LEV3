package com.phuscduowng.lev3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StoryActivity extends AppCompatActivity {

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
        transaction.replace(R.id.container_read, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }


}
