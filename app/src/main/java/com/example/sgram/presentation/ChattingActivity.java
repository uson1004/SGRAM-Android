package com.example.sgram.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.ChatApi;
import com.example.sgram.databinding.ActivityChattingBinding;

public class ChattingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);

        //LiveChattingRequest liveChattingRequest = new LiveChattingRequest()
        ChatApi chatApi = ApiProvider.getChatApi();

        binding.submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.chatInsert.getText().toString();

                Toast.makeText(ChattingActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}