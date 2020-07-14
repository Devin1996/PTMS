package com.example.ptms.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Interface.ItemClickListner;
import com.example.ptms.R;

public class BusTimeDisplayViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView routeRegNo, from, to, depTime, arrTime, rideno,busTimeKey ;

    private ItemClickListner itemClickListner;

    public BusTimeDisplayViewHolder2(@NonNull View itemView) {
        super(itemView);
        rideno = itemView.findViewById(R.id.ride_no_search);
        from = itemView.findViewById(R.id.from_search);
        to = itemView.findViewById(R.id.to_search);
        arrTime = itemView.findViewById(R.id.arr_search);
        depTime = itemView.findViewById(R.id.dep_search);

    }

    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v, getAdapterPosition(), false);

    }
}
