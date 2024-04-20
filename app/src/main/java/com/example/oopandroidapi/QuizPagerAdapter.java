package com.example.oopandroidapi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.oopandroidapi.quizFragment.FinalScoreFragment;
import com.example.oopandroidapi.quizFragment.Question10Fragment;
import com.example.oopandroidapi.quizFragment.Question1Fragment;
import com.example.oopandroidapi.quizFragment.Question2Fragment;
import com.example.oopandroidapi.quizFragment.Question3Fragment;
import com.example.oopandroidapi.quizFragment.Question4Fragment;
import com.example.oopandroidapi.quizFragment.Question5Fragment;
import com.example.oopandroidapi.quizFragment.Question6Fragment;
import com.example.oopandroidapi.quizFragment.Question7Fragment;
import com.example.oopandroidapi.quizFragment.Question8Fragment;
import com.example.oopandroidapi.quizFragment.Question9Fragment;

public class QuizPagerAdapter extends FragmentStateAdapter {
    public QuizPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new Question1Fragment();
            case 1:
                return new Question2Fragment();
            case 2:
                return new Question3Fragment();
            case 3:
                return new Question4Fragment();
            case 4:
                return new Question5Fragment();
            case 5:
                return new Question6Fragment();
            case 6:
                return new Question7Fragment();
            case 7:
                return new Question8Fragment();
            case 8:
                return new Question9Fragment();
            case 9:
                return new Question10Fragment();
            case 10:
                return new FinalScoreFragment();
            default:
                return new FinalScoreFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 11;
    }
}
