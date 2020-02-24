package com.example.zjq.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class HorizontalScrollViewPager extends ViewPager {

    //判断滑动方向，在x轴和y轴的滑动距离的比较

    //起始值
    private float startX;
    private float startY;

    public HorizontalScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public HorizontalScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //请求父层视图不拦截当前控件的事件
                getParent().requestDisallowInterceptTouchEvent(true);

                //记录起始值
                startX = ev.getX();
                startY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                float endX = ev.getX();
                float endY = ev.getY();

                //计算
                float distanceX = endX - startX;
                float distanceY = endY - startY;

                //判断
                if (Math.abs(distanceX) > Math.abs(distanceY)) {

                    //水平方向滑动

                    if (getCurrentItem() == 0 && distanceX > 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (getCurrentItem() == (getAdapter().getCount() - 1) && distanceX < 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);

                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);

                    }

                } else {
                    //垂直方向滑动

                    //请求父层视图不拦截当前控件的事件
                    getParent().requestDisallowInterceptTouchEvent(false);

                }

                break;
            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(ev);


    }
}
