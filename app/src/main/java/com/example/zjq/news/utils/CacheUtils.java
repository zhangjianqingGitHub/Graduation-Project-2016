package com.example.zjq.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zjq.news.SplashActivity;
import com.example.zjq.news.activity.GuideActivity;

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences( "news", Context.MODE_PRIVATE );
        return sp.getBoolean( key, false );

    }

    public static void setBoolean(Context context, String key, boolean b) {

        SharedPreferences.Editor editor=context.getSharedPreferences( "news" ,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, b );
        editor.apply();

    }
}
