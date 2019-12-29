package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PassengerLogin extends AppCompatActivity {

    private TextView pasStatus;
    private EditText pasEmail;
    private EditText pasPwd;
    private Button pasLogInBtn;
    private TextView pasRegNot;
    private Button pasRegGoBtn;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);

        //Authenticating with Firebase
        mAuth = FirebaseAuth.getInstance();

        pasLogInBtn = (Button) findViewById(R.id.passenger_login_btn);
        pasRegGoBtn = (Button) findViewById(R.id.pas_reg_go_btn);
        pasRegNot = (TextView) findViewById(R.id.passenger_reg_noti);
        pasStatus = (TextView) findViewById(R.id.passenger_status);
        pasEmail = (EditText) findViewById(R.id.passenger_login_mail);
        pasPwd = (EditText) findViewById(R.id.passenger_login_pwd);

        loadingBar = new ProgressDialog(this);

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

        pasRegGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Pmail = pasEmail.getText().toString();
                String Ppwd = pasPwd.getText().toString();

                //calling the method of user registration When register button clicked
                RegisterPassenger(Pmail,Ppwd);
            }
        });

        pasLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Pmail = pasEmail.getText().toString();
                String Ppwd = pasPwd.getText().toString();

                //calling the method of user registration When register button clicked
                LogInPassenger(Pmail,Ppwd);
            }
        });

    



    }

    private void LogInPassenger(String pmail, String ppwd) {
        if (TextUtils.isEmpty(pmail)){
            Toast.makeText(PassengerLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(ppwd)){
            Toast.makeText(PassengerLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please wait we are cheking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //create passenger Account
            mAuth.signInWithEmailAndPassword(pmail, ppwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(PassengerLogin.this, "Logged In Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        // Write a message to the database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("message");

                        myRef.setValue("Hello, World!");
                    }
                    else{
                        Toast.makeText(PassengerLogin.this, "Log In Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }

    //Method which is used to validate user input and to create accounts 

    private void RegisterPassenger(String pmail, String ppwd) {
        if (TextUtils.isEmpty(pmail)){
            Toast.makeText(PassengerLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(ppwd)){
            Toast.makeText(PassengerLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Passenger Register");
            loadingBar.setMessage("Please wait we are registering credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //create passenger Account
            mAuth.createUserWithEmailAndPassword(pmail, ppwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(PassengerLogin.this, "Register Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        // Write a message to the database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("message");

                        myRef.setValue("Hello, World!");
                    }
                    else{
                        Toast.makeText(PassengerLogin.this, "Registration Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }

}