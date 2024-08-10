package com.example.sgram.data.local;


import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private final SharedPreferences sharedPreferences;

    public TokenInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        String accessToken = sharedPreferences.getString("accessToken", "대체");

        return null;
    }
}
