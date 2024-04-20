package com.example.oopandroidapi;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private final List<View> listView;

    //Add a Constructor
    public ViewPagerAdapter(List<View> viewList){
        this.listView = viewList;
    }

    //Return the number of views in the list
    @Override
    public int getCount() {
        return listView.size();
    }


    //Add a View to a Page Flip Control
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        container.addView(listView.get(position));

        return listView.get(position);
    }

    //Remove the view of the page flip control
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(listView.get(position));

    }

    // Determine if a view is an object
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
