package com.phuscduowng.lev3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    ViewPager pagerTest;
    TestAdapter testAdapter;
    List<String> testList, ansList, ans3List, ans4List;
    public int rightAnwser, wrongAnwser;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getSupportActionBar().setTitle(R.string.test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);


        testList = new ArrayList<>();
        ansList = new ArrayList<>();
        ans3List = new ArrayList<>();
        ans4List = new ArrayList<>();


        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);
                if(dictionary.favorite_word) {
                    testList.add(dictionary.word);
                    ansList.add(dictionary.mean);
//
//
//
//                    ans1List.add(dictionary.mean);
//                    ans2List.add(dictionary.mean);
//                    ans3List.add(dictionary.mean);
//                    ans4List.add(dictionary.mean);
                }
                testAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        testAdapter = new TestAdapter(testList, ansList, rightAnwser, wrongAnwser, ans3List, ans4List, this);

        pagerTest = findViewById(R.id.pagerTest);
        pagerTest.setAdapter(testAdapter);


//        pagerTest.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.5f);
//                page.setScaleY(normalizedposition / 2 + 0.5f);
//            }
//        });

    }

}
