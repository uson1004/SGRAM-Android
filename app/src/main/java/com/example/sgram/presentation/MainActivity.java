package com.example.sgram.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.ChatApi;
import com.example.sgram.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Intent chatIntent = new Intent(this, ChattingActivity.class);
        ChatApi chatApi = new ApiProvider(MainActivity.this).getChatApi();

        binding.chattingText.setOnClickListener(v -> {
            startActivity(chatIntent);
        });
    }


}