package com.androidworld.antis.AntisApp.models.transformers;

import com.androidworld.antis.AntisApp.enums.ViewItemState;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.Image;
import com.androidworld.antis.AntisApp.models.ItemViewModel;
import com.androidworld.antis.AntisApp.models.PhoneInfoDisplayCard;
import com.androidworld.antis.AntisApp.models.SearchpageDataModel;
import com.androidworld.antis.AntisApp.models.MessageBoxDataModel;
import com.androidworld.antis.AntisApp.storage.DataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by utbose on 6/18/2015.
 */
public class PhoneInfoDisplayCardGenerator implements IGenerator {

    public IModel deserializeJson(JSONObject jsonObject){
        return null;
    }

    private static String UsrName="Adi" ;//Note user will not change for thr phone displat card

    public static ArrayList<ItemViewModel> generateDummyModel(JSONObject object) {

        DataStorage dataStorage = DataStorage.getInstance();
        ArrayList<String> deletedItems = dataStorage.readDeletedItems();
        ArrayList<String> savedItems = dataStorage.readSavedItems();
        ArrayList<PhoneInfoDisplayCard> itemList = new ArrayList<PhoneInfoDisplayCard>();
        //SearchPageDataModel
        SearchpageDataModel searchpageDataModel = new SearchpageDataModel();
        searchpageDataModel.userName = UsrName;
        searchpageDataModel.message = searchpageDataModel.userName +", welcome to Antis buying experience. We help you to make informed choices.To Start with do you have any specific model in mind";
        searchpageDataModel.firstButtonText = "Nothing specific";
        searchpageDataModel.secondButtonText = "Yes, continue";

        //samsung s6

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
        ItemViewModel itemViewModelSrc = new ItemViewModel(searchpageDataModel, "searchpageDataModel", "");
        modelList.add(itemViewModelSrc);

        for(int i = 0; i < itemList.size(); i++) {
            //Actual implementation : Parse json object
            PhoneInfoDisplayCard phoneInfoDisplayCard = itemList.get(i);
            phoneInfoDisplayCard.productId = "phone_android_" + (i+1);
            phoneInfoDisplayCard.featureHeadingText = "Why me?";
            phoneInfoDisplayCard.userReviewHeadingText = "User Reviews(500)";
            phoneInfoDisplayCard.rating = "4.2";
            phoneInfoDisplayCard.featureList = new ArrayList<>();
            phoneInfoDisplayCard.featureList.add("13mp Camera");
            phoneInfoDisplayCard.featureList.add("Wireless Charging");
            phoneInfoDisplayCard.featureList.add("5.5\" big screen ");
            phoneInfoDisplayCard.featureList.add("light weight");
            phoneInfoDisplayCard.userReviewList = new ArrayList<>();
            phoneInfoDisplayCard.userReviewList.add("awesome screen");
            phoneInfoDisplayCard.userReviewList.add("lighting fast");
            phoneInfoDisplayCard.userReviewList.add("not waterproof");
            phoneInfoDisplayCard.userReviewList.add("poor battery");
            phoneInfoDisplayCard.isSaved = false;
            phoneInfoDisplayCard.isRejected = false;
            //phoneInfoDisplayCard.phoneImage = phoneInfoDisplayCard.phoneImage;

            ItemViewModel itemViewModel = new ItemViewModel(phoneInfoDisplayCard, "PhoneInfoDisplayCard", "");

            if(savedItems != null && savedItems.contains(phoneInfoDisplayCard.productId)) {
                itemViewModel.viewItemState = ViewItemState.SAVED;
            }

            if(deletedItems != null && deletedItems.contains(phoneInfoDisplayCard.productId)) {
                itemViewModel.viewItemState = ViewItemState.REJECTED;
            } else {
                modelList.add(itemViewModel);
            }
        }

        return modelList;
    }
}
