package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
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

        ItemViewModel itemViewModel = (ItemViewModel) item;
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
    }

}
