package com.example.sgram.data.response;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("AccessToken")
    private String accessToken;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken(String accessToken) {
        return accessToken;
    }
}
