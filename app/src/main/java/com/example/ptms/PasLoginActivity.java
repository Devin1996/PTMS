package com.example.ptms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class PasLoginActivity extends AppCompatActivity {

    private EditText pasEmail;
    private EditText pasPwd;
    private Button pasLogInBtn;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference PasDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_login);

        //Authenticating with Firebase
        mAuth = FirebaseAuth.getInstance();


        pasLogInBtn = (Button) findViewById(R.id.pas_login_btn);
        pasEmail = (EditText) findViewById(R.id.Pas_login_mail);
        pasPwd = (EditText) findViewById(R.id.Pas_login_pwd);

        loadingBar = new ProgressDialog(this);

        pasLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //convert user Input into Strings
                String Pmail = pasEmail.getText().toString();
                String Ppwd = pasPwd.getText().toString();

                //calling the method of user registration When register button clicked
                LogInPassenger(Pmail, Ppwd);
            }
        });


    }

    private void LogInPassenger(String pmail, String ppwd) {
        if (TextUtils.isEmpty(pmail)) {
            Toast.makeText(PasLoginActivity.this, "Please enter your E mail", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ppwd)) {
            Toast.makeText(PasLoginActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle("Passenger Login");
            loadingBar.setMessage("Please wait we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            //log in passenger Account
            mAuth.signInWithEmailAndPassword(pmail, ppwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent mapIntent = new Intent(PasLoginActivity.this, PasMenuActivity.class);
                        startActivity(mapIntent);
                        //finish();

                        Toast.makeText(PasLoginActivity.this, "Logged In Succesful....", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    } else {
                        Toast.makeText(PasLoginActivity.this, "Log In Unsuccesful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }
    }
}
