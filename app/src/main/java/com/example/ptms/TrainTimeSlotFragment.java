package com.example.ptms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrainTimeSlotFragment extends Fragment {

    public TrainTimeSlotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_train_time_slot_fragment , container , false);
    }
}