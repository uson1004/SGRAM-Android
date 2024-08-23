package com.example.sgram.data.remote.api;

import com.example.sgram.data.request.SignUpRequest;
import com.example.sgram.data.request.LoginRequest;
import com.example.sgram.data.response.user.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/users/join")
    Call<Void> Join(
            @Body SignUpRequest signInRequest
    );

    @POST("/users/login")
    Call<LoginResponse> login(
        @Body LoginRequest loginRequest
    );
}
