package com.example.zjq.news.activity;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zjq.news.R;
import com.example.zjq.news.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;

    private List<ImageView> imageViews;
    private ImageView iv_red_point;
    private int leftMax;//两红点的间距

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_guide );

        intView();
    }

    private void intView() {
        viewPager = findViewById( R.id.viewpager );
        btn_start_main = findViewById( R.id.btn_start_main );
        ll_point_group = findViewById( R.id.ll_point_group );
//        iv_red_point = findViewById( R.id.iv_red_point );   

        iv_red_point = findViewById( R.id.iv_red_point );//fbc
        int[] ids = new int[]{

                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        imageViews = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView( this );

            imageView.setBackgroundResource( ids[i] );

            imageViews.add( imageView );

            //创建指示器，添加到线性布局中
            ImageView point = new ImageView( this );
            point.setBackgroundResource( R.drawable.point_normal );

            // 10 10 单位是像素，到时候需要适配
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 30, 30 );

            if (i != 0) {
                //不包括第0个，距离左边有十个像素

                params.leftMargin = 10;
            }

            point.setLayoutParams( params );

            ll_point_group.addView( point );
        }

        //设置viewpager的适配器
        MyPagerAdapter pagerAdapter = new MyPagerAdapter( imageViews );
        viewPager.setAdapter( pagerAdapter );

        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener( new MyOnGlobalLayoutListener() );

        //得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener( new MyOnPageChangeListener() );
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        /**
         * 当页面滑动了
         *
         * @param i  当前滑动页面的位置
         * @param v  页面滑动的百分比
         * @param i1 滑动的像素
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {

            //两点间移动的距离=屏幕滑动百分比*间距
//            int leftmargin = (int) (v * leftMax);

            //两点间滑动距离=原来的起始位置+两点间移动的距离
            int leftmargin = (int) ((i * leftMax) + v * leftMax);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams( params );

        }

        /**
         * 当页面被选中的时候，回调这个方法
         *
         * @param i
         */
        @Override
        public void onPageSelected(int i) {

        }

        /**
         * 当页面状态发生状态时候，滑动-放开
         *
         * @param i
         */
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        /**
         * Callback method to be invoked when the global layout state or the visibility of views
         * within the view tree changes
         */
        @Override
        public void onGlobalLayout() {

            //执行不止一次，只需要执行一次，后移除这个监听
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener( this );
            }

            leftMax = ll_point_group.getChildAt( 1 ).getLeft() - ll_point_group.getChildAt( 0 ).getLeft();
        }
    }
}
