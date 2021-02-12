package com.utcn.movieproject.presentationLayer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.utcn.movieproject.R;
import com.utcn.movieproject.businessLayer.DBHelper;
import com.utcn.movieproject.presentationLayer.presenter.LoginPresenter;

public class LoginView extends AppCompatActivity {
    private Button loginBtn = null;
    private EditText emailField = null;
    private EditText passwordField = null;
    private DBHelper mydb;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb = new DBHelper(this);
        presenter = new LoginPresenter(mydb);

        loginBtn = findViewById(R.id.button);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.checkValid(emailField.getText().toString(), passwordField.getText().toString())) {
                    if (emailField.getText().toString().equals("admin@admin") && passwordField.getText().toString().equals("!?#$admin123")) {
                        Intent intent = new Intent(LoginView.this, AdminView.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginView.this, ClientView.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginView.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

