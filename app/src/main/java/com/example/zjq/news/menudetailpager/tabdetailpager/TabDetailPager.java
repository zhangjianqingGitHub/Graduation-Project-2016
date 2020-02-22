package com.example.zjq.news.menudetailpager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zjq.news.base.MenuDetailBasePager;

public class TabDetailPager extends MenuDetailBasePager {

    private TextView textView;
    private String data;


    public TabDetailPager(Context context) {
        super(context);
    }

    public TabDetailPager(Context context, String string) {
        super(context);
        this.data = string;
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

        textView.setText(data);
    }
}
