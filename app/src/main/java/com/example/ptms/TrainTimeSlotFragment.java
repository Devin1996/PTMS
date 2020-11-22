package com.example.ptms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Model.BusTimeDisplay;
import com.example.ptms.ViewHolder.BusTimeDisplayViewHolder2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrainTimeSlotFragment extends Fragment {

    private View RequestsFragmentView;
    private ImageButton SearchBtn;
    private EditText inputText, toInputText;
    private RecyclerView searchList;
    private String SearchInput, SearchToInput;

    private DatabaseReference mTimeDatabase;

    public TrainTimeSlotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        RequestsFragmentView = inflater.inflate(R.layout.activity_train_time_slot_fragment , container , false);

        inputText = RequestsFragmentView.findViewById(R.id.search_field);
        toInputText = RequestsFragmentView.findViewById(R.id.search_to_field_123);
        SearchBtn = RequestsFragmentView.findViewById(R.id.search_btn);
        searchList = (RecyclerView) RequestsFragmentView.findViewById(R.id.result_list12);
        searchList.setLayoutManager(new LinearLayoutManager(getContext()));


        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchInput = inputText.getText().toString().toLowerCase() + toInputText.getText().toString();
                //SearchToInput = ;

                onStart();
            }
        });

        return RequestsFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("timeSlots").child("trainTime");

        FirebaseRecyclerOptions<BusTimeDisplay> options =
                new FirebaseRecyclerOptions.Builder<BusTimeDisplay>()
                        .setQuery(reference.orderByChild("searchkey").startAt(SearchInput).endAt(SearchInput + "\uf8ff")
                                //.orderByChild("to").startAt(SearchToInput).endAt(SearchToInput + "\uf8ff")
                                , BusTimeDisplay.class)
                        .build();

        FirebaseRecyclerAdapter<BusTimeDisplay, BusTimeDisplayViewHolder2> adapter =
                new FirebaseRecyclerAdapter<BusTimeDisplay, BusTimeDisplayViewHolder2>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusTimeDisplayViewHolder2 holder , int position , @NonNull final BusTimeDisplay model) {
                        holder.from.setText("From : " + model.getFrom());
                        holder.to.setText("To : " + model.getTo());
                        holder.arrTime.setText("Arrival Time : " + model.getArrTime());
                        holder.depTime.setText("Departure Time : " + model.getDepTime());
                        holder.rideno.setText("Ride No : " + model.getRideNo());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity() , AddNewTrainBooking.class);
                                intent.putExtra("timeSlotKey" , model.getTimeSlotKey());
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public BusTimeDisplayViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout , parent , false);
                        BusTimeDisplayViewHolder2 holder = new BusTimeDisplayViewHolder2(view);
                        return holder;
                    }
                };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}