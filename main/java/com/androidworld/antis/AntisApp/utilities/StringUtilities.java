package com.androidworld.antis.AntisApp.utilities;

/**
 * Created by utbose on 6/18/2015.
 */
public final class StringUtilities {

    public static final boolean isNullOrWhitespace(String s) {
        return s == null || s.trim().length() == 0;
    }
}
