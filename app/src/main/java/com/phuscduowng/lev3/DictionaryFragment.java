package com.phuscduowng.lev3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

public class DictionaryFragment extends Fragment implements DictionaryAdapterListener {

    private List<Dictionary> dictionaryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RVAdapter mAdapter;
    private ImageView imgFavorite;

    DetailFragment detailFragment;


    // Lấy toàn bộ dữ liệu trong Dictionary
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");

    public DictionaryFragment() {
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
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgFavorite = view.findViewById(R.id.imgFavorite);


        detailFragment = new DetailFragment();

        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // Create adapter passing in the sample user data
        mAdapter = new RVAdapter(getActivity(), dictionaryList);


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
                Dictionary dictionary = dataSnapshot.getValue(Dictionary.class);
                dictionaryList.add(dictionary);

//
//                if(dictionary.favorite_word) {
//                    imgFavorite.setImageResource(R.drawable.ic_star_red_24dp);
//                }


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


//        for (int i = 0; i <= dictionaryList.size(); i++) {
//            if (dictionaryList.get(i).favorite_word) {
//                imgFavorite.setImageResource(R.drawable.ic_star_red_24dp);
//            }
//        }


        mAdapter = new RVAdapter(getActivity(), dictionaryList);
        mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);



        /**
         ** Search: Filter text
         **/

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
            if (dictionaryList.get(i).word.startsWith(value)) {
                recyclerView.getLayoutManager().scrollToPosition(i);
                break;
            }
        }
    }
}
