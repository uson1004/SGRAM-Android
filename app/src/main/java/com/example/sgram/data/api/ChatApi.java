package com.example.sgram.data.api;

import com.example.sgram.data.response.chat.LiveChattingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ChatApi {

    // 토큰과 함께 response 받기
    @GET("/live-chatting")
    Call<LiveChattingResponse> getAccessToken(@Header("") LiveChattingResponse liveChattingResponse);


}
