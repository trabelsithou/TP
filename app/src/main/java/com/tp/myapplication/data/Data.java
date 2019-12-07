package com.tp.myapplication.data;

import com.tp.myapplication.model.User;

import java.util.ArrayList;

public class Data {
    private static final Data ourInstance = new Data();
    private ArrayList<User> mData = new ArrayList<>();

    private Data() {
    }

   public static Data getInstance() {
        return ourInstance;
    }

    public ArrayList<User> getmData() {
        return mData;
    }

    public void setmData(ArrayList<User> mData) {
        this.mData = mData;
    }
}
