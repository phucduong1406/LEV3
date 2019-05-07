package com.phuscduowng.lev3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
    List<String> testList, ansList, ans1List, ans2List, ans3List, ans4List;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        testList = new ArrayList<>();
        ansList = new ArrayList<>();
        ans1List = new ArrayList<>();
        ans2List = new ArrayList<>();
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

        testAdapter = new TestAdapter(testList, ansList, ans1List, ans2List, ans3List, ans4List, this);

        pagerTest = findViewById(R.id.pagerTest);
        pagerTest.setAdapter(testAdapter);
//        pagerTest.setPadding(130, 0, 130,0);



//
//        for(int i = 0; i < ansList.size(); i++) {
//            Random rd = new Random();
//            int n = rd.nextInt(ansList.size());
//            ans1List.add(ansList.get(n));
//        }
//
//        for(int i = 0; i < ansList.size(); i++) {
//            Random rd = new Random();
//            int n = rd.nextInt(ansList.size());
//            ans2List.add(ansList.get(n));
//        }
//
//        for(int i = 0; i < ansList.size(); i++) {
//            Random rd = new Random();
//            int n = rd.nextInt(ansList.size());
//            ans3List.add(ansList.get(n));
//        }
//
//        for(int i = 0; i < ansList.size(); i++) {
//            Random rd = new Random();
//            int n = rd.nextInt(ansList.size());
//            ans4List.add(ansList.get(n));
//        }


    }

}
