package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparisonActivity extends AppCompatActivity {
    private Toolbar toolbar6;
    private ImageButton imageButtonBack6;
    private TextView textViewPopulation6, textViewSurface6, textViewWeather6, textViewScreen6, textEmploymentRates7,
            textViewPopulationComparison6, textViewSurfaceComparison6, textViewWeatherComparison6, textViewEmploymentComparison6, loadDataTv, city1Tv, city2Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        toolbar6 = findViewById(R.id.toolbar6);
        imageButtonBack6 = findViewById(R.id.imageButtonBack6);
        textViewPopulation6 = findViewById(R.id.textViewPopulation6);
        textViewSurface6 = findViewById(R.id.textViewSurface6);
        textViewWeather6 = findViewById(R.id.textViewWeather6);
        textViewScreen6 = findViewById(R.id.textViewScreen6);
        textEmploymentRates7 = findViewById(R.id.textEmploymentRates7);
        loadDataTv = findViewById(R.id.loadDataTv);
        city1Tv = findViewById(R.id.city1Tv);
        city2Tv = findViewById(R.id.city2Tv);
        textViewPopulationComparison6 = findViewById(R.id.textViewPopulationComparison6);
        textViewSurfaceComparison6 = findViewById(R.id.textViewSurfaceComparison6);
        textViewWeatherComparison6 = findViewById(R.id.textViewWeatherComparison6);
        textViewEmploymentComparison6 = findViewById(R.id.textViewEmploymentComparison6);

        imageButtonBack6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadData();
    }

    private void loadData() {
        String city1 = getIntent().getStringExtra("city1");
        String city2 = getIntent().getStringExtra("city2");
        startCompare(city1, city2);
    }

    private void startCompare(String selectedCity1, String selectedCity2) {
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();

        // Here we fetch the municipality data in a background service, so that we do not disturb the UI
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                MunicipalityDataRetriever.getMunicipalityCodesMap();


                                // Get data for the first city
                                ArrayList<PopulationData> populationDataArrayList = municipalityDataRetriever.getPopulationData(ComparisonActivity.this, selectedCity1);
                                WorkData workData = municipalityDataRetriever.getWorkplaceAndEmploymentData(ComparisonActivity.this, selectedCity1);
                                WeatherData weatherData = weatherDataRetriever.getData(selectedCity1);
                                String employmentRatesData = municipalityDataRetriever.getEmploymentRatesData(ComparisonActivity.this, selectedCity1);

                                String city1Population = "";
                                if (populationDataArrayList != null) {
                                    for (PopulationData data : populationDataArrayList) {
                                        if (data.getYear() == 2022) {
                                            city1Population = data.getPopulation() + "";
                                            break;
                                        }
                                    }
                                }

                                String city1Weather = "Weather Data No Found !";
                                if (weatherData != null) {
                                    city1Weather = weatherData.getName() + "\n" +
                                            "Weather now: " + weatherData.getMain() + "(" + weatherData.getDescription() + ")\n" +
                                            "Temperature: " + weatherData.getTemperature() + "\n" +
                                            "Wind speed: " + weatherData.getWindSpeed() + "\n";
                                }
                                String statisticsContext = workData == null ? "Not Found!" : workData.getWorkplaceSelfSufficiency().toString();
                                String city1Statistics = ("Workplace self-sufficiency: " + statisticsContext);


                                // Get data for the second city
                                ArrayList<PopulationData> populationDataArrayList2 = municipalityDataRetriever.getPopulationData(ComparisonActivity.this, selectedCity2);
                                WorkData workData2 = municipalityDataRetriever.getWorkplaceAndEmploymentData(ComparisonActivity.this, selectedCity2);
                                WeatherData weatherData2 = weatherDataRetriever.getData(selectedCity2);
                                String employmentRatesData2 = municipalityDataRetriever.getEmploymentRatesData(ComparisonActivity.this, selectedCity2);

                                String city1Population2 = "";
                                if (populationDataArrayList2 != null) {
                                    for (PopulationData data : populationDataArrayList2) {
                                        if (data.getYear() == 2022) {
                                            city1Population2 = data.getPopulation() + "";
                                            break;
                                        }
                                    }
                                }

                                String city1Weather2 = "Weather Data No Found !";
                                if (weatherData2 != null) {
                                    city1Weather2 = weatherData2.getName() + "\n" +
                                            "Weather now: " + weatherData2.getMain() + "(" + weatherData2.getDescription() + ")\n" +
                                            "Temperature: " + weatherData2.getTemperature() + "\n" +
                                            "Wind speed: " + weatherData2.getWindSpeed() + "\n";
                                }
                                String statisticsContext2 = workData2 == null ? "Not Found!" : workData2.getWorkplaceSelfSufficiency().toString();
                                String city1Statistics2 = ("Workplace self-sufficiency: " + statisticsContext2);

                                // When we want to update values we got from the API to the UI, we must do it inside runOnUiThread -method
                                String finalCity1Population = city1Population;
                                String finalCity1Population1 = city1Population2;
                                String finalCity1Weather = city1Weather;
                                String finalCity1Weather1 = city1Weather2;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textViewPopulation6.setText(finalCity1Population);
                                        textViewPopulationComparison6.setText(finalCity1Population1);

                                        textViewSurface6.setText(city1Statistics);
                                        textViewSurfaceComparison6.setText(city1Statistics2);

                                        textViewWeather6.setText(finalCity1Weather);
                                        textViewWeatherComparison6.setText(finalCity1Weather1);

                                        city1Tv.setText(selectedCity1);
                                        city2Tv.setText(selectedCity2);

                                        textEmploymentRates7.setText(employmentRatesData);
                                        textViewEmploymentComparison6.setText(employmentRatesData2);

                                        loadDataTv.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }
        );
    }
}
