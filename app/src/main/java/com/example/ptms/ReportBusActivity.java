package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class ReportBusActivity extends AppCompatActivity {

    Button reportConfirmBtn;
    Button cancelBtn;
    Button violenceBtn;
    Button highNoiceBtn;
    Button highSpeedBtn;
    Button carelessBtn;
    Button inappropriateBtn;
    Button otherBtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bus);

        TextView tv = (TextView)findViewById(R.id.textView);


        //Bundle b = intent.getExtras();
//
//        if(b!=null)
//        {
//            String j =(String) b.get("message");
//            tv.setText(j);
//        }

        reportConfirmBtn =(Button) findViewById(R.id.reprt_confirm_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_report_btn);
        violenceBtn= (Button) findViewById(R.id.violence_btn);
        highNoiceBtn= (Button) findViewById(R.id.high_noise_btn);
        highSpeedBtn= (Button) findViewById(R.id.high_speed_btn);
        carelessBtn= (Button) findViewById(R.id.careless_btn);
        inappropriateBtn= (Button) findViewById(R.id.inappropriate_btn);
        otherBtn= (Button) findViewById(R.id.other_report_btn);
    }
}
