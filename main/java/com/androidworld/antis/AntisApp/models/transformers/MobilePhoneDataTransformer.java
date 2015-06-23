package com.androidworld.antis.AntisApp.models.transformers;

import android.util.Pair;

import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.ProductListDataModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by utbose on 6/23/2015.
 */
public class MobilePhoneDataTransformer implements IGenerator {

    public MobilePhoneDataTransformer(){

    }

    public IModel deserializeJson(JSONObject object) {

        ProductListDataModel productListDataModel= new ProductListDataModel();
        productListDataModel.filtersList = new ArrayList<Pair<String, String>>();
        productListDataModel.filtersList.add(new Pair<String, String>("id_price", "Price"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_brand", "Brand"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_rating", "Rating"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_camera", "Camera"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_battery", "Battery"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_perf", "Perf"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_rating", "Country"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_camera", "Size"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_battery", "Weight"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_perf", "Platform"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_rating", "Feel"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_camera", "Sound"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_battery", "Display"));
        productListDataModel.filtersList.add(new Pair<String, String>("id_perf", "Storage"));

        productListDataModel.headerCountText = "80";
        productListDataModel.headerDisplayText = "Phones";
        Gson jsonBuilder = new Gson();
        String output = jsonBuilder.toJson(productListDataModel);
        productListDataModel.productList = PhoneInfoDisplayCardGenerator.generateDummyModel(object);
        return productListDataModel;
    }
}
