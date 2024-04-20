package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {
    private Toolbar toolbar2;
    private ImageButton imageButtonBack2;
    private TextView textViewCityName2;
    private Button buttonBasicInfo2;
    private Button buttonCompare2;
    private Button buttonQuiz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        ImageButton imageButtonBack2 = findViewById(R.id.imageButtonBack2);
        TextView textViewCityName2 = findViewById(R.id.textViewCityName2);
        Button buttonBasicInfo2 = findViewById(R.id.buttonBasicInfo2);
        Button buttonCompare2 = findViewById(R.id.buttonCompare2);
        Button buttonQuiz2 = findViewById(R.id.buttonQuiz2);

        buttonBasicInfo2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondaryActivity.this, BasicInfoActivity.class);
                startActivity(intent);
                }
        });

        buttonCompare2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondaryActivity.this, CompareActivity.class);
                startActivity(intent);
            }
        });

        buttonQuiz2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondaryActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        imageButtonBack2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondaryActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
