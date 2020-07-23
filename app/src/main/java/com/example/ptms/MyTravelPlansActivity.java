package com.example.ptms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptms.Model.Book;
import com.example.ptms.Prevelent.Prevelent;
import com.example.ptms.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyTravelPlansActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotalAmount, txtMsg1;

    private int overTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travel_plans);

        recyclerView = findViewById(R.id.my_travel_plan_listss);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //NextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        //txtTotalAmount = (TextView) findViewById(R.id.total_price);
        //txtMsg1=(TextView) findViewById(R.id.msg1);

//        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //txtTotalAmount.setText("Total Price" + String.valueOf(overTotalPrice));
//
//                Intent intent = new Intent(MyTravelPlansActivity.this, ConfirmFinalBooking.class);
//                //intent.putExtra("Total Price", String.valueOf(overTotalPrice));
//                startActivity(intent);
//                finish();
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //CheckOrderState();

        final DatabaseReference bookingListRef = FirebaseDatabase.getInstance().getReference().child("bookingList");

        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(bookingListRef.child("passengerBookingView")
                                .child(Prevelent.currentOnlineUser.getPhone())
                                .child("busBooking"), Book.class)
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

                //int oneTyprProductTPrice = ((Integer.valueOf(model.getPrice())))*Integer.valueOf(model.getQuantity());
                //overTotalPrice = overTotalPrice+oneTyprProductTPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MyTravelPlansActivity.this, PasMenuActivity.class);
                        intent.putExtra("timeSlotKey", model.getTimeSlotKey());
                        startActivity(intent);


                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit Booking",
                                        "Remove Booking",
                                        "Confirm"
                                };

                        AlertDialog.Builder builder = new AlertDialog.Builder(MyTravelPlansActivity.this);
                        builder.setTitle("Booking Options");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent intent = new Intent(MyTravelPlansActivity.this, AddToTravelPlansActivity.class);
                                    intent.putExtra("timeSlotKey", model.getTimeSlotKey());
                                    startActivity(intent);

                                }
                                if (which == 1) {
                                    bookingListRef.child("passengerBookingView")
                                            .child(Prevelent.currentOnlineUser.getPhone())
                                            .child("busBooking")
                                            .child(model.getTimeSlotKey())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(MyTravelPlansActivity.this, "Booking removed", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(MyTravelPlansActivity.this, PasMenuActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                                if (which == 2) {
//                                    Intent intent = new Intent(MyTravelPlansActivity.this, AddToTravelPlansActivity.class);
//                                    intent.putExtra("timeSlotKey", model.getTimeSlotKey());
//                                    startActivity(intent);

                                    bookingListRef.child("passengerBookingView")
                                            .child(Prevelent.currentOnlineUser.getPhone())
                                            .child("busBooking")
                                            .child(model.getTimeSlotKey())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(MyTravelPlansActivity.this, "Booking removed", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(MyTravelPlansActivity.this, ConfirmFinalBooking.class);
                                                        intent.putExtra("timeSlotKey", model.getTimeSlotKey());
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }

                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //return null;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_travel_plans, viewGroup, false);
                BookViewHolder holder = new BookViewHolder(view);
                return holder;

            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }




    private void CheckOrderState() {
        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference()
                .child("bookings")
                .child(Prevelent.currentOnlineUser.getPhone());

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String shippingState = dataSnapshot.child("state").getValue().toString();
                    //String userName = dataSnapshot.child("name").getValue().toString();
                    if (shippingState.equals("Reserved")) {
                        txtTotalAmount.setText("Dear Passenger booking is Reserved ");
                        recyclerView.setVisibility(View.GONE);

                        //txtMsg1.setVisibility(View.VISIBLE);
                        //txtMsg1.setText("Your ordred book will be recieved as soon as possible");

                        NextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(MyTravelPlansActivity.this, "You can buy more Books", Toast.LENGTH_SHORT).show();

                    } else if (shippingState.equals("Not Reserved")) {
                        txtTotalAmount.setText("Shipping State = Not Reserved");
                        recyclerView.setVisibility(View.GONE);

                        //txtMsg1.setVisibility(View.VISIBLE);
                        NextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(MyTravelPlansActivity.this, "You can buy more Books", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}