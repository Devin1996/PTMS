package com.example.ptms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ReviewBusActivity extends AppCompatActivity {
    private Button reviewBtn, cancelBtn;
    TextView textQR;
    TextView tv2;
    EditText writtenReview;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_bus);

        textQR = (TextView) findViewById(R.id.textView_qr_review);
        tv2 = (TextView) findViewById(R.id.textView_review_hiden);
        writtenReview = (EditText) findViewById(R.id.editTextTextMultiLine);

        loadingBar = new ProgressDialog(this);

        Intent intent = getIntent();
        String textval = intent.getStringExtra(OnBoardActivity.EXTRA_TEXT);

        textQR.setText(textval);

        reviewBtn = (Button) findViewById(R.id.review);
        cancelBtn = (Button) findViewById(R.id.review_cancel);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tv_s = writtenReview.getText().toString();
                String qrValue = textQR.getText().toString();

                if (TextUtils.isEmpty(tv_s)) {
                    Toast.makeText(ReviewBusActivity.this , "Please Make your review first" , Toast.LENGTH_SHORT).show();
                } else {
                    addToReviewList(tv_s,  qrValue);
                    Toast.makeText(ReviewBusActivity.this , "Your Review Submitted Successfully" , Toast.LENGTH_SHORT).show();
                    Intent intentMenu = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                    startActivity(intentMenu);
                    finish();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                startActivity(intentMenu);
                finish();
            }
        });


    }

    public void addToReviewList(final String reviewInput , final String QrValue) {
        final String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

//        String userPhone = Prevelent.currentOnlineUser.getPhone();
        final String reviewKey = saveCurrentDate + Prevelent.currentOnlineUser.getPhone() + QrValue;

        final DatabaseReference reportListRef;
        reportListRef = FirebaseDatabase.getInstance().getReference().child("review");


        reportListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("reviewBus").child(reviewKey).exists())) {
                    HashMap<String, Object> reportMap = new HashMap<>();
                    reportMap.put("userPhone" , Prevelent.currentOnlineUser.getPhone());
                    reportMap.put("reviewIssue" , reviewInput);
                    reportMap.put("qrValue" , QrValue);
                    reportMap.put("reviewDate" , saveCurrentDate);
                    reportMap.put("reviewTime" , saveCurrentTime);

                    reportListRef.child("reviewBus").child(reviewKey).updateChildren(reportMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(ReviewBusActivity.this , "Your review Completed" , Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(ReviewBusActivity.this , "Network Error.. please try again after some time..." , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ReviewBusActivity.this , "your review already exists" , Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    //Toast.makeText(ReviewBusActivity.this , "Please try again using another way" , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
