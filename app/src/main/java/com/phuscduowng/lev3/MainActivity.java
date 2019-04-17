package com.phuscduowng.lev3;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DictionaryFragment dictFragment;
    DetailFragment detailFragment;
    RecentFragment recentFragment;
    FavoriteFragment favoriteFragment;
    TopicFragment topicFragment;
//    EmptyFragment emptyFragment;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        dictFragment = new DictionaryFragment();
        favoriteFragment = new FavoriteFragment();
        detailFragment = new DetailFragment();
        recentFragment = new RecentFragment();
        topicFragment = new TopicFragment();

        loadFragment(dictFragment, true);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dict:
                    fragment = new DictionaryFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_recent:
                    fragment = new RecentFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_favorite:
                    fragment = new FavoriteFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_topic:
                    fragment = new TopicFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_account:

                    return true;
            }
            return false;
        }
    };


    // Replace fragment Dict
    private void loadFragment(Fragment fragment, boolean isTop) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }


}
