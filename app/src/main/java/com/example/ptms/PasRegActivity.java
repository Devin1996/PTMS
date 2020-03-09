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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PasRegActivity extends AppCompatActivity {


    private EditText pasRegName;
    private EditText pasRegEmail;
    private EditText pasRegPwd;
    private EditText pasRegCpwd;
    private Button PasRegBtn;



    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference PasDatabaseRef;

    String PasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_reg);

        //Authenticating with Firebase
        mAuth = FirebaseAuth.getInstance();


        //pasRegName = (EditText) findViewById(R.id.register_username_input);
        pasRegEmail = (EditText) findViewById(R.id.pas_email_reg);
        pasRegPwd = (EditText) findViewById(R.id.pas_pwd_reg);
        pasRegCpwd = (EditText) findViewById(R.id.pas_cpwd_reg);
        PasRegBtn = (Button) findViewById(R.id.register_btn);


        loadingBar = new ProgressDialog(this);


        PasRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String pasName = pasRegName.getText().toString();
                String pasEmail = pasRegEmail.getText().toString();
                String pasPwd = pasRegPwd.getText().toString();
                String pasCPwd = pasRegCpwd.getText().toString();

                //calling the method of user registration When register button clicked
                //RegisterPassenger(pasName, pasEmail, pasPwd, pasCPwd);
                RegisterPassenger(pasEmail, pasPwd, pasCPwd);
                //StorePassengerData(pasName,pasEmail,pasPwd,pasCPwd);
                //StorePassengerData();
                onAuthStateChanged(mAuth);

            }
        });
    }


    private void RegisterPassenger(final String pasEmail, String pasPwd, String pasCPwd) {


//        if (TextUtils.isEmpty(pasName)) {
//            Toast.makeText(PasRegActivity.this, "Please enter your Name", Toast.LENGTH_SHORT).show();
//        } else

        if (TextUtils.isEmpty(pasEmail)) {

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


            mAuth.createUserWithEmailAndPassword(pasEmail, pasPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        //FirebaseUser user = mAuth.getCurrentUser();
                        Intent mapIntent = new Intent(PasRegActivity.this, PasMenuActivity.class);

                        startActivity(mapIntent);
                        finish();


                        Toast.makeText(PasRegActivity.this, "Registered Successfully....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        // Write a message to the database
                        //FirebaseDatabase database = FirebaseDatabase.getInstance();
                        //DatabaseReference myRef = database.getReference("Users/Passengers");

                        //myRef.setValue("Hello, World!");
//
//                       PasDatabaseRef = FirebaseDatabase.getInstance().getReference().child("User").child("Passenger");
//
//                        HashMap<String, Object> mHashmap = new HashMap<>();
//                        mHashmap.put("Name",pasName );
//                        mHashmap.put("E mail", pasEmail);
//                        PasDatabaseRef.child(pasEmail).updateChildren(mHashmap);


//

                    } else {
                        Toast.makeText(PasRegActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });


        }
    }

    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        String pasEmail = pasRegEmail.getText().toString();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            HashMap<String, Object> mHashmap = new HashMap<>();
            mHashmap.put("E mail", pasEmail);

            ref.child("User").child("Passenger").child(user.getUid()).setValue(mHashmap);
        }
    }

    private void StorePassengerData(final String pasName, final String pasEmail, final String pasPwd, String pasCPwd) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(pasEmail).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", pasEmail);
                    userdataMap.put("password", pasPwd);
                    userdataMap.put("name", pasName);

                    RootRef.child("Users").child(pasEmail).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(PasRegActivity.this, "Congratulations, you account has been created ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(PasRegActivity.this, WelcomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(PasRegActivity.this, "Network Error.. please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else
                {
                    Toast.makeText(PasRegActivity.this, "This " + pasEmail + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(PasRegActivity.this, "Please try again usin another phone number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PasRegActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }






}
