package com.utcn.movieproject.presentationLayer.model;

import com.utcn.movieproject.businessLayer.DBHelper;

public class LoginModel {
    private DBHelper myDb = null;

    public LoginModel(DBHelper db) {
        this.myDb = db;
    }

    public DBHelper getMyDb() {
        return myDb;
    }
}
