package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassengerLogin extends AppCompatActivity {

    private TextView pasStatus;
    private TextView pasEmail;
    private TextView pasPwd;
    private Button pasLogInBtn;
    private TextView pasRegNot;
    private Button pasRegGoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);

        pasLogInBtn = (Button) findViewById(R.id.passenger_login_btn);
        pasRegGoBtn = (Button) findViewById(R.id.pas_reg_go_btn);
        pasRegNot = (TextView) findViewById(R.id.passenger_reg_noti);
        pasStatus = (TextView) findViewById(R.id.passenger_status);

        //Register Btn wiil be not shown Until passenger clicks the Register link

        pasRegGoBtn.setVisibility(View.INVISIBLE);
        pasRegGoBtn.setEnabled(false);


        pasRegNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
                //Log In Btn and Reg link wiil be INVISIBLE when passenger clicks the Reg link
                pasLogInBtn.setVisibility(View.INVISIBLE);
                pasRegNot.setVisibility(View.INVISIBLE);
                
                //Passenger status will change as Register
                pasStatus.setText("Register");
                
                //Register Btn wiil be VISISBLE when passenger clicks the Register link
                pasRegGoBtn.setVisibility(View.VISIBLE);
                pasRegGoBtn.setEnabled(true);
                
                //Intent dintent = new Intent(WelcomeActivity.this, DriverLogin.class);
                //startActivity(dintent);


            }
        });
        
    }

}