package com.example.ptms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Model.BusRoute;
import com.example.ptms.ViewHolder.BusRouteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BusRoutesFragment extends Fragment {

    private View RequestsFragmentView;
    private RecyclerView recyclerView;
    private ArrayList<BusRoute> arrayList;
    private FirebaseRecyclerOptions<BusRoute> options;
    private FirebaseRecyclerAdapter<BusRoute, BusRouteViewHolder> adapter;
    private DatabaseReference databaseReference;

    public BusRoutesFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        RequestsFragmentView = inflater.inflate(R.layout.fragment_bus_routes , container , false);

        recyclerView = (RecyclerView) RequestsFragmentView.findViewById(R.id.bus_route_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<BusRoute>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("routes").child("busRoutes");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<BusRoute>().setQuery(databaseReference , BusRoute.class).build();

        adapter = new FirebaseRecyclerAdapter<BusRoute, BusRouteViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull BusRouteViewHolder BusRouteViewHolder , int i , @NonNull final BusRoute BusRoute) {

                BusRouteViewHolder.routeNo.setText("Route No:" + BusRoute.getRouteNo());
                BusRouteViewHolder.routeName.setText("Name :" + BusRoute.getRouteName());
                BusRouteViewHolder.totalDistance.setText("Distance :" + BusRoute.getTotalDistance() + "Km");
                BusRouteViewHolder.busType.setText("Type :" + BusRoute.getBusType());
                BusRouteViewHolder.avgTime.setText("Est Time :" + BusRoute.getAvgTime() + "hrs");
                BusRouteViewHolder.avgSpeed.setText("Average Speed :" + BusRoute.getAvgSpeed() + "Kmph");


                BusRouteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(TimeSchedulesActivity.this, Search4BookActivity.class);
//                        intent.putExtra("rid", BusRoute.getRouteRegNo());
//                        startActivity(intent);


                    }
                });

            }

            @NonNull
            @Override
            public BusRouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
                return new BusRouteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_routes_layout , parent , false));

            }
        };

        recyclerView.setAdapter(adapter);
        return RequestsFragmentView;
    }
}