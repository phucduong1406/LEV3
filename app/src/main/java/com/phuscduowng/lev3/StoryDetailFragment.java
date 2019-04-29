package com.phuscduowng.lev3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class StoryDetailFragment extends Fragment {

    public String value = "";
    private ViewPager storyPager;
    private TabLayout tabStoryDetail;

    StoryPagerViFragment storyPagerViFragment;
    StoryPagerEnFragment storyPagerEnFragment;
    StoryPagerVoFragment storyPagerVoFragment;

    public StoryDetailFragment() {
        // Required empty public constructor
    }

    public static StoryDetailFragment getNewInstance(String value) {
        StoryDetailFragment fragment = new StoryDetailFragment();
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
        return inflater.inflate(R.layout.fragment_story_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);


        storyPager = view.findViewById(R.id.storyPager);
        tabStoryDetail = view.findViewById(R.id.tabStoryDetail);
        FragmentManager manager = getFragmentManager();
        StoryPagerAdapter adapter = new StoryPagerAdapter(manager);

        storyPager.setAdapter(adapter);
        tabStoryDetail.setupWithViewPager(storyPager);
        storyPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabStoryDetail));
        tabStoryDetail.setTabsFromPagerAdapter(adapter);//deprecated
        tabStoryDetail.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(storyPager));


        storyPagerViFragment = new StoryPagerViFragment();
        storyPagerEnFragment = new StoryPagerEnFragment();
        storyPagerVoFragment = new StoryPagerVoFragment();


        Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
