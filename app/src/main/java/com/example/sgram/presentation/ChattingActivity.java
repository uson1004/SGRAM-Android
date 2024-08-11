package com.example.sgram.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.ChatApi;
import com.example.sgram.data.local.ResponseInterceptor;
import com.example.sgram.data.local.SharedPreferenceManager;
import com.example.sgram.data.local.TokenInterceptor;
import com.example.sgram.data.response.chat.LiveChattingResponse;
import com.example.sgram.databinding.ActivityChattingBinding;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChattingActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);



        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    //sendChat(text);

                }
            });
        });
    }


    public void sendChat(SharedPreferences data) {

        Context sharedContext = (Context) SharedPreferenceManager.getInstance(ChattingActivity.this).edit().putString("accessToken", "");
        OkHttpClient getToken = ApiProvider.getOkHttpClient((SharedPreferences) sharedContext);

        ChatApi chatApi = ApiProvider.getChatApi();
        LiveChattingResponse liveChattingResponse = new LiveChattingResponse("", "", "");
        chatApi.getAccessToken(liveChattingResponse).enqueue(new Callback<LiveChattingResponse>() {
            @Override
            public void onResponse(Call<LiveChattingResponse> call, Response<LiveChattingResponse> response) {
                switch (response.code()) {
                    case 201: {

                    }
                    case 400: {
                        Toast.makeText(sharedContext, "", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LiveChattingResponse> call, Throwable t) {

            }
        });
    }
}