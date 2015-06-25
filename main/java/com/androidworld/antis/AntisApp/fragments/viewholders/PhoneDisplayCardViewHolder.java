package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.enums.ViewItemState;
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

    private View mView;

    private LinearLayout mOverlayLayout;

    private LinearLayout mWarningMessageLayout;

    private View.OnClickListener mOnSaveClickListner;

    private View.OnClickListener mOnRejectClickListner;

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
            this.mView = view;
        }
    }

    @Override
    public void inflateItem(Object item) {

        if(!(item instanceof ItemViewModel)) {
            return;
        }

        final ItemViewModel  itemViewModel = (ItemViewModel) item;
        if(!(itemViewModel.item instanceof PhoneInfoDisplayCard)) {
            return;
        }

        if(mFont == null) {
            this.mFont = Typeface.createFromAsset( this.mContext.getAssets(), "fontawesome-webfont.ttf" );
        }

        ViewItemState itemState = itemViewModel.viewItemState;
        removeOverlayLayouts();
        switch(itemState) {
            case NONE: inflatePhoneDisplayCard(itemViewModel);
                       resetButtonStates();
                       break;
            case SAVED : inflatePhoneDisplayCard(itemViewModel);
                         markSavedButton();
                         break;
            case REJECTED: inflatePhoneDisplayCard(itemViewModel);
                           markRejectedButton();
                           break;
            case INTERMEDIATE: inflatePhoneDisplayCard(itemViewModel);
                               markRejectedButton();
                               inflateWarningMessage(itemViewModel, this.mRejectButton);
                               break;
        }

        inflatePhoneDisplayCard(itemViewModel);

    }

    private void inflatePhoneDisplayCard(final ItemViewModel itemViewModel){
        PhoneInfoDisplayCard phoneInfoDisplayCard = (PhoneInfoDisplayCard) itemViewModel.item;
        setTextView(this.mHeadingText, phoneInfoDisplayCard.headingText);
        setTextView(this.mFeatureHeadingText, phoneInfoDisplayCard.featureHeadingText);
        setTextView(this.mUserRatingView, phoneInfoDisplayCard.userReviewHeadingText);
        setImageView(this.mHeroImage, phoneInfoDisplayCard.phoneImage);
        setTextView(this.mPriceTag, phoneInfoDisplayCard.priceTag);
        setLinearLayoutItems(this.mFeatureSectionList, phoneInfoDisplayCard.featureList);
        setLinearLayoutItems(this.mUserRatingList, phoneInfoDisplayCard.userReviewList);
        setSaveButton(itemViewModel);
        setIgnoreButton(itemViewModel);
        setCompareButton();
    }

    private void removeOverlayLayouts(){
        FrameLayout frameLayout = (FrameLayout) this.mView;
        int count = frameLayout.getChildCount();
        // 2 because the first 2 layout are phone card layouts
        for(int i = count-1; i > 1; i--){
            frameLayout.removeViewAt(i);
        }
    }

    private void resetButtonStates(){
        this.mSaveButton.setTextColor(this.mContext.getResources().getColor(R.color.gray));
        this.mSaveButton.setChecked(true);
        this.mRejectButton.setTextColor(this.mContext.getResources().getColor(R.color.gray));
        this.mRejectButton.setChecked(true);
    }

    private void markSavedButton(){
        this.mSaveButton.setChecked(false);
        this.mSaveButton.setTextColor(this.mContext.getResources().getColor(R.color.green));
    }

    private void markRejectedButton(){
        this.mRejectButton.setTextColor(this.mContext.getResources().getColor(R.color.red));
        this.mRejectButton.setChecked(false);
    }

    private void setSaveButton(final ItemViewModel itemViewModel) {
        this.mSaveButton.setTypeface(this.mFont);
        this.mSaveButton.setOnClickListener(getmOnSaveClickListner(itemViewModel));
    }

    private View.OnClickListener getmOnSaveClickListner(final ItemViewModel itemViewModel){
        //if(this.mOnSaveClickListner == null) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToggleButton toggleButton = (ToggleButton) v;
                    boolean checked = toggleButton.isChecked();
                    if (!checked) {
                        itemViewModel.viewItemState = ViewItemState.SAVED;
                    } else {
                        itemViewModel.viewItemState = ViewItemState.NONE;
                    }

                    notifyDatasetChanged();
                }
            };
        //}

        //return mOnSaveClickListner;
    }

    private void notifyDatasetChanged(){
        this.mAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener getmOnRejectClickListner(final ItemViewModel itemViewModel){
        this.mRejectButton.setTypeface(this.mFont);
       // if(this.mOnRejectClickListner == null) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToggleButton toggleButton = (ToggleButton) v;
                    boolean isChecked = toggleButton.isChecked();
                    if (!isChecked) {
                        itemViewModel.viewItemState = ViewItemState.INTERMEDIATE;
                    } else {
                        itemViewModel.viewItemState = ViewItemState.NONE;
                    }

                    notifyDatasetChanged();
                }
            };
        //}

        //return mOnRejectClickListner;
    }

    private void setIgnoreButton(final ItemViewModel itemViewModel) {
        this.mRejectButton.setTypeface(this.mFont);
        this.mRejectButton.setOnClickListener(getmOnRejectClickListner(itemViewModel));
    }

    private void inflateWarningMessage(final ItemViewModel itemViewModel, final ToggleButton toggleButton){
        if(this.mOverlayLayout == null) {
            this.mOverlayLayout = (LinearLayout) View.inflate(this.mContext, R.layout.semi_transparent_overlay_layout, null);
            this.mOverlayLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do nothing;
                }
            });
        }


        if(this.mWarningMessageLayout == null) {
            this.mWarningMessageLayout = (LinearLayout) View.inflate(this.mContext, R.layout.message_popup_layout, null);
            Button button = (Button) this.mWarningMessageLayout.findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeWarningMessageViews();
                    itemViewModel.viewItemState = ViewItemState.REJECTED;
                    removeItem(itemViewModel);
                }
            });
            button = (Button) this.mWarningMessageLayout.findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeWarningMessageViews();
                    itemViewModel.viewItemState = ViewItemState.NONE;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                    toggleButton.setChecked(true);
                }
            });

            TextView textView = (TextView) this.mWarningMessageLayout.findViewById(R.id.user_icon);
            textView.setTypeface(this.mFont);
        }

        PhoneInfoDisplayCard phoneInfoDisplayCard = (PhoneInfoDisplayCard) itemViewModel.item;
        String displayText = String.format("You have rejected %s, we will hide this item going ahead.", phoneInfoDisplayCard.headingText);
        TextView textView = (TextView) this.mWarningMessageLayout.findViewById(R.id.display_message);
        textView.setText(displayText);

        FrameLayout frameLayout = (FrameLayout) this.mView;
        frameLayout.addView(this.mOverlayLayout);
        frameLayout.addView(this.mWarningMessageLayout);
    }

    private void removeWarningMessageViews() {
        FrameLayout frameLayout = (FrameLayout) this.mView;
        int total = frameLayout.getChildCount();
        frameLayout.removeViewAt(total - 1);
        frameLayout.removeViewAt(total - 2);
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
