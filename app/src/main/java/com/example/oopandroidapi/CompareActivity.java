package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class CompareActivity extends AppCompatActivity {
    private Toolbar toolbar5;
    private ImageButton imageButtonBack5;
    private TextView textViewChooseCity5, textViewCityOne5, textViewScreen5;
    private EditText editTextSearchCity5;
    private ImageView imageViewSearch5, imageViewCityOne5;
    private Button buttonComparison5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        Toolbar toolbar5 = findViewById(R.id.toolbar5);
        ImageButton imageButtonBack5 = findViewById(R.id.imageButtonBack5);
        TextView textViewChooseCity5 = findViewById(R.id.textViewChooseCity5);
        TextView textViewCityOne5 = findViewById(R.id.textViewCityOne5);
        TextView textViewScreen5 = findViewById(R.id.textViewScreen5);
        EditText editTextSearchCity5 = findViewById(R.id.editTextSearchCity5);
        ImageView imageViewSearch5 = findViewById(R.id.imageViewSearch5);
        ImageView imageViewCityOne5 = findViewById(R.id.imageViewCityOne5);
        Button buttonComparison5 = findViewById(R.id.buttonComparison5);

        imageButtonBack5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompareActivity.this, SecondaryActivity.class);
                startActivity(intent);
            }
        });
    }


}
