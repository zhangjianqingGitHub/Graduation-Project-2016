package com.example.zjq.news.fragment;


import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.adapter.ContentFramentAdapter;
import com.example.zjq.news.base.BaseFragment;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.pager.HomePager;
import com.example.zjq.news.pager.NewsCenterPager;
import com.example.zjq.news.pager.MePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    //装五个页面的集合
    private ArrayList<BasePager> pagers;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.content_fragment, null);

//        x.view().inject(Activity activity); //在Activity中使用
        //(handler ,view) //将这个fragment与Activity用handler关联起来
        x.view().inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //初始化页面，并且放入集合中
        pagers = new ArrayList<>();
        pagers.add(new NewsCenterPager(mContext));
        pagers.add(new HomePager(mContext));
        pagers.add(new MePager(mContext));
        //默认选中首页
        rg_main.check(R.id.rb_newscenter);

        //设置viewPager适配器
        viewPager.setAdapter(new ContentFramentAdapter(mContext, pagers));
        //第一次进来初始化主页面
        pagers.get(0).initData();

        //一进来第一个页面不可以滑动
        isEnableSlidingMenu(false);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * 当某个页面被选中
             * @param
             */
            @Override
            public void onPageSelected(int position) {

                pagers.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_home:
                        viewPager.setCurrentItem(1, false);

                        isEnableSlidingMenu(true);

                        break;
                    case R.id.rb_newscenter:


                        viewPager.setCurrentItem(0, false);//加上false 没有动画

                        isEnableSlidingMenu(false);

                        break;

                    case R.id.rb_setting:
                        viewPager.setCurrentItem(2, false);

                        isEnableSlidingMenu(false);

                        break;
                }
            }
        });

    }

    private void isEnableSlidingMenu(boolean b) {

        MainActivity mainActivity = (MainActivity) mContext;

        int mode;

        if (b) {
            mode = SlidingMenu.TOUCHMODE_FULLSCREEN;
        } else {
            mode = SlidingMenu.TOUCHMODE_NONE;

        }

        mainActivity.getSlidingMenu().setTouchModeAbove(mode);
    }

    //得到新闻中心
    public NewsCenterPager getNewsCenterPager() {

        return (NewsCenterPager) pagers.get(0);
    }

}
