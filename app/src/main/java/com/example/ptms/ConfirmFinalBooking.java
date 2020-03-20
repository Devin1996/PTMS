package com.example.ptms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ptms.Prevelent.Prevelent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalBooking extends AppCompatActivity {

    private EditText nameEditText, phoneEditText;
    private Button confirmOrderBtn;

    //private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_booking);

        //totalAmount = getIntent().getStringExtra("Total Price");
        //Toast.makeText(this, "TotalPrice = $"+totalAmount, Toast.LENGTH_SHORT).show();

        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText = (EditText) findViewById(R.id.shippment_name);
        phoneEditText = (EditText) findViewById(R.id.shippment_phone_number);


        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

    }

    private void Check() {
        if (TextUtils.isEmpty(nameEditText.getText().toString())) {
            Toast.makeText(this, "Please provide your Full name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
            Toast.makeText(this, "Please provide number", Toast.LENGTH_SHORT).show();
        } else {
            ConfirmOrder();
        }
    }

    private void ConfirmOrder() {
        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("bookings")
                .child(Prevelent.currentOnlineUser.getPhone()).child("busBooking");

        HashMap<String, Object> orderMap = new HashMap<>();
        //orderMap.put("totalAmount", totalAmount);
        orderMap.put("name", nameEditText.getText().toString());
        orderMap.put("phone", phoneEditText.getText().toString());
        orderMap.put("date", saveCurrentDate);
        orderMap.put("time", saveCurrentTime);
        orderMap.put("state", "Not Reserved");

        ordersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("bookingList")
                            .child("passengerBookingView")
                            .child(Prevelent.currentOnlineUser.getPhone()).child("busBooking")
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ConfirmFinalBooking.this, "Your Book has been completed", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ConfirmFinalBooking.this, PasMenuActivity.class);
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        //finish();
                                    }
                                }
                            });
                }
            }
        });

    }
}