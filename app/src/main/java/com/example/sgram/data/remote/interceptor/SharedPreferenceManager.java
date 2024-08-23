package com.example.sgram.data.remote.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("sgram", Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }
}
