package com.example.sgram.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.databinding.ActivityLogInBinding;

public class logInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ActivityLogInBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        Intent mainIntent = new Intent(this, JoinActivity.class);

        binding.logInText.setOnClickListener(v -> startActivity(mainIntent));
    }
}