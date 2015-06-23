package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.PhoneInfoDisplayCard;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class PhoneDisplayCardViewHolder extends BaseViewHolder {

    private TextView mHeadingText;

    private ImageView mHeroImage;

    private TextView mFeatureHeadingText;

    private TextView mUserRatingView;

    private TextView mPriceTag;

    private LinearLayout mFeatureSectionList;

    private LinearLayout mUserRatingList;

    private ToggleButton mSaveButton;

    private ToggleButton mRejectButton;

    private Button mCompareButton;

    private Typeface mFont;

    public PhoneDisplayCardViewHolder(View view) {
        if(view != null) {
            this.mHeadingText = (TextView) view.findViewById(R.id.heading_text);
            this.mFeatureHeadingText = (TextView) view.findViewById(R.id.feature_heading_text);
            this.mUserRatingView = (TextView) view.findViewById(R.id.user_review_heading_text);
            this.mHeroImage = (ImageView) view.findViewById(R.id.heroimage);
            this.mFeatureSectionList = (LinearLayout) view.findViewById(R.id.feature_list);
            this.mUserRatingList = (LinearLayout) view.findViewById(R.id.user_rating_list);
            this.mPriceTag = (TextView) view.findViewById(R.id.price_tag);
            this.mSaveButton = (ToggleButton) view.findViewById(R.id.save_button);
            this.mRejectButton = (ToggleButton) view.findViewById(R.id.reject_button);
            this.mCompareButton = (Button) view.findViewById(R.id.compare_button);
        }
    }

    @Override
    public void inflateItem(Object item) {

        if(!(item instanceof ItemViewModel)) {
            return;
        }

        ItemViewModel itemViewModel = (ItemViewModel) item;
        if(!(itemViewModel.item instanceof PhoneInfoDisplayCard)) {
            return;
        }

        if(mFont == null) {
            this.mFont = Typeface.createFromAsset( this.mContext.getAssets(), "fontawesome-webfont.ttf" );
        }

        PhoneInfoDisplayCard phoneInfoDisplayCard = (PhoneInfoDisplayCard) itemViewModel.item;
        setTextView(this.mHeadingText, phoneInfoDisplayCard.headingText);
        setTextView(this.mFeatureHeadingText, phoneInfoDisplayCard.featureHeadingText);
        setTextView(this.mUserRatingView, phoneInfoDisplayCard.userReviewHeadingText);
        setImageView(this.mHeroImage, phoneInfoDisplayCard.phoneImage);
        setTextView(this.mPriceTag, phoneInfoDisplayCard.priceTag);
        setLinearLayoutItems(this.mFeatureSectionList, phoneInfoDisplayCard.featureList);
        setLinearLayoutItems(this.mUserRatingList, phoneInfoDisplayCard.userReviewList);
        setSaveButton(phoneInfoDisplayCard.isSaved, phoneInfoDisplayCard);
        setIgnoreButton(phoneInfoDisplayCard.isRejected, phoneInfoDisplayCard);
        setCompareButton();
    }

    private void setSaveButton(boolean isSaved, final PhoneInfoDisplayCard phoneInfoDisplayCard) {
        this.mSaveButton.setTypeface(this.mFont);
        int saveTextColor = R.color.gray;
        boolean checked = true;
        if (isSaved) {
            saveTextColor = R.color.green;
            checked = false;
        }

        this.mSaveButton.setTextColor(this.mContext.getResources().getColor(saveTextColor));
        this.mSaveButton.setChecked(checked);

        this.mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton toggleButton = (ToggleButton) v;
                boolean on = toggleButton.isChecked();
                if (!on) {
                    phoneInfoDisplayCard.isSaved = true;
                    phoneInfoDisplayCard.isRejected = false;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.green));
                    mRejectButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                    mRejectButton.setChecked(true);
                } else {
                    phoneInfoDisplayCard.isSaved = false;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                }
            }
        });
    }

    private void setIgnoreButton(boolean isIgnored, final PhoneInfoDisplayCard phoneInfoDisplayCard) {
        this.mRejectButton.setTypeface(this.mFont);
        boolean checked = true;
        int ignoreColor = R.color.gray;
        if (isIgnored) {
            ignoreColor = R.color.red;
            checked = false;
        }

        this.mRejectButton.setTextColor(this.mContext.getResources().getColor(ignoreColor));
        this.mRejectButton.setChecked(checked);

        this.mRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton toggleButton = (ToggleButton) v;
                boolean on = toggleButton.isChecked();
                if (!on) {
                    phoneInfoDisplayCard.isRejected = true;
                    phoneInfoDisplayCard.isSaved = false;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.red));
                    mSaveButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                    mSaveButton.setChecked(true);
                } else {
                    phoneInfoDisplayCard.isRejected = false;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                }
            }
        });
    }


    private void setCompareButton() {
        this.mCompareButton.setTypeface(this.mFont);
        this.mCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to comparision page
            }
        });
    }

    private void setLinearLayoutItems(LinearLayout linearLayout, ArrayList<String> featureList) {

        linearLayout.removeAllViews();
        for(String item : featureList) {
            if (!StringUtilities.isNullOrWhitespace(item)) {
                TextView textView = new TextView(this.mContext);
                textView.setText(item);
                linearLayout.addView(textView);
            }
        }
    }

}
