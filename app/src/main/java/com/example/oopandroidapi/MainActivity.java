package com.example.oopandroidapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity  {

    BasicInforFragment basicInforFragment = new BasicInforFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tablayout = findViewById(R.id.tabLayout);
        ViewPager2 fragmentArea = findViewById(R.id.viewArea);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        fragmentArea.setAdapter(tabPagerAdapter);


        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentArea.setCurrentItem(tab.getPosition());

//                getSupportFragmentManager().beginTransaction().replace(R.id.bottomFrame, basicInforFragment).commit();

                // Update the bottom fragment information
//                basicInforFragment.updateImportantNotes();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                basicInforFragment.updateImportantNotes();


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                basicInforFragment.updateImportantNotes();

            }
        });
        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tablayout.getTabAt(position).select();
            }
        });
    }

}