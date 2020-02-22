package com.example.zjq.news.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.menudetailpager.tabdetailpager.TabDetailPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 新闻详情页面
 */
public class NewsMenuDetailPager extends MenuDetailBasePager {


    @ViewInject(R.id.vp_viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.tabPageIndicator)
    private TabPageIndicator tabPageIndicator;

    private String[] strings;

    @ViewInject(R.id.tab_next)
    private ImageButton ib_tab_next;

    //页签页面的集合
    private ArrayList<TabDetailPager> tabDetailPagers;

    public NewsMenuDetailPager(Context context, String[] strings) {
        super(context);
        this.strings = strings;


    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.newsmenu_detail_pager, null);

        x.view().inject(this, view);

        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        tabDetailPagers = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {

            tabDetailPagers.add(new TabDetailPager(context, strings[i]));
        }

        viewPager.setAdapter(new MynewMenuDetailPagerAdapter());

        //viewpager和tabpageIndicator关联
        tabPageIndicator.setViewPager(viewPager);

        //以后监听页面的变化，TabpageIndicator监听页面的变化
        tabPageIndicator.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                //SlidingMenu可以全屏滑动
                isEnableSlidingMenu(true);
            } else {
                isEnableSlidingMenu(false);

            }


        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private void isEnableSlidingMenu(boolean b) {

        MainActivity mainActivity = (MainActivity) context;

        int mode;

        if (b) {
            mode = SlidingMenu.TOUCHMODE_FULLSCREEN;
        } else {
            mode = SlidingMenu.TOUCHMODE_NONE;

        }

        mainActivity.getSlidingMenu().setTouchModeAbove(mode);
    }

    class MynewMenuDetailPagerAdapter extends PagerAdapter {


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            TabDetailPager tabDetailPager = tabDetailPagers.get(position);

            View rootView = tabDetailPager.rootview;

            tabDetailPager.initData();

            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }

        @Override
        public int getCount() {


            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
