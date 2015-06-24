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
                "http://static.guim.co.uk/sys-images/Education/Pix/pictures/2011/5/6/1304683598967/Mobile-phones-can-be-used-007.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("camera", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://www.slrphotographyguide.com/images/dslr-camera.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://newsroom.electrolux.com/uk/wp-content/common/photos_uk/zanussi-washing-machine-zwf14791w-ls.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://absocold.com/wp-content/uploads/all-refrigerator-ARD492AS-full.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "https://upload.wikimedia.org/wikipedia/commons/4/47/Ipod_nano_in_palm.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://www.peoplepeople.se/wp-content/uploads/2012/10/Transparent_Speaker_Floor_People_People.jpg", "since, June 15" ));
        homePageDataModel.productDisplayCardList.add(getModel("mobile_phone", "8", "Filters", "5", "Rejected", "6", "Saved",
                "http://i2.cdnds.net/14/09/618x394/tech-samsung-gear-fit-01.jpg", "since, June 15" ));

        homePageDataModel.trendingProductModelList = new ArrayList<>();
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("deals_of_the_day", "Deals of the Day", "http://www.slrphotographyguide.com/images/dslr-camera.jpg"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("trending_at_microsoft","Hot at Microsoft", "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("trending_at_google", "Hot at Google", "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("trending_at_facebook", "Hot at Facebook", "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG"));
        homePageDataModel.trendingProductModelList.add(getTrendingProductModel("trending_at_lt", "Hot at L&T", "http://cnet4.cbsistatic.com/hub/i/r/2012/08/09/09b73cf7-67c3-11e3-a665-14feb5ca9861/thumbnail/770x433/0a2cf60d5357efaef64a4cd687d36f8b/parrot_headphones_35409771_04.JPG"));
        return homePageDataModel;
    }

    private TrendingProductModel getTrendingProductModel(String id, String headingText, String s1){
        TrendingProductModel trendingProductModel = new TrendingProductModel();
        trendingProductModel.id = id;
        trendingProductModel.headingText = headingText;
        trendingProductModel.productItemsList = new ArrayList<>();
        if (id.equals("trending_at_microsoft")) {
            trendingProductModel.productItemsList.add(new Image("http://mscorp.blob.core.windows.net/mscorpmedia/2015/06/PhilSpencer_XboxE320151-640x445.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://microsoft-news.com/wp-content/uploads/2013/06/Microsoft-Surface-RT-Available.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://movietvtechgeeks.com/wp-content/uploads/2015/01/microsoft-hololens-hot-chick-fingering-air-bubbles-2015.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://www.tabletpcreview.com/assets/22932.jpg"));
        } else if (id.equals("trending_at_google")) {
            trendingProductModel.productItemsList.add(new Image("http://attunelive.com/blog/wp-content/uploads/2014/03/Google-Glass-For-Medical.jpg"));
            trendingProductModel.productItemsList.add(new Image("https://kdawpmedia.storage.googleapis.com/cloud-platform-icons.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://ignitiononline.com/wp-content/uploads/2014/11/wearable-technology-on-sh-011.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://2.bp.blogspot.com/-npk5nlfCnpA/VMlOLhtX_EI/AAAAAAAAAzs/aqx2ubnk9vw/s1600/ShineTechSlide.png"));
        } else if (id.equals("trending_at_facebook")) {
            trendingProductModel.productItemsList.add(new Image("http://core1.staticworld.net/images/article/2015/02/985-100568731-carousel.idge.jpg"));
            trendingProductModel.productItemsList.add(new Image("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-xfa1/v/t15.0-10/s350x350/11188956_10152721014632100_752710761_n.jpg?oh=432af1a1edb24cc1c15143ba0eb3bc19&oe=55E8B5E6&__gda__=1445973915_18f787e4e7dd98996a64ca520d560c8f"));
            trendingProductModel.productItemsList.add(new Image("http://www3.pcmag.com/media/images/375675-facebook-altoona-data-center.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://i.huffpost.com/gen/2129958/images/o-TECHNOLOGY-facebook.jpg"));
        } else if (id.equals("deals_of_the_day")) {
            trendingProductModel.productItemsList.add(new Image("http://core1.staticworld.net/images/article/2015/02/985-100568731-carousel.idge.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://attunelive.com/blog/wp-content/uploads/2014/03/Google-Glass-For-Medical.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://mscorp.blob.core.windows.net/mscorpmedia/2015/06/PhilSpencer_XboxE320151-640x445.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://2.bp.blogspot.com/-npk5nlfCnpA/VMlOLhtX_EI/AAAAAAAAAzs/aqx2ubnk9vw/s1600/ShineTechSlide.png"));
        } else {
            trendingProductModel.productItemsList.add(new Image("https://kdawpmedia.storage.googleapis.com/cloud-platform-icons.jpg"));
            trendingProductModel.productItemsList.add(new Image("http://ignitiononline.com/wp-content/uploads/2014/11/wearable-technology-on-sh-011.jpg"));
        }
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
