package com.utcn.movieproject.presentationLayer.presenter;

import com.utcn.movieproject.R;
import com.utcn.movieproject.presentationLayer.model.slider.Slider;
import com.utcn.movieproject.presentationLayer.view.LoginView;
import com.utcn.movieproject.presentationLayer.view.RegisterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter {
    private List<Slider> recentlyAddedMovies;

    public MainActivityPresenter(){
        recentlyAddedMovies=new ArrayList<>();
    }

    public List<Slider> getRecentlyAddedMovies() {
        recentlyAddedMovies.add(new Slider(R.drawable.draw1,"GET OUT \n A movie about a foolish man"));
        recentlyAddedMovies.add(new Slider(R.drawable.draw2,"AQUAMAN \n  A movie about a strong man"));
        recentlyAddedMovies.add(new Slider(R.drawable.draw3,"HOME ALONE \n A wholesome movie"));
        return recentlyAddedMovies;
    }

    public Class getRegisterClass(){
        return RegisterView.class;
    }

    public Class getLoginClass(){
        return LoginView.class;
    }

}
