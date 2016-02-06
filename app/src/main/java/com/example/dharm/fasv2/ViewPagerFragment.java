package com.example.dharm.fasv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPagerFragment is a generic fragment class that will be used for each page of the ViewPager.
 */
public class ViewPagerFragment extends Fragment {

    /**
     * Empty constructor for the PopularFragment class.
     */
    public ViewPagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}