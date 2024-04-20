package com.example.oopandroidapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 */
public class SearchActivity extends AppCompatActivity {

    private TextView txtPopulation;
    private TextView txtWeather;
    private TextView txtStatistics;

    private EditText editMunicipalityName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        txtPopulation = findViewById(R.id.txtPopulation);
        txtWeather = findViewById(R.id.txtWeather);
        editMunicipalityName = findViewById(R.id.editMunicipalityName);
        txtStatistics = findViewById(R.id.workStatistics);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onSearchButtonClick(View view) {
        Context context = this;
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();

        // Here we fetch the municipality data in a background service, so that we do not disturb the UI
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                MunicipalityDataRetriever.getMunicipalityCodesMap();
                                ArrayList<PopulationData> municipalityDataArrayList = municipalityDataRetriever.getPopulationData(context, editMunicipalityName.getText().toString());

                                if (municipalityDataArrayList == null) {
                                    return;
                                }
                                WorkData workData = municipalityDataRetriever.getWorkplaceAndEmploymentData(context, editMunicipalityName.getText().toString());


                                WeatherData weatherData = weatherDataRetriever.getData(editMunicipalityName.getText().toString());

                                // When we want to update values we got from the API to the UI, we must do it inside runOnUiThread -method
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String dataString = "";
                                        for (PopulationData data: municipalityDataArrayList) {
                                            dataString = dataString + data.getYear() + ": " + data.getPopulation() + "\n";
                                        }
                                        txtPopulation.setText(dataString);

                                        String weatherDataAsString = "Weather Data No Found !";
                                        if (weatherData != null) {
                                            weatherDataAsString = weatherData.getName() + "\n" +
                                                    "Weather now: " + weatherData.getMain() + "(" + weatherData.getDescription() + ")\n" +
                                                    "Temperature: " + weatherData.getTemperature() + "\n" +
                                                    "Wind speed: " + weatherData.getWindSpeed() + "\n";
                                        }
                                        txtWeather.setText(weatherDataAsString);
                                        String statisticsContext = workData == null? "Not Found!" : workData.getWorkplaceSelfSufficiency().toString();
                                        txtStatistics.setText("Workplace self-sufficiency: " + statisticsContext);
                                    }
                                });                            }
                        }
        );
    }



}
