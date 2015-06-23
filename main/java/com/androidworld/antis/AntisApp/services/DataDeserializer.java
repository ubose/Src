package com.androidworld.antis.AntisApp.services;

import com.androidworld.antis.AntisApp.mappers.DataIdToTransformerMap;
import com.androidworld.antis.AntisApp.models.transformers.IGenerator;

import org.json.JSONObject;

/**
 * Created by utbose on 6/22/2015.
 */
public class DataDeserializer {

    public Object getObjectModel(String result, String dataTransformId){
        Class transformerClass = DataIdToTransformerMap.getTransformerFormDataId(dataTransformId);
        if (transformerClass == null) {
            return null;
        }

        try {
            //JSONObject jsonObject = new JSONObject(result);
            IGenerator modelGenerator = (IGenerator) transformerClass.newInstance();
            return modelGenerator.deserializeJson(new JSONObject());
        } catch (Exception e) {
            return null;
        }
    }
}
