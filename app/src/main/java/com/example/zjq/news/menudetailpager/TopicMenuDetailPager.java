package com.example.zjq.news.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zjq.news.base.MenuDetailBasePager;

/**
 * 专题详情页面
 */
public class TopicMenuDetailPager extends MenuDetailBasePager {


    private TextView textView;

    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();


        textView.setText("专题详情页面的内容");
    }
}
