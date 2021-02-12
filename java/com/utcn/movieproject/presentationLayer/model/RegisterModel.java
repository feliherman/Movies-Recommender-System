package com.utcn.movieproject.presentationLayer.model;

import com.utcn.movieproject.businessLayer.DBHelper;

public class RegisterModel {
    private DBHelper myDb = null;

    public RegisterModel(DBHelper db) {
        this.myDb = db;
    }

    public DBHelper getMyDb() {
        return myDb;
    }

}