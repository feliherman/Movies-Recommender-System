package com.utcn.movieproject.presentationLayer.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.utcn.movieproject.R;
import com.utcn.movieproject.presentationLayer.model.slider.SliderPageAdapter;
import com.utcn.movieproject.presentationLayer.presenter.MainActivityPresenter;



public class MainActivityView extends AppCompatActivity {
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
        ViewPager slider = findViewById(R.id.slider);
        SliderPageAdapter adapter = new SliderPageAdapter(this, presenter.getRecentlyAddedMovies());
        slider.setAdapter(adapter);

        Button register = this.findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openRegister();
            }
        });
        Button login = this.findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    public void openRegister() {
        Intent intent = new Intent(this, presenter.getRegisterClass());
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, presenter.getLoginClass());
        startActivity(intent);
    }
}