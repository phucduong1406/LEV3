package com.phuscduowng.lev3;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestAdapter extends PagerAdapter {

    private List<String> test, ans, ans1, ans2, ans3, ans4;
    private LayoutInflater layoutInflater;
    private Context context;
    public int right = 0, wrong = 0;

    List<String> ans1List, ans2List, ans3List, ans4List;

    public TestAdapter(List<String> test, List<String> ans, List<String> ans1, List<String> ans2, List<String> ans3, List<String> ans4, Context context) {
        this.test = test;
        this.ans = ans;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.context = context;
    }

    @Override
    public int getCount() {
        return test.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.test_item, container, false);

        final TextView word;
        final Button btnTestAns4, btnTestAns1, btnTestAns2, btnTestAns3;

        ans1List = new ArrayList<>();
        ans2List = new ArrayList<>();
        ans3List = new ArrayList<>();
        ans4List = new ArrayList<>();


        for(int i = 0; i < ans.size(); i++) {
            Random rd = new Random();
            int n = rd.nextInt(ans.size());
            ans1List.add(ans.get(n));
        }

        for(int i = 0; i < ans.size(); i++) {
            Random rd = new Random();
            int n = rd.nextInt(ans.size());
            ans2List.add(ans.get(n));
        }

        for(int i = 0; i < ans.size(); i++) {
            Random rd = new Random();
            int n = rd.nextInt(ans.size());
            ans3List.add(ans.get(n));
        }

        for(int i = 0; i < ans.size(); i++) {
            Random rd = new Random();
            int n = rd.nextInt(ans.size());
            ans4List.add(ans.get(n));
        }




        String s = position + "/" + test.size();
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

        word = view.findViewById(R.id.txtTestWord);
        btnTestAns1 = view.findViewById(R.id.btnTestAns1);
        btnTestAns2 = view.findViewById(R.id.btnTestAns2);
        btnTestAns3 = view.findViewById(R.id.btnTestAns3);
        btnTestAns4 = view.findViewById(R.id.btnTestAns4);


        word.setText(test.get(position));
        btnTestAns1.setText(ans1List.get(position));
        btnTestAns2.setText(ans2List.get(position));
        btnTestAns3.setText(ans3List.get(position));
        btnTestAns4.setText(ans4List.get(position));

        btnTestAns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans.get(position).equals(btnTestAns1.getText())) {
                    btnTestAns1.setBackgroundResource(R.drawable.bg_button_test_green);
                    right++;
                }
                else {
                    btnTestAns1.setBackgroundResource(R.drawable.bg_button_test_red);
                    wrong++;
                }
            }
        });

        btnTestAns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans.get(position).equals(btnTestAns2.getText())) {
                    btnTestAns2.setBackgroundResource(R.drawable.bg_button_test_green);
                    right++;
                }
                else {
                    btnTestAns2.setBackgroundResource(R.drawable.bg_button_test_red);
                    wrong++;
                }
            }
        });

        btnTestAns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans.get(position).equals(btnTestAns3.getText())) {
                    btnTestAns3.setBackgroundResource(R.drawable.bg_button_test_green);
                    right++;
                }
                else {
                    btnTestAns3.setBackgroundResource(R.drawable.bg_button_test_red);
                    wrong++;
                }
            }
        });

        btnTestAns4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans.get(position).equals(btnTestAns4.getText())) {
                    btnTestAns4.setBackgroundResource(R.drawable.bg_button_test_green);
                    right++;
                }
                else {
                    btnTestAns4.setBackgroundResource(R.drawable.bg_button_test_red);
                    wrong++;
                }
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
