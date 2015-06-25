package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.MessageBoxDataModel;
import com.androidworld.antis.AntisApp.models.SearchpageDataModel;

/**
 * Created by utbose on 6/25/2015.
 */
public class MessageBoxViewHolder extends BaseViewHolder {

    private TextView mUserIcon;

    private TextView mMessage;

    private Button mButtonText;

    private Typeface mFont;

    public MessageBoxViewHolder(View view) {
        if(view != null) {
            this.mUserIcon = (TextView) view.findViewById(R.id.message_box_user_icon);
            this.mMessage = (TextView) view.findViewById(R.id.message_box_display_text);
            this.mButtonText = (Button) view.findViewById(R.id.message_box_cancel_button);
        }
    }

    public void inflateItem(Object item) {

        if(!(item instanceof ItemViewModel)) {
            return;
        }

        final ItemViewModel itemViewModel = (ItemViewModel) item;
        if(!(itemViewModel.item instanceof MessageBoxDataModel)) {
            return;
        }

        if(mFont == null) {
            this.mFont = Typeface.createFromAsset(this.mContext.getAssets(), "fontawesome-webfont.ttf");
        }

        MessageBoxDataModel messageBoxDataModel = (MessageBoxDataModel) itemViewModel.item;
        setTextView(this.mMessage, messageBoxDataModel.message);
        setButtonView(this.mButtonText, messageBoxDataModel.buttonText);
        this.mUserIcon.setTypeface(this.mFont);
        this.mButtonText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                removeItem(itemViewModel);
            }
        });
    }
}
