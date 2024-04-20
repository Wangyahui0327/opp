package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * This fragment is shown on the bottom of the screen all the time
 *
 */
public class QuizFragment extends Fragment {

    TextView textViewCityName7;
    Button buttonStart7;
    View v;

    public QuizFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quiz, container, false);

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
        textViewCityName7 = v.findViewById(R.id.textViewCityName7);
        buttonStart7 = v.findViewById(R.id.buttonStart7);


        textViewCityName7.setText(DataManager.getInstance().getCity());

        buttonStart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().startActivity(new Intent(requireActivity(), QuestionActivity.class));
            }
        });
    }
}