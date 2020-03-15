package com.example.ptms.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Interface.ItemClickListner;
import com.example.ptms.R;

public class BusRouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView routeNo, routeName, totalDistance, busType, avgSpeed, avgTime ;
    private ItemClickListner itemClickListner;

    public BusRouteViewHolder(@NonNull View itemView) {
        super(itemView);
        routeNo = itemView.findViewById(R.id.bus_route_no);
        routeName = itemView.findViewById(R.id.bus_route_name);
        totalDistance = itemView.findViewById(R.id.total_distance);
        busType = itemView.findViewById(R.id.bus_type);
        avgSpeed = itemView.findViewById(R.id.avg_speed);
        avgTime = itemView.findViewById(R.id.est_time);
    }

    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v, getAdapterPosition(), false);

    }
}