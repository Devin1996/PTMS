package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverLogin extends AppCompatActivity {

    private TextView driStatus;
    private TextView driEmail;
    private TextView driPwd;
    private Button driLogInBtn;
    private TextView driRegNot;
    private Button driRegGoBtn;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
    
        driLogInBtn = (Button) findViewById(R.id.dri_log_btn);
        driRegGoBtn = (Button) findViewById(R.id.dri_reg_men_btn);
        driRegNot = (TextView) findViewById(R.id.dri_reg_noti);
        driStatus = (TextView) findViewById(R.id.driver_status);

        //Register Btn wiil be not shown Until Driver clicks the Register link

        driRegGoBtn.setVisibility(View.INVISIBLE);
        driRegGoBtn.setEnabled(false);


        driRegNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
                //Log In Btn and Reg link wiil be INVISIBLE when Driver clicks the Reg link
                driLogInBtn.setVisibility(View.INVISIBLE);
                driRegNot.setVisibility(View.INVISIBLE);
                
                //Driver status will change as Register
                driStatus.setText("Register");
                
                //Register Btn wiil be VISISBLE when Driver clicks the Register link
                driRegGoBtn.setVisibility(View.VISIBLE);
                driRegGoBtn.setEnabled(true);
                
                //Intent dintent = new Intent(WelcomeActivity.this, DriverLogin.class);
                //startActivity(dintent);


            }
        });
    
    
    }
}
