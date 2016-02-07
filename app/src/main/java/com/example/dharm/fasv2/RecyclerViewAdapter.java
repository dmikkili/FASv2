package com.example.dharm.fasv2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DoodleViewHolder> {
    private List<DoodleData> mDoodleData;

    public RecyclerViewAdapter() { this.mDoodleData = new ArrayList<>(); }

    @Override
    public int getItemCount() { return mDoodleData.size(); }

    @Override
    public void onBindViewHolder(DoodleViewHolder doodleViewHolder, int position) {
        DoodleData doodleData= mDoodleData.get(position);
        doodleViewHolder.vTitle.setText(doodleData.getTitle());

    }

    @Override
    public DoodleViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.doodle_layout, viewGroup, false);
        return new DoodleViewHolder(itemView);
    }

    public static class DoodleViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitle;

        public DoodleViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    public void SetDoodleData(ArrayList<DoodleData> doodleDataArrayList) {
        this.mDoodleData = doodleDataArrayList;
    }
}
