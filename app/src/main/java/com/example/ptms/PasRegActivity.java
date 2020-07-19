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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PasRegActivity extends AppCompatActivity {


    private EditText pasRegName;
    private EditText pasRegNo;
    private EditText pasRegPwd;
    private EditText pasRegCpwd;
    private Button PasRegBtn;



    private ProgressDialog loadingBar;

//    private FirebaseAuth mAuth;
//    private FirebaseUser currentUser;
//    private DatabaseReference PasDatabaseRef;

//    String PasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_reg);

        //Authenticating with Firebase
//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getCurrentUser();
//        PasId = currentUser.getUid();


        pasRegName = (EditText) findViewById(R.id.pas_name);
        pasRegNo = (EditText) findViewById(R.id.pas_mobile_reg);
        pasRegPwd = (EditText) findViewById(R.id.pas_pwd_reg);
        pasRegCpwd = (EditText) findViewById(R.id.pas_cpwd_reg);
        PasRegBtn = (Button) findViewById(R.id.register_btn);


        loadingBar = new ProgressDialog(this);


        PasRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String pasName = pasRegName.getText().toString();
                //String pasEmail = pasRegEmail.getText().toString();
                //String pasPwd = pasRegPwd.getText().toString();
                //String pasCPwd = pasRegCpwd.getText().toString();

                //calling the method of user registration When register button clicked
                //RegisterPassenger(pasName, pasEmail, pasPwd, pasCPwd);
                //RegisterPassenger(pasEmail, pasPwd, pasCPwd);
                //StorePassengerData(pasName,pasEmail,pasPwd,pasCPwd);
                //StorePassengerData();
                //onAuthStateChanged();
                CreateAccount();

            }
        });
    }


//    private void RegisterPassenger(final String pasEmail, String pasPwd, String pasCPwd) {


//        if (TextUtils.isEmpty(pasName)) {
//            Toast.makeText(PasRegActivity.this, "Please enter your Name", Toast.LENGTH_SHORT).show();
//        } else

//        if (TextUtils.isEmpty(pasEmail)) {
//
//            Toast.makeText(PasRegActivity.this, "Please enter a valid Email", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pasPwd)) {
//
//            Toast.makeText(PasRegActivity.this, "Please enter a Password", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(pasCPwd)) {
//
//            Toast.makeText(PasRegActivity.this, "Please confirm your Password", Toast.LENGTH_SHORT).show();
//        } else if (!pasPwd.equals(pasCPwd)) {
//
//            Toast.makeText(PasRegActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
//        } else {
//
//            loadingBar.setTitle("Passenger Register");
//            loadingBar.setMessage("Please wait we are Setting up your Account");
//            loadingBar.setCanceledOnTouchOutside(false);
//            loadingBar.show();
//
//
//            mAuth.createUserWithEmailAndPassword(pasEmail, pasPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//
//                        //FirebaseUser user = mAuth.getCurrentUser();
//                        Intent mapIntent = new Intent(PasRegActivity.this, PasMenuActivity.class);
//                        mapIntent.putExtra("PasId", PasId);
//
//                        startActivity(mapIntent);
//                        finish();
//
//
//                        Toast.makeText(PasRegActivity.this, "Registered Successfully....", Toast.LENGTH_SHORT).show();
//
//                        loadingBar.dismiss();
//
//
//                    } else {
//                        Toast.makeText(PasRegActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//                    }
//
//                }
//            });
//
//
//        }
//    }

//    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//        String pasEmail = pasRegEmail.getText().toString();
//
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//            HashMap<String, Object> mHashmap = new HashMap<>();
//            mHashmap.put("E mail", pasEmail);
//
//            ref.child("User").child("Passenger").child(user.getUid()).setValue(mHashmap);
//        }
//    }

//    public void onAuthStateChanged() {
//        String pasEmail = pasRegEmail.getText().toString();
//
//
//        if (currentUser != null) {
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//            HashMap<String, Object> mHashmap = new HashMap<>();
//            mHashmap.put("E mail", pasEmail);
//
//            ref.child("User").child("Passenger").child(String.valueOf(pasRegEmail)).setValue(mHashmap);
//        }
//    }
private void CreateAccount() {
        String name = pasRegName.getText().toString();
        String phone= pasRegNo.getText().toString();
    String password = pasRegPwd.getText().toString();
    String confirmPassword = pasRegCpwd.getText().toString();


    if (TextUtils.isEmpty(name)){
        Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show();
    }
    else if (TextUtils.isEmpty(phone)){
        Toast.makeText(this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
    }

    else if (TextUtils.isEmpty(password)){
        Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
    }

    else if (TextUtils.isEmpty(confirmPassword)){
        Toast.makeText(this, "Please Confirm your Password", Toast.LENGTH_SHORT).show();
    }
    else if (!password.equals(confirmPassword)) {

        Toast.makeText(PasRegActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
    }
    else{
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait we are checking your credentials");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        ValidatephoneNumber(name, phone, password);


    }

}

    private void ValidatephoneNumber(final String name,final String phone,final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("User").child("Passenger").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);



                    RootRef.child("User").child("Passenger").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(PasRegActivity.this, "Congratulations, you account has been created ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(PasRegActivity.this, PasLoginActivity.class);
                                        startActivity(intent);
                                        finish();
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
                    Toast.makeText(PasRegActivity.this, "A account with " + phone + " already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(PasRegActivity.this, "Please try again using another Email", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PasRegActivity.this, PasMainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}
