package com.example.sgram.presentation;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sgram.R;
import com.example.sgram.data.remote.interceptor.SharedPreferenceManager;
import com.example.sgram.data.remote.interceptor.TokenInterceptor;
import com.example.sgram.presentation.recycle.RecyclerAdapter;
import com.example.sgram.presentation.recycle.RecyclerData;
import com.example.sgram.databinding.ActivityChattingBinding;

import org.json.JSONObject;

import java.util.List;

import io.socket.client.Socket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Retrofit;

public class ChattingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String BASE_URL = "http://172.20.10.3:8080";
    private WebSocket webSocket;
    private Socket socket;

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

        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();
            sendChat(text);
            getUserInfo();

            recyclerAdapter.notifyDataSetChanged();
            binding.chatInsert.setText("");
        });
    }


    public void sendChat(String data) {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(ChattingActivity.this);
        Handler mainHandler = new Handler(Looper.getMainLooper());

        String Token = sharedPreferences.getString("accessToken", "");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/send")
                .addHeader("Authorization", "Bearer " + Token)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                //JSONObject message = new JSONObject();
                mainHandler.post(() -> {
                        webSocket.send(data);
                        Toast.makeText(ChattingActivity.this, "연결에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                    }
                );
            }

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
        if (webSocket != null) {
            webSocket.close(1000, "Activity destroyed");
        }
        if (socket != null) {
            socket.disconnect();
        }
    }

    public void getUserInfo() {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(this);
        String Token = sharedPreferences.getString("accessToken", "0");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/live-chatting")
                .addHeader("Authorization", "Bearer" + Token)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                // websocket 연결 성공 시
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                // 서버에서 받은 메세지 처리 (String)
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);

            }
        });
    }
}