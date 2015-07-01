package com.androidworld.antis.AntisApp.mappers;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by utbose on 6/18/2015.
 */
public class ItemTypeToLayoutMap {

    public static final Map<String, Integer> ITEM_TYPE_TO_LAYOUT_MAP = new HashMap<String, Integer>() {
        {
            put("PhoneInfoDisplayCard", R.layout.phone_info_diplaycard_tile);
            put("searchpageDataModel", R.layout.search_product_card_layout);
            put("MessageBoxDisplayCard",R.layout.single_button_message_box_layout);
        }
    };

    public static int getLayoutFromItemType(String key) {
        return (!StringUtilities.isNullOrWhitespace(key)
                && ITEM_TYPE_TO_LAYOUT_MAP.containsKey(key))
                ? ITEM_TYPE_TO_LAYOUT_MAP.get(key) : R.layout.empty_item;
    }
}
