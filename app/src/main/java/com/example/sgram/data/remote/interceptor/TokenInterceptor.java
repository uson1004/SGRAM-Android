package com.example.sgram.data.remote.interceptor;


import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private final SharedPreferences sharedPreferences;

    public TokenInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String accessToken = sharedPreferences.getString("access_token", "");
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        return chain.proceed(request);
    }
}
