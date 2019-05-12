package com.phuscduowng.lev3;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class FlashcardsAdapter extends PagerAdapter {

    private List<Flashcards> flashcards, flashcardsRandomed;
    private LayoutInflater layoutInflater;
    private Context context;


    TextToSpeech toSpeech;
    private static final int REQUEST_CODE = 111;

    public FlashcardsAdapter(List<Flashcards> flashcards, Context context) {
        this.flashcards = flashcards;
        this.context = context;
    }

    @Override
    public int getCount() {
        return flashcards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.flashcards_item, container, false);

        ImageView img;
        final LottieAnimationView imgFlashcardsPronun;
        final TextView word, pronun, mean;
        final Button flip;

        img = view.findViewById(R.id.imgFlashcards);
        word = view.findViewById(R.id.txtFlashcardsWord);
        imgFlashcardsPronun = view.findViewById(R.id.imgFlashcardsPronun);
        pronun = view.findViewById(R.id.txtFlashcardsPronun);
        mean = view.findViewById(R.id.txtFlashcardsMean);
        flip = view.findViewById(R.id.btnFlashcardsFlip);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lev3-usuow.appspot.com/o/flashcards%2F" + flashcards.get(position).getWord() +".jpg?alt=media").into(img);
//        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lev3-usuow.appspot.com/o/flashcards%2Fphoto_flashcards.jpg?alt=media").into(img);

        mean.setText(flashcards.get(position).getDef());


        word.setVisibility(View.GONE);
        pronun.setVisibility(View.GONE);
        imgFlashcardsPronun.setVisibility(View.GONE);
//        mean.setVisibility(View.GONE);

        imgFlashcardsPronun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgFlashcardsPronun.playAnimation();
                imgFlashcardsPronun.setRepeatCount(3);
                imgFlashcardsPronun.setSpeed(15);
                imgFlashcardsPronun.setColorFilter (R.drawable.bg_blue);

                final String s = word.getText().toString();
                toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i != TextToSpeech.ERROR) {

                            toSpeech.setLanguage(Locale.ENGLISH);
                            toSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });


        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word.setVisibility(View.VISIBLE);
                imgFlashcardsPronun.setVisibility(View.VISIBLE);
                pronun.setVisibility(View.VISIBLE);
                flip.setVisibility(View.GONE);
                word.setText(flashcards.get(position).getWord());
                pronun.setText(flashcards.get(position).getPronun());
                mean.setText(flashcards.get(position).getMean());


            }
        });


        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
