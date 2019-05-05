package com.phuscduowng.lev3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuscduowng.lev3.intf.StoryInterface;

public class StoryPagerEnFragment extends Fragment {

    private String value = "story002";
    private WebView readContent;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Story");

    public StoryPagerEnFragment() {
        // Required empty public constructor
    }

//    public static StoryPagerEnFragment getNewInstance(String value) {
//        StoryPagerEnFragment fragment = new StoryPagerEnFragment();
//        fragment.value = value;
//        return fragment;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story_pager_en, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);


        readContent = view.findViewById(R.id.storyContentEn);
//        WebSettings webSettings = readContent.getSettings();
//        webSettings.setJavaScriptEnabled(true);

//        value = getArguments().getString("data");
//        Log.d("s--", "onViewCreated: " + value);
//        if (value == null) value = "story002";

        // DB
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Story story = dataSnapshot.getValue(Story.class);

                if (value.equals(story.id)) {
                    readContent.loadDataWithBaseURL(null, story.content_e, "text/html", "utf-8", null);
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

    public void clickStoryDetail(String s) {
        Log.d("s--", "Value frag2: " + value);
        value = s;
//        Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
