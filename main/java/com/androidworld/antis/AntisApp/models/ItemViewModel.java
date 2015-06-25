package com.androidworld.antis.AntisApp.models;

import com.androidworld.antis.AntisApp.enums.ViewItemState;

/**
 * Created by utbose on 6/18/2015.
 */
public class ItemViewModel extends BaseModel {

    public Object item;

    public String type;

    public String clickTarget;

    public ViewItemState viewItemState;

    public ItemViewModel(Object object, String itemType, String target) {
        this.item = object;
        this.type = itemType;
        this.clickTarget = target;
        this.viewItemState = ViewItemState.NONE;
    }
}
