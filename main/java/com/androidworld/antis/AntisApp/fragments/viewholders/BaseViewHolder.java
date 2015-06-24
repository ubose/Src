package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.fragments.adapters.GenericListAdapter;
import com.androidworld.antis.AntisApp.models.Image;
import com.androidworld.antis.AntisApp.utilities.ApplicationUtilities;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;
import com.squareup.picasso.Picasso;

/**
 * Created by utbose on 6/18/2015.
 */
public class BaseViewHolder {

    protected Context mContext;

    protected GenericListAdapter mAdapter;

    public void inflateItem(Object item, Context context, GenericListAdapter genericListAdapter) {
        this.mContext = context;
        this.mAdapter = genericListAdapter;
        inflateItem(item);
    }

    public void inflateItem(Object item) {

    }

    public void setTextView(TextView viewHolderItem, CharSequence textValue){
        if (viewHolderItem == null) {
            return;
        }

        if (textValue == null) {
            return;
        }

        if (!StringUtilities.isNullOrWhitespace(textValue.toString())) {
            viewHolderItem.setText(textValue);
            viewHolderItem.setVisibility(View.VISIBLE);
        } else {
            viewHolderItem.setVisibility(View.GONE);
        }
    }

    public void setImageView(ImageView viewHolderItem, Image image){
        if(image == null) {
            return;
        }

        ApplicationUtilities.setImageView(this.mContext, viewHolderItem, image.src);
    }

    public void setButtonView(TextView viewHolderItem, CharSequence textValue){
        viewHolderItem.setText(textValue);
    }

    //http://3.bp.blogspot.com/-JqKMEYHEfjY/UfvZcNdwbII/AAAAAAAAM2c/JDPO2Uke1LU/s1600/ScreenLock.png
    //http://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2015/02/galaxy-s6-edge-full-front-angle-2-9zh2jqc.jpg
}
