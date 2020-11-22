package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ptms.Model.Book;
import com.example.ptms.Prevelent.Prevelent;
import com.example.ptms.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadConfirmedTrainBooking extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_confirmed_train_booking);


        recyclerView = findViewById(R.id.my_confirm_plan_listss);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //CheckOrderState();
        final DatabaseReference bookingListRef = FirebaseDatabase.getInstance().getReference().child("bookingList");

        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(bookingListRef.child("confirmedBookings")
                                .child(Prevelent.currentOnlineUser.getPhone())
                                .child("trainBooking"), Book.class)
                        .build();

        FirebaseRecyclerAdapter<Book, BookViewHolder> adapter
                = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull final Book model) {

                holder.txtBookDate.setText("Booked Date : " + model.getBookedDate());
                holder.txtBookNoOfSeats.setText("No of Seats : " + model.getNumberOfSeats());
                holder.txtBookarrTime.setText("Arrival Time : " + model.getArrTime());
                holder.txtBookDepTime.setText("Departure Time : " + model.getDepTime());
                holder.txtBookFrom.setText("From : " + model.getFrom());
                holder.txtBookTo.setText("To : " + model.getTo());


            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //return null;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_book_list, viewGroup, false);
                BookViewHolder holder = new BookViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}