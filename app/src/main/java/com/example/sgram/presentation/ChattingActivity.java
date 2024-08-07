package com.example.sgram.presentation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.ChatApi;

public class ChattingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);



        //LiveChattingRequest liveChattingRequest = new LiveChattingRequest()
        ChatApi chatApi = ApiProvider.getChatApi();


    }
}