package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptms.Model.Book;
import com.example.ptms.Prevelent.Prevelent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalTrainBookingActivity extends AppCompatActivity {

    private EditText payName, payID, payAmount, payNote;
    private Button confirmOrderBtn;
    private TextView fromCity, toCity, arrivalTime, departureTime, rideNo, noSeats, txtDate;
    private String timeSlotKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_train_booking);


        timeSlotKey = getIntent().getStringExtra("timeSlotKey");


        txtDate = (TextView) findViewById(R.id.booked_date);
        fromCity = (TextView) findViewById(R.id.from_pay_travel_plans);
        toCity = (TextView) findViewById(R.id.to_pay_travel_plans);
        arrivalTime = (TextView) findViewById(R.id.arr_pay_travel_plans);
        departureTime = (TextView) findViewById(R.id.dep_pay_travel_plans);
        rideNo = (TextView) findViewById(R.id.track_no_pay_travel_plans);
        noSeats = (TextView) findViewById(R.id.no_seats_tv);

        confirmOrderBtn = (Button) findViewById(R.id.confirm_btn_final);
        payName = (EditText) findViewById(R.id.pay_name);
        payID = (EditText) findViewById(R.id.pay_id);
        payAmount = (EditText) findViewById(R.id.pay_amount);
        payNote = (EditText) findViewById(R.id.pay_transaction);

        getBookingDetails(timeSlotKey);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

    }

    private void Check() {
        if (TextUtils.isEmpty(payName.getText().toString())) {
            Toast.makeText(this , "Please provide your payment name" , Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(payID.getText().toString())) {
            Toast.makeText(this , "Please enter UPI ID number" , Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(payAmount.getText().toString())) {
            Toast.makeText(this , "Please provide Payment Amount" , Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(payNote.getText().toString())) {
            Toast.makeText(this , "Please provide Transaction Note" , Toast.LENGTH_SHORT).show();
        } else {
            ConfirmBooking();
        }
    }

    private void ConfirmBooking() {
        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("bookingList").child("confirmedBookings")
                .child(Prevelent.currentOnlineUser.getPhone()).child("trainBooking").child(timeSlotKey);

        final HashMap<String, Object> orderMap = new HashMap<>();
        //orderMap.put("totalAmount", totalAmount);
        orderMap.put("name" , payName.getText().toString());
        orderMap.put("payAmount" , payAmount.getText().toString());
        orderMap.put("payID" , payID.getText().toString());
        orderMap.put("payNote" , payNote.getText().toString());
        orderMap.put("date" , saveCurrentDate);
        orderMap.put("time" , saveCurrentTime);
        orderMap.put("timeSlotKey" , timeSlotKey);
        orderMap.put("from" , fromCity.getText().toString());
        orderMap.put("to" , toCity.getText().toString());
        orderMap.put("arrTime" , arrivalTime.getText().toString());
        orderMap.put("depTime" , departureTime.getText().toString());
        orderMap.put("rideNo" , rideNo.getText().toString());
        orderMap.put("numberOfSeats" , noSeats.getText().toString());
        orderMap.put("BookedDate", txtDate.getText().toString());
        orderMap.put("userMobile",Prevelent.currentOnlineUser.getPhone());
        orderMap.put("state","initial");

        ordersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("bookingList")
                            .child("passengerBookingView")
                            .child(Prevelent.currentOnlineUser.getPhone()).child("trainBooking")
                            .child(timeSlotKey)
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseDatabase.getInstance().getReference().child("bookingList").child("trains").child("AllConfirmedBookings").push().updateChildren(orderMap);

                                        Toast.makeText(ConfirmFinalTrainBookingActivity.this , "Your Book has been completed" , Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ConfirmFinalTrainBookingActivity.this , PasMenuActivity.class);
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });

    }

    private void getBookingDetails(String timeSlotKey) {
        DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference().child("bookingList").child("passengerBookingView")
                .child(Prevelent.currentOnlineUser.getPhone())
                .child("trainBooking");
        //DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("timeSlots").child("busTime");

        bookingRef.child(timeSlotKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Book booking = dataSnapshot.getValue(Book.class);

                    fromCity.setText(booking.getFrom().toUpperCase());
                    toCity.setText(booking.getTo().toUpperCase());
                    arrivalTime.setText(booking.getArrTime());
                    departureTime.setText(booking.getDepTime());
                    //rideNo.setText(products.getTrackNo());
                    noSeats.setText(booking.getNumberOfSeats());
                    txtDate.setText(booking.getBookedDate());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }
}