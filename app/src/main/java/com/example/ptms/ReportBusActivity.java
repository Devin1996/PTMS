package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class ReportBusActivity extends AppCompatActivity {

    Button reportConfirmBtn;
    Button cancelBtn;
    CheckBox violenceBtn;
    CheckBox highNoiceBtn;
    CheckBox highSpeedBtn;
    CheckBox carelessBtn;
    CheckBox inappropriateBtn;
    CheckBox otherBtn;
    TextView tv;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bus);

        TextView tv = (TextView) findViewById(R.id.textView);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        String text = intent.getStringExtra(OnBoardActivity.EXTRA_TEXT);

        tv.setText(text);

        reportConfirmBtn = (Button) findViewById(R.id.reprt_confirm_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_report_btn);
        violenceBtn = (CheckBox) findViewById(R.id.violence_btn);
        highNoiceBtn = (CheckBox) findViewById(R.id.high_noise_btn);
        highSpeedBtn = (CheckBox) findViewById(R.id.high_speed_btn);
        carelessBtn = (CheckBox) findViewById(R.id.careless_btn);
        inappropriateBtn = (CheckBox) findViewById(R.id.inappropriate_btn);
        otherBtn = (CheckBox) findViewById(R.id.other_report_btn);

        violenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (violenceBtn.isChecked()) {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    violenceBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                String reporting = "";
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
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
                if (violenceBtn.isChecked()){
                    reporting += "\n Violence";
                }
                if (highSpeedBtn.isChecked()){
                    reporting += "\n High Speed";
                }
                if (highNoiceBtn.isChecked()){
                    reporting += " \n High Noise";
                }
                if (carelessBtn.isChecked()){
                    reporting += " \n Careless";
                }
                if (inappropriateBtn.isChecked()){
                    reporting += " \n Inappropriate";
                }
                if (otherBtn.isChecked()){
                    reporting += " \nOther";
                }
                tv2.setText(reporting);


            }
        });

        reportConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ReportBusActivity.this , "Your Report Submitted Successfully" , Toast.LENGTH_SHORT).show();
                Intent intentMenu = new Intent(ReportBusActivity.this , PasMenuActivity.class);
                startActivity(intentMenu);
                finish();
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

    public void reportIssues(){
        String reporting = "";
        if (violenceBtn.isChecked()){
            reporting += "\n Violence";
        }
        if (highSpeedBtn.isChecked()){
            reporting += "\n High Speed";
        }
        if (highNoiceBtn.isChecked()){
            reporting += " \n High Noise";
        }
        if (carelessBtn.isChecked()){
            reporting += " \n Careless";
        }
        if (inappropriateBtn.isChecked()){
            reporting += " \n Inappropriate";
        }
        if (otherBtn.isChecked()){
            reporting += " \nOther";
        }
        tv2.setText(reporting);
    }
}
