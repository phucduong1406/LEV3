package com.phuscduowng.lev3;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuscduowng.lev3.listener.DictionaryAdapterListener;

import java.util.ArrayList;
import java.util.List;


public class TopicChildFragment extends Fragment implements DictionaryAdapterListener {

    Boolean isAdded = false;

    private List<Dictionary> topicChildList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RVAdapter mAdapter;
    String value = "";
    Dialog dialog;
    ImageView menuAddTopic;

    DetailFragment detailFragment;

    SwipeController swipeController = null;

    // Lấy toàn bộ dữ liệu trong Dictionary
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("Dictionary");


    public TopicChildFragment() {
        // Required empty public constructor
    }

    public static TopicChildFragment getNewInstance(String value) {
        TopicChildFragment fragment = new TopicChildFragment();
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
        return inflater.inflate(R.layout.fragment_topic_child, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuAddTopic = view.findViewById(R.id.menuAddTopic);
        menuAddTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_dictionary);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;  // Animation dialog
                dialog.show();

                Button btnOKAddTopic = (Button) dialog.findViewById(R.id.btnOKAddDictionary);
                Button btnCacelAddTopic = (Button) dialog.findViewById(R.id.btnCacelAddDictionary);

                btnOKAddTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText word = dialog.findViewById(R.id.txtWordDictionary);
                        final EditText mean = dialog.findViewById(R.id.txtMeanDictionary);
                        final EditText pronun = dialog.findViewById(R.id.txtPronunDictionary);

                        if (!word.getText().toString().equals("") && !mean.getText().toString().equals("") && !pronun.getText().toString().equals("") ) {
                            mData.child(word.getText().toString().toLowerCase()).child("word").setValue(word.getText().toString().toLowerCase());
                            mData.child(word.getText().toString().toLowerCase()).child("pronun").setValue(mean.getText().toString().toLowerCase());
                            mData.child(word.getText().toString().toLowerCase()).child("def").setValue(word.getText().toString().toLowerCase());
                            mData.child(word.getText().toString().toLowerCase()).child("detail").setValue(word.getText().toString().toLowerCase());
                            mData.child(word.getText().toString().toLowerCase()).child("ex").setValue("");
                            mData.child(word.getText().toString().toLowerCase()).child("favorite_word").setValue(false);
                            mData.child(word.getText().toString().toLowerCase()).child("recent_word").setValue(false);
                            mData.child(word.getText().toString().toLowerCase()).child("my_word").setValue(false);
                            mData.child(word.getText().toString().toLowerCase()).child("syn").setValue("");
                            mData.child(word.getText().toString().toLowerCase()).child("mean").setValue(pronun.getText().toString().toLowerCase());
                            mData.child(word.getText().toString().toLowerCase()).child("topic").setValue(value.toLowerCase());

                            Dictionary d = new Dictionary(word.getText().toString(), "", "", "", pronun.getText().toString(), mean.getText().toString(), "", value.toLowerCase(), false, false, false);
                            topicChildList.add(d);

                            isAdded = true;

                            dialog.dismiss();

                        } else {
                            word.setError("Enter name");
                            mean.setError("Enter mean");
                            pronun.setError("Enter pronunciation");
                        }





                    }
                });

                btnCacelAddTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        if (isAdded) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false);
            }
            ft.detach(this).attach(this).commit();
            isAdded = false;
        }



        detailFragment = new DetailFragment();

        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // Create adapter passing in the sample user data
        mAdapter = new RVAdapter(getActivity(), topicChildList);


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
                assert dictionary != null;

                if (value.toLowerCase().equals(dictionary.topic))
                topicChildList.add(dictionary);

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



        mAdapter = new RVAdapter(getActivity(), topicChildList);
        mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);





        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                mData.child(topicChildList.get(position).word).removeValue();

                topicChildList.remove(position);

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
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }

}
