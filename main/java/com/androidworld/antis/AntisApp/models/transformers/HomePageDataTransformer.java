package com.androidworld.antis.AntisApp.models.transformers;

import com.androidworld.antis.AntisApp.models.HomePageDataModel;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.Image;
import com.androidworld.antis.AntisApp.models.ProductDisplayCard;
import com.androidworld.antis.AntisApp.models.TrendingProductModel;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by utbose on 6/23/2015.
 */
public class HomePageDataTransformer implements IGenerator{

    public HomePageDataTransformer(){

    }

    public IModel deserializeJson(JSONObject object) {
        HomePageDataModel homePageDataModel = new HomePageDataModel();
        homePageDataModel.headerText1 = "How Can I help you?";
        homePageDataModel.headerText2 = "Start of where you left";
        homePageDataModel.headerText3 = "browse";

        homePageDataModel.productList = new ArrayList<String>();
        homePageDataModel.productList.add("android mobiles");
        homePageDataModel.productList.add("mobile phones");
        homePageDataModel.productList.add("dual sim mobile phones");
        homePageDataModel.productList.add("samsung mobiles");

        homePageDataModel.productDisplayCardList = new ArrayList<>();
        homePageDataModel.productDisplayCardList.add(getModel("head_phones", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2015/02/galaxy-s6-edge-full-front-angle-2-9zh2jqc.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("camera", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://www.slrphotographyguide.com/images/dslr-camera.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));

        homePageDataModel.trendingProductModelList = new ArrayList<>();
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("Deals of the Day"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("Hot at Microsoft"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("Hot at L&T"));
        return homePageDataModel;
    }

    private TrendingProductModel getTrendingProductModel(String headingText){
        TrendingProductModel trendingProductModel = new TrendingProductModel();
        trendingProductModel.HeadingText = headingText;
        trendingProductModel.productItemsList = new ArrayList<Image>();
        trendingProductModel.productItemsList.add(new Image("http://blogs-images.forbes.com/gordonkelly/files/2014/03/P1060987.jpg"));
        trendingProductModel.productItemsList.add(new Image("http://blogs-images.forbes.com/gordonkelly/files/2014/03/P1060987.jpg"));
        trendingProductModel.productItemsList.add(new Image("http://blogs-images.forbes.com/gordonkelly/files/2014/03/P1060987.jpg"));
        trendingProductModel.productItemsList.add(new Image("http://blogs-images.forbes.com/gordonkelly/files/2014/03/P1060987.jpg"));
        return trendingProductModel;
    }

    private ProductDisplayCard getModel(String s1, String s2, String s3, String s4,String s5, String s6, String s7, String s8, String s9){
        ProductDisplayCard productDisplayCard = new ProductDisplayCard();
        productDisplayCard.productId = s1;
        productDisplayCard.filtersCount = s2;
        productDisplayCard.filtersDisplayText = s3;
        productDisplayCard.rejectCount = s4;
        productDisplayCard.rejectDisplayText = s5;
        productDisplayCard.saveCount = s6;
        productDisplayCard.saveDisplayText = s7;
        productDisplayCard.productImage = new Image(s8);
        productDisplayCard.imageDisplayText = s9;
        return productDisplayCard;
    }
}
