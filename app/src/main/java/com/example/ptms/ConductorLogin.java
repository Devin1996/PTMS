package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class ConductorLogin extends AppCompatActivity {

    private TextView conStatus;
    private EditText conEmail;
    private EditText conPwd;
    private Button conLogInBtn;
    private TextView conRegNot;
    private Button conRegGoBtn;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_login);

         //Authenticating with Firebase
         mAuth = FirebaseAuth.getInstance();

        conLogInBtn = (Button) findViewById(R.id.con_log_btn);
        conRegGoBtn = (Button) findViewById(R.id.con_reg_men_btn);
        conRegNot = (TextView) findViewById(R.id.con_reg_noti);
        conStatus = (TextView) findViewById(R.id.conductor_status);
        conEmail = (EditText) findViewById(R.id.con_log_email);
        conPwd = (EditText) findViewById(R.id.con_log_pwd);

        loadingBar = new ProgressDialog(this);

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

        conRegGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Cmail = conEmail.getText().toString();
                String Cpwd = conPwd.getText().toString();

                //calling the method of user registration When register button clicked
                RegisterConductor(Cmail,Cpwd);
            }
        });

        conLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //convert user Input into Strings
                String Cmail = conEmail.getText().toString();
                String Cpwd = conPwd.getText().toString();

                //calling the method of user registration When register button clicked
                LogInConductor(Cmail,Cpwd);
            }
        });
    }

    private void LogInConductor(String cmail, String cpwd) {
        if (TextUtils.isEmpty(cmail)){
            Toast.makeText(ConductorLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(cpwd)){
            Toast.makeText(ConductorLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Conductor Login");
            loadingBar.setMessage("Please wait we are cheking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //create passenger Account
            mAuth.signInWithEmailAndPassword(cmail, cpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ConductorLogin.this, "Logged In Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else{
                        Toast.makeText(ConductorLogin.this, "Log In Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }

    //Method which is used to validate user input and to create accounts 

    private void RegisterConductor(String pmail, String ppwd) {
        if (TextUtils.isEmpty(pmail)){
            Toast.makeText(ConductorLogin.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(ppwd)){
            Toast.makeText(ConductorLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Conductor Register");
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
                        Toast.makeText(ConductorLogin.this, "Register Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else{
                        Toast.makeText(ConductorLogin.this, "Registration Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }
}
