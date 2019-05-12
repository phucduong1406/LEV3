package com.phuscduowng.lev3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.phuscduowng.lev3.intf.StoryInterface;

public class MainActivity extends AppCompatActivity implements StoryInterface {

    DictionaryFragment dictFragment;
    DetailFragment detailFragment;
    RecentFragment recentFragment;
    FavoriteFragment favoriteFragment;
    TopicFragment topicFragment;
//    EmptyFragment emptyFragment;

    StoryFragment storyFragment;
    StoryDetailFragment storyDetailFragment;
    StoryPagerEnFragment storyPagerEnFragment;
    StoryPagerViFragment storyPagerViFragment;


    private NotificationCompat.Builder notBuilder;

    private static final int MY_NOTIFICATION_ID = 12345;

    private static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        dictFragment = new DictionaryFragment();
        favoriteFragment = new FavoriteFragment();
        detailFragment = new DetailFragment();
        recentFragment = new RecentFragment();
        topicFragment = new TopicFragment();

        storyFragment = new StoryFragment();
        storyDetailFragment = new StoryDetailFragment();

        loadFragment(dictFragment, true);



        this.notBuilder = new NotificationCompat.Builder(this);

        // Thông báo sẽ tự động bị hủy khi người dùng click vào Panel

        this.notBuilder.setAutoCancel(true);


    }


    public void notiButtonClicked(View view)  {

        // --------------------------
        // Chuẩn bị một thông báo
        // --------------------------

        this.notBuilder.setSmallIcon(R.mipmap.ic_launcher);
        this.notBuilder.setTicker("This is a ticker");

        // Sét đặt thời điểm sự kiện xẩy ra.
        // Các thông báo trên Panel được sắp xếp bởi thời gian này.
        this.notBuilder.setWhen(System.currentTimeMillis()+ 10* 1000);
        this.notBuilder.setContentTitle("This is title");
        this.notBuilder.setContentText("This is content text ....");

        // Tạo một Intent
        Intent intent = new Intent(this, MainActivity.class);


        // PendingIntent.getActivity(..) sẽ start mới một Activity và trả về
        // đối tượng PendingIntent.
        // Nó cũng tương đương với gọi Context.startActivity(Intent).
        PendingIntent pendingIntent = PendingIntent.getActivity(this, MY_REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        this.notBuilder.setContentIntent(pendingIntent);

        // Lấy ra dịch vụ thông báo (Một dịch vụ có sẵn của hệ thống).
        NotificationManager notificationService  =
                (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Xây dựng thông báo và gửi nó lên hệ thống.

        Notification notification =  notBuilder.build();
        notificationService.notify(MY_NOTIFICATION_ID, notification);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dict:
                    fragment = new DictionaryFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_favorite:
                    fragment = new FavoriteFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_story:
                    fragment = new StoryFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_topic:
                    fragment = new TopicFragment();
                    loadFragment(fragment, true);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccountFragment();
                    loadFragment(fragment, true);
                    return true;
            }
            return false;
        }
    };


    // Replace fragment Dict
    private void loadFragment(Fragment fragment, boolean isTop) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (!isTop)
            transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);  //chuyển giữa các fragment đẹp hơn
        transaction.commit();
    }

    @Override
    public void onDataStory(String value) {
//        Log.d("s--","Value actvt: " + value);

        StoryPagerEnFragment storyPagerEnFragment = new StoryPagerEnFragment();

        storyPagerEnFragment.clickStoryDetail(value);


        Bundle bundle = new Bundle();
        bundle.putString("data", "From Activity");
        storyPagerEnFragment.setArguments(bundle);

    }

}
