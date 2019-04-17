package com.phuscduowng.lev3;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class DetailFragment extends Fragment {

    private String value = "";

    private TextView textWord, textPronun;
    private ImageButton btnBookmark, btnVolume, btnHear;
    private WebView textMean;


    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public DetailFragment() {
        // Required empty public constructor
    }

    //
    public static DetailFragment getNewInstance(String value) {
        DetailFragment fragment = new DetailFragment();
        fragment.value = value;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);

        btnHear = view.findViewById(R.id.btnHear);
        textWord = view.findViewById(R.id.textWord);
        textPronun = view.findViewById(R.id.textPronun);
        btnBookmark = view.findViewById(R.id.btnBookmark);
        textMean = view.findViewById(R.id.textWordTranslate);

        // DB
        mData.child("Dictionary").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                final Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);

                assert dictionary != null;
                if (value.equals(dictionary.word)) {
                    textWord.setText(value);
                    textPronun.setText(dictionary.pronun);
                    textMean.loadDataWithBaseURL(null, dictionary.detail, "text/html", "utf-8", null);

                    // Set icon
                    int icon;

                    if (!dictionary.favorite_word) {

                        icon = R.drawable.ic_star_border;
                        btnBookmark.setImageResource(icon);
                    } else {

                        icon = R.drawable.ic_star_red_24dp;
                        btnBookmark.setImageResource(icon);
                    }

                    btnBookmark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (dictionary.favorite_word) {

                                mData.child("Dictionary").child(value).child("favorite_word").setValue(false);
                                btnBookmark.setImageResource(R.drawable.ic_star_border);




                                //Toast.makeText(getContext(), "false" + dictionary.favorite_word, Toast.LENGTH_SHORT).show();
                            }

                            else if (!dictionary.favorite_word) {

                                mData.child("Dictionary").child(value).child("favorite_word").setValue(true);
                                btnBookmark.setImageResource(R.drawable.ic_star_red_24dp);
                                //Toast.makeText(getContext(), "true" + dictionary.favorite_word, Toast.LENGTH_SHORT).show();

                            }



                        }
                    });
                }


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

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
