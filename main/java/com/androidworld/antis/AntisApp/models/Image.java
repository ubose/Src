package com.androidworld.antis.AntisApp.models;

/**
 * Created by utbose on 6/18/2015.
 */
public class Image {

    public String src;

    public int width;

    public int height;

    public String name;

    public String altText;

    public String caption;

    public String attribution;

    public String link;

    public Image(String sourceLink){
        this.src = sourceLink;
    }
}
