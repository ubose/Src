package com.androidworld.antis.AntisApp.models.transformers;

import com.androidworld.antis.AntisApp.models.IModel;

import org.json.JSONObject;

/**
 * Created by utbose on 6/18/2015.
 */
public interface IGenerator {

    public IModel deserializeJson(JSONObject object);
}
