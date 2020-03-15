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
import com.example.ptms.ViewHolder.BusRouteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TimeSchedulesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<BusRoute> arrayList;
    private FirebaseRecyclerOptions<BusRoute> options;
    private FirebaseRecyclerAdapter<BusRoute, BusRouteViewHolder> adapter;
    private DatabaseReference databaseReference;

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
        setContentView(R.layout.activity_time_schedules);

        recyclerView = findViewById(R.id.bus_route_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<BusRoute>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("routes").child("busRoutes");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<BusRoute>().setQuery(databaseReference, BusRoute.class).build();



        adapter = new FirebaseRecyclerAdapter<BusRoute, BusRouteViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull BusRouteViewHolder BusRouteViewHolder, int i, @NonNull final BusRoute BusRoute) {

                BusRouteViewHolder.routeNo.setText(BusRoute.getRouteNo());
                BusRouteViewHolder.routeName.setText(BusRoute.getRouteName());
                BusRouteViewHolder.totalDistance.setText(BusRoute.getTotalDistance());
                BusRouteViewHolder.busType.setText(BusRoute.getBusType());
                BusRouteViewHolder.avgTime.setText(BusRoute.getAvgTime());
                BusRouteViewHolder.avgSpeed.setText(BusRoute.getAvgSpeed());


                BusRouteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TimeSchedulesActivity.this, PasMenuActivity.class);
                        intent.putExtra("rid", BusRoute.getRouteRegNo());
                        startActivity(intent);


                    }
                });

            }

            @NonNull
            @Override
            public BusRouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new BusRouteViewHolder(LayoutInflater.from(TimeSchedulesActivity.this).inflate(R.layout.bus_routes_layout, parent, false));

            }
        };

        recyclerView.setAdapter(adapter);
    }
}
