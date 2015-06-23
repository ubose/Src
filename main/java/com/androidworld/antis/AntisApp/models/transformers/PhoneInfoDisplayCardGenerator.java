package com.androidworld.antis.AntisApp.models.transformers;

import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.Image;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.PhoneInfoDisplayCard;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class PhoneInfoDisplayCardGenerator implements IGenerator {

    public IModel deserializeJson(JSONObject jsonObject){
        return null;
    }

    public static ArrayList<ItemViewModel> generateDummyModel(JSONObject object) {

        //samsung s6
        ArrayList<PhoneInfoDisplayCard> itemList = new ArrayList<PhoneInfoDisplayCard>();
        PhoneInfoDisplayCard ph = new PhoneInfoDisplayCard();
        ph.headingText = "Samsung Galaxy S6 Edge";
        ph.priceTag = "Rs 60,000";
        ph.phoneImage = new Image("http://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2015/02/galaxy-s6-edge-full-front-angle-2-9zh2jqc.jpg");
        itemList.add(ph);

        //galaxy A7
        ph = new PhoneInfoDisplayCard();
        ph.headingText = "Samsung Galaxy A7";
        ph.priceTag = "Rs 23,000";
        ph.phoneImage = new Image("http://thegadgetflow.com/wp-content/uploads/2015/01/Samsung-Galaxy-A7.jpeg");
        itemList.add(ph);

        //galaxy J1
        ph = new PhoneInfoDisplayCard();
        ph.headingText = "Samsung Galaxy J1";
        ph.priceTag = "Rs 40,000";
        ph.phoneImage = new Image("http://www.samsung.com/hk_en/consumer-images/product/smartphones/2015/SM-J100FZWDTGY/features/SM-J100FZWDTGY-590464-0.jpg");
        itemList.add(ph);

        ph = new PhoneInfoDisplayCard();
        ph.headingText = "Samsung Galaxy S4";
        ph.priceTag = "Rs 44,000";
        ph.phoneImage = new Image("http://r3.whistleout.com.au/public/images/articles/2013/07/samsung-galaxy-s4-review-33.jpg");
        itemList.add(ph);

        ph = new PhoneInfoDisplayCard();
        ph.headingText = "Samsung Galaxy S5";
        ph.priceTag = "Rs 56,000";
        ph.phoneImage = new Image("http://blogs-images.forbes.com/gordonkelly/files/2014/03/P1060987.jpg");
        itemList.add(ph);

        ArrayList<ItemViewModel> modelList = new ArrayList<ItemViewModel>();
        for(int i = 0; i < itemList.size(); i++) {
            //Actual implementation : Parse json object
            PhoneInfoDisplayCard phoneInfoDisplayCard = itemList.get(i);
            //phoneInfoDisplayCard.priceTag = "Rs 60,000";
            //phoneInfoDisplayCard.headingText = "Samsung Galaxy S6 Edge";
            phoneInfoDisplayCard.featureHeadingText = "Why me?";
            phoneInfoDisplayCard.userReviewHeadingText = "User Reviews(500)";
            phoneInfoDisplayCard.rating = "4.2";
            phoneInfoDisplayCard.featureList = new ArrayList<String>();
            phoneInfoDisplayCard.featureList.add("13mp Camera");
            phoneInfoDisplayCard.featureList.add("Wireless Charging");
            phoneInfoDisplayCard.featureList.add("5.5\" big screen ");
            phoneInfoDisplayCard.featureList.add("light weight");
            phoneInfoDisplayCard.userReviewList = new ArrayList<String>();
            phoneInfoDisplayCard.userReviewList.add("awesome screen");
            phoneInfoDisplayCard.userReviewList.add("lighting fast");
            phoneInfoDisplayCard.userReviewList.add("not waterproof");
            phoneInfoDisplayCard.userReviewList.add("poor battery");
            phoneInfoDisplayCard.isSaved = false;
            phoneInfoDisplayCard.isRejected = false;
            //phoneInfoDisplayCard.phoneImage = phoneInfoDisplayCard.phoneImage;

            ItemViewModel itemViewModel = new ItemViewModel(phoneInfoDisplayCard, "PhoneInfoDisplayCard", "");
            modelList.add(itemViewModel);
        }

        return modelList;
    }
}
