package com.androidworld.antis.AntisApp.models;

/**
 * Created by Utsab Bose on 6/24/2015.
 */
public class MessageBoxDataModel {

    public String message;

    public String buttonText;

    public MessageBoxDataModel(String msg, String buttonText){
        this.message = msg;
        this.buttonText = buttonText;
    }
}
