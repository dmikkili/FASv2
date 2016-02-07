package com.example.dharm.fasv2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.DoodleViewHolder> {
    private Context mContext;
    private List<DoodleData> mDoodleData;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mDoodleData = new ArrayList<>();
    }

    @Override
    public int getItemCount() { return mDoodleData.size(); }

    @Override
    public void onBindViewHolder(DoodleViewHolder doodleViewHolder, int position) {
        DoodleData doodleData= mDoodleData.get(position);
        Picasso.with(mContext).load(mDoodleData.get(position).getImageUrl()).noFade()
                .into(doodleViewHolder.imageView);
        doodleViewHolder.titleView.setText(doodleData.getTitle());

    }

    @Override
    public DoodleViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.doodle_layout, viewGroup, false);
        return new DoodleViewHolder(itemView);
    }

    public static class DoodleViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView titleView;

        public DoodleViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            titleView = (TextView) view.findViewById(R.id.title);
        }
    }

    public void SetDoodleData(ArrayList<DoodleData> doodleDataArrayList) {
        mDoodleData = doodleDataArrayList;
    }
}
