package com.androidworld.antis.AntisApp.fragments.viewholders;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.PhoneInfoDisplayCard;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import org.w3c.dom.Text;

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

        PhoneInfoDisplayCard phoneInfoDisplayCard = (PhoneInfoDisplayCard) itemViewModel.item;
        setTextView(this.mHeadingText, phoneInfoDisplayCard.headingText);
        setTextView(this.mFeatureHeadingText, phoneInfoDisplayCard.featureHeadingText);
        setTextView(this.mUserRatingView, phoneInfoDisplayCard.userReviewHeadingText);
        setImageView(this.mHeroImage, phoneInfoDisplayCard.phoneImage);
        setTextView(this.mPriceTag, phoneInfoDisplayCard.priceTag);
        setLinearLayoutItems(this.mFeatureSectionList, phoneInfoDisplayCard.featureList);
        setLinearLayoutItems(this.mUserRatingList, phoneInfoDisplayCard.userReviewList);
        setSaveButton(phoneInfoDisplayCard.isSaved, phoneInfoDisplayCard);
        setIgnoreButton(phoneInfoDisplayCard.isRejected, phoneInfoDisplayCard, itemViewModel);
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

    private void setIgnoreButton(boolean isIgnored, final PhoneInfoDisplayCard phoneInfoDisplayCard, final ItemViewModel item) {
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
                    showWarningMessage(phoneInfoDisplayCard.headingText, item, toggleButton);
                } else {
                    phoneInfoDisplayCard.isRejected = false;
                    toggleButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                }
            }
        });
    }

    private void showWarningMessage(String itemName, final ItemViewModel item, final ToggleButton toggleButton){
        LinearLayout overlay_layout = (LinearLayout) View.inflate(this.mContext, R.layout.semi_transparent_overlay_layout, null);
        overlay_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing;
            }
        });

        LinearLayout linearLayout = (LinearLayout) View.inflate(this.mContext, R.layout.message_popup_layout, null);
        String displayText = String.format("You have rejected %s, we will hide this item going ahead.", itemName);
        TextView textView = (TextView) linearLayout.findViewById(R.id.display_message);
        textView.setText(displayText);
        textView = (TextView) linearLayout.findViewById(R.id.user_icon);
        textView.setTypeface(this.mFont);
        Button button = (Button) linearLayout.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWarningMessageViews();
                removeItem(item);
            }
        });

        button = (Button) linearLayout.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWarningMessageViews();
                toggleButton.setTextColor(mContext.getResources().getColor(R.color.gray));
                toggleButton.setChecked(true);
            }
        });

        FrameLayout frameLayout = (FrameLayout) this.mView;
        frameLayout.addView(overlay_layout);
        frameLayout.addView(linearLayout);
    }

    private void removeWarningMessageViews() {
        FrameLayout frameLayout = (FrameLayout) this.mView;
        int total = frameLayout.getChildCount();
        frameLayout.removeViewAt(total - 1);
        frameLayout.removeViewAt(total - 2);
    }

    private final void removeItem(final ItemViewModel item){
        this.mAdapter.remove(item);
        this.mAdapter.notifyDataSetChanged();
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
