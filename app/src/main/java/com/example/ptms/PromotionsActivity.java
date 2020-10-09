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
import com.example.ptms.Model.DatabasePromotions;
import com.example.ptms.ViewHolder.BusRouteViewHolder;
import com.example.ptms.ViewHolder.PromotionViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PromotionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DatabasePromotions> arrayList;
    private FirebaseRecyclerOptions<DatabasePromotions> options;
    private FirebaseRecyclerAdapter<DatabasePromotions, PromotionViewHolder> adapter;
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
        setContentView(R.layout.activity_promotions);

        recyclerView = findViewById(R.id.recycler_promotion_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DatabasePromotions>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Promotions");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<DatabasePromotions>().setQuery(databaseReference, DatabasePromotions.class).build();



        adapter = new FirebaseRecyclerAdapter<DatabasePromotions, PromotionViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull PromotionViewHolder PromotionViewHolder, int i, @NonNull final DatabasePromotions DatabasePromotions) {

                PromotionViewHolder.txtProductDescription.setText(DatabasePromotions.getDesc());
                Picasso.get().load(DatabasePromotions.getImage()).into(PromotionViewHolder.imageView);

                PromotionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PromotionsActivity.this, PasMenuActivity.class);
                        //intent.putExtra("rid", BusRoute.getRouteRegNo());
                        startActivity(intent);


                    }
                });

            }

            @NonNull
            @Override
            public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new PromotionViewHolder(LayoutInflater.from(PromotionsActivity.this).inflate(R.layout.database_promo_layout, parent, false));

            }
        };

        recyclerView.setAdapter(adapter);
    }
}
