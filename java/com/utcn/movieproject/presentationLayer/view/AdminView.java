package com.utcn.movieproject.presentationLayer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.utcn.movieproject.R;

public class AdminView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button seeUsersBtn = this.findViewById(R.id.seeUsersBtn);
        seeUsersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seeUsers();
            }
        });

        Button addMovieBtn = this.findViewById(R.id.addMovieBtn);
        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMovie();
            }
        });

        Button deleteMovieBtn = this.findViewById(R.id.deleteMovieBtn);
        deleteMovieBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteMovie();
            }
        });

        Button logoutBtn = this.findViewById(R.id.logoutBtnAdmin);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void seeUsers() {
        Intent intent = new Intent(AdminView.this, UsersView.class);
        startActivity(intent);
    }

    public void addMovie() {
        Intent intent = new Intent(AdminView.this, AddMovieView.class);
        startActivity(intent);
    }

    public void deleteMovie() {
        Intent intent = new Intent(AdminView.this, DeleteMovieView.class);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(AdminView.this, MainActivityView.class);
        startActivity(intent);
        finish();
    }
}

