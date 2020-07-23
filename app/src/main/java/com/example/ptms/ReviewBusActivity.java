package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReviewBusActivity extends AppCompatActivity {
    private Button reviewBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_bus);

        reviewBtn = (Button) findViewById(R.id.review);
        cancelBtn = (Button) findViewById(R.id.review_cancel);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ReviewBusActivity.this , "Your Review Submitted Successfully" , Toast.LENGTH_SHORT).show();
                Intent intentMenu = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                startActivity(intentMenu);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ReviewBusActivity.this , PasMenuActivity.class);
                startActivity(intentMenu);
                finish();
            }
        });


    }
}
