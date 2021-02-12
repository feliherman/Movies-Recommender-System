package com.utcn.movieproject.presentationLayer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.utcn.movieproject.R;

public class ClientView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button seeMoviesBtn = this.findViewById(R.id.seeMoviesBtn);
        seeMoviesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seeMovies();
            }
        });

        Button showRecommendationsBtn = this.findViewById(R.id.showRecBtn);
        showRecommendationsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showRecommendations();
            }
        });

        Button logoutBtn = this.findViewById(R.id.logoutBtnClient);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void seeMovies() {
        Intent intent = new Intent(ClientView.this, MovieView.class);
        startActivity(intent);
    }

    public void showRecommendations() {
        Intent intent = new Intent(ClientView.this, RecommendView.class);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(ClientView.this, MainActivityView.class);
        startActivity(intent);
        finish();
    }
}