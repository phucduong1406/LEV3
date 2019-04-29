package com.phuscduowng.lev3;

import android.animation.ArgbEvaluator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FlashcardsActivity extends AppCompatActivity {

    ViewPager pagerFlashcards;
    FlashcardsAdapter flashcardsAdapter;
    List<Flashcards> flashcardsList;
    Integer[] color = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        flashcardsList = new ArrayList<>();

        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);
                if(dictionary.favorite_word) {
                    flashcardsList.add(new Flashcards("", dictionary.word, dictionary.pronun, dictionary.mean));
                }
                flashcardsAdapter.notifyDataSetChanged();
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

        flashcardsAdapter = new FlashcardsAdapter(flashcardsList, this);

        pagerFlashcards = findViewById(R.id.pagerFlashcards);
        pagerFlashcards.setAdapter(flashcardsAdapter);
        pagerFlashcards.setPadding(130, 0, 130,0);

        Integer[] color_temp = {
                getResources().getColor(R.color.colorAccent)
        };
        color = color_temp;

        pagerFlashcards.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i < (flashcardsAdapter.getCount() - 1) && i < (color.length - 1)) {
                    pagerFlashcards.setBackgroundColor((Integer) argbEvaluator.evaluate(v, color[i], color[i + 1]));
                } else {
                    pagerFlashcards.setBackgroundColor(color[color.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
