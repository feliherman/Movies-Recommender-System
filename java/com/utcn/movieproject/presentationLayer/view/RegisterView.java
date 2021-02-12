package com.utcn.movieproject.presentationLayer.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.utcn.movieproject.R;
import com.utcn.movieproject.businessLayer.DBHelper;
import com.utcn.movieproject.dataLayer.ERRORS;
import com.utcn.movieproject.presentationLayer.model.ClientModel;
import com.utcn.movieproject.presentationLayer.presenter.RegisterPresenter;

import java.util.ArrayList;

public class RegisterView extends AppCompatActivity {

    private Button registerBtn = null;
    private EditText nameField = null;
    private EditText surnameField = null;
    private EditText emailField = null;
    private EditText passwordField = null;
    private EditText confirmPasswordField = null;
    private DBHelper mydb = null;
    private RegisterPresenter presenter = null;
    private static int CURRENT_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mydb = new DBHelper(this);
        presenter = new RegisterPresenter(mydb);

        ArrayList<ClientModel> array_list = mydb.getAllClients();
        CURRENT_ID = array_list.size();//poate fi defectuos sa faci asta aici
        if (array_list.size() == 0) {
            mydb.insertClient(1, "admin", "admin", "admin@admin", "!?#$admin123");
        }
        for (ClientModel item : array_list) {
            System.out.println(item.toString());
        }

        registerBtn = findViewById(R.id.button);
        nameField = findViewById(R.id.name);
        surnameField = findViewById(R.id.surname);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        confirmPasswordField = findViewById(R.id.passwordConfirm);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ERRORS check=presenter.checkValid(nameField.getText().toString(), surnameField.getText().toString(), emailField.getText().toString(), passwordField.getText().toString(), confirmPasswordField.getText().toString());
                if (check==ERRORS.VALID) {
                    mydb.insertClient(++CURRENT_ID, nameField.getText().toString(), surnameField.getText().toString(), emailField.getText().toString(), passwordField.getText().toString());
                    Toast.makeText(RegisterView.this, "REGISTRATION COMPLETE!", Toast.LENGTH_SHORT).show();
                    ArrayList<ClientModel> clients = mydb.getAllClients();
                    System.out.println("OUR CLIENTS:");
                    for (ClientModel client : clients) {
                        System.out.println(client);
                    }
                } else {
                    switch (check) {
                        case INVALID_PASSWORD:
                            Toast.makeText(RegisterView.this, "INVALID PASSWORD!(required:at least 8 characters, at least one upper,at least one lower, no white spaces and one special (@#$%^&+=))}", Toast.LENGTH_LONG).show();
                            break;
                        case INVALID_MATCH_PASSWORD:
                            Toast.makeText(RegisterView.this, "INVALID PASSWORD! Passwords must match!", Toast.LENGTH_SHORT).show();
                            break;
                        case INVALID_EMAIL:
                            Toast.makeText(RegisterView.this, "INVALID EMAIL!", Toast.LENGTH_SHORT).show();
                            break;
                        case INVALID_NAME:
                            Toast.makeText(RegisterView.this, "INVALID FIELD(name)!", Toast.LENGTH_SHORT).show();
                            break;
                        case INVALID_SURNAME:
                            Toast.makeText(RegisterView.this, "INVALID FIELD(surname)!", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(RegisterView.this, "INVALID FIELD/FIELDS!", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });
    }

}