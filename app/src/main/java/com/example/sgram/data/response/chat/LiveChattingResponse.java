package com.example.sgram.data.response.chat;

import com.google.gson.annotations.SerializedName;

public class LiveChattingResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("account_id")
    private String account_id;

    @SerializedName("contents")
    private String contents;

    @SerializedName("AccessToken")
    private String accessToken;

    public LiveChattingResponse(String message, String account_id, String contents) {
        this.message = message;
        this.account_id = account_id;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
