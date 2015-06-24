package com.androidworld.antis.AntisApp.mappers;

import com.androidworld.antis.AntisApp.fragments.viewholders.PhoneDisplayCardViewHolder;
import com.androidworld.antis.AntisApp.fragments.viewholders.SearchPageViewHolder;
import com.androidworld.antis.AntisApp.models.SuggestionDataModel;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by utbose on 6/18/2015.
 */
public class ItemTypeToViewHolderMap {

    public static final Map<String, Class> ITEM_TYPE_TO_VIEW_HOLDER_MAP = new HashMap<String, Class>() {
        {
            put("PhoneInfoDisplayCard", PhoneDisplayCardViewHolder.class);
            put("searchpageDataModel", SearchPageViewHolder.class);
            put("suggestionDataModel", SuggestionDataModel.class);
        }
    };

    public static Class getClassTypeFromTileName(String key) {
        return (!StringUtilities.isNullOrWhitespace(key)
                && ITEM_TYPE_TO_VIEW_HOLDER_MAP.containsKey(key))
                ? ItemTypeToViewHolderMap.ITEM_TYPE_TO_VIEW_HOLDER_MAP.get(key) : null;
    }
}
