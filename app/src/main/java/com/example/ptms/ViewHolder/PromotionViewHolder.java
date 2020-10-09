package com.example.ptms.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Interface.ItemClickListner;
import com.example.ptms.R;

public class PromotionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductDescription;
    public ImageView imageView;
    public ItemClickListner listner;


    public PromotionViewHolder(View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
