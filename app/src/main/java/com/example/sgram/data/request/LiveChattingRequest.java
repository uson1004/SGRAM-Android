package com.example.sgram.data.request;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.HEAD;
import retrofit2.http.Header;

public class LiveChattingRequest {
    @SerializedName("AccessToken")
    private String AccessToken;

    public LiveChattingRequest(String accessToken) {
        AccessToken = accessToken;
    }

    public String getAccessToken() {
        return AccessToken;
    }
}
