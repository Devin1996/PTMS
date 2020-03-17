package com.example.ptms.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Interface.ItemClickListner;
import com.example.ptms.R;

public class BusTimeDisplayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView routeRegNo, from, to, depTime, arrTime, trackNo,busTimeKey ;

    private ItemClickListner itemClickListner;

    public BusTimeDisplayViewHolder(@NonNull View itemView) {
        super(itemView);
        trackNo = itemView.findViewById(R.id.track_no_time_slot);
        from = itemView.findViewById(R.id.from_time_slot);
        to = itemView.findViewById(R.id.to_time_slot);
        arrTime = itemView.findViewById(R.id.arr_time_slot);
        depTime = itemView.findViewById(R.id.dep_time_slot);

    }

    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v, getAdapterPosition(), false);

    }
}
