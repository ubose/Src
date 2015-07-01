package com.androidworld.antis.AntisApp.storage;

import android.content.Context;
import android.text.TextUtils;

import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by utbose on 6/30/2015.
 */

public class DataStorage {

    public final static String deletedItemsFileName = "/com.androidworld.antis.AntisApp.phones.deletedItems";

    public final static String savedItemsFileName = "/com.android.antis.antisApp.phones.savedItems";

    public File mDeletedItems;

    public File mSavedItems;

    private static DataStorage mDataStorage;

    private DataStorage(){

    }

    public static DataStorage getInstance(){
            if(mDataStorage == null) {
                mDataStorage = new DataStorage();
            }

            return mDataStorage;
    }

    public void initialize(Context context){

        String path = context.getFilesDir().getAbsolutePath();
        this.mDeletedItems = new File(path + deletedItemsFileName);
        this.mSavedItems = new File(path + savedItemsFileName);
    }

    public void writeToDeletedItems(String productId){
        ArrayList<String> list = readDeletedItems();
        if(list != null && !list.contains(productId) && !StringUtilities.isNullOrWhitespace(productId)){
            list.add(productId);
            writeToFile(getSerializedObject(list), this.mDeletedItems);
        }
    }

    public void removeFromDeletedItems(String productId){
        ArrayList<String> list = readDeletedItems();
        if(list != null && !list.contains(productId)){
            list.remove(productId);
        }

        writeToFile(getSerializedObject(list), this.mDeletedItems);
    }

    public void writeToSavedItems(String productId) {
        ArrayList<String> list = readSavedItems();
        if(list != null && !list.contains(productId)){
            list.add(productId);
        }

        writeToFile(productId, this.mSavedItems);
    }

    public void removeFromSavedItems(String productId){
        ArrayList<String> list = readSavedItems();
        if(list.contains(productId)) {
            list.remove(productId);
        }

        writeToFile(getSerializedObject(list), this.mSavedItems);
    }

    public ArrayList<String> readDeletedItems(){
        return readItems(this.mDeletedItems);
    }

    public ArrayList<String> readSavedItems(){
        return readItems(this.mSavedItems);
    }

    public ArrayList<String> readItems(File file){
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        List<String> output = new ArrayList<String>();
        try {
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } catch (Exception e) {
                // do nothing
            }

            try {
                in.close();
            } catch(Exception e) {
                // do nothing
            }

        } catch (FileNotFoundException e){

        }

        String contents = new String(bytes);
        return new ArrayList<>(Arrays.asList(contents.split(",")));
    }

    public void resetLocalData(){
        writeToFile("", this.mDeletedItems);
        writeToFile("", this.mSavedItems);
    }

    private String getSerializedObject(ArrayList<String> list) {
        return TextUtils.join(",", list);
    }

    
    private void writeToFile(String key, File file){
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);

            try {
                stream.write(key.getBytes());
            } catch (IOException e) {
                // log e
            }

            try {
                stream.close();
            } catch (IOException e) {
                // log e
            }

        } catch(FileNotFoundException e) {
            // log e
        }
    }
}
