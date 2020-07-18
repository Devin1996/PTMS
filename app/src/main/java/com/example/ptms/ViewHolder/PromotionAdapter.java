package com.example.ptms.ViewHolder;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ptms.DetailActivity;
import com.example.ptms.Model.Promotion;
import com.example.ptms.R;

import java.util.List;

public class PromotionAdapter extends PagerAdapter {

    private List<Promotion> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public PromotionAdapter(List<Promotion> models , Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view , @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container , final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image1234);
        title = view.findViewById(R.id.title1234);
        desc = view.findViewById(R.id.desc1234);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("param", models.get(position).getTitle());
                context.startActivity(intent);
                // finish();
            }
        });
        container.addView(view,0  );
        return view;
        //return super.instantiateItem(container , position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container , int position , @NonNull Object object) {
        //super.destroyItem(container , position , object);
        container.removeView((View)object);
    }
}
