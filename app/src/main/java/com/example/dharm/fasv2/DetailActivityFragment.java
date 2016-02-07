package com.example.dharm.fasv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_activity_fragment, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null) {
            final DoodleData doodleData = intent.getParcelableExtra("doodleData");
            ImageView doodle_image = (ImageView) view.findViewById(R.id.image);
            Picasso.with(getActivity()).load(doodleData.getImageUrl()).noFade().into(doodle_image);

            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(doodleData.getTitle());
        }

        return view;
    }


}
