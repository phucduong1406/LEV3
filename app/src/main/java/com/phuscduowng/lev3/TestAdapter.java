package com.phuscduowng.lev3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestAdapter extends PagerAdapter {

    private List<String> test, ans, ans1, ans2, ans3, ans4;
    private LayoutInflater layoutInflater;
    private Context context;
    public int right, wrong;
    TextToSpeech toSpeech;
    private static final int REQUEST_CODE = 111;
    ViewPager pagerTest;

    List<String> ansMeanEV, ansWordVE, ansWordListening;
    int[] ansButon = new int[4];
    private Activity testActivity;

    public TestAdapter(List<String> test, List<String> ans, int right, int wrong, List<String> ans3, List<String> ans4, Context context) {
        this.test = test;
        this.ans = ans;
        this.right = right;
        this.wrong = wrong;
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
        pagerTest = ((TestActivity) context).findViewById(R.id.pagerTest);


        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.test_mean_ev_item, container, false);
        Random rdType = new Random();
        int nType = rdType.nextInt(3);
        switch (nType) {
            case 0:
                view = layoutInflater.inflate(R.layout.test_listening_item, container, false);

                final Button btnListeningAns4, btnListeningAns1, btnListeningAns2, btnListeningAns3;
                final LottieAnimationView imgListeningWord;

                imgListeningWord = view.findViewById(R.id.imgListeningWord);
                btnListeningAns1 = view.findViewById(R.id.btnListeningAns1);
                btnListeningAns2 = view.findViewById(R.id.btnListeningAns2);
                btnListeningAns3 = view.findViewById(R.id.btnListeningAns3);
                btnListeningAns4 = view.findViewById(R.id.btnListeningAns4);

                imgListeningWord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String s = test.get(position);
                        toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                if (i != TextToSpeech.ERROR) {

                                    toSpeech.setLanguage(Locale.ENGLISH);
                                    toSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                                }
                            }
                        });


//                        imgListeningWord.pauseAnimation();
//                        try
//                        {
//                            Thread.sleep(3000);
//                        }
//                        catch(InterruptedException ex)
//                        {
//                            Thread.currentThread().interrupt();
//                        }
//                        imgListeningWord.playAnimation();
                    }

                });


                ansWordListening = new ArrayList<>();
                ansWordListening.add(ans.get(position));
                for(int i = 0; i < 3; i++) {
                    Random rd = new Random();
                    int n;
                    n = rd.nextInt(ans.size());

                    if (n != position) {
                        ansWordListening.add(ans.get(n));
                    }
                    else {
                        n = rd.nextInt(ans.size());
                        ansWordListening.add(ans.get(n));
                    }
                }

                ansButon[0] = 0;
                ansButon[1] = 1;
                ansButon[2] = 2;
                ansButon[3] = 3;

                RandomizeArray(ansButon);

                btnListeningAns1.setText(ansWordListening.get(ansButon[0]));
                btnListeningAns2.setText(ansWordListening.get(ansButon[1]));
                btnListeningAns3.setText(ansWordListening.get(ansButon[2]));
                btnListeningAns4.setText(ansWordListening.get(ansButon[3]));

                btnListeningAns1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnListeningAns1.getText())) {
                            btnListeningAns1.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnListeningAns1.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }

                        btnListeningAns1.setClickable(false);
                        btnListeningAns2.setClickable(false);
                        btnListeningAns3.setClickable(false);
                        btnListeningAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnListeningAns2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnListeningAns2.getText())) {
                            btnListeningAns2.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnListeningAns2.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }

                        btnListeningAns1.setClickable(false);
                        btnListeningAns2.setClickable(false);
                        btnListeningAns3.setClickable(false);
                        btnListeningAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnListeningAns3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnListeningAns3.getText())) {
                            btnListeningAns3.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnListeningAns3.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }

                        btnListeningAns1.setClickable(false);
                        btnListeningAns2.setClickable(false);
                        btnListeningAns3.setClickable(false);
                        btnListeningAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnListeningAns4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnListeningAns4.getText())) {
                            btnListeningAns4.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnListeningAns4.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }

                        btnListeningAns1.setClickable(false);
                        btnListeningAns2.setClickable(false);
                        btnListeningAns3.setClickable(false);
                        btnListeningAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });
                break;
            case 1:
                layoutInflater.inflate(R.layout.test_mean_ev_item, container, false);

                final TextView word;
                final Button btnTestAns4, btnTestAns1, btnTestAns2, btnTestAns3;

                word = view.findViewById(R.id.txtTestWord);
                btnTestAns1 = view.findViewById(R.id.btnTestAns1);
                btnTestAns2 = view.findViewById(R.id.btnTestAns2);
                btnTestAns3 = view.findViewById(R.id.btnTestAns3);
                btnTestAns4 = view.findViewById(R.id.btnTestAns4);


                word.setText(test.get(position));

                ansMeanEV = new ArrayList<>();
                ansMeanEV.add(ans.get(position));
                for(int i = 0; i < 3; i++) {
                    Random rd = new Random();
                    int n;
                    n = rd.nextInt(ans.size());

                    if (n != position) {
                        ansMeanEV.add(ans.get(n));
                    }
                    else {
                        n = rd.nextInt(ans.size());
                        ansMeanEV.add(ans.get(n));
                    }
                }

                ansButon[0] = 0;
                ansButon[1] = 1;
                ansButon[2] = 2;
                ansButon[3] = 3;

                RandomizeArray(ansButon);

                btnTestAns1.setText(ansMeanEV.get(ansButon[0]));
                btnTestAns2.setText(ansMeanEV.get(ansButon[1]));
                btnTestAns3.setText(ansMeanEV.get(ansButon[2]));
                btnTestAns4.setText(ansMeanEV.get(ansButon[3]));

                btnTestAns1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnTestAns1.getText())) {
//                            btnTestAns1.setBackgroundResource(R.drawable.bg_button_test_green);
                            btnTestAns1.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAns1.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }

                        btnTestAns1.setClickable(false);
                        btnTestAns2.setClickable(false);
                        btnTestAns3.setClickable(false);
                        btnTestAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnTestAns2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnTestAns2.getText())) {
                            btnTestAns2.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAns2.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAns1.setClickable(false);
                        btnTestAns2.setClickable(false);
                        btnTestAns3.setClickable(false);
                        btnTestAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnTestAns3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnTestAns3.getText())) {
                            btnTestAns3.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAns3.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAns1.setClickable(false);
                        btnTestAns2.setClickable(false);
                        btnTestAns3.setClickable(false);
                        btnTestAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnTestAns4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans.get(position).equals(btnTestAns4.getText())) {
                            btnTestAns4.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAns4.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAns1.setClickable(false);
                        btnTestAns2.setClickable(false);
                        btnTestAns3.setClickable(false);
                        btnTestAns4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                break;
            case 2:
                Boolean isClick = false;
                view = layoutInflater.inflate(R.layout.test_mean_ve_item, container, false);

                final TextView wordVE;
                final Button btnTestAnsVE4, btnTestAnsVE1, btnTestAnsVE2, btnTestAnsVE3;

                wordVE = view.findViewById(R.id.txtTestWordVE);
                btnTestAnsVE1 = view.findViewById(R.id.btnTestAnsVE1);
                btnTestAnsVE2 = view.findViewById(R.id.btnTestAnsVE2);
                btnTestAnsVE3 = view.findViewById(R.id.btnTestAnsVE3);
                btnTestAnsVE4 = view.findViewById(R.id.btnTestAnsVE4);


                wordVE.setText(ans.get(position));

                ansWordVE = new ArrayList<>();
                ansWordVE.add(test.get(position));

                for(int i = 0; i < 3; i++) {
                    Random rd = new Random();
                    int n;
                    n = rd.nextInt(test.size());

                    if (n != position) {
                        ansWordVE.add(test.get(n));
                    }
                    else {
                        n = rd.nextInt(test.size());
                        ansWordVE.add(test.get(n));
                    }
                }

                ansButon[0] = 0;
                ansButon[1] = 1;
                ansButon[2] = 2;
                ansButon[3] = 3;

                RandomizeArray(ansButon);

                btnTestAnsVE1.setText(ansWordVE.get(ansButon[0]));
                btnTestAnsVE2.setText(ansWordVE.get(ansButon[1]));
                btnTestAnsVE3.setText(ansWordVE.get(ansButon[2]));
                btnTestAnsVE4.setText(ansWordVE.get(ansButon[3]));

                btnTestAnsVE1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnTestAnsVE1.getText())) {
                            btnTestAnsVE1.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAnsVE1.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAnsVE1.setClickable(false);
                        btnTestAnsVE2.setClickable(false);
                        btnTestAnsVE3.setClickable(false);
                        btnTestAnsVE4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);

                    }
                });

                btnTestAnsVE2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnTestAnsVE2.getText())) {
                            btnTestAnsVE2.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAnsVE2.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAnsVE1.setClickable(false);
                        btnTestAnsVE2.setClickable(false);
                        btnTestAnsVE3.setClickable(false);
                        btnTestAnsVE4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnTestAnsVE3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnTestAnsVE3.getText())) {
                            btnTestAnsVE3.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAnsVE3.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAnsVE1.setClickable(false);
                        btnTestAnsVE2.setClickable(false);
                        btnTestAnsVE3.setClickable(false);
                        btnTestAnsVE4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                btnTestAnsVE4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnTestAnsVE4.getText())) {
                            btnTestAnsVE4.setTextColor(Color.rgb(31, 199, 74));
                            right++;
                        }
                        else {
                            btnTestAnsVE4.setTextColor(Color.rgb(255, 77, 69));
                            wrong++;
                        }
                        btnTestAnsVE1.setClickable(false);
                        btnTestAnsVE2.setClickable(false);
                        btnTestAnsVE3.setClickable(false);
                        btnTestAnsVE4.setClickable(false);

//                        pagerTest.setCurrentItem(position + 1);
                    }
                });

                break;
            default:
                layoutInflater.inflate(R.layout.test_mean_ve_item, container, false);
                break;
        }


        TextView txtTestPos = ((TestActivity) context).findViewById(R.id.txtTestPos);
        TextView txtTestRight = ((TestActivity) context).findViewById(R.id.txtTestRight);
        TextView txtTestWrong = ((TestActivity) context).findViewById(R.id.txtTestWrong);

        int posp = position + 1;
        String pos = posp + "/" + test.size();
        String rightAnwer = String.valueOf(right);
        String wrongAnwer = String.valueOf(wrong);
        txtTestPos.setText(pos);
        txtTestRight.setText(rightAnwer);
        txtTestWrong.setText(wrongAnwer);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}