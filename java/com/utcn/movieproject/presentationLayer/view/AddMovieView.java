package com.utcn.movieproject.presentationLayer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.utcn.movieproject.R;

public class AddMovieView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_view);

        Button button=findViewById(R.id.addMovieBtn);
    }
}