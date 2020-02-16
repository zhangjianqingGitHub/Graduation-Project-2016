package com.example.zjq.news.base;

import android.content.Context;
import android.view.View;

/**
 * 详情页面得基类
 */
public abstract class MenuDetailBasePager {

    //上下文
    public final Context context;

    //代表各个详情页面的视图
    public View rootview;

    public MenuDetailBasePager(Context context) {
        this.context = context;

        rootview = initView();
    }

    /**
     * 抽象方法
     * @return
     */
    public abstract View initView();


    public void initData(){

    }


}
