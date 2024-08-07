package com.example.sgram.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.AuthApi;
import com.example.sgram.data.request.JoinRequest;
import com.example.sgram.databinding.ActivityJoinBinding;
import com.example.sgram.databinding.ActivityLogInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_join);

        ActivityJoinBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        Intent loginIntent = new Intent(this, LogInActivity.class);

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.idTx.getText().toString();
                String pw = binding.pwdTx.getText().toString();
                String phone = binding.phoneTx.getText().toString();

                JoinRequest joinRequest = new JoinRequest(id, pw, phone);
                AuthApi authApi = ApiProvider.getAuthApi();

                authApi.Join(joinRequest).enqueue(new Callback<JoinRequest>() {
                    @Override
                    public void onResponse(Call<JoinRequest> call, Response<JoinRequest> response) {
                        binding.logInText.setOnClickListener(v -> startActivity(loginIntent));
                    }

                    @Override
                    public void onFailure(Call<JoinRequest> call, Throwable t) {

                    }
                });
            }
        });
    }
}