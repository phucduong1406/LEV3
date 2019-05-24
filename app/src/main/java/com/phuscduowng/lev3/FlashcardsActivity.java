package com.phuscduowng.lev3;

import android.animation.ArgbEvaluator;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FlashcardsActivity extends AppCompatActivity {

    ViewPager pagerFlashcards;
    FlashcardsAdapter flashcardsAdapter;
    List<Flashcards> flashcardsList;
    Integer[] color = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    TextToSpeech toSpeech;
    private static final int REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        getSupportActionBar().setTitle(R.string.flashcards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);


        flashcardsList = new ArrayList<>();

        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);
                if(dictionary.favorite_word) {
                    flashcardsList.add(new Flashcards("", dictionary.word, dictionary.def, dictionary.syn, dictionary.pronun, dictionary.mean, dictionary.ex));
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

//        pagerFlashcards.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                int pageWidth = pagerFlashcards.getMeasuredWidth() -
//                        pagerFlashcards.getPaddingLeft() - pagerFlashcards.getPaddingRight();
//                int paddingLeft = pagerFlashcards.getPaddingLeft();
//                float transformPos = (float) (page.getLeft() -
//                        (pagerFlashcards.getScrollX() + paddingLeft)) / pageWidth;
//                if (transformPos < -1){
//                    page.setScaleY(0.8f);
//                } else if (transformPos <= 1) {
//                    page.setScaleY(1f);
//                } else {
//                    page.setScaleY(0.8f);
//                }
//            }
//        });

//        pagerFlashcards.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.5f);
//                page.setScaleY(normalizedposition / 2 + 0.5f);
//            }
//        });

//        Integer[] color_temp = {
//                getResources().getColor(R.color.colorWhite)
//        };
//        color = color_temp;

        pagerFlashcards.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                if (i < (flashcardsAdapter.getCount() - 1) && i < (color.length - 1)) {
//                    pagerFlashcards.setBackgroundColor((Integer) argbEvaluator.evaluate(v, color[i], color[i + 1]));
//                } else {
//                    pagerFlashcards.setBackgroundColor(color[color.length - 1]);
//                }


//                final String s = flashcardsList.get(i).getWord();
//
//                toSpeech = new TextToSpeech(FlashcardsActivity.this, new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int i) {
//                        if (i != TextToSpeech.ERROR) {
//
//                                /*if (flagLang == 0) {
//                                    toSpeech.setLanguage(Locale.ENGLISH);
//                                } else if (flagLang == 1) {
//                                    toSpeech.setLanguage(Locale.forLanguageTag("vi-VN"));
//                                }*/
//
//                            toSpeech.setLanguage(Locale.ENGLISH);
//                            toSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
//                        }
//                    }
//                });
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
