package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    private Toolbar toolbar9;
    private TextView textViewScreen9, textViewScore9, textViewMessage9;
    private Button buttonExit9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        toolbar9 = findViewById(R.id.toolbar9);
        textViewScreen9 = findViewById(R.id.textViewScreen9);
        textViewScore9 = findViewById(R.id.textViewScore9);
        textViewMessage9 = findViewById(R.id.textViewMessage9);
        buttonExit9 = findViewById(R.id.buttonExit9);

        buttonExit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, SecondaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
