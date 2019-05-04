package com.phuscduowng.lev3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuscduowng.lev3.listener.DictionaryAdapterListener;

import java.util.ArrayList;
import java.util.List;


public class StoryFragment extends Fragment implements DictionaryAdapterListener {

    private List<Story> storyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoryAdapter mAdapter;

    StoryDetailFragment storyDetailFragment;
    StoryPagerEnFragment storyPagerEnFragment;

    // Lấy toàn bộ dữ liệu trong Story
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Story");

    public StoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        storyPagerEnFragment = new StoryPagerEnFragment();
        storyDetailFragment = new StoryDetailFragment();

        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) view.findViewById(R.id.storyView);

        // Create adapter passing in the sample user data
        mAdapter = new StoryAdapter(getActivity(), storyList);


        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(mAdapter);

        // Lấy danh sách Story
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Story story = dataSnapshot.getValue(Story.class);
                storyList.add(story);

                mAdapter.notifyDataSetChanged();

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



        mAdapter = new StoryAdapter(getActivity(), storyList);
        mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(String value) {
        loadFragment(storyDetailFragment.getNewInstance(value), true);
//        loadFragment(storyPagerEnFragment.getNewInstance("story002"), true);
    }


    // Replace fragment Dict
    private void loadFragment(Fragment fragment, boolean isTop) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_read, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }
}
