package com.androidworld.antis.AntisApp.models;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class PhoneInfoDisplayCard extends BaseModel {


    public String productId;

    public Image phoneImage;

    public String priceTag;

    public String rating;

    public String headingText;

    public String featureHeadingText;

    public String userReviewHeadingText;

    public ArrayList<String> featureList;

    public ArrayList<String> userReviewList;

    public boolean isSaved;

    public boolean isRejected;
}
