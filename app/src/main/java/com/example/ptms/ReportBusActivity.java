package com.example.ptms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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


public class ReportBusActivity extends AppCompatActivity {

    Button reportConfirmBtn;
    Button cancelBtn;
    CheckBox violenceBtn;
    CheckBox highNoiceBtn;
    CheckBox highSpeedBtn;
    CheckBox carelessBtn;
    CheckBox inappropriateBtn;
    CheckBox otherBtn;
    TextView textQR;
    TextView tv2;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bus);

        final TextView textQR = (TextView) findViewById(R.id.textView_qr);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        String textval = intent.getStringExtra(OnBoardActivity.EXTRA_TEXT);

        textQR.setText(textval);

        reportConfirmBtn = (Button) findViewById(R.id.reprt_confirm_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_report_btn);
        violenceBtn = (CheckBox) findViewById(R.id.violence_btn);
        highNoiceBtn = (CheckBox) findViewById(R.id.high_noise_btn);
        highSpeedBtn = (CheckBox) findViewById(R.id.high_speed_btn);
        carelessBtn = (CheckBox) findViewById(R.id.careless_btn);
        inappropriateBtn = (CheckBox) findViewById(R.id.inappropriate_btn);
        otherBtn = (CheckBox) findViewById(R.id.other_report_btn);

        loadingBar = new ProgressDialog(this);

        violenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (violenceBtn.isChecked()) {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);


            }
        });

        highNoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (highNoiceBtn.isChecked()) {
                    highNoiceBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    highNoiceBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);

            }
        });

        highSpeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (highSpeedBtn.isChecked()) {
                    highSpeedBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    highSpeedBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);

            }
        });

        carelessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carelessBtn.isChecked()) {
                    carelessBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    carelessBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);

            }
        });


        violenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (violenceBtn.isChecked()) {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);

            }
        });


        inappropriateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inappropriateBtn.isChecked()) {
                    inappropriateBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    inappropriateBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);

            }
        });

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otherBtn.isChecked()) {
                    otherBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    otherBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()) {
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()) {
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()) {
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()) {
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()) {
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()) {
                    reporting += " \n Other";
                }
                tv2.setText(reporting);


            }
        });

        reportConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv_s = tv2.getText().toString();
                String tv_q = textQR.getText().toString();
                String s = "Check";
                if (TextUtils.isEmpty(tv_s)) {
                    Toast.makeText(ReportBusActivity.this , "Please Select your issues" , Toast.LENGTH_SHORT).show();
                } else {
                    addToReportList(tv_s , tv_q);
                    Toast.makeText(ReportBusActivity.this , "Your Report Submitted Successfully" , Toast.LENGTH_SHORT).show();
                    Intent intentMenu = new Intent(ReportBusActivity.this , PasMenuActivity.class);
                    startActivity(intentMenu);
                    finish();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ReportBusActivity.this , PasMenuActivity.class);
                startActivity(intentMenu);
                finish();
            }
        });


    }

    public void reportIssues() {
        String reporting = "";
        if (violenceBtn.isChecked()) {
            reporting += "\n Violence";
        }
        if (highSpeedBtn.isChecked()) {
            reporting += "\n High Speed";
        }
        if (highNoiceBtn.isChecked()) {
            reporting += " \n High Noise";
        }
        if (carelessBtn.isChecked()) {
            reporting += " \n Careless";
        }
        if (inappropriateBtn.isChecked()) {
            reporting += " \n Inappropriate";
        }
        if (otherBtn.isChecked()) {
            reporting += " \n Other";
        }
        tv2.setText(reporting);
    }

    public void addToReportList(final String reportInput , final String QrValue) {
        final String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

//        String userPhone = Prevelent.currentOnlineUser.getPhone();
        final String reportKey = saveCurrentTime + saveCurrentDate + Prevelent.currentOnlineUser.getPhone() + QrValue;

        final DatabaseReference reportListRef;
        reportListRef = FirebaseDatabase.getInstance().getReference().child("report");


        reportListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("reportBus").child(reportKey).exists())) {
                    HashMap<String, Object> reportMap = new HashMap<>();
                    reportMap.put("userPhone" , Prevelent.currentOnlineUser.getPhone());
                    reportMap.put("reportIssue" , reportInput);
                    reportMap.put("qrValue" , QrValue);
                    reportMap.put("reportDate" , saveCurrentDate);
                    reportMap.put("reportTime" , saveCurrentTime);

                    reportListRef.child("reportBus").child(reportKey).updateChildren(reportMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(ReportBusActivity.this , "Your Report Completed " , Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(ReportBusActivity.this , PasMenuActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(ReportBusActivity.this , "Network Error.. please try again after some time..." , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ReportBusActivity.this , "A account with already exists" , Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(ReportBusActivity.this , "Please try again using another way" , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ReportBusActivity.this , PasMenuActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");


//        final HashMap<String, Object> reportMap = new HashMap<>();
//        //reportMap.put("userPhone" , userPhone);
//        reportMap.put("reportIssue" , tv2.getText().toString());
//        reportMap.put("qrValue" , textQR.getText().toString());
//        reportMap.put("date" , saveCurrentDate);
//        reportMap.put("time" , saveCurrentTime);
//
//        bookingListRef.child("reportBus")
//                //.child(reportKey)
//                .updateChildren(reportMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful()) {
//
//                            Toast.makeText(ReportBusActivity.this , "Reported Successfully" , Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(ReportBusActivity.this , PasMenuActivity.class);
//                            startActivity(intent);
//
//                        }
//
//                    }
//                });

    }
}
