package com.androidworld.antis.AntisApp.application;

import android.app.Application;

import com.androidworld.antis.AntisApp.storage.DataStorage;

/**
 * Created by utbose on 6/22/2015.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        DataStorage dataStorage = DataStorage.getInstance();
        dataStorage.initialize(getApplicationContext());
    }
}
