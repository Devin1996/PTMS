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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PasRegActivity extends AppCompatActivity {


    private EditText pasRegName;
    private EditText pasRegEmail;
    private EditText pasRegPwd;
    private EditText pasRegCpwd;
    private Button PasRegBtn;


    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference PasDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_reg);

        //Authenticating with Firebase
        mAuth = FirebaseAuth.getInstance();

        pasRegName = (EditText) findViewById(R.id.register_username_input);
        pasRegEmail = (EditText) findViewById(R.id.pas_email_reg);
        pasRegPwd = (EditText) findViewById(R.id.pas_pwd_reg);
        pasRegCpwd = (EditText) findViewById(R.id.pas_cpwd_reg);
        PasRegBtn = (Button) findViewById(R.id.register_btn);


        loadingBar = new ProgressDialog(this);


        PasRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pasName = pasRegName.getText().toString();
                String pasEmail = pasRegEmail.getText().toString();
                String pasPwd = pasRegPwd.getText().toString();
                String pasCPwd = pasRegCpwd.getText().toString();

                //calling the method of user registration When register button clicked
                RegisterPassenger(pasName, pasEmail, pasPwd, pasCPwd);
                //StorePassengerData(pasName,pasEmail,pasPwd,pasCPwd);
                //StorePassengerData();

            }
        });
    }


    private void RegisterPassenger(String pasName, String pasEmail, String pasPwd, String pasCPwd) {


        if (TextUtils.isEmpty(pasName)) {
            Toast.makeText(PasRegActivity.this, "Please enter your Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pasEmail)) {

            Toast.makeText(PasRegActivity.this, "Please enter a valid Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pasPwd)) {

            Toast.makeText(PasRegActivity.this, "Please enter a Password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pasCPwd)) {

            Toast.makeText(PasRegActivity.this, "Please confirm your Password", Toast.LENGTH_SHORT).show();
        } else if (!pasPwd.equals(pasCPwd)) {

            Toast.makeText(PasRegActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle("Passenger Register");
            loadingBar.setMessage("Please wait we are Setting up your Account");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //StorePassengerData(pasName,pasEmail,pasCPwd,pasCPwd);

            //create passenger Account
            mAuth.createUserWithEmailAndPassword(pasEmail, pasPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent mapIntent = new Intent(PasRegActivity.this, WelcomeActivity.class);
                        startActivity(mapIntent);
                        //finish();
                        //StorePassengerData(pasName,pasEmail,pasCPwd,pasCPwd);


                        Toast.makeText(PasRegActivity.this, "Registered Successfully....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

//                    PasDatabaseRef = FirebaseDatabase.getInstance().getReference();
//                    if (!(child("Users").child("Passenger").child(eMail).exists()))
//                    HashMap<String, Object> mHashmap = new HashMap<>();
//                    mHashmap.put("Name 1/title", "Ashok");
//                    mHashmap.put("Name 1/content", "Parinitha");
//                    mHashmap.put("Name 2/title", "Krishna");
//                    mHashmap.put("Name 2/content", "Sumuthra");
//                    PasDatabaseRef.updateChildren(mHashmap);


                    } else {
                        Toast.makeText(PasRegActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });


        }
    }

    private void StorePassengerData() {
        final String pasName = pasRegName.getText().toString();
        final String pasEmail = pasRegEmail.getText().toString();
        final String pasPwd = pasRegPwd.getText().toString();
        final String pasCPwd = pasRegCpwd.getText().toString();


        PasDatabaseRef = FirebaseDatabase.getInstance().getReference();

        PasDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child("Passenger").child(pasEmail).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Name ", pasName);
                    userdataMap.put("E mail", pasEmail);
                    userdataMap.put("password", pasPwd);
                    userdataMap.put("Confirm Password", pasCPwd);

                    PasDatabaseRef.child("Users").child("Passenger").child(pasEmail).updateChildren(userdataMap);
                    Intent mapIntent = new Intent(PasRegActivity.this, WelcomeActivity.class);
                    startActivity(mapIntent);


                } else {
                    Toast.makeText(PasRegActivity.this, "This " + pasEmail + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(PasRegActivity.this, "Please try again using another Email", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
