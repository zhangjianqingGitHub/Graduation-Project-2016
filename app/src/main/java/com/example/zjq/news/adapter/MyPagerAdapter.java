package com.example.zjq.news.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {


    private List<ImageView> list;

    public MyPagerAdapter(List<ImageView> imageViews) {
        this.list = imageViews;
    }

    //实现一个viewpager 必须重写这四个方法


    /**
     * 返回数据的总个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }


    /**
     * @param view 当前创建的视图
     * @param o
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * getview
     *
     * @param container viewpager
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = list.get( position );

        //添加到容器中

        container.addView( imageView );

        return imageView;


//        return super.instantiateItem( container, position );
    }

    /**
     * 销毁，进来会默认创建两个，最多两个
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem( container, position, object );  //注意去掉这个
        container.removeView( (View) object );
    }
}

