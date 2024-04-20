package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private Toolbar toolbar7;
    private TextView textViewScreen7, textViewCityName7, textViewMessage7;
    private ImageButton imageButtonBack7;
    private Button buttonStart7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar7 = findViewById(R.id.toolbar7);
        ImageButton imageButtonBack7 = findViewById(R.id.imageButtonBack7);
        TextView textViewScreen7 = findViewById(R.id.textViewScreen7);
        TextView textViewCityName7 = findViewById(R.id.textViewCityName7);
        TextView textViewMessage7 = findViewById(R.id.textViewMessage7);
        Button buttonStart7 = findViewById(R.id.buttonStart7);

        buttonStart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        imageButtonBack7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, SecondaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
