package com.example.zjq.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zjq.news.SplashActivity;

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences( "news", Context.MODE_PRIVATE );
        return sp.getBoolean( key, false );

    }
}
