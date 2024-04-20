package com.example.oopandroidapi.quizFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.oopandroidapi.DataManager;
import com.example.oopandroidapi.R;


/**
 * This fragment is shown on the bottom of the screen all the time
 *
 */
public class Question3Fragment extends Fragment {

    Button buttonNext8;
    View v;
    private RadioGroup questionGroup;
    private RadioButton option1, option2, option3, option4, option5;
    private boolean isCorrect;


    public Question3Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiestion3_item, container, false);

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
        option1 = v.findViewById(R.id.option1);
        option2 = v.findViewById(R.id.option2);
        option3 = v.findViewById(R.id.option3);
        option4 = v.findViewById(R.id.option4);
        option5 = v.findViewById(R.id.option5);

        String city = DataManager.getInstance().getCity();
        DataManager.ChoiceListener choiceListener = DataManager.getInstance().getChoiceListener();

        questionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String optionTag = (String)v.findViewById(checkedId).getTag();

                isCorrect = optionTag.equals(city);

                Log.e("选择结果3", "是否正确 : " + isCorrect);
            }
        });

        buttonNext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceListener != null) {
                    if (isCorrect)
                        DataManager.getInstance().setScore(DataManager.getInstance().getScore() + 1);
                    choiceListener.onChoice(isCorrect, 3);
                }
            }
        });
    }
}