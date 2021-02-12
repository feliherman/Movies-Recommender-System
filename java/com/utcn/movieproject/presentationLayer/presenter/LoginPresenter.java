package com.utcn.movieproject.presentationLayer.presenter;

import com.utcn.movieproject.businessLayer.DBHelper;
import com.utcn.movieproject.presentationLayer.model.LoginModel;

public class LoginPresenter {
    private LoginModel model;

    public LoginPresenter(DBHelper database){
        this.model=new LoginModel(database);
    }

    public boolean checkValid(String email,String password) {
        if (email.equals("EMAIL")|| (!model.getMyDb().findClient(email))) {
            return false;
        }
        if (password.equals("")|| (!model.getMyDb().validateClient(email,password))) {
            return false;
        }
        return true;
    }
}
