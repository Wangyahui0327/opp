package com.example.oopandroidapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private Toolbar toolbar8;
    private TextView textViewScreen8, textViewCounter8, textViewQuestion8;

    private ViewPager2 questionVp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        toolbar8 = findViewById(R.id.toolbar8);
        textViewScreen8 = findViewById(R.id.textViewScreen8);
        View imageButtonBack7 = findViewById(R.id.imageButtonBack7);

        questionVp = findViewById(R.id.questionVp);
        questionVp.setUserInputEnabled(false);
        questionVp.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        QuizPagerAdapter pagerAdapter = new QuizPagerAdapter(this);
        questionVp.setAdapter(pagerAdapter);
        DataManager.getInstance().setChoiceListener(new DataManager.ChoiceListener() {
            @Override
            public void onChoice(boolean isCorrect, int nextPosition) {
                questionVp.setCurrentItem(nextPosition, true);

            }
        });

        questionVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                tablayout.getTabAt(position).select();
            }
        });

        imageButtonBack7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    protected void onDestroy() {
        DataManager.getInstance().setScore(0);
        super.onDestroy();
    }
}
