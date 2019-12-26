package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConductorLogin extends AppCompatActivity {

    private TextView conStatus;
    private TextView conEmail;
    private TextView conPwd;
    private Button conLogInBtn;
    private TextView conRegNot;
    private Button conRegGoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_login);

        conLogInBtn = (Button) findViewById(R.id.con_log_btn);
        conRegGoBtn = (Button) findViewById(R.id.con_reg_men_btn);
        conRegNot = (TextView) findViewById(R.id.con_reg_noti);
        conStatus = (TextView) findViewById(R.id.conductor_status);

        //Register Btn wiil be not shown Until Driver clicks the Register link

        conRegGoBtn.setVisibility(View.INVISIBLE);
        conRegGoBtn.setEnabled(false);


        conRegNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
                //Log In Btn and Reg link wiil be INVISIBLE when Driver clicks the Reg link
                conLogInBtn.setVisibility(View.INVISIBLE);
                conRegNot.setVisibility(View.INVISIBLE);
                
                //Driver status will change as Register
                conStatus.setText("Register");
                
                //Register Btn wiil be VISISBLE when Driver clicks the Register link
                conRegGoBtn.setVisibility(View.VISIBLE);
                conRegGoBtn.setEnabled(true);
                
                //Intent dintent = new Intent(WelcomeActivity.this, DriverLogin.class);
                //startActivity(dintent);


            }
        });
    }
}
