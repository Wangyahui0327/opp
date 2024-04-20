package com.example.oopandroidapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * This fragment is shown on the bottom of the screen all the time
 */
public class ComparisonFragment extends Fragment {
    private RadioGroup rgHistory1, rgHistory2;
    private RadioButton rbFirstCity1, rbSecondCity1, rbThirdCity1, rbForthCity1, rbFifthCity1;
    private RadioButton rbFirstCity2, rbSecondCity2, rbThirdCity2, rbForthCity2, rbFifthCity2;

    private Button compareBtn;
    private ScrollView scrollView;
    private TextView recordTv;
    private String selectedCity1 = "";
    private String selectedCity2 = "";
    TextView lblImportantNotes;
    View v;

    public ComparisonFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_comparison, container, false);

        initView();
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * This method updates the list in the bottom fragment, it is called when tab
     * is changed in MainActivity
     */
    public void initView() {
        rgHistory1 = v.findViewById(R.id.rgHistory1);
        rbFirstCity1 = v.findViewById(R.id.rbFirstCity1);
        rbSecondCity1 = v.findViewById(R.id.rbSecondCity1);
        rbThirdCity1 = v.findViewById(R.id.rbThirdCity1);
        rbForthCity1 = v.findViewById(R.id.rbForthCity1);
        rbFifthCity1 = v.findViewById(R.id.rbFifthCity1);

        rgHistory2 = v.findViewById(R.id.rgHistory2);
        rbFirstCity2 = v.findViewById(R.id.rbFirstCity2);
        rbSecondCity2 = v.findViewById(R.id.rbSecondCity2);
        rbThirdCity2 = v.findViewById(R.id.rbThirdCity2);
        rbForthCity2 = v.findViewById(R.id.rbForthCity2);
        rbFifthCity2 = v.findViewById(R.id.rbFifthCity2);

        recordTv = v.findViewById(R.id.recordTv);

        compareBtn = v.findViewById(R.id.compareBtn);
        scrollView = v.findViewById(R.id.scrollView);

        rgHistory1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbFirstCity1:
                        String city = rbFirstCity1.getText().toString();
                        selectedCity1 = city;
                        if (checkConflict(city, selectedCity2)) {
                            rbSecondCity2.performClick();
                        }
                        break;
                    case R.id.rbSecondCity1:
                        String secondCity = rbSecondCity1.getText().toString();
                        selectedCity1 = secondCity;
                        if (checkConflict(secondCity, selectedCity2)) {
                            rbThirdCity2.performClick();
                        }
                        break;
                    case R.id.rbThirdCity1:
                        String thirdCity = rbThirdCity1.getText().toString();
                        selectedCity1 = thirdCity;
                        if (checkConflict(thirdCity, selectedCity2)) {
                            rbForthCity2.performClick();
                        }
                        break;
                    case R.id.rbForthCity1:
                        String forthCity = rbForthCity1.getText().toString();
                        selectedCity1 = forthCity;
                        if (checkConflict(forthCity, selectedCity2)) {
                            rbFifthCity2.performClick();
                        }
                        break;
                    case R.id.rbFifthCity1:
                        String fifthCity = rbFifthCity1.getText().toString();
                        selectedCity1 = fifthCity;
                        if (checkConflict(fifthCity, selectedCity2)) {
                            rbFirstCity2.performClick();
                        }
                        break;
                }
            }
        });

        rgHistory2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbFirstCity2:
                        String city1 = rbFirstCity2.getText().toString();
                        selectedCity2 = city1;
                        if (checkConflict(city1, selectedCity1)) {
                            rbSecondCity1.performClick();
                        }
                        break;
                    case R.id.rbSecondCity2:
                        String city2 = rbSecondCity2.getText().toString();
                        selectedCity2 = city2;
                        if (checkConflict(city2, selectedCity1)) {
                            rbThirdCity1.performClick();
                        }
                        break;
                    case R.id.rbThirdCity2:
                        String city3 = rbThirdCity2.getText().toString();
                        selectedCity2 = city3;
                        if (checkConflict(city3, selectedCity1)) {
                            rbForthCity1.performClick();
                        }
                        break;
                    case R.id.rbForthCity2:
                        String city4 = rbForthCity2.getText().toString();
                        selectedCity2 = city4;
                        if (checkConflict(city4, selectedCity1)) {
                            rbFifthCity1.performClick();
                        }
                        break;
                    case R.id.rbFifthCity2:
                        String city5 = rbFifthCity2.getText().toString();
                        selectedCity2 = city5;
                        if (checkConflict(city5, selectedCity1)) {
                            rbFirstCity1.performClick();
                        }
                        break;
                }
            }
        });

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireActivity(), ComparisonActivity.class);
                intent.putExtra("city1", selectedCity1);
                intent.putExtra("city2", selectedCity2);
                requireActivity().startActivity(intent);

                recordCompare();
            }
        });

        rbFirstCity1.performClick();
        rbSecondCity2.performClick();
        scrollView.post(new Runnable() {

            public void run() {

                scrollView.fullScroll(View.FOCUS_DOWN);

            }

        });


    }

    private void recordCompare() {
        String lastStr = recordTv.getText().toString().trim();
        lastStr =  selectedCity1 + " - " + selectedCity2  + "\n" +  lastStr;
        recordTv.setText(lastStr);
    }

    private boolean checkConflict(String city1, String city2) {
        return city2.equals(city1);
    }

}