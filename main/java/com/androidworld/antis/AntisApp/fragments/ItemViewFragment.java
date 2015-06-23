package com.androidworld.antis.AntisApp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.fragments.adapters.GenericListAdapter;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.ItemViewModel;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class ItemViewFragment extends Fragment implements IFragment  {

    public static final String ARG_POSITION = "ARG_POSITION";

    public ArrayList<ItemViewModel> mItemList;

    public ItemViewFragment() {
    }

    public void initialize(ArrayList<ItemViewModel> itemsList){
        this.mItemList = itemsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        Activity activity = getActivity();
        if(activity == null) {
            return rootView;
        }

        //this.mItemList = PhoneInfoDisplayCardGenerator.generateDummyModel(null);
        GenericListAdapter adapter = new GenericListAdapter(activity, this.mItemList);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void updateModel(IModel model){

    }
}
