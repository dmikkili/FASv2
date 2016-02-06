package com.example.dharm.fasv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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

        // Dynamically generate the number of columns to have in the grid view based on the device
        // the user has.
        float scaleFactor = getResources().getDisplayMetrics().density * 100;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int columns = (int) ((float) displayMetrics.widthPixels / scaleFactor) / 2;
        if (columns == 0 || columns == 1) {
            columns = 2;
        }


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_pager_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.recycler_view_in_fragment);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(),
                columns);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);

        ArrayList<DoodleData> doodleDataList = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            DoodleData temp = new DoodleData("omega", "some really long title", "omega2", "omega3",
                    "omega4", "omega5");
            doodleDataList.add(temp);
        }
        RecyclerViewAdapter rva = new RecyclerViewAdapter(doodleDataList);
        recyclerView.setAdapter(rva);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}