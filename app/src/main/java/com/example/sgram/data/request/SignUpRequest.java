package com.example.sgram.data.request;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("account_id")
    private String account_id;

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone_number;

    public SignUpRequest(String account_id, String password, String phone) {
        this.account_id = account_id;
        this.password = password;
        this.phone_number = phone;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone_number;
    }

    public void setPhone(String phone) {
        this.phone_number = phone;
    }
}
