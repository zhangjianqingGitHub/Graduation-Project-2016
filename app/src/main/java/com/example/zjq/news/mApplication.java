package com.example.zjq.news;

import android.app.Application;

import org.xutils.x;

public class mApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
