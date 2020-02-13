package com.example.zjq.news.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.zjq.news.base.BasePager;

import java.util.ArrayList;

public class ContentFramentAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<BasePager> pagers;

    public ContentFramentAdapter(Activity mContext, ArrayList<BasePager> pagers) {

        this.context = mContext;
        this.pagers = pagers;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        BasePager basePager = pagers.get(position);//各个页面的实例

        View rootView = basePager.rootView;//各个子页面

//        basePager.initData();

        container.addView(rootView);

        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }


}
