package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread()
        {
            @Override
            public void run(){
                try{
                    sleep(7000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }finally{
                    Intent WelcomeIntent = new Intent(SplashActivity.this, PasMainActivity.class);
                    startActivity(WelcomeIntent);
                }
            }

        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
