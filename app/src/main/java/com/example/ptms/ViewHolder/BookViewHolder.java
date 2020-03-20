package com.example.ptms.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Interface.ItemClickListner;
import com.example.ptms.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtBookDate, txtBookarrTime, txtBookDepTime, txtBookFrom, txtBookTo, txtBookNoOfSeats;
    private ItemClickListner itemClickListner;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        txtBookDate = itemView.findViewById(R.id.my_travel_list_to_booked_date);
        txtBookarrTime = itemView.findViewById(R.id.my_travel_list_arr_time);
        txtBookDepTime = itemView.findViewById(R.id.my_travel_list_dep_time);
        txtBookFrom = itemView.findViewById(R.id.my_travel_list_from);
        txtBookTo = itemView.findViewById(R.id.my_travel_list_to);
        txtBookNoOfSeats = itemView.findViewById(R.id.my_travel_list_to_booked_seats);

    }


    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v, getAdapterPosition(), false);

    }
}

