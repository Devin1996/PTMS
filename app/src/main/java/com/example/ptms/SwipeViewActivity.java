package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.ptms.Model.Promotion;
import com.example.ptms.ViewHolder.PromotionAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipeViewActivity extends AppCompatActivity {

    ViewPager viewPager;
    PromotionAdapter adapter;
    List<Promotion> models;
    Integer[] colors= null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view);

        models = new ArrayList<>();
        models.add(new Promotion(R.drawable.brochure, "Brchure","dkldldkdekd"));
        models.add(new Promotion(R.drawable.sticker, "sticker","dkldldkdekd"));
        models.add(new Promotion(R.drawable.poster, "poster","dkldldkdekd"));
        models.add(new Promotion(R.drawable.namecard, "namecard","dkldldkdekd"));

        adapter = new PromotionAdapter(models,this);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position , float positionOffset , int positionOffsetPixels) {
                if (position<(adapter.getCount()-1) && position < (colors.length-1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position+1]
                            )
                    );
                }else {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}