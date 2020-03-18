package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ptms.Model.BusRoute;
import com.example.ptms.Model.BusTimeDisplay;
import com.example.ptms.ViewHolder.BusRouteViewHolder;
import com.example.ptms.ViewHolder.BusTimeDisplayViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TimeDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<BusTimeDisplay> arrayList;
    private FirebaseRecyclerOptions<BusTimeDisplay> options;
    private FirebaseRecyclerAdapter<BusTimeDisplay, BusTimeDisplayViewHolder> adapter;
    private DatabaseReference busTimeSlotDBRef;

    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_display);

        recyclerView = findViewById(R.id.bus_time_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<BusTimeDisplay>();

        busTimeSlotDBRef = FirebaseDatabase.getInstance().getReference().child("timeSlots").child("busTimes");
        busTimeSlotDBRef.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<BusTimeDisplay>().setQuery(busTimeSlotDBRef.child("busTimeDislpay"), BusTimeDisplay.class).build();



        adapter = new FirebaseRecyclerAdapter<BusTimeDisplay, BusTimeDisplayViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull BusTimeDisplayViewHolder BusTimeDisplayViewHolder, int i, @NonNull final BusTimeDisplay BusTimeDisplay) {

                BusTimeDisplayViewHolder.from.setText("From :"+BusTimeDisplay.getFrom());
                BusTimeDisplayViewHolder.to.setText("To :"+BusTimeDisplay.getTo());
                BusTimeDisplayViewHolder.arrTime.setText("Arrival Time :"+BusTimeDisplay.getArrTime());
                BusTimeDisplayViewHolder.depTime.setText("Departure Time :"+BusTimeDisplay.getDepTime());
                BusTimeDisplayViewHolder.trackNo.setText("Track No :"+BusTimeDisplay.getTrackNo());



                BusTimeDisplayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TimeDisplayActivity.this, AddToTravelPlansActivity.class);
                        intent.putExtra("timeSlotKey", BusTimeDisplay.getBusTimeKey());
                        startActivity(intent);


                    }
                });

            }

            @NonNull
            @Override
            public BusTimeDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new BusTimeDisplayViewHolder(LayoutInflater.from(TimeDisplayActivity.this).inflate(R.layout.bus_time_slots_display, parent, false));

            }
        };

        recyclerView.setAdapter(adapter);
    }
}
