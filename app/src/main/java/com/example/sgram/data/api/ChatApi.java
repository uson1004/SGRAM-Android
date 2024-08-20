package com.example.sgram.data.api;

import com.example.sgram.data.response.chat.LiveChattingResponse;
import com.example.sgram.data.response.chat.SendChatResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ChatApi {

    // 토큰과 함께 response 받기
    @GET("/chat/live-chatting")
    Call<LiveChattingResponse> getLiveChatting(@Header("Authorization") LiveChattingResponse liveChattingResponse);

    @POST("/chat/chatting")
    Call<SendChatResponse> sendChat(@Header("Authorization") SendChatResponse sendChatResponse);
}
