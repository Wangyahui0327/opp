package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BasicInfoActivity extends AppCompatActivity {
    private Toolbar toolbar3;
    private ImageButton imageButtonBack3;
    private TextView textViewCity3, textViewPopulation3, textViewGetPopulation3,
            textViewWeather3, textViewGetWeather3, textViewSurface3, textViewGetSurface3,
            textViewScreen3;
    private ImageView imageViewCity3, imageViewWeather3;
    private CardView cardViewPopulation3, cardViewWeather3, cardViewSurface3;
    private Button buttonSeeMore3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        ImageButton imageButtonBack3 = findViewById(R.id.imageButtonBack3);
        TextView textViewCity3 = findViewById(R.id.textViewCity3);
        TextView textViewPopulation3 = findViewById(R.id.textViewPopulation3);
        TextView textViewGetPopulation3 = findViewById(R.id.textViewGetPopulation3);
        TextView textViewWeather3 = findViewById(R.id.textViewWeather3);
        TextView textViewGetWeather3 = findViewById(R.id.textViewGetWeather3);
        TextView textViewSurface3 = findViewById(R.id.textViewSurface3);
        TextView textViewGetSurface3 = findViewById(R.id.textViewGetSurface3);
        TextView textViewScreen3 = findViewById(R.id.textViewScreen3);
        ImageView imageViewCity3 = findViewById(R.id.imageViewCity3);
        ImageView imageViewWeather3 = findViewById(R.id.imageViewWeather3);
        CardView cardViewPopulation3 = findViewById(R.id.cardViewPopulation3);
        CardView cardViewWeather3 = findViewById(R.id.cardViewWeather3);
        CardView cardViewSurface3 = findViewById(R.id.cardViewSurface3);
        Button buttonSeeMore3 = findViewById(R.id.buttonSeeMore3);

        buttonSeeMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BasicInfoActivity.this, SeeMorePopulation.class);
                startActivity(intent);
            }
        });

        imageButtonBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BasicInfoActivity.this, SecondaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
