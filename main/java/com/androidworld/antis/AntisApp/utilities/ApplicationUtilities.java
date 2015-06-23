package com.androidworld.antis.AntisApp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by utbose on 6/22/2015.
 */
public class ApplicationUtilities {

    public static int dpToPixels(int dp) {

        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float getDensity(){
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int pixelsToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeightInPixels(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getScreenWidthInPixels(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }
}
