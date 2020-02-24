package com.example.zjq.news.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.fragment.bean.LeftMenuBean;
import com.example.zjq.news.menudetailpager.InteracMenuDetailPager;
import com.example.zjq.news.menudetailpager.NewsMenuDetailPager;
import com.example.zjq.news.menudetailpager.PhotosMenuDetailPager;
import com.example.zjq.news.menudetailpager.TopicMenuDetailPager;
import com.example.zjq.news.utils.CacheUtils;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.ToastUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    private List<NewsCenterPagerBean.DataBean> list;
    private String url;

    //详情页面的集合
    private List<MenuDetailBasePager> detailBasePagers;

    private List<LeftMenuBean.DataBean> list_left;
    private int position_left;

    public NewsCenterPager(Context context) {
        super(context);
    }


    @Override
    public void initData() {
        super.initData();

        ib_menu.setVisibility(View.VISIBLE);

        if (CacheUtils.isConnect(context)) {

            url = Constants.NewsList;
            //有网
            getDataFromNet(url);

        } else {

            //解析缓存数据
            String result = CacheUtils.getString(context, url);

            if (!TextUtils.isEmpty(result)) {

                ToastUtil.show_center(context, "没有联网哦！先看看缓存的数据吧~");

                processData(result);
            }


        }


    }

    private void getDataFromNet(final String url) {


        RequestParams params = new RequestParams(url);
        params.addBodyParameter("typeId", list_left.get(position_left).getTypeId());
        params.addBodyParameter("page", 1);

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("zjq", result);

                //解析数据
                processData(result);

                CacheUtils.setString(context, url, result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("zjq-onError-1", ex.getMessage() + "--" + ex.getLocalizedMessage());


            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("zjq", cex.getMessage());


            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    //解析json数据
    private void processData(String result) {
        list = new ArrayList<>();

        NewsCenterPagerBean bean = new Gson().fromJson(result, NewsCenterPagerBean.class);

        int code = bean.getCode();

        if (code == 1) {
            list.addAll(bean.getData());
        }


//        MainActivity mainActivity = (MainActivity) context;
//        LeftMenuFragment leftMenuFragment = (LeftMenuFragment) mainActivity.getLeftMenuFragment();

        //添加详情页面
        detailBasePagers = new ArrayList<>();
        detailBasePagers.add(new NewsMenuDetailPager(context,list));
        detailBasePagers.add(new TopicMenuDetailPager(context));
        detailBasePagers.add(new PhotosMenuDetailPager(context));
        detailBasePagers.add(new InteracMenuDetailPager(context));


    }

    //根据位置切换详情页面
    public void swichPager(int position, List<LeftMenuBean.DataBean> list) {

        this.list_left = list;
        this.position_left = position;


        //1 标题
        tv_title.setText(list.get(position).getTypeName());

        //移除之前内容
        fl_content.removeAllViews();

        //添加新内容
        MenuDetailBasePager detailBasePager = detailBasePagers.get(position);
        View rootView = detailBasePager.rootview;
        detailBasePager.initData();
        fl_content.addView(rootView);


    }

}
