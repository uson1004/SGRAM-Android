package com.example.sgram.data.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("account_id")
    private String account_id;

    @SerializedName("password")
    private String password;

    public LoginRequest(String account_id, String password) {
        this.account_id = account_id;
        this.password = password;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }
}
