package com.utcn.movieproject.presentationLayer.presenter;

import com.utcn.movieproject.businessLayer.DBHelper;
import com.utcn.movieproject.dataLayer.ERRORS;
import com.utcn.movieproject.presentationLayer.model.RegisterModel;

public class RegisterPresenter {
    private RegisterModel model;

    public RegisterPresenter(DBHelper database) {
        this.model = new RegisterModel(database);
    }

    public ERRORS checkValid(String name, String surname, String email, String password, String confirmPassword) {
        if (name.toUpperCase().equals("NAME") || name.equals("")) {
            return ERRORS.INVALID_NAME;
        }
        if (surname.toUpperCase().equals("SURNAME") || surname.equals("")) {
            return ERRORS.INVALID_SURNAME;
        }
        if (email.toUpperCase().equals("EMAIL") || surname.equals("") || model.getMyDb().findClient(email) == Boolean.TRUE || !email.contains("@")) {
            return ERRORS.INVALID_EMAIL;
        }
        if (!password.equals(confirmPassword)) {
            return ERRORS.INVALID_MATCH_PASSWORD;
        } else {
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
            if (!password.matches(pattern))
                return ERRORS.INVALID_PASSWORD;
        }
        return ERRORS.VALID;
    }

}
