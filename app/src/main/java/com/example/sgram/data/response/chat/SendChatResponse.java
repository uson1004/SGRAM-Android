package com.example.sgram.data.response.chat;

import com.google.gson.annotations.SerializedName;

public class SendChatResponse {
    @SerializedName("contents")
    private String contents;

    public SendChatResponse(String contents) {
        this.contents = contents;
    }

    public String getContents(String contents) {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
