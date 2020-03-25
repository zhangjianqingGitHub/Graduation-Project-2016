package com.example.zjq.news.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.fragment.ContentFragment;
import com.example.zjq.news.fragment.LeftMenuFragment;
import com.example.zjq.news.utils.DensityUtil;
import com.example.zjq.news.utils.SharepUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String LEFT_MENU_TAG = "left_menu_tag";
    public static final String MAIN_CONTENT_TAG = "main_content_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置侧滑菜单
        initSlidingMenu();

        //初始化Fragment
        initFragment();
    }



    private void initSlidingMenu() {
        //设置主页面
        setContentView(R.layout.activity_main);

        //设置左侧菜单
        setBehindContentView(R.layout.activity_left_menu);

        //设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
//        slidingMenu.setSecondaryMenu( R.layout.activity_left_menu );

        //设置模式
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动模式,(全屏)
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));

        //设置淡入淡出
        slidingMenu.setFadeEnabled(true);
    }

    private void initFragment() {

        //得到FragmentManger

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //替换
        ft.replace(R.id.fl_main_content, new ContentFragment(), MAIN_CONTENT_TAG);//主页
        ft.replace(R.id.fl_lefu_menu, new LeftMenuFragment(), LEFT_MENU_TAG);//左侧菜单

        //提交
        ft.commit();


    }

    public Fragment getLeftMenuFragment() {
        return getSupportFragmentManager().findFragmentByTag(LEFT_MENU_TAG);
    }

    //得到正文
    public Fragment getContentFragment() {

        return getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);

    }


}
