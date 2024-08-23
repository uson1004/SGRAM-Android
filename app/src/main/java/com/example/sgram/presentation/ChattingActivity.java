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
import com.example.sgram.data.remote.interceptor.SharedPreferenceManager;
import com.example.sgram.data.remote.interceptor.TokenInterceptor;
import com.example.sgram.presentation.recycle.RecyclerAdapter;
import com.example.sgram.presentation.recycle.RecyclerData;
import com.example.sgram.databinding.ActivityChattingBinding;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Retrofit;

public class ChattingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String BASE_URL = "http://172.20.10.3:8080";
    private WebSocket webSocket;

    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<RecyclerData> list;

    private Socket socket;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        binding.submitBt.setOnClickListener(v -> {

        });

        try {
            socket = IO.socket("http/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();
            new Thread(() -> {
                    runOnUiThread(() -> {
                            socket.emit("", text);

                        }
                    );
            });

            //list.add(new RecyclerData();
            recyclerAdapter.notifyDataSetChanged();
            binding.chatInsert.setText("");

        });


    }


    public void sendChat(String data) {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(ChattingActivity.this);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .addInterceptor(new ResponseInterceptor())
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        Emitter eventName = socket.off("EVENT_NAME");

    }
}