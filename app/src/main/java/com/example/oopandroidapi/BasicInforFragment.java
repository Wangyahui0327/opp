package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * This fragment is shown on the bottom of the screen all the time
 *
 */
public class BasicInforFragment extends Fragment {

    TextView lblImportantNotes;
    TextView workStatisticsTv;
    TextView cityTv, employmentRatesTv;
    TextView populationTv;
    TextView weatherTv;
    ImageView HeadImg;
    Button seeMoreBtn;
    View v;

    public BasicInforFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_basic_info, container, false);

        updateImportantNotes();
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        String importantNotes = ListNotes.getInstance().getImportantNotes();

        lblImportantNotes.setText(importantNotes);
        populationTv.setText(DataManager.getInstance().getPopulationDataAsString());
        weatherTv.setText(DataManager.getInstance().getWeatherDataAsString());
        workStatisticsTv.setText(DataManager.getInstance().getWorkPlaceSelfSufficiencyDataAsString());
        employmentRatesTv.setText(DataManager.getInstance().getEmployRates() + "%");
        String city = DataManager.getInstance().getCity();
        cityTv.setText(city);
        switch (city) {
            case "Helsinki":
                HeadImg.setImageResource(R.drawable.helsinki);
                break;
            case "Oulu":
                HeadImg.setImageResource(R.drawable.oulu);
                break;
            case "Lappeenranta":
                HeadImg.setImageResource(R.drawable.lappeenranta);
                break;
            case "Rovaniemi":
                HeadImg.setImageResource(R.drawable.rovaniemi);
                break;
            case "Lahti":
                HeadImg.setImageResource(R.drawable.lahti);
                break;
        }

    }

    /**
     * This method updates the list in the bottom fragment, it is called when tab
     * is changed in MainActivity
     */
    public void updateImportantNotes() {
        lblImportantNotes = v.findViewById(R.id.textImportant);
        cityTv = v.findViewById(R.id.cityTv);
        employmentRatesTv = v.findViewById(R.id.employmentRatesTv);
        populationTv = v.findViewById(R.id.populationTv);
        weatherTv = v.findViewById(R.id.weatherTv);
        workStatisticsTv = v.findViewById(R.id.workStatisticsTv);
        HeadImg = v.findViewById(R.id.HeadImg);
        seeMoreBtn = v.findViewById(R.id.seeMoreBtn);

        seeMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SeeMorePopulation.class);
                startActivity(intent);
            }
        });

    }

    public void onSeeMoreButtonClick(View view) {

    }
}