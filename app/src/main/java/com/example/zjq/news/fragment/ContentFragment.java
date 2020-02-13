package com.example.zjq.news.fragment;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.zjq.news.R;
import com.example.zjq.news.adapter.ContentFramentAdapter;
import com.example.zjq.news.base.BaseFragment;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.pager.GovaffairPager;
import com.example.zjq.news.pager.HomePager;
import com.example.zjq.news.pager.NewsCenterPager;
import com.example.zjq.news.pager.SettingPager;
import com.example.zjq.news.pager.SmartServicePager;
import com.example.zjq.news.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpager)
    private NoScrollViewPager viewPager;
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

        //初始化五个页面，并且放入集合中
        pagers = new ArrayList<>();
        pagers.add(new HomePager(mContext));
        pagers.add(new NewsCenterPager(mContext));
        pagers.add(new SmartServicePager(mContext));
        pagers.add(new GovaffairPager(mContext));
        pagers.add(new SettingPager(mContext));
        //默认选中首页
        rg_main.check(R.id.rb_home);

        //设置viewPager适配器
        viewPager.setAdapter(new ContentFramentAdapter(mContext, pagers));


        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_home:

                        viewPager.setCurrentItem(0,false);//加上false 没有动画

                        break;
                    case R.id.rb_newscenter:
                        viewPager.setCurrentItem(1,false);

                        break;
                    case R.id.rb_smartservice:
                        viewPager.setCurrentItem(2,false);

                        break;
                    case R.id.rb_govaffair:
                        viewPager.setCurrentItem(3,false);

                        break;
                    case R.id.rb_setting:
                        viewPager.setCurrentItem(4,false);

                        break;
                }
            }
        });

    }
}
