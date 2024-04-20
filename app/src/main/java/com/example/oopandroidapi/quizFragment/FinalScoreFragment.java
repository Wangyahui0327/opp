package com.example.oopandroidapi.quizFragment;

import android.os.Bundle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.oopandroidapi.DataManager;
import com.example.oopandroidapi.R;


/**
 * This fragment is shown on the bottom of the screen all the time
 *
 */
public class FinalScoreFragment extends Fragment {

    Button buttonNext8;
    View v;
    private TextView finalScoreTv;

    public FinalScoreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiz_finalscore, container, false);

        initView();
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        finalScoreTv.setText(DataManager.getInstance().getScore() + "");
    }

    /**
     * This method updates the list in the bottom fragment, it is called when tab
     * is changed in MainActivity
     */
    public void initView() {
        buttonNext8 = v.findViewById(R.id.buttonNext8);
        finalScoreTv = v.findViewById(R.id.finalScoreTv);



        DataManager.ChoiceListener choiceListener = DataManager.getInstance().getChoiceListener();


        buttonNext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
    }
}