package com.example.ptms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SpinnerTestActivity extends AppCompatActivity {

    Spinner spinner;
    EditText editText;
    String textData = "";
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        spinner = (Spinner) findViewById(R.id.myspinner);
        editText = (EditText) findViewById(R.id.search_from);

        databaseReference = FirebaseDatabase.getInstance().getReference("SpinnerData");

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(SpinnerTestActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);

        spinner.setAdapter(adapter);
        retriveData();
    }

    public void btnAddData(View view) {
        textData = editText.getText().toString().trim();
        databaseReference.push().setValue(textData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                editText.setText("");
                spinnerDataList.clear();
                retriveData();
                adapter.notifyDataSetChanged();
                Toast.makeText(SpinnerTestActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void retriveData() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
