package com.example.zjq.news.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {


    public static void show_center(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.show();
    }
}
