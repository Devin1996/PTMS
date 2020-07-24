package com.example.ptms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class OnBoardActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.ptms.EXTRA_TEXT";
    public final static int QRcodeWidth = 350;
    TextView tv_qr_readTxt;
    Button scanQrBtn;
    Button reportBtn;
    Button reviewBtn;
    Button askHelpBtn, chatBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);


        tv_qr_readTxt = (TextView) findViewById(R.id.tv_qr_readTxt1);
        scanQrBtn = (Button) findViewById(R.id.scan_qr);
        reportBtn = (Button) findViewById(R.id.report_btn);
        reviewBtn = (Button) findViewById(R.id.review_btn);
        askHelpBtn = (Button) findViewById(R.id.ask_help_btn);
        chatBot = (Button) findViewById(R.id.travel_bot_btn);

        scanQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(OnBoardActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busDetail = tv_qr_readTxt.getText().toString();
                if (TextUtils.isEmpty(busDetail)) {
                    Toast.makeText(OnBoardActivity.this , "Please Scan the Qr Code" , Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(OnBoardActivity.this , ReportBusActivity.class);
                    intent.putExtra(EXTRA_TEXT , busDetail);
                    startActivity(intent);
                }

            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busDetail = tv_qr_readTxt.getText().toString();
                if (TextUtils.isEmpty(busDetail)) {
                    Toast.makeText(OnBoardActivity.this , "Please Scan the Qr Code" , Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(OnBoardActivity.this , ReviewBusActivity.class);
                    intent.putExtra(EXTRA_TEXT , busDetail);
                    startActivity(intent);
                }
            }
        });

        askHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushValue();
                Intent intent = new Intent(OnBoardActivity.this , AskHelpActivity.class);
                startActivity(intent);
            }
        });

        chatBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardActivity.this , ChatbotActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode , resultCode , data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("Scan*******" , "Cancelled scan");

            } else {
                Log.e("Scan" , "Scanned");

                tv_qr_readTxt.setText(result.getContents());
                Toast.makeText(this , "Scanned: " + result.getContents() , Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode , resultCode , data);
        }
    }

    public void pushValue() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("QR");

        myRef.setValue(tv_qr_readTxt.getText().toString());
        //myRef.setValue("Scanning My Qr code");
        //travelPlanMap.put("from", fromCity.getText().toString());
    }
}
