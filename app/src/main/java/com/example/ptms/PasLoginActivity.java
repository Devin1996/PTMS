package com.example.ptms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ptms.Model.Passenger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.ptms.Prevelent.Prevelent;

import io.paperdb.Paper;


public class PasLoginActivity extends AppCompatActivity {

    private EditText pasMobile;
    private EditText pasPwd;
    private Button pasLogInBtn;
    private CheckBox chkBoxRememberMe;

    private ProgressDialog loadingBar;

//    private FirebaseAuth mAuth;
//    private FirebaseUser currentUser;
//    private DatabaseReference PasDatabaseRef;

//    String PasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_login);

        //Authenticating with Firebase
//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getCurrentUser();
//        PasId = currentUser.getUid();


        pasLogInBtn = (Button) findViewById(R.id.pas_login_btn);
        pasMobile = (EditText) findViewById(R.id.Pas_login_no);
        pasPwd = (EditText) findViewById(R.id.Pas_login_pwd);

        loadingBar = new ProgressDialog(this);


        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);
        pasLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //convert user Input into Strings
//                String pMobile = pasMobile.getText().toString();
//                String pPwd = pasPwd.getText().toString();

                //calling the method of user registration When register button clicked
                LoginUser();
            }
        });


    }
    private void LoginUser() {
        String phone = pasMobile.getText().toString();
        String password = pasPwd.getText().toString();

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait we are cheking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevelent.UserPhoneKey, phone);
            Paper.book().write(Prevelent.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("User").child("Passenger").child(phone).exists())
                {
                    Passenger usersData = dataSnapshot.child("User").child("Passenger").child(phone).getValue(Passenger.class);

                    if (usersData.getPhone().equals(phone)){

                        if (usersData.getPassword().equals(password)){


                                Toast.makeText(PasLoginActivity.this, "You have Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(PasLoginActivity.this, PasMenuActivity.class);
                                Prevelent.currentOnlineUser = usersData;
                                startActivity(intent);



                        }

                        else {
                            loadingBar.dismiss();
                            Toast.makeText(PasLoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else
                {
                    Toast.makeText(PasLoginActivity.this, "Account with this " +phone+ "number do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private void LogInPassenger(String pmail, String ppwd) {
//        if (TextUtils.isEmpty(pmail)) {
//            Toast.makeText(PasLoginActivity.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(ppwd)) {
//            Toast.makeText(PasLoginActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
//        } else {
//
//            loadingBar.setTitle("Passenger Login");
//            loadingBar.setMessage("Please wait we are checking your credentials");
//            loadingBar.setCanceledOnTouchOutside(false);
//            loadingBar.show();
//
//            //log in passenger Account
//            mAuth.signInWithEmailAndPassword(pmail, ppwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//
//                        Intent mapIntent = new Intent(PasLoginActivity.this, PasMenuActivity.class);
//                        startActivity(mapIntent);
//                        finish();
//
//                        Toast.makeText(PasLoginActivity.this, "Logged In Succesful....", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database.getReference("message");
//                        myRef.setValue("Just Checking "+PasId);
//
//                    } else {
//                        Toast.makeText(PasLoginActivity.this, "Log In Unsuccesful", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//                    }
//
//                }
//            });
//        }
//    }
}
