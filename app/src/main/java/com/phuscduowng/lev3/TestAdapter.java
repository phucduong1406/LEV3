package com.phuscduowng.lev3;

import android.content.Context;
import android.graphics.Color;
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

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestAdapter extends PagerAdapter {

    private List<String> test, ans, ans1, ans2, ans3, ans4;
    private LayoutInflater layoutInflater;
    private Context context;
    public int right = 0, wrong = 0;
    TextToSpeech toSpeech;
    private static final int REQUEST_CODE = 111;

    List<String> ansMeanEV, ansWordListening;
    int[] ansButon = new int[4];

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

                        imgListeningWord.playAnimation();
                        imgListeningWord.setRepeatCount(3);
                        imgListeningWord.setSpeed(15);
                    }

                });


                ansWordListening = new ArrayList<>();
                ansWordListening.add(test.get(position));
                for(int i = 0; i < 3; i++) {
                    Random rd = new Random();
                    int n;
                    n = rd.nextInt(test.size());

                    if (n != position) {
                        ansWordListening.add(test.get(n));
                    }
                    else {
                        n = rd.nextInt(ans.size());
                        ansWordListening.add(test.get(n));
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
                        if (test.get(position).equals(btnListeningAns1.getText())) {
                            btnListeningAns1.setBackgroundResource(R.drawable.bg_button_test_green);
//                    btnTestAns1.setTextColor(Color.rgb(0, 0, 0));
                            right++;
                        }
                        else {
                            btnListeningAns1.setBackgroundResource(R.drawable.bg_button_test_red);

                            wrong++;
                        }
                    }
                });

                btnListeningAns2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnListeningAns2.getText())) {
                            btnListeningAns2.setBackgroundResource(R.drawable.bg_button_test_green);
                            right++;
                        }
                        else {
                            btnListeningAns2.setBackgroundResource(R.drawable.bg_button_test_red);
                            wrong++;
                        }
                    }
                });

                btnListeningAns3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnListeningAns3.getText())) {
                            btnListeningAns3.setBackgroundResource(R.drawable.bg_button_test_green);
                            right++;
                        }
                        else {
                            btnListeningAns3.setBackgroundResource(R.drawable.bg_button_test_red);
                            wrong++;
                        }
                    }
                });

                btnListeningAns4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (test.get(position).equals(btnListeningAns4.getText())) {
                            btnListeningAns4.setBackgroundResource(R.drawable.bg_button_test_green);
                            right++;
                        }
                        else {
                            btnListeningAns4.setBackgroundResource(R.drawable.bg_button_test_red);
                            wrong++;
                        }
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
                            btnTestAns1.setBackgroundResource(R.drawable.bg_button_test_green);
//                    btnTestAns1.setTextColor(Color.rgb(0, 0, 0));
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

                break;
            case 2:
                layoutInflater.inflate(R.layout.test_mean_ve_item, container, false);



                break;
            case 3:
                layoutInflater.inflate(R.layout.test_writing_item, container, false);
                break;
            default:
                layoutInflater.inflate(R.layout.test_mean_ev_item, container, false);
                break;
        }

        Toast.makeText(context, position + "/" + test.size(), Toast.LENGTH_SHORT).show();

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