package com.androidworld.antis.AntisApp.models;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by utbose on 6/23/2015.
 */
public class ProductListDataModel implements IModel {

    public ArrayList<Pair<String, String>> filtersList;

    public String headerCountText;

    public String headerDisplayText;

    public ArrayList<ItemViewModel> productList;
}
