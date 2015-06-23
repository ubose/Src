package com.androidworld.antis.AntisApp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by utbose on 6/24/2015.
 */
public class GridViewFragment extends Fragment implements IFragment {

    public ArrayList<Image> mItemList;

    public GridViewFragment(){

    }

    public void initialize(ArrayList<Image> itemsList){
        this.mItemList = itemsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.trending_fragment, container, false);
        if(this.mItemList == null) {
            return rootView;
        }

        for(int i= 0; i < this.mItemList.size(); i+=2) {

            if(i+1 >= this.mItemList.size()) {
                continue;
            }

            Image image1 = this.mItemList.get(i);
            Image image2 = this.mItemList.get(i+1);
            View gridLayout = inflater.inflate(R.layout.trending_images_layout, container, false);
            ImageView imageView = (ImageView) gridLayout.findViewById(R.id.first_image);
            Picasso.with(getActivity()).load(image1.src).fit().into(imageView);

            imageView = (ImageView) gridLayout.findViewById(R.id.second_image);
            Picasso.with(getActivity()).load(image2.src).fit().into(imageView);
            rootView.addView(gridLayout);
        }

        return rootView;
    }

    @Override
    public void updateModel(IModel model){

    }
}
