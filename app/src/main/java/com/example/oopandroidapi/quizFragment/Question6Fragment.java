package com.example.oopandroidapi.quizFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.oopandroidapi.DataManager;
import com.example.oopandroidapi.R;


/**
 * This fragment is shown on the bottom of the screen all the time
 *
 */
public class Question6Fragment extends Fragment {

    Button buttonNext8;
    View v;
    RadioGroup questionGroup;
    boolean isCorrect;

    public Question6Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiestion6_item, container, false);

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
        buttonNext8 = v.findViewById(R.id.buttonNext8);
        questionGroup = v.findViewById(R.id.questionGroup);
        String city = DataManager.getInstance().getCity();
        DataManager.ChoiceListener choiceListener = DataManager.getInstance().getChoiceListener();

        questionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.option1:
                        isCorrect = city.equals("Lappeenranta") || city.equals("Rovaniemi");
                        break;
                    case R.id.option2:
                        isCorrect = city.equals("Helsinki") || city.equals("Oulu");
                        break;
                    case R.id.option3:
                        isCorrect = city.equals("Lahti");
                        break;
                }

                Log.e("选择结果6", "是否正确 : " + isCorrect);
            }
        });
        buttonNext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceListener != null) {
                    if (isCorrect)
                        DataManager.getInstance().setScore(DataManager.getInstance().getScore() + 1);
                    choiceListener.onChoice(isCorrect, 6);
                }
            }
        });
    }
}