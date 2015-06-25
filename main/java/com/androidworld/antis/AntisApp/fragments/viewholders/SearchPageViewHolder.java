package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.MessageBoxDataModel;
import com.androidworld.antis.AntisApp.models.SearchpageDataModel;

/**
 * Created by diganguly on 6/24/2015.
 */
public class SearchPageViewHolder extends BaseViewHolder {

    private TextView mUserTxt;

    private TextView mUserIcon;
    private Button mContinueBtn;
    private Button mNoThanksBtn;
    private Typeface mFont;
    private View.OnClickListener mContinueButtonListner;
    private View.OnClickListener mCancelButtonListner;

    public SearchPageViewHolder(View view) {
        if(view != null) {
            this.mUserIcon = (TextView) view.findViewById(R.id.ic_User);
            this.mUserTxt = (TextView) view.findViewById(R.id.txt_User);
            this.mContinueBtn = (Button) view.findViewById(R.id.continue_src2);
            this.mNoThanksBtn = (Button) view.findViewById(R.id.nothanks_src2);
        }
    }

    public void inflateItem(Object item) {

        if(!(item instanceof ItemViewModel)) {
            return;
        }

        final ItemViewModel itemViewModel = (ItemViewModel) item;
        if(!(itemViewModel.item instanceof SearchpageDataModel)) {
            return;
        }

        if(mFont == null) {
            this.mFont = Typeface.createFromAsset(this.mContext.getAssets(), "fontawesome-webfont.ttf");
        }

        SearchpageDataModel searchpageDataModel = (SearchpageDataModel) itemViewModel.item;
        setTextView(this.mUserTxt, searchpageDataModel.message);
        setButtonView(this.mContinueBtn, searchpageDataModel.secondButtonText);
        setButtonView(this.mNoThanksBtn, searchpageDataModel.firstButtonText);
        this.mUserIcon.setTypeface(this.mFont);
        this.mContinueBtn.setOnClickListener(getContinueButtonListner(itemViewModel));
        this.mNoThanksBtn.setOnClickListener(getCancelButtonListner(itemViewModel));
    }

    private View.OnClickListener getContinueButtonListner(final ItemViewModel itemViewModel){
        if(this.mContinueButtonListner == null) {
            this.mContinueButtonListner = new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    removeItem(itemViewModel);
                    // TODO: the count is to be derived later
                    String displayText = String.format("there are %s items we found based on your search.", "5");
                    MessageBoxDataModel messageBoxDataModel = new MessageBoxDataModel(displayText, "Dismiss");
                    ItemViewModel messageItem = new ItemViewModel(messageBoxDataModel, "MessageBoxDisplayCard", "");
                    insertItem(messageItem, 0);
                }
            };
        }

        return this.mContinueButtonListner;
    }

    private View.OnClickListener getCancelButtonListner(final ItemViewModel itemViewModel){
        if(this.mCancelButtonListner == null) {
            this.mCancelButtonListner = new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    removeItem(itemViewModel);
                }
            };
        }

        return this.mCancelButtonListner;
    }
}
