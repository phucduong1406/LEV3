package com.phuscduowng.lev3;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuscduowng.lev3.listener.DictionaryAdapterListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RecentFragment extends Fragment implements DictionaryAdapterListener{

    private List<Dictionary> recentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RVAdapter mAdapter;

    DetailFragment detailFragment;

    SwipeController swipeController = null;


    // Lấy toàn bộ dữ liệu trong Dictionary
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    public RecentFragment() {
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
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailFragment = new DetailFragment();

        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // Create adapter passing in the sample user data
        mAdapter = new RVAdapter(getActivity(), recentList);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(mAdapter);

        // Lấy danh sách Dictionary
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);
                if(dictionary.recent_word) {
                    recentList.add(dictionary);
                }


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

//        // Xóa phần từ trùng
//        for (Dictionary element : recentList) {
//            // Check if element not exist in list, perform add element to list
//            if (!recentList.contains(element)) {
//                recentList.add(element);
//            }
//        }

        mAdapter = new RVAdapter(getActivity(), recentList);
        mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);

        final EditText search = view.findViewById(R.id.edit_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));  // Adding RecyclerView Divider / Separator




        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                mData.child(recentList.get(position).word).child("recent_word").setValue(false);

                recentList.remove(position);

                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
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

    @Override
    public void onItemClick(String name) {
        loadFragment(detailFragment.getNewInstance(name), true);
    }

    // Replace fragment Dict
    private void loadFragment(Fragment fragment, boolean isTop) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }

    // Filter
    public void filterValue(String value) {

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            if (recentList.get(i).word.startsWith(value)) {
                recyclerView.getLayoutManager().scrollToPosition(i);
                break;
            }
        }
    }

    public void emptyFragment() {
        if (recentList.isEmpty()) {
            Fragment fragment = new EmptyFragment();
            loadFragment(fragment, true);
        }
    }
}
