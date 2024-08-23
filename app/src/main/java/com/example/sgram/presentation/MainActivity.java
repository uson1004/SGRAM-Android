package com.example.sgram.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPreferences = getSharedPreferences("sgram", MODE_PRIVATE);
        sharedPreferences.getString("accessToken", "0");

        binding.chattingText.setOnClickListener(v -> {
            chatApi.getLiveChatting(sharedPreferences.toString());

            startActivity(chatIntent);
        });
    }


}