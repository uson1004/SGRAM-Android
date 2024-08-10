    package com.example.sgram.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.AuthApi;
import com.example.sgram.data.local.SharedPreferenceManager;
import com.example.sgram.data.request.JoinRequest;
import com.example.sgram.data.request.LoginRequest;
import com.example.sgram.data.response.user.LoginResponse;
import com.example.sgram.databinding.ActivityJoinBinding;
import com.example.sgram.databinding.ActivityLogInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ActivityLogInBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        Intent mainIntent = new Intent(this, JoinActivity.class);

        binding.logInButton.setOnClickListener(v -> {
            String id = binding.idTx.getText().toString();
            String pw = binding.pwdTx.getText().toString();

            LoginRequest loginRequest = new LoginRequest(id, pw);
            // api 생성
            AuthApi authApi = ApiProvider.getAuthApi();

            authApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    // 로그인 성공 시 메인화면으로 이동
                    startActivity(mainIntent);
                    String accessToken = response.body().getAccess_token();

                    SharedPreferenceManager.getInstance(LogInActivity.this).edit().putString("accessToken", accessToken);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LogInActivity.this, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}