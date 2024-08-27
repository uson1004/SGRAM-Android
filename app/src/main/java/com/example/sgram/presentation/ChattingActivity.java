package com.example.sgram.presentation;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgram.R;
import com.example.sgram.data.remote.interceptor.SharedPreferenceManager;
import com.example.sgram.data.remote.interceptor.TokenInterceptor;
import com.example.sgram.presentation.recycle.RecyclerAdapter;
import com.example.sgram.presentation.recycle.RecyclerData;
import com.example.sgram.databinding.ActivityChattingBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.socket.client.Socket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Retrofit;

public class ChattingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private WebSocket webSocket;
    private Socket socket;

    private Gson gson = new Gson();

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);

        recyclerView = binding.recyclerView;
        connectSocket();

        // 버튼 클릭 소켓 연결
        binding.submitBt.setOnClickListener(v -> {
            String text = binding.chatInsert.getText().toString();
            sendChat(text);

            getUserInfo();
        });
    }

    public void connectSocket() {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(ChattingActivity.this);

        String Token = sharedPreferences.getString("access_token", "");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .build();

        Request request = new Request.Builder()
                .url("ws://172.20.10.5:8080/ws/chat")
                .addHeader("Authorization", "Bearer " + Token)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);

            }
        });
    }

    public void sendChat(String data) {
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(ChattingActivity.this);
        //Handler mainHandler = new Handler(Looper.getMainLooper());

        String Token = sharedPreferences.getString("access_token", "");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .build();

        Request request = new Request.Builder()
                .url("ws://172.20.10.5:8080/ws/chat")
                .addHeader("Authorization", "Bearer " + Token)
                .build();
        Log.d("TEST", Token);
        webSocket = client.newWebSocket(request, new WebSocketListener() {

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                runOnUiThread(() -> {
                        webSocket.send(data);
                        Toast.makeText(ChattingActivity.this, "연결에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                    }
                );
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                runOnUiThread(() -> {
                    webSocket.send("{\"message\":"+data);
                });
            }
        });
    }

    public void getUserInfo() {
        ActivityChattingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);
        SharedPreferences sharedPreferences = SharedPreferenceManager.getInstance(this);
        String Token = sharedPreferences.getString("access_token", "0");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(sharedPreferences))
                .pingInterval(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://172.20.10.5:8080/ws/chat")
                .addHeader("Authorization", "Bearer " + Token)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                // websocket 연결 성공 시
                JSONObject jsonObject = new JSONObject();
                String socketText = binding.chatInsert.getText().toString();

                try {
                    jsonObject.put("message", socketText);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                // 서버에서 받은 메세지 처리 (String)
                try {
                    JSONObject jsonMessage = new JSONObject(text);
                    int messageId = jsonMessage.getInt("message");

                    if (messageId != 0) {
                        String accountId = jsonMessage.getString("account_id");
                        String content = jsonMessage.getString("content");

                        runOnUiThread(() -> {
                            RecyclerData data = new RecyclerData(messageId, accountId, content);
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
                            recyclerAdapter.addItem(data);

                            Log.d("TEST", accountId);
                            Log.d("TEST", content);
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                webSocket.close(1000, "destroy Activity");
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                // 소켓 연결이 완전히 종료되었을 때 재연결 로직 작성
//                new Handler().postDelayed(() -> {
//                    getUserInfo();
//                }, 5000);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                runOnUiThread(() -> {
                    Log.e("TEST", Objects.requireNonNull(t.getMessage()));
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
}