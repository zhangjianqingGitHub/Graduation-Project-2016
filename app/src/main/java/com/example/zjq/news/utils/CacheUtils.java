package com.example.zjq.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CacheUtils {


    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isConnect(Context context) {
        boolean _isConnect = false;
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            _isConnect = conManager.getActiveNetworkInfo().isAvailable();
        }
        return _isConnect;
    }


    public static boolean getBoolean(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("news", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);

    }

    public static void setBoolean(Context context, String key, boolean b) {

        SharedPreferences.Editor editor = context.getSharedPreferences("news", Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, b);
        editor.apply();

    }


    public static String getString(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("news", Context.MODE_PRIVATE);
        return sp.getString(key, "");

    }

    public static void setString(Context context, String key, String b) {

        SharedPreferences.Editor editor = context.getSharedPreferences("news", Context.MODE_PRIVATE).edit();
        editor.putString(key, b);
        editor.apply();

    }

}
