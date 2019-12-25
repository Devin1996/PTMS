package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button WelcomeDriverBtn;
    private Button WelcomePassengerBtn;
    private Button WelcomeConductorBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeDriverBtn = (Button) findViewById(R.id.wel_dri_btn);
        WelcomePassengerBtn = (Button) findViewById(R.id.wel_pas_btn);
        WelcomeConductorBtn = (Button) findViewById(R.id.wel_con_btn);

        WelcomeDriverBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PassengerLogin.class);
                startActivity(intent);
            }
        });

        WelcomeDriverBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, DriverLogin.class);
                startActivity(intent);
            }
        });

        WelcomeDriverBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ConductorLogin.class);
                startActivity(intent);
            }
        });
    }
    }