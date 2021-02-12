package com.utcn.movieproject.presentationLayer.model.slider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utcn.movieproject.R;

import java.util.List;

public class SliderPageAdapter extends PagerAdapter {

    private Context context;
    private List<Slider> sliders;

    public SliderPageAdapter(Context context, List<Slider> sliders) {
        this.context = context;
        this.sliders = sliders;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.mainTrailer);
        TextView slideText = slideLayout.findViewById(R.id.title);
        slideImg.setImageResource(sliders.get(position).getImage());
        slideText.setText(sliders.get(position).getTitle());

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
