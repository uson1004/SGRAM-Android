package com.example.sgram.presentation;

import android.os.Bundle;

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

        ChatApi chatApi = ApiProvider.getChatApi();

        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();

            final String data = "Bearer b2iidkkdiskejfjv.dsjseilsjdlfe.tokaaweolfskeioswldkeosl";
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });

        });
    }

    public void sendChat(String data, String contents) {

    }
}