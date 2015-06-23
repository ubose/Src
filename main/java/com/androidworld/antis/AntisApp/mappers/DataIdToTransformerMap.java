package com.androidworld.antis.AntisApp.mappers;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.transformers.HomePageDataTransformer;
import com.androidworld.antis.AntisApp.models.transformers.MobilePhoneDataTransformer;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by utbose on 6/22/2015.
 */
public class DataIdToTransformerMap {

    public static final Map<String, Class> DATA_ID_TO_TRANSFORMER_MAP = new HashMap<String, Class>() {
        {
            put("homepage", HomePageDataTransformer.class);
            put("android_mobiles", MobilePhoneDataTransformer.class);
        }
    };

    public static Class getTransformerFormDataId(String key) {
        return (!StringUtilities.isNullOrWhitespace(key)
                && DATA_ID_TO_TRANSFORMER_MAP.containsKey(key))
                ? DATA_ID_TO_TRANSFORMER_MAP.get(key) : null;
    }
}
