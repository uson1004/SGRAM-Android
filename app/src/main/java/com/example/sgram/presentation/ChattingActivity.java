package com.example.sgram.presentation;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sgram.R;
import com.example.sgram.data.local.ResponseInterceptor;
import com.example.sgram.data.local.SharedPreferenceManager;
import com.example.sgram.data.local.TokenInterceptor;
import com.example.sgram.data.recycle.RecyclerAdapter;
import com.example.sgram.data.recycle.RecyclerData;
import com.example.sgram.databinding.ActivityChattingBinding;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChattingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String BASE_URL = "http://localhost:8080/example/";
    private WebSocket webSocket;

    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<RecyclerData> list;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        binding.submitBt.setOnClickListener(v -> {
            list.add(new RecyclerData(binding.chatInsert.getText().toString()));
            recyclerAdapter.notifyDataSetChanged();
            binding.chatInsert.setText("");
        });


        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();
            new Thread(() -> runOnUiThread(() ->
                    sendChat(text)
            )
            ).start();
        });
    }


    public void sendChat(String data) {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(ChattingActivity.this);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .addInterceptor(new ResponseInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                runOnUiThread(() -> {

                });
            }
        });
    }
}