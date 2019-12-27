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

public class DriverLogin extends AppCompatActivity {

    private TextView driStatus;
    private EditText driEmail;
    private EditText driPwd;
    private Button driLogInBtn;
    private TextView driRegNot;
    private Button driRegGoBtn;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

         //Authenticating with Firebase
         mAuth = FirebaseAuth.getInstance();
    
        driLogInBtn = (Button) findViewById(R.id.dri_log_btn);
        driRegGoBtn = (Button) findViewById(R.id.dri_reg_men_btn);
        driRegNot = (TextView) findViewById(R.id.dri_reg_noti);
        driStatus = (TextView) findViewById(R.id.driver_status);
        driEmail = (EditText) findViewById(R.id.dri_log_mail);
        driPwd = (EditText) findViewById(R.id.dri_log_pwd);

        loadingBar = new ProgressDialog(this);

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
                



            }
        });

        driRegGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Dmail = driEmail.getText().toString();
                String Dpwd = driPwd.getText().toString();

                //calling the method of user registration When register button clicked
                RegisterDriver(Dmail,Dpwd);
            }
        });
        
        driLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Dmail = driEmail.getText().toString();
                String Dpwd = driPwd.getText().toString();

                //calling the method of user registration When register button clicked
                LogInDriver(Dmail,Dpwd);
            }
        });
    }

    private void LogInDriver(String dmail, String dpwd) {
        if (TextUtils.isEmpty(dmail)){
            Toast.makeText(DriverLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(dpwd)){
            Toast.makeText(DriverLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please wait we are cheking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //create passenger Account
            mAuth.signInWithEmailAndPassword(dmail, dpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(DriverLogin.this, "Logged In Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        Intent dintent = new Intent(DriverLogin.this, DriversMapsActivity.class);
                        startActivity(dintent);
                    }
                    else{
                        Toast.makeText(DriverLogin.this, "Log In Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }

    //Method which is used to validate user input and to create accounts 

    private void RegisterDriver(String pmail, String ppwd) {
        if (TextUtils.isEmpty(pmail)){
            Toast.makeText(DriverLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(ppwd)){
            Toast.makeText(DriverLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Driver Register");
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
                        Toast.makeText(DriverLogin.this, "Register Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        Intent dintent = new Intent(DriverLogin.this, DriversMapsActivity.class);
                        startActivity(dintent);
                    }
                    else{
                        Toast.makeText(DriverLogin.this, "Registration Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }
}
