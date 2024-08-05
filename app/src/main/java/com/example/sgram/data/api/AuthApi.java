package com.example.sgram.data.api;

import com.example.sgram.data.request.JoinRequest;
import com.example.sgram.data.request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/join")
    Call<JoinRequest> SignIn(
            @Body JoinRequest signInRequest
    );

    @POST("/login")
    Call<LoginRequest> SignUp(
        @Body LoginRequest loginRequest
    );
}
