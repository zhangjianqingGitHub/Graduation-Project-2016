package com.example.zjq.news.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.adapter.ContentFramentAdapter;
import com.example.zjq.news.base.BaseFragment;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.pager.HappyPager;
import com.example.zjq.news.pager.NewsCenterPager;
import com.example.zjq.news.pager.MePager;
import com.example.zjq.news.pager.VideoPager;
import com.example.zjq.news.utils.SharepUtils;
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
        pagers.add(new VideoPager(mContext));
        pagers.add(new HappyPager(mContext));
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

                VideoPager videoPager= (VideoPager) pagers.get(1);

                if (videoPager.adapter!=null){
                    if (position==1){
                        //视频界面，播放视频

                        videoPager.adapter.start();
                    }else {
                        //其它页面，暂定播放视频
                        videoPager.adapter.pause();
                    }
                }


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

                    case R.id.rb_newscenter:


                        viewPager.setCurrentItem(0, false);//加上false 没有动画

                        isEnableSlidingMenu(true);

                        break;

                    case R.id.rb_vedio:


                        viewPager.setCurrentItem(1, false);//加上false 没有动画

                        isEnableSlidingMenu(false);

                        break;

                    case R.id.rb_home:
                        viewPager.setCurrentItem(2, false);

                        isEnableSlidingMenu(false);

                        break;

                    case R.id.rb_setting:
                        viewPager.setCurrentItem(3, false);

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

    //得到新闻中心
    public MePager getMePager() {

        return (MePager) pagers.get(3);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            //登录完成后回来
            getMePager().showUserInfo();

        } else if (requestCode == 2 && resultCode == 2) {
            //设置中点击退出登录
            getMePager().ll_login.setVisibility(View.VISIBLE);
            getMePager().ll_user.setVisibility(View.GONE);
            getMePager().tv_editdata.setVisibility(View.INVISIBLE);

            Glide.with(mContext).load(R.drawable.iv_me_header).into(getMePager().iv_me_photo);

            //设置退出登录状态

            SharepUtils.deleLogin(mContext);

        } else if (requestCode == 3 && resultCode == 3) {
            //编辑完信息回来的
            getMePager().showUserInfo();
        }
    }

}
