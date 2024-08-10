package com.example.sgram.data.api;

import android.content.Context;

import com.example.sgram.data.local.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static Retrofit retrofit;
    private static final String BASE_URL = "";

    private static Context context;

    public ApiProvider(Context context) {
        this.context = context;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(context)).build();

        return okHttpClient;
    }

    public static AuthApi getAuthApi() {
        return getRetrofit().create(AuthApi.class);
    }

    public static ChatApi getChatApi() {
        return getRetrofit().create(ChatApi.class);
    }
}



