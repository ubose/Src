package com.androidworld.antis.AntisApp.fragments.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.fragments.viewholders.BaseViewHolder;
import com.androidworld.antis.AntisApp.mappers.ItemTypeToLayoutMap;
import com.androidworld.antis.AntisApp.mappers.ItemTypeToViewHolderMap;
import com.androidworld.antis.AntisApp.models.BaseModel;
import com.androidworld.antis.AntisApp.models.ItemViewModel;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class GenericListAdapter extends ArrayAdapter<ItemViewModel> {

    protected LayoutInflater mLayoutInflater;

    protected Context mContext;

    public GenericListAdapter(Context context, ArrayList<ItemViewModel> itemsList) {
        super(context, 0, itemsList);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String itemType = getItemType(position);
        View view = getCurrentView(getItem(position), itemType, convertView, parent);
        return view;
    }

    public final String getItemType(int position) {
        ItemViewModel item = getItem(position);
        return item.type;
    }

    private View getCurrentView(Object item, String itemType, View convertView, ViewGroup parent) {
        final View view;
        int viewId = ItemTypeToLayoutMap.getLayoutFromItemType(itemType);

        // When convertView is not null, we can reuse it directly, there is no need
        // to re-inflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (null == convertView || convertView.getId() != viewId) {
            // create a new view
            view = this.mLayoutInflater.inflate(viewId, parent, false);
            view.setId(viewId);
            // sets view holder to the view
            try {
                setViewHolder(view, itemType);
            } catch (Exception e) {

            }
        } else {
            view = convertView;
        }

        BaseViewHolder viewHolder = (BaseViewHolder) view.getTag();
        if (null != viewHolder) {
            viewHolder.inflateItem(item, this.mContext);
        }

        return view;
    }

    protected void setViewHolder(View view, String itemType) throws Exception {
        Class viewHolder = ItemTypeToViewHolderMap.getClassTypeFromTileName(itemType);
        if (viewHolder != null) {
            BaseViewHolder vh = (BaseViewHolder) viewHolder.getConstructor(View.class).newInstance(view);
            view.setTag(vh);
        }
    }

}
