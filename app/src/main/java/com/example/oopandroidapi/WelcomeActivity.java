package com.example.oopandroidapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WelcomeActivity extends AppCompatActivity {

    private RadioGroup rgHistory1;
    private RadioButton rbFirstCity1, rbSecondCity1, rbThirdCity1, rbForthCity1, rbFifthCity1;
    private EditText editTextSearch1;
    private Button buttonStart1, buttonStatistics1;
    private Toolbar toolbar1;
    private TextView textViewWelcome1, textViewMessage1;
    private ImageView imageViewSearch1;
    private ArrayList<String> mValidCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        initData();

        toolbar1 = findViewById(R.id.toolbar1);
        textViewWelcome1 = findViewById(R.id.textViewWelcome1);
        textViewMessage1 = findViewById(R.id.textViewMessage1);
        imageViewSearch1 = findViewById(R.id.imageViewSearch1);
        rgHistory1 = findViewById(R.id.rgHistory1);
        editTextSearch1 = findViewById(R.id.editTextSearch1);
        buttonStart1 = findViewById(R.id.buttonStart1);
        rbFirstCity1 = findViewById(R.id.rbFirstCity1);
        rbSecondCity1 = findViewById(R.id.rbSecondCity1);
        rbThirdCity1 = findViewById(R.id.rbThirdCity1);
        rbForthCity1 = findViewById(R.id.rbForthCity1);
        rbFifthCity1 = findViewById(R.id.rbFifthCity1);
        buttonStatistics1 = findViewById(R.id.buttonStatistics1);

        buttonStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSearch()) {
                    buttonStart1.setText("loading...");
                    buttonStart1.setEnabled(false);
                    getCityDataAndGoNextPage(editTextSearch1.getText().toString());
                    editTextSearch1.setText("");

                }
            }
        });


        rgHistory1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbFirstCity1:
                        editTextSearch1.setText(rbFirstCity1.getText().toString());
                        break;
                    case R.id.rbSecondCity1:
                        editTextSearch1.setText(rbSecondCity1.getText().toString());
                        break;
                    case R.id.rbThirdCity1:
                        editTextSearch1.setText(rbThirdCity1.getText().toString());
                        break;
                    case R.id.rbForthCity1:
                        editTextSearch1.setText(rbForthCity1.getText().toString());
                        break;
                    case R.id.rbFifthCity1:
                        editTextSearch1.setText(rbFifthCity1.getText().toString());
                        break;
                }
            }
        });

    }

    private void initData() {
        mValidCity = new ArrayList<>();
        mValidCity.add("Helsinki");
        mValidCity.add("Oulu");
        mValidCity.add("Lappeenranta");
        mValidCity.add("Rovaniemi");
        mValidCity.add("Lahti");
    }

    public boolean checkSearch() {
        String enterCity = editTextSearch1.getText().toString();
        if (TextUtils.isEmpty(enterCity)) {
            Toast.makeText(this, "Please enter city name", Toast.LENGTH_LONG).show();
            return false;
        }

        for (String city : mValidCity) {
            if (enterCity.equals(city)) {
                return true;
            }
        }

        Toast.makeText(this, "Please enter a valid city name", Toast.LENGTH_LONG).show();
        return false;
    }

    public void getCityDataAndGoNextPage(String city) {
        Context context = this;
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();

        // Here we fetch the municipality data in a background service, so that we do not disturb the UI
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                MunicipalityDataRetriever.getMunicipalityCodesMap();
                String employRates = municipalityDataRetriever.getEmploymentRatesData(context, city);
                ArrayList<PopulationData> populationDataArrayList = municipalityDataRetriever.getPopulationData(context, city);
                WorkData workData = municipalityDataRetriever.getWorkplaceAndEmploymentData(context, city);

                WeatherData weatherData = weatherDataRetriever.getData(city);

                //Keep a list of the population and all populations in 2020
                // When we want to update values we got from the API to the UI, we must do it inside runOnUiThread -method
                String dataString = "";
                if (populationDataArrayList != null) {
                    for (PopulationData data : populationDataArrayList) {
                        if (data.getYear() == 2022) {
                            dataString = data.getPopulation() + "";
                            break;
                        }
                    }
                }
                DataManager.getInstance().setPopulationData(dataString);
                DataManager.getInstance().setPopulationDataList(populationDataArrayList);

                //save weather
                String weatherDataAsString = "Weather Data No Found !";
                if (weatherData != null) {
                    weatherDataAsString = weatherData.getName() + "\n" +
                            "Weather now: " + weatherData.getMain() + "(" + weatherData.getDescription() + ")\n" +
                            "Temperature: " + weatherData.getTemperature() + "\n" +
                            "Wind speed: " + weatherData.getWindSpeed() + "\n";
                }
                DataManager.getInstance().setWeatherData(weatherDataAsString);

                //save work place data
                String workplaceSelfSufficiencyDataAsString = workData == null ? "Not Found!" : workData.getWorkplaceSelfSufficiency().toString();
                DataManager.getInstance().setWorkPlaceSelfSufficiencyData("Workplace self-sufficiency: " + workplaceSelfSufficiencyDataAsString);

                // Save user-entered city
                DataManager.getInstance().setCityData(city);

                //save employment rate
                DataManager.getInstance().setEmployRates(employRates);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonStart1.setEnabled(true);
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}